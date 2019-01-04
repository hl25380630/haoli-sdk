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

/**
 * 邮件发送工具类
 * @author 李昊
 *
 */
public class EmailUtil {
	
	private String host = "smtp.boe.com.cn";

	private String username = "service@boe.com.cn";

	private String passwd = "boe654321";
	
	private String fromEmail = "service@boe.com.cn";

	private String fromName = "京东方（BOE）";
	
	public static void main(String[] args) throws Exception {
		String content ="<img src=\"cid:www.baidu.com/img/bd_logo1.png\" alt=\"\" style=\"display:block; margin:0 auto; width:80%; text-align:center;\">";
		String img = "http://boe-ssc-object.oss-cn-beijing.aliyuncs.com/pdf/zhusu.pdf";
		EmailUtil eu = new EmailUtil();
		String[] toList = {"lihao_100@boe.com.cn","qiuxue@nancal.com"};
		String[] contentIds = {"attr1"};
		String[] urlList = {img};
		eu.sendEmail(toList , "test email",content, null, contentIds,urlList);
	}
	
	/**
	 * 发送带有附件和内嵌附件的邮件，可以避免outlook等部分邮箱屏蔽图片或其他资源
	 * @param to 收件人
	 * @param subject 主题
	 * @param content 内容
	 * @param cc 抄送人
	 * @param contentIds 附件或资源的识别id,content-id 字段头用于为"multipart/related"组合消息中的内嵌资源指定一个唯一标识符
	 * 					在html格式的正文中可以使用这个唯一标识符来引用内嵌资源。
	 * @param urlList 附件或资源列表
	 */
	public void sendEmail(String[] toList, String subject, String content, String cc, 
							String[] contentIds, String[] urlList) throws Exception {
		Authenticator authenticator = new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, passwd);
			}
		};
		Properties props = new Properties();
		props.setProperty("mail.smtp.host", host);
		props.put("mail.smtp.auth", "true");
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
			//用于读取互联网上的资源
			DataHandler picDataHandler = new DataHandler(new URLDataSource(new URL(urlList[i])));
			imageBody.setDataHandler(picDataHandler);
			imageBody.setContentID("<" + contentId + ">");
			relatedMultipart.addBodyPart(imageBody);
		}
		relatedMultipart.setSubType("related");
		message.setContent(relatedMultipart);
		Transport.send(message);
	}

}
