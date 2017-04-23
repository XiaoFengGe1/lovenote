package com.app.service;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;


import com.app.dao.RecordHome;
import com.app.model.Record;

@Service
public class RecordService {

	@Resource
	RecordHome recordHome;
	
	public void save(Record record){
		recordHome.save(record);
	}
	
	public void delete(Integer id){
		recordHome.delete(id);
	}
	
	public void update(Record record){
		recordHome.update(record);
	}
	
	public Record findById(Integer id){
		return recordHome.findById(id);
	}
	
	public Map<String,String> getData(){
		return recordHome.getData();
	}
	public void addRecord(String ip,String name,String device,int isSearch){
		if(isSearch==1){
			if(ip!=null&&!ip.equals("")){
				Record record = recordHome.findByIp(ip);
				if(record==null){
					record = new Record();
					record.setSearchnum(1);
					if(name==null){
						if(device==null){
							
						}else{
							record.setDevice(device);
						}
					}else{
						if(device==null){
							
						}else{
							record.setDevice(device);
						}
						record.setUser(name);
					}
					record.setIp(ip);
					save(record);
				}else{
					record.setSearchnum(record.getSearchnum()+1);
					if(name==null){
						if(device==null){
							
						}else{
							if(record.getDevice().contains(device)){
								
							}else{
								record.setDevice(record.getDevice()+device+",");
							}
						}
					}else{
						if(record.getUser().contains(name)){
							
						}else{
							if(record.getUser().length()<200){
								record.setUser(record.getUser()+name+",");
							}
							if(device==null){
								
							}else{
								if(record.getDevice().contains(device)){
									
								}else{
									record.setDevice(record.getDevice()+device+",");
								}
							}
						}
					}
					save(record);
				}
			}
		}else{
			if(ip!=null&&!ip.equals("")){
				Record record = recordHome.findByIp(ip);
				if(record==null){
					record = new Record();
					record.setSearchnum(1);
					if(name==null){
						if(device==null){
							
						}else{
							record.setDevice(device);
						}
					}else{
						if(device==null){
							
						}else{
							record.setDevice(device);
						}
						record.setUser(name);
					}
					record.setIp(ip);
					save(record);
				}else{
					record.setIndexnum(record.getIndexnum()+1);
					record.setEnddate(new Date());
					if(name==null){
						if(device==null){
							
						}else{
							if(record.getDevice().contains(device)){
								
							}else{
								record.setDevice(record.getDevice()+device+",");
							}
						}
					}else{
						if(record.getUser().contains(name)){
							
						}else{
							if(record.getUser().length()<200){
								record.setUser(record.getUser()+name+",");
							}
							if(device==null){
								
							}else{
								if(record.getDevice().contains(device)){
									
								}else{
									record.setDevice(record.getDevice()+device+",");
								}
							}
						}
					}
					update(record);
				}
			}
		}

	}
	
}	