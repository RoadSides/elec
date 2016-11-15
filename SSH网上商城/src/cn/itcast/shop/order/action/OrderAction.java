package cn.itcast.shop.order.action;

import java.util.Date;

import org.apache.struts2.ServletActionContext;

import cn.itcast.shop.cart.vo.Cart;
import cn.itcast.shop.cart.vo.CartItem;
import cn.itcast.shop.order.service.OrderService;
import cn.itcast.shop.order.vo.OrderItem;
import cn.itcast.shop.order.vo.Orders;
import cn.itcast.shop.user.vo.User;
import cn.itcast.shop.utils.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class OrderAction extends ActionSupport implements ModelDriven<Orders>{

	private Orders orders=new Orders();
	private OrderService orderService;
	private int page;
	private String pd_FrpId;
	
	public void setPd_FrpId(String pd_FrpId) {
		this.pd_FrpId = pd_FrpId;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	@Override
	public Orders getModel() {
		return orders;
	}
		
	public String save(){
		//订单数据补全
		orders.setOrdertime(new Date());
		/**
		 * 1:未付款   2:已经付款   3:已经发货，单位确认收货   4:交易完成
		 */
		orders.setState(1);
		Cart cart=(Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");
		if(cart==null){
			this.addActionError("亲，您还没有购物，请先去购物");
			return "msg";
		}
		orders.setTotal(cart.getTotal());
		for(CartItem cartItem:cart.getCartItems()){
			OrderItem orderItem=new OrderItem();
			orderItem.setCount(cartItem.getCount());
			orderItem.setSubtotal(cartItem.getSubtotal());
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setOrders(orders);
			orders.getOrderItems().add(orderItem);
		}
		//设置订单所属用户；
		User user=(User) ServletActionContext.getRequest().getSession().getAttribute("userexit");
		if(user==null){
			this.addActionError("亲，您还没有登录，请先去登陆");
			return "login";
		}
		orders.setUser(user);
		orderService.save(orders);
		cart.clearCart();
		return "saveSuccess";
	}
	
	public String findByUid(){
		User user=(User) ServletActionContext.getRequest().getSession().getAttribute("userexit");
		PageBean<Orders> pageBean=orderService.findByPageUid(user.getUid(),page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByUidSuccess";
	}
	
	public String findByOid(){
		orders=orderService.findByOid(orders.getOid());
		return "findByOidSuccess";
	}
	
	public String payOrder(){
		Orders currOrder=orderService.findByOid(orders.getOid());
		System.out.println(orders.getAddr());
		currOrder.setAddr(orders.getAddr());
		currOrder.setName(orders.getName());
		currOrder.setPhone(orders.getPhone());
		currOrder.setState(2);
		 
		orderService.update(currOrder);
		
		
		this.addActionMessage("订单付款成功");
		return "msg";
	}
	
	public String updateState(){
		Orders currOrder=orderService.findByOid(orders.getOid());
		currOrder.setState(4);
		orderService.update(currOrder);
		return "updateStateSuccess";
	}
}
