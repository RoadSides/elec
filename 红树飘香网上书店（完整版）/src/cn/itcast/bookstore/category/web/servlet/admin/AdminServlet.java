package cn.itcast.bookstore.category.web.servlet.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.bookstore.category.domain.Category;
import cn.itcast.bookstore.category.service.CategoryService;
import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;

public class AdminServlet extends BaseServlet {
		private CategoryService service=new CategoryService();
		
		public String findAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			
			request.setAttribute("categorylist", service.findAll());
			
			return "f:/adminjsps/admin/category/list.jsp";
		}
		
		public String add(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			Category category=CommonUtils.toBean(request.getParameterMap(), Category.class);
			String cid=CommonUtils.uuid();
			category.setCid(cid);
			service.add(category);
			return findAll(request,response);
		}
		
		public String delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
			String cid=request.getParameter("cid");
			try{
				service.delete(cid);
				return findAll(request,response);
			}catch(CategoryException e){
				request.setAttribute("msg", e.getMessage());
				return "f:/adminjsps/admin/msg.jsp";
			}
		}
		
		public String editPre(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
			String cid=request.getParameter("cid");
			request.setAttribute("category", service.load(cid));
			return "f:/adminjsps/admin/category/mod.jsp";
		}
		
		public String edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
			String cid=request.getParameter("cid");
			String cname=request.getParameter("cname");
			service.edit(cid,cname);
			return findAll(request,response);
		}
}
