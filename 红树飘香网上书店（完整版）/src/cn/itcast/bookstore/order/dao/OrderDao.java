package cn.itcast.bookstore.order.dao;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.itcast.bookstore.book.domain.Book;
import cn.itcast.bookstore.order.domain.Order;
import cn.itcast.bookstore.order.domain.OrderItem;
import cn.itcast.commons.CommonUtils;
import cn.itcast.jdbc.TxQueryRunner;

public class OrderDao {
	private QueryRunner qr=new TxQueryRunner();
	
	public void addOrder(Order order){
		String sql="insert into orders values(?,?,?,?,?,?)";
		
		Timestamp stamp=new Timestamp(order.getOrdertime().getTime());
		
		Object[] params={order.getOid(),stamp,order.getTotal(),order.getState(),order.getUser().getUid(),order.getAddress()};
		
		try {
			qr.update(sql,params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public void addOrderItemList(List<OrderItem> OrderItemList){
		String sql="insert into orderitem values(?,?,?,?,?)";
		
		Object[][] params=new Object[OrderItemList.size()][];
		
		for(int i=0;i<params.length;i++){
			OrderItem item=OrderItemList.get(i);
			
			params[i]=new Object[]{item.getIid(),item.getCount(),item.getSubtotal(),item.getOrder().getOid(),item.getBook().getBid()};
			
			
		}
		try {
			qr.batch(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	public List<Order> findByUid(String uid) {
		String sql="select * from orders where uid=?";
		try {
			List<Order> list=qr.query(sql, new BeanListHandler<Order>(Order.class),uid);
			for(Order order:list){
				loadOrderItem(order);
			}
			return list;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	private void loadOrderItem(Order order) {
		String sql="select * from orderitem i,book b where i.bid=b.bid and oid=?";
		
		try {
			List<Map<String,Object>> maplist=qr.query(sql, new MapListHandler(),order.getOid());
			List<OrderItem> orderitem=toOrderItemList(maplist);
			order.setOrderitemlist(orderitem);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private List<OrderItem> toOrderItemList(List<Map<String, Object>> maplist) {
		List<OrderItem> orderitemlist=new ArrayList<OrderItem>();
		for(Map<String,Object> map:maplist){
			OrderItem orderitem=toOrderItem(map);
			orderitemlist.add(orderitem);
		}
		return orderitemlist;
	}

	private OrderItem toOrderItem(Map<String, Object> map) {
		OrderItem orderitem=CommonUtils.toBean(map, OrderItem.class);
		Book book=CommonUtils.toBean(map, Book.class);
		orderitem.setBook(book);
		return orderitem;
	}

	public Order load(String oid) {
		try {
			String sql="select * from orders where oid=?";
			Order order= qr.query(sql, new BeanHandler<Order>(Order.class),oid);
			loadOrderItem(order);
			return order;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public int getStateById(String oid){
		
		String sql="select state from orders where oid=?";
		
		Number num;
		try {
			num = (Number) qr.query(sql, new ScalarHandler(),oid);
			int value=num.intValue();
			return value;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	
	}
	
	public void UpdateState(String oid,int state){
		
		String sql="update  orders set state=?  where oid=?";

		try {
			qr.update(sql,state,oid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	
	}

	public List<Order> findAll() {
		String sql="select * from orders";
		try {
			List<Order> pre= qr.query(sql, new BeanListHandler<Order>(Order.class));
			for(Order order:pre){
				loadOrderItem(order);
			}
			return pre;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Order> findnonpayment() {
		String sql="select * from orders where state=1";
		List<Order> pre;
		try {
			pre = qr.query(sql, new BeanListHandler<Order>(Order.class));	
			for(Order order:pre){
				loadOrderItem(order);
			}
			return pre;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	public List<Order> findhaspayment() {
		String sql="select * from orders where state=2";
		try {
			List<Order> pre = qr.query(sql, new BeanListHandler<Order>(Order.class));	
			for(Order order:pre){
				loadOrderItem(order);
			}
			return pre;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Order> nonreceive() {
		String sql="select * from orders where state=3";
		try {
			List<Order> pre = qr.query(sql, new BeanListHandler<Order>(Order.class));	
			for(Order order:pre){
				loadOrderItem(order);
			}
			return pre;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Order> complete() {
		String sql="select * from orders where state=4";
		try {
			List<Order> pre = qr.query(sql, new BeanListHandler<Order>(Order.class));	
			for(Order order:pre){
				loadOrderItem(order);
			}
			return pre;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
}
