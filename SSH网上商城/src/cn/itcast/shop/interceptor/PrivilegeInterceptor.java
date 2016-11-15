package cn.itcast.shop.interceptor;

import org.apache.struts2.ServletActionContext;

import cn.itcast.shop.adminuser.vo.AdminUser;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class PrivilegeInterceptor extends MethodFilterInterceptor{

	@Override
	protected String doIntercept(ActionInvocation arg0) throws Exception {
		AdminUser au=(AdminUser) ServletActionContext.getRequest().getSession().getAttribute("existAdminUser");
		if(au==null){
			ActionSupport as= (ActionSupport) arg0.getAction();
			as.addActionError("你没有权限访问");
			return "loginFail";
		}else
		return arg0.invoke();
	}
		
}
