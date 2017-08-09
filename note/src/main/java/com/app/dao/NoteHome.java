package com.app.dao;

// Generated 2016-3-21 12:15:08 by Hibernate Tools 4.3.1.Final

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.model.Note;

//import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Note.
 * 
 * @see com.app.dao.Note
 * @author Hibernate Tools
 */
@Repository
public class NoteHome {

	// private static final Log log = LogFactory.getLog(NoteHome.class);

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

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Transactional
	public void save(Note note) {
		sessionFactory.getCurrentSession().save(note);
	}

	@Transactional
	public void delete(Integer id) {
		sessionFactory.getCurrentSession().delete(new Note(id));
	}

	@Transactional
	public void update(Note note) {
		sessionFactory.getCurrentSession().update(note);
	}

	public Note findById(Integer id) {
		return (Note) sessionFactory.getCurrentSession().get(Note.class, id);
	}

	public void fixType(String typeName, String uname) {
		jdbcTemplate.update("update note set type='其他' where type ='"
				+ typeName + "' and uname ='" + uname + "'  and isdelete=0");
		/*
		 * sessionFactory.getCurrentSession().createQuery(
		 * "update Note set type='其他' where type in :type and uname in :uname  and isdelete=0"
		 * ) .setParameter("type", typeName).setParameter("uname",
		 * uname).executeUpdate();
		 */
	}

	public int checkTitle(String name, String title) {
		return jdbcTemplate
				.queryForInt("select count(id) from note where title='" + title
						+ "' and uname='" + name + "' and isdelete=0");
		// return (Note)
		// sessionFactory.getCurrentSession().createQuery("from Note where title='"+title+"' and uname='"+name+"' and isdelete=0");
	}

