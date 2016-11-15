package cn.itcast.bookstore.user.web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.bookstore.user.domain.User;

public class LoginFilter implements Filter {


	public void destroy() {

	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest) request;
		HttpServletResponse res=(HttpServletResponse) response;
		User user=(User) req.getSession().getAttribute("session_user");
		if(user!=null){
			chain.doFilter(request, response);
		}else{
			req.setAttribute("msg", "你还没有登录");
			req.getRequestDispatcher("/jsps/user/login.jsp").forward(req, res);
		}
	}


	public void init(FilterConfig fConfig) throws ServletException {

	}

}
