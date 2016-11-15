package cn.itcast.bookstore.order.domain;

import java.util.Date;
import java.util.List;

import cn.itcast.bookstore.user.domain.User;

public class Order {
	private String oid;
	private Date ordertime;
	private List<OrderItem> orderitemlist;
	private double total;
	private int state;
	private User user;
	private String address;
	
	public List<OrderItem> getOrderitemlist() {
		return orderitemlist;
	}
	public void setOrderitemlist(List<OrderItem> orderitemlist) {
		this.orderitemlist = orderitemlist;
	}
	public Order(String oid, Date ordertime, double total, int state,
			User user, String address) {
		super();
		this.oid = oid;
		this.ordertime = ordertime;
		this.total = total;
		this.state = state;
		this.user = user;
		this.address = address;
	}
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Order [oid=" + oid + ", ordertime=" + ordertime
				+ ", orderitemlist=" + orderitemlist + ", total=" + total
				+ ", state=" + state + ", user=" + user + ", address="
				+ address + "]";
	}
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public Date getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

}
