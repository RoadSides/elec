package cn.itcast.bookstore.order.web.admin.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.bookstore.order.service.OrderService;
import cn.itcast.servlet.BaseServlet;

public class AdminOrderServlet extends BaseServlet {
	
	private OrderService service=new OrderService();
	public String findAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			request.setAttribute("orderList",service.findAll());
			return "/adminjsps/admin/order/list.jsp";
	}
	
	public String send(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			String oid=request.getParameter("oid");
			service.updateOrder(oid,3);
			return findAll(request,response);
	}
	
	public String receive(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			String oid=request.getParameter("oid");
			service.updateOrder(oid,4);
			return findAll(request,response);
	}
	
	public String nonpayment(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("orderList", service.findnonpayment());
		return "/adminjsps/admin/order/list.jsp";
	}
	
	public String haspayment(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("orderList", service.findhaspayment());
		return "/adminjsps/admin/order/list.jsp";
	}
	
	public String nonreceive(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("orderList", service.nonreceive());
		return "/adminjsps/admin/order/list.jsp";
	}
	
	public String complete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("orderList", service.complete());
		return "/adminjsps/admin/order/list.jsp";
	}
}
