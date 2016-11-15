package cn.itcast.shop.adminuser.action;

import org.apache.struts2.ServletActionContext;

import cn.itcast.shop.adminuser.service.AdminUserService;
import cn.itcast.shop.adminuser.vo.AdminUser;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AdminUserAction extends ActionSupport implements ModelDriven<AdminUser>{

	private AdminUser adminUser=new AdminUser();
	
	private AdminUserService adminUserService;

	public void setAdminUserService(AdminUserService adminUserService) {
		this.adminUserService = adminUserService;
	}

	@Override
	public AdminUser getModel() {
		return adminUser;
	}
		
	public String login(){
		AdminUser existAdminUser=adminUserService.login(adminUser); 
		if(existAdminUser==null){
			//登录失败
			this.addActionError("亲，你的用户名或密码错误");
			return "loginFail";
		}else //登陆成功
			ServletActionContext.getRequest().getSession().setAttribute("existAdminUser", existAdminUser); 
			return "loginSuccess";
	}
}
