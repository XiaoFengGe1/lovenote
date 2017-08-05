package com.app.dao;

// Generated 2016-3-21 12:15:08 by Hibernate Tools 4.3.1.Final

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.model.User;
import com.app.utils.VAR;

/**
 * Home object for domain model class User.
 * 
 * @see com.app.dao.User
 * @author Hibernate Tools
 */
@Repository
public class UserHome {

	// private static final Log log = LogFactory.getLog(UserHome.class);

	/*
	 * private final SessionFactory sessionFactory = getSessionFactory();
	 * 
	 * protected SessionFactory getSessionFactory() { try { return
	 * (SessionFactory) new InitialContext().lookup("SessionFactory"); } catch
	 * (Exception e) { log.error("Could not locate SessionFactory in JNDI", e);
	 * throw new
	 * IllegalStateException("Could not locate SessionFactory in JNDI"); } }
	 */

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	@Transactional
	public void save(User user) {
		sessionFactory.getCurrentSession().save(user);
	}

	@Transactional
	public void delete(Integer id) {
		sessionFactory.getCurrentSession().delete(new User(id));
	}

	@Transactional
	public void update(User user) {
		sessionFactory.getCurrentSession().update(user);
	}

	public User findById(Integer id) {
		return (User) sessionFactory.getCurrentSession().get(User.class, id);
	}

