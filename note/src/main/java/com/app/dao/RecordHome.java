package com.app.dao;

// Generated 2016-3-21 12:15:08 by Hibernate Tools 4.3.1.Final

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.model.Record;

/**
 * Home object for domain model class Record.
 * 
 * @see com.app.dao.Record
 * @author Hibernate Tools
 */
@Repository
public class RecordHome {

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Transactional
	public void save(Record record) {
		sessionFactory.getCurrentSession().save(record);
	}

	@Transactional
	public void delete(Integer id) {
		sessionFactory.getCurrentSession().delete(new Record(id));
	}

	@Transactional
	public void update(Record record) {
		sessionFactory.getCurrentSession().update(record);
	}

	public Record findById(Integer id) {
		return (Record) sessionFactory.getCurrentSession()
				.get(Record.class, id);
	}

	public List<Record> findByIp(String ip) {
		List<Record> list = null;
		String sql = "select * from record where ip = '" + ip + "' limit 0,1 ";
		try {
			list = jdbcTemplate.query(sql, new BeanPropertyRowMapper(
					Record.class));

			return list;
		} catch (RuntimeException re) {
			re.printStackTrace();
			return null;
		}
	}

	public Map<String, String> getData() {
		List<Map> list = jdbcTemplate
				.query("select count(id) as id ,sum(searchnum) as searchnum ,sum(indexnum) as indexnum ,GROUP_CONCAT(user) as users from record where 1=1 ",
						new RowMapper<Map>() {
							public Map mapRow(ResultSet rs, int rowNum)
									throws SQLException {
								Map row = new HashMap();
								row.put("id", rs.getString("id"));
								row.put("searchnum", rs.getString("searchnum"));
								row.put("indexnum", rs.getString("indexnum"));
								row.put("users", rs.getString("users"));
								return row;
							}
						});
		return list.get(0);
	}

}
