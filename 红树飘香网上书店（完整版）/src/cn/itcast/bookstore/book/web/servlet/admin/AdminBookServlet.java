package cn.itcast.bookstore.book.web.servlet.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.bookstore.book.domain.Book;
import cn.itcast.bookstore.book.service.BookService;
import cn.itcast.bookstore.category.domain.Category;
import cn.itcast.bookstore.category.service.CategoryService;
import cn.itcast.bookstore.category.web.servlet.CategoryServlet;
import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;

public class AdminBookServlet extends BaseServlet {
	
	private BookService service=new BookService();
	private CategoryService categoryservice=new CategoryService();
	public String findAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("bookList", service.findAll());
		return "f:/adminjsps/admin/book/list.jsp";
	}
	
	public String findById(HttpServletRequest request, HttpServletResponse response){
		
		Book book= service.load(request.getParameter("bid"));
		List<Category> category=categoryservice.findAll();
		request.setAttribute("book",book);
		request.setAttribute("categoryList",category);
		return "f:/adminjsps/admin/book/desc.jsp";
	}
	
	public String addPre(HttpServletRequest request, HttpServletResponse response){
		request.setAttribute("categoryList", categoryservice.findAll());
		return "f:/adminjsps/admin/book/add.jsp";
	}
	
	public String delete(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		
		String bid=request.getParameter("bid");
		service.delete(bid);
		return findAll(request,response);
	}
	
	public String edit(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		
		Book book=CommonUtils.toBean(request.getParameterMap(), Book.class);
		Category category=CommonUtils.toBean(request.getParameterMap(), Category.class);
		book.setCategory(category);
		service.edit(book);
		
		
		return findAll(request,response);
	}
}
