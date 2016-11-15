package cn.itcast.bookstore.order.web.servlet;



import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cn.itcast.bookstore.cart.domain.Cart;
import cn.itcast.bookstore.cart.domain.CartItem;
import cn.itcast.bookstore.order.domain.Order;
import cn.itcast.bookstore.order.domain.OrderItem;
import cn.itcast.bookstore.order.service.OrderException;
import cn.itcast.bookstore.order.service.OrderService;
import cn.itcast.bookstore.user.domain.User;
import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;

public class OrderServlet extends BaseServlet {
	
	private OrderService service=new OrderService();
	
	public String add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Cart cart=(Cart) request.getSession().getAttribute("cart");
		
		Order order=new Order();
		order.setOid(CommonUtils.uuid());
		order.setOrdertime(new Date());
		order.setState(1);
		User u=(User) request.getSession().getAttribute("session_user");
		order.setUser(u);
		order.setTotal(cart.getTotal());
		
		List<OrderItem> orderlist=new ArrayList<OrderItem>();
		
		for(CartItem cartitem:cart.getcartitems()){
			OrderItem or=new OrderItem();
		
			or.setBook(cartitem.getBook());
			or.setIid(CommonUtils.uuid());
			or.setCount(cartitem.getCount());
			or.setOrder(order);
			or.setSubtotal(cartitem.getSubtotal());
			
			orderlist.add(or);
		}
		order.setOrderitemlist(orderlist);
		
		cart.clear();
		
		service.addOrder(order);
		
		request.setAttribute("order", order);
		
		return "f:/jsps/order/desc.jsp";
	}
	
	public String myOrders(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		User user=(User) request.getSession().getAttribute("session_user");
		List<Order> orderlist=service.myOrders(user.getUid());
		request.setAttribute("orderlist", orderlist);
		
		return "f:/jsps/order/list.jsp";
	}
	
	public String load(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String oid=request.getParameter("oid");
		request.setAttribute("order", service.load(oid));
		return "f:/jsps/order/desc.jsp";
	}
	
	
	public String confirm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String oid=(String) request.getParameter("oid");
		
		try {
			service.confirm(oid);
			request.setAttribute("msg", "交易成功");
		} catch (OrderException e) {
			request.setAttribute("msg", e.getMessage());
		}
		return "f:/jsps/msg.jsp";
	}
	
	public String pay(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Properties prop=new Properties();
		InputStream is=this.getClass().getClassLoader().getResourceAsStream("merchantInfo.properties");
		prop.load(is);
		String p0_cmd="Buy";
		String p1_MerId=prop.getProperty("p1_MerId");
		String keyvalue=prop.getProperty("keyValue");
		String p2_order=request.getParameter("oid");
		String p3_Amt="0.01";
		String p4_Cur="CNY";
		String p5_Pid="";
		String p6_Pcat="";
		String p7_Pdesc="";
		String p8_Url="http;//localhost:8080/bookstroe/OrderServlet?method=back";
		String p9_SAF="";
		String pa_MP="";
		String pd_FrpId=request.getParameter("pd_FrpId");
		String pr_NeedResponse="1";
		String hmac=PaymentUtil.buildHmac(p0_cmd, p1_MerId, p2_order, p3_Amt,
				p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url,p9_SAF, pa_MP, 
				pd_FrpId,pr_NeedResponse, keyvalue);
		StringBuilder url=new StringBuilder(prop.getProperty("url"));
		url.append("?p0_cmd=").append(p0_cmd);
		url.append("&p1_MerId=").append(p1_MerId);
		url.append("&p2_order=").append(p2_order);
		url.append("&p3_Amt=").append(p3_Amt);
		url.append("&p4_Cur=").append(p4_Cur);
		url.append("&p5_Pid=").append(p5_Pid);
		url.append("&p6_Pcat=").append(p6_Pcat);
		url.append("&p7_Pdesc=").append(p7_Pdesc);
		url.append("&p8_Url=").append(p8_Url);
		url.append("&p9_SAF=").append(p9_SAF);
		url.append("&pa_MP=").append(pa_MP);
		url.append("&pd_FrpId=").append(pd_FrpId);
		url.append("&pr_NeedResponse=").append(pr_NeedResponse);
		url.append("&hmac=").append(hmac);
		
		System.out.println(url);
		response.sendRedirect(url.toString());
		return "";
	}
	
	public String back(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		return "";
	}
	
	
	
	
	
	
}
