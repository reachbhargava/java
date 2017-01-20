package com.bhargava.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.bhargava.model.UserDetails;
/**
 * DAOImpl to interact with the entities. This file is using Criteria and Restriction (Hibernate specific and not JPA)
 * to fetch data from the table.
 * 
 * @author Bhargava
 *
 */
@Repository
public class UserDAOImpl implements UserDAO {

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void saveUserDetails(UserDetails user) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(user);
		// session.close();
	}

	@Override
	public boolean checkValidity(UserDetails user) {
		boolean isValid = true;
		Session session = this.sessionFactory.openSession();
		try {
			
			Criteria criteria = session.createCriteria(UserDetails.class);
			criteria.add(Restrictions.eq("userName", user.getUserName()));
			List<UserDetails> resultList = criteria.list();
			if (resultList.size() >= 1) {
				isValid = false;
			}
		} finally {
			session.flush();
			session.close();
		}

		return isValid;
	}

}
