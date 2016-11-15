package cn.itcast.shop.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import org.junit.Test;



public class MailUtils {
		
		public void sendMail(String to,String code){
				/*
				 * 1.获得一个session对象
				 * 2.创建一个代表邮件的Message
				 * 3.发送邮件Transport
				 */
			//创建连接对象
			Properties  p=new Properties();	
			p.put("mail.smtp.auth", "true");
			p.setProperty("mail.smtp.host", "smtp.163.com");


			Session session=Session.getInstance(p, new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("itcast_lj@163.com", "lj19960409325575");
				}
			});
			//创建邮件对象
			Message message=new MimeMessage(session);
			try {
				message.setFrom(new InternetAddress("itcast_lj@163.com"));
				message.addRecipient(RecipientType.TO, new InternetAddress(to));
				//设置标题
				message.setSubject("Tim");
				//设置正文  <h1>购物天堂官方邮件，请点击下面链接进行激活</h1><h3><a href='http://localhost:8080/shop/user_active.action?code='"+code+"'>激活"+code+"</a></h3>
				message.setContent("aaa","text/html;charset=UTF-8");
				//发送邮件
				Transport.send(message);
			} catch (AddressException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		@Test
		public void fun(){
			sendMail("itcast_lj@126.com","1111111111");
		}
}


