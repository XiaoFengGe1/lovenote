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

import com.app.model.Money;
import com.app.model.Review;

//import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Money.
 * @see com.app.dao.Money
 * @author Hibernate Tools
 */
@Repository
public class MoneyHome {

	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Transactional
	public void save(Money money) {
		sessionFactory.getCurrentSession().save(money);
	}
	
	@Transactional
	public void delete(Integer id) {
		sessionFactory.getCurrentSession().delete(new Money(id));
	}
	
	@Transactional
	public void update(Money money) {
		sessionFactory.getCurrentSession().update(money);
	}
	
	public Money findById(Integer id) {
		return (Money) sessionFactory.getCurrentSession().get(Money.class, id);
	}
	public  HashMap<String, Object> findMoney(String uname,int pageNum){
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<Review> list = null;
		try {
			String sql1 = "from money where uname = '"+uname+"'"; 
			int count = jdbcTemplate.queryForInt("select count(id) "+sql1+"");
			list = jdbcTemplate.query("select * "+sql1+" limit "+((pageNum-1)*10)+",10", new BeanPropertyRowMapper(Money.class));
			map.put("total", count);
			map.put("rows", list);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
