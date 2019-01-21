package com.haoli.sdk.web.util;

import java.net.URL;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.URLDataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.haoli.sdk.web.domain.MailConfig;

/**
 * 邮件发送工具类
 * 可以动态配置发送邮件所用的邮件服务器，也可以写死
 * @author 李昊
 *
 */
public class EmailUtil {
	
	//smtp邮箱服务器地址
	private String host;
	
	//smtp端口
	private String port;
	
	//用户名
	private String userName;

	//密码
	private String password;
	
	//发件人邮箱地址
	private String fromEmail;
	
	//发件人名称
	private String fromName;
	
	//是否使用ssl
	private boolean ssl;
	
	public EmailUtil() {
		
	}
	
	public EmailUtil(MailConfig mailConfig) {
		this.host = mailConfig.getHost();
		this.port = mailConfig.getPort();
		this.userName = mailConfig.getUserName();
		this.password = mailConfig.getPassword();
		this.fromEmail = mailConfig.getFromEmail();
		this.fromName = mailConfig.getFromName();
		this.ssl = mailConfig.isSsl();
	}
	
	/**
	 * 发送带有附件和内嵌附件的邮件，可以避免outlook等部分邮箱屏蔽图片或其他资源
	 * @param to 收件人
	 * @param subject 主题
	 * @param content 内容
	 * @param cc 抄送
	 * @param contentIds 附件或资源的识别id,content-id 字段头用于为"multipart/related"组合消息中的内嵌资源指定一个唯一标识符
	 * 					在html格式的正文中可以使用这个唯一标识符来引用内嵌资源。
	 * @param urlList 附件或资源列表
	 */
	public void sendEmail(String[] toList, String subject, String content, String cc, 
							String[] contentIds, String[] urlList) throws Exception {
		Authenticator authenticator = new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, password);
			}
		};
		Properties props = new Properties();
		props.setProperty("mail.smtp.host", host);
		props.put("mail.smtp.auth", "true");
		props.setProperty("mail.smtp.port", port);
		//设置为true时，当时用指定的socket类创建socket失败后，将使用java.net.Socket创建socket，默认为true
		props.setProperty("mail.smtp.ssl.enable", "false");
		if(ssl) {
			props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.setProperty("mail.smtp.socketFactory.port", "465");
		}
		Session session = Session.getInstance(props, authenticator);
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(fromEmail, fromName));
		message.setSubject(subject);
		Integer length = toList.length;
		Address[] addrList = new InternetAddress[length];
		for(int i=0; i<length; i++) {
			String to = toList[i];
			addrList[i] = new InternetAddress(to);
		}
		message.addRecipients(Message.RecipientType.TO, addrList);
		MimeBodyPart textBody = new MimeBodyPart();
		textBody.setContent(content, "text/html;charset=utf-8");
		MimeMultipart relatedMultipart = new MimeMultipart();
		relatedMultipart.addBodyPart(textBody);
		for(int i=0;i<contentIds.length;i++) {
			String contentId = contentIds[i];
			MimeBodyPart imageBody = new MimeBodyPart();
			DataHandler picDataHandler = new DataHandler(new URLDataSource(new URL(urlList[i])));
			imageBody.setDataHandler(picDataHandler);
			imageBody.setContentID("<" + contentId + ">");
			relatedMultipart.addBodyPart(imageBody);
		}
		relatedMultipart.setSubType("related");
		message.setContent(relatedMultipart);
		Transport.send(message);
	}

	/**
	 * 用于判断是否可以连接指定的邮件服务器
	 */
	public boolean connect() throws Exception {
		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", "smtps");
		if(ssl) {
			props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.setProperty("mail.smtp.socketFactory.port", "465");
		}
		Session session = Session.getInstance(props);
		session.setDebug(true);
		Transport t = session.getTransport();
		t.connect(host, Integer.valueOf(port), userName, password);
		return true;
	}
}
