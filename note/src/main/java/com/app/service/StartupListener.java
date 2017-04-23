package com.app.service;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
/**
 * 启动监听器,继承ApplicationListener后能监听每一个请求。
 */
//@Service
public class StartupListener implements ApplicationListener {
	
	/*@Autowired
	JdbcTemplate jdbcTemplate;
    private void createSitemap() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
            	//Email email = new Email();
            	List<Map> list  =  jdbcTemplate.query("select count(id) as id ,sum(searchnum) as searchnum ,sum(indexnum) as indexnum ,GROUP_CONCAT(user) as users from record where 1=1 ",new RowMapper<Map>() {  
      		      public Map mapRow(ResultSet rs, int rowNum) throws SQLException {  
      		          Map row = new HashMap();  
      		          row.put("id", rs.getString("id"));  
      		          row.put("searchnum", rs.getString("searchnum"));  
      		          row.put("indexnum", rs.getString("indexnum"));  
      		          row.put("users", rs.getString("users"));  
      		          return row;  
      		  }});  
            	Map map = list.get(0);
            	String data = "访问IP数:"+map.get("id")+"全网搜点击数"+map.get("searchnum")+"首页点击数"+map.get("indexnum")+"所有用户"+map.get("users");
            	//email.toAddress("1174254785@qq.com", "爱笔记数据汇报",data );
            	System.out.println(data);
            }
        }, 60*1000,10*1000);
    }*/
	@Override
	public void onApplicationEvent(ApplicationEvent arg0) {
		// TODO Auto-generated method stub
		//System.out.println(123123);
	}

}
