package cn.itcast.shop.order.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.itcast.shop.order.vo.OrderItem;
import cn.itcast.shop.order.vo.Orders;
import cn.itcast.shop.utils.PageHibernateCallback;

public class OrderDao extends HibernateDaoSupport{

	//保存订单
	public void save(Orders orders) {
			this.getHibernateTemplate().save(orders);
	}

	//dao 我的订单个数统计
	public int findByCountUid(int uid) {
		String hql="select count(*) from Orders o where o.user.uid=?";
		List<Long> list= this.getHibernateTemplate().find(hql,uid);
		if(list!=null&&list.size()>0){
			return list.get(0).intValue();
		}else return 0;
	}

	public List<Orders> finByPageUid(int uid, int begin, int limit) {
		 String hql="from Orders o where o.user.uid=? order by ordertime desc";
		 List<Orders> list=this.getHibernateTemplate().execute(new PageHibernateCallback<Orders>(hql, new Object[]{uid}, begin, limit));
		return list;
	}

	public Orders findByOid(int oid) {
		
		return this.getHibernateTemplate().get(Orders.class,oid);
	}

	public void update(Orders currOrder) {
			this.getHibernateTemplate().update(currOrder);
	}

	public int findByCount() {
		String hql="select count(*) from Orders ";
		List<Long> list=this.getHibernateTemplate().find(hql);
		if(list!=null&&list.size()>0){
			return list.get(0).intValue();
		}else
		return 0;
	}

	public List<Orders> findByPage(int begin, int limit) {
		String hql="from Orders o order by o.ordertime desc";
		
		List<Orders> list=this.getHibernateTemplate().execute(new PageHibernateCallback<Orders>(hql,new Object[]{},begin,limit));
		
		if(list!=null&&list.size()>0){
			return list;
		}else
			return null;
	}

	public List<OrderItem> findOrderItem(int oid) {
		String hql="from OrderItem oi where oi.orders.oid=?";
		List<OrderItem> list=this.getHibernateTemplate().find(hql,oid);
		if(list!=null&&list.size()>0){
			return list;
		}else
		return null;
	}
		
}
