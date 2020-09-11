package app.core.domain.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.core.domain.model.Role;
import app.core.domain.service.DomainInitializationService;

@Service
@Transactional
public class DomainInitializationServiceImpl implements DomainInitializationService {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	@SuppressWarnings("unchecked")
	public void initAppRoles(String defaultRoles) {

		Session session = sessionFactory.getCurrentSession();
		String[] defaultRole = defaultRoles.split(",");
		List<String> dbRoles = new ArrayList<String>();
		Query query = session.createQuery("select o.description from " + Role.class.getName() + " o");
		dbRoles = query.getResultList();
		int sortOrder = dbRoles.size();
		for (String role : defaultRole) {
			if (!dbRoles.contains(role)) {
				sortOrder = sortOrder + 1;
				Role newRole = new Role();
				newRole.setName(role);
				newRole.setDescription(role);
				newRole.setSortOrder(Long.valueOf(sortOrder));
				session.save(newRole);
			}
		}
		
	}

}
