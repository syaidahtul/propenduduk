package app.core.usermgmt.service.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.NonUniqueResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hibernate.type.StandardBasicTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.core.domain.dto.PersonDTO;
import app.core.domain.dto.UserDTO;
import app.core.domain.model.IdentityCardType;
import app.core.domain.model.NationalityStatus;
import app.core.domain.model.Person;
import app.core.domain.model.User;
import app.core.usermgmt.service.UserRegistrationService;
import app.core.utils.MD5Utils;

@Service
@Transactional
@SuppressWarnings("unchecked")
public class UserRegistrationServiceImpl implements UserRegistrationService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserRegistrationServiceImpl.class);

	@Autowired
	private SessionFactory sessionFactory;
	
	private Person person = new Person();

	@Override
	public List<IdentityCardType> findAllIdentityType() {
		Session session = sessionFactory.getCurrentSession();
		Query<IdentityCardType> query = session.createQuery("SELECT m FROM " + IdentityCardType.class.getName() + " m ORDER BY m.sortOrder");
		List<IdentityCardType> idTypes = (List<IdentityCardType>) query.list();
		LOGGER.info("Identity Card Type Records: " + idTypes.size());
		return idTypes;
	}

	@Override
	public List<NationalityStatus> findAllNationalityStatus() {
		Session session = sessionFactory.getCurrentSession();
		Query<NationalityStatus> query = session.createQuery("SELECT m FROM " + NationalityStatus.class.getName() + " m ORDER BY m.sortOrder");
		List<NationalityStatus> nationalityStatus = (List<NationalityStatus>) query.list();
		LOGGER.info("Nationality Status Records: " + nationalityStatus.size());
		return null;
	}

	@Override
	public void registerPerson(PersonDTO personDTO, UserDTO userDTO) {
		LOGGER.info("=== Start Registration ===");
		// only on successfully create the person we create user
		if (createPerson(personDTO)) { 
			User user = new User();
			user.setUsername(personDTO.getFullName());
			String salt = MD5Utils.getNewSaltValue();
			user.setSalt(salt);
			user.setPassword(MD5Utils.encrypt(salt, userDTO.getPassword()));
			user.setDateFormat("dd/MM/yyyy");
			user.setConnectedFlag(false);
			user.setActiveFlag(true);
			user.setFirstName(personDTO.getFullName());
			user.setLastName(null);
			user.setForceChangePwdFlag(false);
			user.setPwdExpiryDays(732L);
			user.setInvalidLoginCount(0L);
			user.setMaxInvalidLoginCount(0L);
			user.setSessionTimeoutMinutes(60L);
			user.setCreateUserId(0L);
			user.setCreateDate(new Date());
			createUserIfNotFound(user);
		}
	}
	
	@Transactional
	public Long createUserIfNotFound(User user) {
		Session session = sessionFactory.getCurrentSession();
		Query<User> q = session.createQuery("SELECT o FROM " + User.class.getName() + " o WHERE o.username = :username")
				.setParameter("username", user.getUsername());
		try {
			User dbUser = (User) q.uniqueResult();
			if (dbUser != null) {
				LOGGER.info("skip to create, user found with username [" + user.getUsername() + "]");
				return dbUser.getId();
			} else {
				return (Long) session.save(user);
			}
		} catch (NonUniqueResultException e) {
			LOGGER.error("Non-unique user found, username [" + user.getUsername() + "]", e);
		}
		return -1L;
	}

	protected boolean createPerson(PersonDTO dto) {
		Session session = sessionFactory.getCurrentSession();
		boolean isCreated = false;
		
		Query<Person> query = session.createNativeQuery(
				"INSERT INTO person (id , full_name, identity_card_type, identity_no, gender, date_of_birth, place_of_birth, mobile_no, email) " +
					"VALUES ((select nextval('penduduksabah.person_seq')), :name, :idType, :idNo, :gender, :dob, :pob, :phoneNo, :email)");

		query.setParameter("name", dto.getFullName(), StandardBasicTypes.STRING)
			.setParameter("idType", dto.getIdentityType(), StandardBasicTypes.STRING)
			.setParameter("idNo", dto.getIdentityNo(), StandardBasicTypes.STRING)
			.setParameter("gender", dto.getGender(), StandardBasicTypes.STRING)
			.setParameter("dob", dto.getDateOfBirth(), StandardBasicTypes.DATE)
			.setParameter("pob", dto.getPlaceOfBirth(), StandardBasicTypes.STRING)
			.setParameter("phoneNo", dto.getPhoneNo(), StandardBasicTypes.STRING)
			.setParameter("email", dto.getEmail(), StandardBasicTypes.STRING);

		try {
			query.executeUpdate();
			isCreated = true;
		} catch (Exception e) {
			LOGGER.error("ERROR execute query: ", e);
		}
		
		return isCreated;
	}

	protected Person convertEntity(PersonDTO dto) {
		person.setFullName(dto.getFullName());
		person.setIdentityType(new IdentityCardType(dto.getIdentityType()));
		person.setIdentityNo(dto.getIdentityNo());
		person.setEmail(dto.getEmail());
		person.setMobileNo(dto.getMobileNo());
		
		if (dto.getIdentityType().equalsIgnoreCase("NIRC")) {
			// get modulo for gender even == female
			int genderNo = Integer.valueOf(dto.getIdentityNo().substring(dto.getIdentityNo().length() - 1));
			if (((genderNo % 2) == 0)) {
				// person.setGender(new Gender("F"));
			};
			
			// get date of birth from ic no
			Date dob = convertDob(dto.getIdentityNo().substring(0, 5));
			
		}
		
		return person;
	}

	private Date convertDob(String dob) {
		// Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(sDate1); 
		return null;
	}
	
	
}
