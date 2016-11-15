package cn.itcast.shop.user.action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import cn.itcast.shop.user.service.UserService;
import cn.itcast.shop.user.vo.User;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class UserAction extends ActionSupport implements ModelDriven<User>{
		 
		private User user=new User();
		private UserService userService;

		public void setUserService(UserService userService) {
			this.userService = userService;
		}

		public String registPage(){
			return "registPage";
		}
		
		public String findByName() throws IOException{		
			 User u=userService.findByUsername(user.getUsername());

			 HttpServletResponse response=ServletActionContext.getResponse();
			 response.setContentType("text/html;charset=UTF-8");
			 if(u!=null){
				 response.getWriter().println("<font color='red'>用户名已经存在</font>");
			}
			 else{
				 response.getWriter().println("<font color='green'>用户名可以注册</font>");
			 }
				 return  NONE;
		}

		public String regist(){
			String checkcode1=(String) ServletActionContext.getRequest().getSession().getAttribute("checkcode");
			if(!user.getCheckcode().equalsIgnoreCase(checkcode1)){
				 	this.addActionError("验证码输入错误");
				 	return "checkcodeFail";
			 }
			userService.save(user); 
			this.addActionMessage("注册成功");
			return "msg";
		} 
		
		@Override
		public User getModel() {
			return user;
		}
		
		public String loginPage(){
			return "loginPage";
		}
		
		public String login(){
			User userexit=userService.login(user);
			 if(userexit==null){
				 this.addActionError("登录失败，用户名或密码错误或用户未激活");
				 return LOGIN;
			 }else{
				 ServletActionContext.getRequest().getSession().setAttribute("userexit", userexit);
				 return "loginSuccess";
			 }
		}
		
		public String quit(){
			ServletActionContext.getRequest().getSession().invalidate();
			return "quit";
		}
} 
