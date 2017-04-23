package com.app.dao;
// Generated 2016-3-21 12:15:08 by Hibernate Tools 4.3.1.Final


import javax.annotation.Resource;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.model.Zan;


/**
 * Home object for domain model class Zan.
 * @see com.app.dao.Zan
 * @author Hibernate Tools
 */
@Repository
public class ZanHome {

	//private static final Log log = LogFactory.getLog(ZanHome.class);

	/*private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext().lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}*/

	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	@Transactional
	public void save(Zan zan) {
		sessionFactory.getCurrentSession().save(zan);
	}
	
	@Transactional
	public void delete(Integer id) {
		sessionFactory.getCurrentSession().delete(new Zan(id));
	}
	@Transactional
	public void update(Zan zan) {
		sessionFactory.getCurrentSession().update(zan);
	}
	
	public Zan findById(Integer id) {
		return (Zan) sessionFactory.getCurrentSession().get(Zan.class, id);
	}
	
	public Boolean isExist (String name,int noteid){
		Zan zan = null;
		zan = (Zan)sessionFactory.getCurrentSession().createQuery("from Zan where uname='"+name+"'and noteid = '"+noteid+"'").uniqueResult();
		if(zan==null){
			return false;
		}else{
			return true;
		}
	}
	/*
	public void persist(Zan transientInstance) {
		log.debug("persisting Zan instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Zan instance) {
		log.debug("attaching dirty Zan instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Zan instance) {
		log.debug("attaching clean Zan instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Zan persistentInstance) {
		log.debug("deleting Zan instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Zan merge(Zan detachedInstance) {
		log.debug("merging Zan instance");
		try {
			Zan result = (Zan) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Zan findById(java.lang.Integer id) {
		log.debug("getting Zan instance with id: " + id);
		try {
			Zan instance = (Zan) sessionFactory.getCurrentSession().get("com.app.dao.Zan", id);
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

	public List<Zan> findByExample(Zan instance) {
		log.debug("finding Zan instance by example");
		try {
			List<Zan> results = (List<Zan>) sessionFactory.getCurrentSession().createCriteria("com.app.dao.Zan")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}*/
}
