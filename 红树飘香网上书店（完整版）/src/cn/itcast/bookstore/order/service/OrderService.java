package cn.itcast.bookstore.order.service;

import java.sql.SQLException;
import java.util.List;

import cn.itcast.bookstore.order.dao.OrderDao;
import cn.itcast.bookstore.order.domain.Order;
import cn.itcast.jdbc.JdbcUtils;

public class OrderService {
	private OrderDao dao=new OrderDao();
	
	public void addOrder(Order order){
		try {
			JdbcUtils.beginTransaction();
					
			dao.addOrder(order);
			dao.addOrderItemList(order.getOrderitemlist());
			
			JdbcUtils.commitTransaction();
		} catch (SQLException e) {
			
			try {
				JdbcUtils.rollbackTransaction();
			} catch (SQLException e1) {
				throw new RuntimeException(e);
			}
		}
		
	}

	public List<Order> myOrders(String uid) {
		
		return dao.findByUid(uid);
	}

	public Order load(String oid) {
		return dao.load(oid);
	}
	
	public void confirm(String oid) throws OrderException{
		int state=dao.getStateById(oid);
		if(state!=3) throw new OrderException("订单状态不符合");
		dao.UpdateState(oid, 4);
	}
	
	public List<Order> findAll(){
		return dao.findAll();
	}

	public void updateOrder(String oid,int state) {
		dao.UpdateState(oid, state);
	}

	public List<Order> findnonpayment() {
		return dao.findnonpayment();
	}

	public List<Order> findhaspayment() {
		return dao.findhaspayment();
	}

	public List<Order> nonreceive() {
		
		return dao.nonreceive();
	}

	public List<Order> complete() {
		return dao.complete();
	}
}
