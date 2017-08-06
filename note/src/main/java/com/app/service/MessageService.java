package com.app.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.app.dao.MessageHome;
import com.app.model.Message;

@Service
public class MessageService {

	@Resource
	MessageHome MessageHome;

	public void save(Message Message) {
		MessageHome.save(Message);
	}

	public void delete(Integer id) {
		MessageHome.delete(id);
	}

	public void update(Message Message) {
		MessageHome.update(Message);
	}

	public Message findById(Integer id) {
		return MessageHome.findById(id);
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
		return MessageHome.findByFromTo(fromId, toIn);
	}

	/**
	 * @discription 获取最近几条数据
	 * @author lxf
	 * @created 2017-8-6 上午9:36:29
	 * @param num
	 * @return
	 */
	public List<Message> findLastData(int num) {
		List<Message> list = MessageHome.findLastData(num);
		List<Message> listNew = new ArrayList<Message>();
		for (Message message : list) {
			listNew.add(0, message);
		}
		return listNew;
	}
}