package cn.itcast.shop.order.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.shop.order.dao.OrderDao;
import cn.itcast.shop.order.vo.OrderItem;
import cn.itcast.shop.order.vo.Orders;
import cn.itcast.shop.utils.PageBean;

@Transactional
public class OrderService {
		private OrderDao orderDao;

		public void setOrderDao(OrderDao orderDao) {
			this.orderDao = orderDao;
		}

		public void save(Orders orders) {
			orderDao.save(orders);
		}

		public PageBean<Orders> findByPageUid(int uid, int page) {
			 PageBean<Orders> pageBean=new PageBean<Orders>();
			 //设置当前页数
			 pageBean.setPage(page);
			 int limit=5;
			 //设置每页显示的记录数
			 pageBean.setLimit(limit);
			 int totalCount=0;
			 totalCount=orderDao.findByCountUid(uid);
			 //设置总记录数
			 pageBean.setTotalCount(totalCount);
			 int totalPage=0;
			//设置总页数;
			 if(totalCount%limit==0){
				 totalPage=totalCount/limit;
			 }else{
				 totalPage=totalCount/limit+1;
			 }
			 pageBean.setTotalPage(totalPage);
			 int begin=(page-1)*limit;
			 //每页显示的集合
			 List<Orders> list=orderDao.finByPageUid(uid,begin,limit);
			 pageBean.setList(list);
			return pageBean;
		}

		public Orders findByOid(int oid) {
			
			return orderDao.findByOid(oid);
		}

		public void update(Orders currOrder) {
			orderDao.update(currOrder);
		}

		public PageBean<Orders> findByPage(int page) {
			PageBean<Orders> pageBean=new PageBean<Orders>();
			pageBean.setPage(page);
			int limit=15;
			pageBean.setLimit(limit);
			int totalCount=orderDao.findByCount();
			pageBean.setTotalCount(totalCount);
			int totalPage=0;
			if(totalCount%limit==0){
				totalPage=totalCount/limit;
			}else{
				totalPage=totalCount/limit+1;
			}
			pageBean.setTotalPage(totalPage);
			int begin=(page-1)*limit;
			List<Orders> list=orderDao.findByPage(begin,limit);
			pageBean.setList(list);
			return pageBean;
		}

		public List<OrderItem> findOrderItem(int oid) {
			
			return orderDao.findOrderItem(oid);
		}
		
		
}
