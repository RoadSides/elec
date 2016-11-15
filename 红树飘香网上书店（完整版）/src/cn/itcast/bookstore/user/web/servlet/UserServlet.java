package cn.itcast.bookstore.user.web.servlet;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.bookstore.cart.domain.Cart;
import cn.itcast.bookstore.user.domain.User;
import cn.itcast.bookstore.user.service.UserException;
import cn.itcast.bookstore.user.service.UserService;
import cn.itcast.commons.CommonUtils;
import cn.itcast.mail.Mail;
import cn.itcast.mail.MailUtils;
import cn.itcast.servlet.BaseServlet;

public class UserServlet extends BaseServlet {
	private String code;
	private UserService us=new UserService();
	
	public String quit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().invalidate();
		return "r:/index.jsp";
	}
	
	
	public String regist(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		User form=CommonUtils.toBean(request.getParameterMap(), User.class);
		form.setUid(CommonUtils.uuid());
		this.code=CommonUtils.uuid()+CommonUtils.uuid();
		form.setCode(this.code);
		
		
		Map<String,String> errors=new HashMap<String, String>();
		if(form.getUsername()==null||form.getUsername().trim().isEmpty()){
			errors.put("username", "用户名不能为空");
		}else if(form.getUsername().length()<1||form.getUsername().length()>13){
			errors.put("username", "用户名的长度必须在1-13之间");
		}
		
		if(form.getPassword()==null||form.getPassword().trim().isEmpty()){
			errors.put("password", "密码不能为空");
		}else if(form.getPassword().length()<1||form.getPassword().length()>13){
			errors.put("password", "密码的长度必须在1-13之间");
		}
		
		if(form.getEmail()==null||form.getEmail().trim().isEmpty()){
			errors.put("email", "邮箱不能为空");
		}else if(!form.getEmail().matches("\\w+@\\w*\\.\\w*")){
			errors.put("email", "邮箱格式错误");
		}
		
		if(errors.size()>0){
			request.setAttribute("errors",errors);
			request.setAttribute("form", form);
			return "f:/jsps/user/regist.jsp";
		}
		
		try {
			us.regist(form);
		} catch (UserException e) {

			request.setAttribute("msg",e.getMessage() );
			request.setAttribute("form", form);
			return "f:/jsps/user/regist.jsp";
		}
		
		Properties pr=new Properties();
		pr.load(this.getClass().getClassLoader().getResourceAsStream("email_template.properties"));
		String host=pr.getProperty("host");
		String uname=pr.getProperty("uname");
		String password=pr.getProperty("password");
		String to=form.getEmail();
		String from=pr.getProperty("from");
		String subject=pr.getProperty("subject");
		String content=pr.getProperty("content");
		content=MessageFormat.format(content, form.getCode());
		
		
		Session session=MailUtils.createSession(host, uname, password);
		

		Mail mail=new Mail(from, to, subject, content);
		

		try {
			MailUtils.send(session, mail);
		} catch (MessagingException e) {
			e.printStackTrace();
		}

		request.setAttribute("msg", "注册成功，请到邮箱激活账号");
		request.setAttribute("code", this.code);
		return "f:/jsps/msg.jsp";
	}

	

	public String active(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String code=request.getParameter("code");
		try {
			us.active(code);
			request.setAttribute("msg", "激活成功");
		} catch (UserException e) {
			request.setAttribute("msg", e.getMessage());
		}
		return "f:/jsps/msg.jsp";
	}
	

	public String login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User form=CommonUtils.toBean(request.getParameterMap(), User.class);
		try {
			User user=us.login(form);
			request.getSession().setAttribute("session_user", user);
			request.getSession().setAttribute("cart", new Cart());
			return "r:/index.jsp";
		} catch (UserException e) {
			request.setAttribute("msg", e.getMessage());
			request.setAttribute("form", form);
			return "f:/jsps/user/login.jsp";
		}
	}
}
