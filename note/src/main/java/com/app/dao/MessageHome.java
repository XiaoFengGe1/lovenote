package com.app.dao;

// Generated 2016-3-21 12:15:08 by Hibernate Tools 4.3.1.Final

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.model.Message;

/**
 * Home object for domain model class Message.
 * 
 * @see com.app.dao.Message
 * @author Hibernate Tools
 */
@Repository
public class MessageHome {

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Transactional
	public void save(Message Message) {
		sessionFactory.getCurrentSession().save(Message);
	}

	@Transactional
	public void delete(Integer id) {
		sessionFactory.getCurrentSession().delete(new Message(id));
	}

	@Transactional
	public void update(Message Message) {
		sessionFactory.getCurrentSession().update(Message);
	}

	public Message findById(Integer id) {
		return (Message) sessionFactory.getCurrentSession().get(Message.class,
				id);
	}

	/**
	 * @discription 查询获取列表
	 * @author lxf
	 * @created 2017-8-6 上午9:24:21
	 * @param fromId
	 *            小的ID序号 ，最小是1
	 * @param toIn
	 *            大的ID序号
	 * @return
	 */
	public List<Message> findByFromTo(int fromId, int toIn) {
		List<Message> list = null;
		if (fromId <= 0) {
			fromId = 1;
		}
		if (toIn < fromId) {
			toIn = fromId;
		}
		String sql = "select * from message where id between ' " + fromId
				+ " ' and  ' " + toIn + " '";
		try {
			list = jdbcTemplate.query(sql, new BeanPropertyRowMapper(
					Message.class));
			return list;
		} catch (RuntimeException re) {
			re.printStackTrace();
			return null;
		}
	}

	/**
	 * @discription 获取最近几条数据
	 * @author lxf
	 * @created 2017-8-6 上午9:36:29
	 * @param num
	 * @return
	 */
	public List<Message> findLastData(int num) {
		List<Message> list = null;
		if (num <= 0) {
			num = 1;
		}
		String sql = "select * from message order by time DESC,id DESC limit "
				+ num;
		try {
			list = jdbcTemplate.query(sql, new BeanPropertyRowMapper(
					Message.class));
			return list;
		} catch (RuntimeException re) {
			re.printStackTrace();
			return null;
		}
	}
}
