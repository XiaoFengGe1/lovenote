package com.app.dao;

// Generated 2016-3-21 12:15:08 by Hibernate Tools 4.3.1.Final

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.model.LikeUrl;
import com.app.utils.StringUtil;

/**
 * Home object for domain model class Type.
 * 
 * @see com.app.dao.Type
 * @author Hibernate Tools
 */
@Repository
public class LikeUrlHome {
	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Transactional
	public void save(LikeUrl likeUrl) {
		sessionFactory.getCurrentSession().save(likeUrl);
	}

	@Transactional
	public void delete(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(findById(id));
	}

	@Transactional
	public void update(LikeUrl likeUrl) {
		sessionFactory.getCurrentSession().update(likeUrl);
	}

	@Transactional
	public LikeUrl findById(Integer id) {
		return (LikeUrl) sessionFactory.getCurrentSession().get(LikeUrl.class,
				id);
	}

	@Transactional
	public List<LikeUrl> findByUid(int uid) {
		return sessionFactory.getCurrentSession()
				.createQuery("from LikeUrl where uid=" + uid).list();
	}

	@Transactional
	public List<LikeUrl> search(int uid, String str, String startTime,
			String endTime) {
		String query = "";
		if (StringUtil.isBlank(str)) {
			query = "from LikeUrl where uid=" + uid + " and date >= "
					+ startTime + " and date <= " + endTime;
		} else {
			query = "from LikeUrl where uid=" + uid + " and date >= "
					+ startTime + " and date <= " + endTime
					+ " and title like '%" + str + "%' or url like '%" + str
					+ "%'";
		}
		return sessionFactory.getCurrentSession().createQuery(query)
				.setMaxResults(50).list();
	}

}
