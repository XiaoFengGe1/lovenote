package com.app.dao;
// Generated 2016-3-21 12:15:08 by Hibernate Tools 4.3.1.Final


import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.model.Review;


/**
 * Home object for domain model class Review.
 * @see com.app.dao.Review
 * @author Hibernate Tools
 */
@Repository
public class ReviewHome {

	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Transactional
	public void save(Review review) {
		sessionFactory.getCurrentSession().save(review);
	}
	
	@Transactional
	public void delete(Integer id) {
		sessionFactory.getCurrentSession().delete(new Review(id));
	}
	
	@Transactional
	public void update(Review review) {
		sessionFactory.getCurrentSession().update(review);
	}
	
	public Review findById(Integer id) {
		return (Review) sessionFactory.getCurrentSession().get(Review.class, id);
	}
	
	public  HashMap<String, Object> findReview(int noteid,int pageNum){
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<Review> list = null;
		try {
			String sql1 = "from review where noteid = '"+noteid+"'"; 
			int count = jdbcTemplate.queryForInt("select count(id) "+sql1+"");
			list = jdbcTemplate.query("select * "+sql1+" limit "+((pageNum-1)*10)+",10", new BeanPropertyRowMapper(Review.class));
			map.put("total", count);
			map.put("rows", list);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/*public void persist(Review transientInstance) {
		log.debug("persisting Review instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Review instance) {
		log.debug("attaching dirty Review instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Review instance) {
		log.debug("attaching clean Review instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Review persistentInstance) {
		log.debug("deleting Review instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Review merge(Review detachedInstance) {
		log.debug("merging Review instance");
		try {
			Review result = (Review) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Review findById(java.lang.Integer id) {
		log.debug("getting Review instance with id: " + id);
		try {
			Review instance = (Review) sessionFactory.getCurrentSession().get("com.app.dao.Review", id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Review> findByExample(Review instance) {
		log.debug("finding Review instance by example");
		try {
			List<Review> results = (List<Review>) sessionFactory.getCurrentSession()
					.createCriteria("com.app.dao.Review").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}*/
}
