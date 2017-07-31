package com.app.utils;

import java.io.File;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

/**
 * 邮箱发送工具类
 * 
 * @date 2016年1月18日
 * @verison 1.00
 * @author lixiaofeng
 * @see
 */
public class Email {

	public static void main(String[] args) {
		toAddress("1174254785@qq.com", "123", "test");
	}

	public Email() {
		super();
		// TODO Auto-generated constructor stub
	}

	public static void toAddress(String address, String title, String content) {
		// TODO Auto-generated constructor stub
		Properties properties = new Properties();
		properties.put("mail.smtp.host", "smtp.qq.com");
		properties.put("mail.smtp.port", "25");
		properties.put("mail.smtp.auth", "true");
		// 使用JavaMail发送邮件的5个步骤
		// 1、创建session
		Session session = Session.getInstance(properties);
		// 开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
		session.setDebug(true);
		// 2、通过session得到transport对象
		properties.setProperty("mail.transport.protocol", "smtp");
		Transport ts = null;
		try {
			ts = session.getTransport();
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 3、使用邮箱的用户名和密码连上邮件服务器，发送邮件时，发件人需要提交邮箱的用户名和密码给smtp服务器，用户名和密码都通过验证之后才能够正常发送邮件给收件人。
		try {
			ts.connect("smtp.qq.com", "1174254785@qq.com", "svayifstipxxibfc");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 4、创建邮件
		Message message = null;
		try {
			message = createSimpleMail(session, address, title, content);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 5、发送邮件
		try {
			ts.sendMessage(message, message.getAllRecipients());
			ts.close();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static MimeMessage createSimpleMail(Session session,
			String toAddress, String title, String contnt) throws Exception {
		// 创建邮件对象
		MimeMessage message = new MimeMessage(session);
		// 指明邮件的发件人
		message.setFrom(new InternetAddress("1174254785@qq.com"));
		// 指明邮件的收件人，现在发件人和收件人是一样的，那就是自己给自己发
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(
				toAddress));
		// 邮件的标题
		message.setSubject(title);
		// 邮件的文本内容
		message.setContent(contnt, "text/html;charset=UTF-8");
		// 返回创建好的邮件对象
		return message;
	}

	public void toAddressWithFile(String address, String title, String content,
			String fileAdd) {
		// TODO Auto-generated constructor stub
		Properties properties = new Properties();
		properties.put("mail.smtp.host", "smtp.qq.com");
		properties.put("mail.smtp.port", "25");
		properties.put("mail.smtp.auth", "true");
		// 使用JavaMail发送邮件的5个步骤
		// 1、创建session
		Session session = Session.getInstance(properties);
		// 开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
		session.setDebug(true);
		// 2、通过session得到transport对象
		properties.setProperty("mail.transport.protocol", "smtp");
		Transport ts = null;
		try {
			ts = session.getTransport();
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 3、使用邮箱的用户名和密码连上邮件服务器，发送邮件时，发件人需要提交邮箱的用户名和密码给smtp服务器，用户名和密码都通过验证之后才能够正常发送邮件给收件人。
		try {
			ts.connect("smtp.qq.com", "1174254785@qq.com", "zpamzgxtacjqhged");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 4、创建邮件
		Message message = null;
		try {
			message = createSimpleMail(session, address, title, content,
					fileAdd);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 5、发送邮件
		try {
			ts.sendMessage(message, message.getAllRecipients());
			ts.close();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static MimeMessage createSimpleMail(Session session,
			String toAddress, String title, String contnt, String fileAdd)
			throws Exception {
		// 创建邮件对象
		MimeMessage message = new MimeMessage(session);
		// 指明邮件的发件人
		message.setFrom(new InternetAddress("1174254785@qq.com"));
		// 指明邮件的收件人，现在发件人和收件人是一样的，那就是自己给自己发
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(
				toAddress));
		// 邮件的标题
		message.setSubject(title);
		// 邮件的文本内容
		// message.setContent(contnt, "text/html;charset=UTF-8");

		// 附件
		// 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
		Multipart multipart = new MimeMultipart();
		// 添加邮件正文
		BodyPart contentPart = new MimeBodyPart();
		contentPart.setContent(contnt, "text/html;charset=UTF-8");
		multipart.addBodyPart(contentPart);
		File file = new File(fileAdd);
		if (file != null) {
			BodyPart attachmentBodyPart = new MimeBodyPart();
			DataSource source = new FileDataSource(file);
			attachmentBodyPart.setDataHandler(new DataHandler(source));

			// 网上流传的解决文件名乱码的方法，其实用MimeUtility.encodeWord就可以很方便的搞定
			// 这里很重要，通过下面的Base64编码的转换可以保证你的中文附件标题名在发送时不会变成乱码
			// sun.misc.BASE64Encoder enc = new sun.misc.BASE64Encoder();
			// messageBodyPart.setFileName("=?GBK?B?" +
			// enc.encode(attachment.getName().getBytes()) + "?=");

			// MimeUtility.encodeWord可以避免文件名乱码
			attachmentBodyPart.setFileName(MimeUtility.encodeWord(file
					.getName()));
			multipart.addBodyPart(attachmentBodyPart);
		}
		// 返回创建好的邮件对象
		// 将multipart对象放到message中
		message.setContent(multipart);
		// 保存邮件
		message.saveChanges();
		return message;
	}
}