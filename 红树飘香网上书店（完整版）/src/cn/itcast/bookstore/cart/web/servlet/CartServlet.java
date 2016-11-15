package cn.itcast.bookstore.cart.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.bookstore.book.domain.Book;
import cn.itcast.bookstore.book.service.BookService;
import cn.itcast.bookstore.cart.domain.Cart;
import cn.itcast.bookstore.cart.domain.CartItem;
import cn.itcast.servlet.BaseServlet;

public class CartServlet extends BaseServlet {


	public String add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		Cart cart=(Cart) request.getSession().getAttribute("cart");
		String bid=request.getParameter("bid");
		Book book=new BookService().load(bid);
		int count=Integer.parseInt(request.getParameter("count"));
		CartItem item=new CartItem();
		item.setBook(book);
		item.setCount(count);
		
		cart.add(item);
		
		return "f:/jsps/cart/list.jsp";
	}

	public String delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Cart cart=(Cart) request.getSession().getAttribute("cart");
		String bid=(String) request.getParameter("bid");		
		cart.delete(bid);
		
		return "f:/jsps/cart/list.jsp";
	}
	
	public String clear(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Cart cart=(Cart) request.getSession().getAttribute("cart");
		cart.clear();
		
		return "f:/jsps/cart/list.jsp";
	}
}
