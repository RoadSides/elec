package cn.itcast.shop.order.adminaction;

import java.util.List;

import cn.itcast.shop.order.service.OrderService;
import cn.itcast.shop.order.vo.OrderItem;
import cn.itcast.shop.order.vo.Orders;
import cn.itcast.shop.utils.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AdminOrderAction extends ActionSupport implements ModelDriven<Orders>{

	private Orders orders=new Orders();
	private OrderService orderService;
	private int page;
	
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	
	public void setPage(int page) {
		this.page = page;
	}
		
	@Override
	public Orders getModel() {
		return orders;
	}

	public String findAll(){
		PageBean<Orders> pageBean=orderService.findByPage(page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}
	
	public String findOrderItem(){
		
		List<OrderItem> list=orderService.findOrderItem(orders.getOid());
		
		ActionContext.getContext().getValueStack().set("list", list);
		return "findOrderItem";
	}
	
	public String updateState(){
		
		Orders currOrder=orderService.findByOid(orders.getOid());
		
		currOrder.setState(3);
		
		orderService.update(currOrder);
		
		return "updateStateSuccess";
	}
	
}