	public User findByName(String name) {
		// log.debug("getting User instance with name: " + name);
		try {
			User instance = (User) sessionFactory
					.getCurrentSession()
					.createQuery(
							"from User where name='" + name
									+ "' and isdelete=0").uniqueResult();
			if (instance == null) {
				// log.debug("get successful, no instance found");
			} else {
				// log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			// log.error("get failed", re);
			return null;
		}
	}

	public User findByPass(String pass) {
		// log.debug("getting User instance with name: " + name);
		String[] result2 = pass.split("@@@@@@");
		try {
			User instance = (User) sessionFactory
					.getCurrentSession()
					.createQuery(
							"from User where id='" + result2[0]
									+ "' and password='" + result2[1]
									+ "' and isdelete=0").uniqueResult();
			if (instance == null) {
				// log.debug("get successful, no instance found");
			} else {
				// log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			// log.error("get failed", re);
			return null;
		}
	}

	public User CheckByEmail(String email) {
		// log.debug("getting User instance with email: " + email);
		try {
			User instance = (User) sessionFactory
					.getCurrentSession()
					.createQuery(
							"from User where email='" + email
									+ "' and isdelete=0").uniqueResult();
			if (instance == null) {
				// log.debug("get successful, no instance found");
			} else {
				// log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			// log.error("get failed", re);
			throw re;
		}
	}

	public User CheckByName(String name) {
		// log.debug("getting User instance with name: " + name);
		try {
			User instance = (User) sessionFactory
					.getCurrentSession()
					.createQuery(
							"from User where name='" + name
									+ "' and isdelete=0").uniqueResult();
			if (instance == null) {
				// log.debug("get successful, no instance found");
			} else {
				// log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			// log.error("get failed", re);
			throw re;
		}
	}

	public User login(String account) {
		User user = null;
		try {
			if (account.matches(VAR.matchEmail)) {
				user = (User) sessionFactory
						.getCurrentSession()
						.createQuery(
								"from User where email='" + account
										+ "' and isdelete=0").uniqueResult();
			} else if (account.matches(VAR.matchTel)) {
				user = (User) sessionFactory
						.getCurrentSession()
						.createQuery(
								"from User where tel='" + account
										+ "' and isdelete=0").uniqueResult();
			} else {
				user = (User) sessionFactory
						.getCurrentSession()
						.createQuery(
								"from User where name='" + account
										+ "' and isdelete=0").uniqueResult();
			}
			return user;
		} catch (RuntimeException re) {
			re.printStackTrace();
		}
		return null;
	}

	/*
	 * @SuppressWarnings("unchecked") public PagerModel<Article> findAll(String
	 * searchValue, Integer start, Integer length) { String hqlCount =
	 * "select count(*) from Article where 1=1"; String hql =
	 * "from Article where 1=1"; String where = ""; if(searchValue!=null &&
	 * !searchValue.equals("")) where += " and title like '%"+searchValue+"%'";
	 * long count = (Long)
	 * sessionFactory.getCurrentSession().createQuery(hqlCount
	 * +where).uniqueResult(); List<Article> list =
	 * sessionFactory.getCurrentSession
	 * ().createQuery(hql+where+" order by id desc"
	 * ).setFirstResult(start).setMaxResults(length).list(); return new
	 * PagerModel<Article>(count, list); }
	 * 
	 * @SuppressWarnings("unchecked") public List<Article> findAll(String type,
	 * String tag, Integer start) { Integer length = 20; String hqlCount =
	 * "select count(*) from Article where 1=1"; String hql =
	 * "from Article where 1=1"; String where = ""; if(type!=null &&
	 * !type.equals("")) where += " and type = '"+type+"'"; if(tag!=null &&
	 * !tag.equals("")) where += " and tag = '"+tag+"'"; long count = (Long)
	 * sessionFactory
	 * .getCurrentSession().createQuery(hqlCount+where).uniqueResult();
	 * List<Article> list =
	 * sessionFactory.getCurrentSession().createQuery(hql+where
	 * +" order by id desc").setFirstResult(start).setMaxResults(length).list();
	 * return list; }
	 */

	/*
	 * 
	 * public void persist(User transientInstance) {
	 * log.debug("persisting User instance"); try {
	 * sessionFactory.getCurrentSession().persist(transientInstance);
	 * log.debug("persist successful"); } catch (RuntimeException re) {
	 * log.error("persist failed", re); throw re; } }
	 * 
	 * public void attachDirty(User instance) {
	 * log.debug("attaching dirty User instance"); try {
	 * sessionFactory.getCurrentSession().saveOrUpdate(instance);
	 * log.debug("attach successful"); } catch (RuntimeException re) {
	 * log.error("attach failed", re); throw re; } }
	 * 
	 * public void attachClean(User instance) {
	 * log.debug("attaching clean User instance"); try {
	 * sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
	 * log.debug("attach successful"); } catch (RuntimeException re) {
	 * log.error("attach failed", re); throw re; } }
	 * 
	 * public void delete(User persistentInstance) {
	 * log.debug("deleting User instance"); try {
	 * sessionFactory.getCurrentSession().delete(persistentInstance);
	 * log.debug("delete successful"); } catch (RuntimeException re) {
	 * log.error("delete failed", re); throw re; } }
	 * 
	 * public User merge(User detachedInstance) {
	 * log.debug("merging User instance"); try { User result = (User)
	 * sessionFactory.getCurrentSession().merge(detachedInstance);
	 * log.debug("merge successful"); return result; } catch (RuntimeException
	 * re) { log.error("merge failed", re); throw re; } }
	 * 
	 * public User findById(java.lang.Integer id) {
	 * log.debug("getting User instance with id: " + id); try { User instance =
	 * (User) sessionFactory.getCurrentSession().get("com.app.dao.User", id); if
	 * (instance == null) { log.debug("get successful, no instance found"); }
	 * else { log.debug("get successful, instance found"); } return instance; }
	 * catch (RuntimeException re) { log.error("get failed", re); throw re; } }
	 * 
	 * public List<User> findByExample(User instance) {
	 * log.debug("finding User instance by example"); try { List<User> results =
	 * (List<User>)
	 * sessionFactory.getCurrentSession().createCriteria("com.app.dao.User")
	 * .add(create(instance)).list();
	 * log.debug("find by example successful, result size: " + results.size());
	 * return results; } catch (RuntimeException re) {
	 * log.error("find by example failed", re); throw re; } }
	 */
}
