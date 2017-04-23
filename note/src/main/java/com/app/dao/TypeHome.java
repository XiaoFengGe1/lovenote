package com.app.dao;
// Generated 2016-3-21 12:15:08 by Hibernate Tools 4.3.1.Final

import java.util.List;

import javax.annotation.Resource;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.model.Type;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Type.
 * @see com.app.dao.Type
 * @author Hibernate Tools
 */
@Repository
public class TypeHome {

	//private static final Log log = LogFactory.getLog(TypeHome.class);

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
	public void save(Type type) {
		System.out.println(type);
		sessionFactory.getCurrentSession().save(type);
	}
	
	@Transactional
	public void delete(Integer id) {
		sessionFactory.getCurrentSession().delete(new Type(id));
	}
	
	@Transactional
	public void update(Type type) {
		sessionFactory.getCurrentSession().update(type);
	}
	
	public Type findById(Integer id) {
		return (Type) sessionFactory.getCurrentSession().get(Type.class, id);
	}
	
	public List<Type> findByName(String name) {
		return  sessionFactory.getCurrentSession().createQuery("from Type where name='"+name+"' and isdelete=0").list();
	}
	
	public Boolean isHas(String classify,String name){
		Type type = null;
		type = (Type) sessionFactory.getCurrentSession().createQuery("from Type where name='"+name+"'and classify = '"+classify+"' and isdelete=0").uniqueResult();
		if(type==null){
			return false;
		}else{
			return true;
		}
	}
	
	/*public void persist(Type transientInstance) {
		log.debug("persisting Type instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Type instance) {
		log.debug("attaching dirty Type instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Type instance) {
		log.debug("attaching clean Type instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Type persistentInstance) {
		log.debug("deleting Type instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Type merge(Type detachedInstance) {
		log.debug("merging Type instance");
		try {
			Type result = (Type) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Type findById(java.lang.Integer id) {
		log.debug("getting Type instance with id: " + id);
		try {
			Type instance = (Type) sessionFactory.getCurrentSession().get("com.app.dao.Type", id);
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

	public List<Type> findByExample(Type instance) {
		log.debug("finding Type instance by example");
		try {
			List<Type> results = (List<Type>) sessionFactory.getCurrentSession().createCriteria("com.app.dao.Type")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}*/
}
