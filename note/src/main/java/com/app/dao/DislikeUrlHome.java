package com.app.dao;

// Generated 2016-3-21 12:15:08 by Hibernate Tools 4.3.1.Final

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.model.DislikeUrl;
import com.app.utils.StringUtil;

/**
 * Home object for domain model class Type.
 * 
 * @see com.app.dao.Type
 * @author Hibernate Tools
 */
@Repository
public class DislikeUrlHome {
	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	@Transactional
	public void save(DislikeUrl dislikeUrl) {
		sessionFactory.getCurrentSession().save(dislikeUrl);
	}

	@Transactional
	public void delete(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(findById(id));
	}

	@Transactional
	public void update(DislikeUrl dislikeUrl) {
		sessionFactory.getCurrentSession().update(dislikeUrl);
	}

	@Transactional
	public DislikeUrl findById(Integer id) {
		return (DislikeUrl) sessionFactory.getCurrentSession().get(
				DislikeUrl.class, id);
	}

	@Transactional
	public List<DislikeUrl> findByUid(int uid) {
		return sessionFactory.getCurrentSession()
				.createQuery("from DislikeUrl where uid=" + uid).list();
	}

	@Transactional
	public List<DislikeUrl> search(int uid, String str, String startTime,
			String endTime) {
		String query = "";
		if (StringUtil.isBlank(str)) {
			query = "from DislikeUrl where uid=" + uid + " and date >= "
					+ startTime + " and date <= " + endTime;
		} else {
			query = "from DislikeUrl where uid=" + uid + " and date >= "
					+ startTime + " and date <= " + endTime
					+ " and title like '%" + str + "%' or url like '%" + str
					+ "%'";
		}
		return sessionFactory.getCurrentSession().createQuery(query)
				.setMaxResults(50).list();
	}
}
