package com.app.dao;

// Generated 2016-3-21 12:15:08 by Hibernate Tools 4.3.1.Final

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.model.BlackUrl;

/**
 * Home object for domain model class Type.
 * 
 * @see com.app.dao.Type
 * @author Hibernate Tools
 */
@Repository
public class BlackUrlHome {
	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	@Transactional
	public void save(BlackUrl blackUrl) {
		sessionFactory.getCurrentSession().save(blackUrl);
	}

	@Transactional
	public void delete(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(findById(id));
	}

	@Transactional
	public void update(BlackUrl blackUrl) {
		sessionFactory.getCurrentSession().update(blackUrl);
	}

	@Transactional
	public BlackUrl findById(Integer id) {
		return (BlackUrl) sessionFactory.getCurrentSession().get(
				BlackUrl.class, id);
	}

	@Transactional
	public List<BlackUrl> findByUid(int uid) {
		return sessionFactory.getCurrentSession()
				.createQuery("from BlackUrl where uid=" + uid).list();
	}
}
