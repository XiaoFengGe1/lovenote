package com.app.dao;
// Generated 2016-3-21 12:15:08 by Hibernate Tools 4.3.1.Final


import javax.annotation.Resource;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.model.Relation;


/**
 * Home object for domain model class Relation.
 * @see com.app.dao.Relation
 * @author Hibernate Tools
 */
@Repository
public class RelationHome {

	//private static final Log log = LogFactory.getLog(RelationHome.class);

	/*private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext().lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}
*/
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	@Transactional
	public void save(Relation relation) {
		sessionFactory.getCurrentSession().save(relation);
	}
	
	@Transactional
	public void delete(Integer id) {
		sessionFactory.getCurrentSession().delete(new Relation(id));
	}
	
	@Transactional
	public void update(Relation relation) {
		sessionFactory.getCurrentSession().update(relation);
	}
	
	public Relation findById(Integer id) {
		return (Relation) sessionFactory.getCurrentSession().get(Relation.class, id);
	}
	
	/*
	public void persist(Relation transientInstance) {
		log.debug("persisting Relation instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Relation instance) {
		log.debug("attaching dirty Relation instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Relation instance) {
		log.debug("attaching clean Relation instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Relation persistentInstance) {
		log.debug("deleting Relation instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Relation merge(Relation detachedInstance) {
		log.debug("merging Relation instance");
		try {
			Relation result = (Relation) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Relation findById(java.lang.Integer id) {
		log.debug("getting Relation instance with id: " + id);
		try {
			Relation instance = (Relation) sessionFactory.getCurrentSession().get("com.app.dao.Relation", id);
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

	public List<Relation> findByExample(Relation instance) {
		log.debug("finding Relation instance by example");
		try {
			List<Relation> results = (List<Relation>) sessionFactory.getCurrentSession()
					.createCriteria("com.app.dao.Relation").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}*/
}
