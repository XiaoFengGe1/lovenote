package com.app.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.app.dao.RecordHome;
import com.app.model.Record;
import com.app.utils.StringUtil;

@Service
public class RecordService {

	@Resource
	RecordHome recordHome;

	public void save(Record record) {
		recordHome.save(record);
	}

	public void delete(Integer id) {
		recordHome.delete(id);
	}

	public void update(Record record) {
		recordHome.update(record);
	}

	public Record findById(Integer id) {
		return recordHome.findById(id);
	}

	public Map<String, String> getData() {
		return recordHome.getData();
	}

	public void addRecord(String ip, String name, String device, int isSearch) {
		if (!StringUtil.isBlank(ip)) {
			List<Record> recordList = recordHome.findByIp(ip);
			if (recordList == null || recordList.size() == 0) {
				Record record = new Record();
				if (isSearch == 1) {
					record.setSearchnum(1);
				} else {
					record.setIndexnum(1);
				}
				if (!StringUtil.isBlank(device)) {
					record.setDevice(device + ",");
				}
				if (!StringUtil.isBlank(name)) {
					record.setUser(name + ",");
				}
				record.setIp(ip);
				save(record);
			} else {
				Record record = recordList.get(0);
				if (isSearch == 1) {
					record.setSearchnum(record.getSearchnum() + 1);
				} else {
					record.setIndexnum(record.getIndexnum() + 1);
				}
				if (!StringUtil.isBlank(device)
						&& record.getDevice().length() < 100
						&& !record.getDevice().contains(device)) {
					record.setDevice(record.getDevice() + device + ",");
				}
				if (!StringUtil.isBlank(name)
						&& record.getUser().length() < 2000
						&& !record.getUser().contains(name)) {
					record.setUser(record.getUser() + name + ",");
				}
				record.setEnddate(new Date());
				update(record);
			}
		}
	}
}