	public HashMap<String, Object> findMain(String[] keys, int pageNum) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<Note> list = null;
		if (keys == null || keys.length == 0) {
			return findMainBlock(pageNum);
		}
		try {
			String sql1 = "from note as c left join (select DISTINCT id,count(id) as countn from ((select id from note where concat(content,title) like '%"
					+ keys[0] + "%' and isdelete=0 )";
			if (keys.length > 1) {
				for (int i = 1; i < keys.length; i++) {
					sql1 = sql1
							+ "union all (select id from note where concat(content,title) like '%"
							+ keys[i] + "%' and isdelete=0 )";
				}
			}
			String sql2 = ") as j group by id ) as a using(id)  where countn>0  order by countn desc , likenum desc , click desc";
			int count = jdbcTemplate.queryForInt("select count(id)" + sql1
					+ ") as j group by id ) as a using(id)  where countn>0 ");
			list = jdbcTemplate.query(
					"select id,title,uname,createtime,click,likenum,part,content,extendone "
							+ sql1 + sql2 + " limit " + ((pageNum - 1) * 10)
							+ ",10", new BeanPropertyRowMapper(Note.class));
			Collections.reverse(list);
			map.put("total", count);
			map.put("rows", list);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public HashMap<String, Object> findMainBlock(int pageNum) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<Note> list = null;
		try {
			// int num = (int)(long) runner.query(conn,
			// "select count(id) from note where isdelete=0 ",new
			// ScalarHandler());
			int num = jdbcTemplate
					.queryForInt("select count(id) from note where isdelete=0 ");
			// list =
			// runner.query(conn,"select id,title,uname,content,createtime,click,likenum from note where isdelete=0 order by likenum desc , click asc limit "+((pageNum-1)*10)+","+(pageNum*10-1),new
			// BeanListHandler<>(Note.class));
			list = jdbcTemplate
					.query("select id,title,uname,createtime,click,likenum,part from note where isdelete=0 order by createtime desc , likenum desc , click desc limit "
							+ ((pageNum - 1) * 10) + ",10",
							new BeanPropertyRowMapper(Note.class));
			Collections.reverse(list);
			map.put("total", num);
			map.put("rows", list);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public HashMap<String, Object> findMainTool(String[] keys, int pageNum,
			HashMap hashmap) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<Note> list = null;
		if (keys == null || keys.length == 0) {
			return findMainToolBlock(pageNum, hashmap);
		}
		String sql = "";
		if (hashmap.get("classify").equals("全部")) {

		} else {
			sql = " type = '" + hashmap.get("classify") + "' and ";
		}
		if (hashmap.get("user").equals("所有用户")) {

		} else {
			sql = sql + " uname = '" + hashmap.get("user") + "' and ";
		}
		sql = sql + " createtime between '" + hashmap.get("fromTime")
				+ "' and '" + hashmap.get("toTime") + " 23:59:59' and ";

		if (hashmap.get("title").equals("标题正文")) {
			sql = sql + " concat(content,title) ";
		} else if (hashmap.get("title").equals("标题")) {
			sql = sql + " title ";
		} else {
			sql = sql + " content ";
		}

		try {
			String sql1 = "from note as c left join (select DISTINCT id,count(id) as countn from ((select id from note where "
					+ sql + " like '%" + keys[0] + "%' and isdelete=0 )";
			if (keys.length > 1) {
				for (int i = 1; i < keys.length; i++) {
					sql1 = sql1 + "union all (select id from note where " + sql
							+ " like '%" + keys[i] + "%' and isdelete=0 )";
				}
			}
			String sql2 = ") as j group by id ) as a using(id)  where countn>0  order by countn desc , likenum desc , click desc";
			// int num = (int)(long) runner.query(conn,
			// "select count(id)"+sql1+") as j group by id ) as a using(id)  where countn>0 ",new
			// ScalarHandler());
			int num = jdbcTemplate.queryForInt("select count(id)" + sql1
					+ ") as j group by id ) as a using(id)  where countn>0 ");
			// list =
			// runner.query(conn,"select id,title,uname,content,createtime,click,likenum "+sql1+sql2+" limit "+((pageNum-1)*10)+","+(pageNum*10-1),new
			// BeanListHandler<>(Note.class));
			list = jdbcTemplate.query(
					"select id,title,uname,createtime,click,likenum,part "
							+ sql1 + sql2 + " limit " + ((pageNum - 1) * 10)
							+ ",10", new BeanPropertyRowMapper(Note.class));
			Collections.reverse(list);
			map.put("total", num);
			map.put("rows", list);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public HashMap<String, Object> findMainToolBlock(int pageNum,
			HashMap hashmap) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<Note> list = null;
		String sql = "";
		if (hashmap.get("classify").equals("全部")) {

		} else {
			sql = " type = '" + hashmap.get("classify") + "' and ";
		}
		if (hashmap.get("user").equals("所有用户")) {

		} else {
			sql = sql + " uname = '" + hashmap.get("user") + "' and ";
		}
		sql = sql + " createtime between '" + hashmap.get("fromTime")
				+ "' and '" + hashmap.get("toTime") + " 23:59:59' ";

		try {
			String sql2 = "and isdelete=0 order by createtime desc , likenum desc , click desc ";
			// int num = (int)(long) runner.query(conn,
			// "select count(id) from note where isdelete=0 ",new
			// ScalarHandler());
			// list =
			// runner.query(conn,"select id,title,uname,content,createtime,click,likenum from note where "+sql+sql2+" limit "+((pageNum-1)*10)+","+(pageNum*10-1),new
			// BeanListHandler<>(Note.class));
			int num = jdbcTemplate
					.queryForInt("select count(id) from note where isdelete=0 ");
			list = jdbcTemplate.query(
					"select id,title,uname,createtime,click,likenum,part from note where "
							+ sql + sql2 + " limit " + ((pageNum - 1) * 10)
							+ ",10", new BeanPropertyRowMapper(Note.class));
			Collections.reverse(list);
			map.put("total", num);
			map.put("rows", list);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/*
	 * public void persist(Note transientInstance) {
	 * log.debug("persisting Note instance"); try {
	 * sessionFactory.getCurrentSession().persist(transientInstance);
	 * log.debug("persist successful"); } catch (RuntimeException re) {
	 * log.error("persist failed", re); throw re; } }
	 * 
	 * public void attachDirty(Note instance) {
	 * log.debug("attaching dirty Note instance"); try {
	 * sessionFactory.getCurrentSession().saveOrUpdate(instance);
	 * log.debug("attach successful"); } catch (RuntimeException re) {
	 * log.error("attach failed", re); throw re; } }
	 * 
	 * public void attachClean(Note instance) {
	 * log.debug("attaching clean Note instance"); try {
	 * sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
	 * log.debug("attach successful"); } catch (RuntimeException re) {
	 * log.error("attach failed", re); throw re; } }
	 * 
	 * public void delete(Note persistentInstance) {
	 * log.debug("deleting Note instance"); try {
	 * sessionFactory.getCurrentSession().delete(persistentInstance);
	 * log.debug("delete successful"); } catch (RuntimeException re) {
	 * log.error("delete failed", re); throw re; } }
	 * 
	 * public Note merge(Note detachedInstance) {
	 * log.debug("merging Note instance"); try { Note result = (Note)
	 * sessionFactory.getCurrentSession().merge(detachedInstance);
	 * log.debug("merge successful"); return result; } catch (RuntimeException
	 * re) { log.error("merge failed", re); throw re; } }
	 * 
	 * public Note findById(java.lang.Integer id) {
	 * log.debug("getting Note instance with id: " + id); try { Note instance =
	 * (Note) sessionFactory.getCurrentSession().get("com.app.dao.Note", id); if
	 * (instance == null) { log.debug("get successful, no instance found"); }
	 * else { log.debug("get successful, instance found"); } return instance; }
	 * catch (RuntimeException re) { log.error("get failed", re); throw re; } }
	 * 
	 * public List<Note> findByExample(Note instance) {
	 * log.debug("finding Note instance by example"); try { List<Note> results =
	 * (List<Note>)
	 * sessionFactory.getCurrentSession().createCriteria("com.app.dao.Note")
	 * .add(create(instance)).list();
	 * log.debug("find by example successful, result size: " + results.size());
	 * return results; } catch (RuntimeException re) {
	 * log.error("find by example failed", re); throw re; } }
	 */
}
