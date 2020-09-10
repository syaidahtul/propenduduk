package app.core.usermgmt.service.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hibernate.type.StandardBasicTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import app.core.domain.dto.PersonDTO;
import app.core.domain.dto.UserDTO;
import app.core.domain.model.IdentityCardType;
import app.core.domain.model.NationalityStatus;
import app.core.domain.model.Person;
import app.core.usermgmt.service.UserRegistrationService;

@Service
@Transactional
public class UserRegistrationServiceImpl implements UserRegistrationService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserRegistrationServiceImpl.class);

	@Autowired
	private SessionFactory sessionFactory;
	
	private Person person = new Person();

	@Override
	public List<IdentityCardType> findAllIdentityType() {
		Session session = sessionFactory.getCurrentSession();
		Query<IdentityCardType> query = session.createQuery("SELECT m FROM IdentityCardType m ORDER BY m.sortOrder");
		List<IdentityCardType> idTypes = (List<IdentityCardType>) query.list();
		LOGGER.info("Found Records: " + idTypes.size());
		return idTypes;
	}

	@Override
	public List<NationalityStatus> findAllNationalityStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void registerPerson(PersonDTO personDTO, UserDTO userDTO) {
		LOGGER.info("=== Start Registration ===");
		// only on successfully create the person we create user
		if (createPerson(personDTO)) {
			
		}
	}

	@SuppressWarnings("unchecked")
	protected boolean createPerson(PersonDTO dto) {
		person = convertEntity(dto);
		Session session = sessionFactory.getCurrentSession();
		boolean isCreated = false;
		
		Query<Person> query = session.createNativeQuery(
				"INSERT INTO person (id , full_name, " + 
				"identity_card_type," + 
				"identity_no," + 
				"gender," + 
				"date_of_birth," + 
				"place_of_birth," + 
				"mobile_no," + 
				"email) " +
				" VALUES ((select nextval('penduduksabah.person_seq')), :name, :idType, :idNo, :gender, " +
					":dob, :pob, :phoneNo, :email)");

		query.setParameter("name", dto.getFullName(), StandardBasicTypes.STRING)
			.setParameter("idType", dto.getIdentityType(), StandardBasicTypes.STRING)
			.setParameter("idNo", dto.getIdentityNo(), StandardBasicTypes.STRING)
			.setParameter("gender", dto.getGender(), StandardBasicTypes.STRING)
			.setParameter("dob", dto.getDateOfBirth(), StandardBasicTypes.DATE)
			.setParameter("pob", dto.getPlaceOfBirth(), StandardBasicTypes.STRING)
			.setParameter("phoneNo", dto.getPhoneNo(), StandardBasicTypes.STRING)
			.setParameter("email", dto.getEmail(), StandardBasicTypes.STRING);

		query.executeUpdate();

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
