package cn.itcast.shop.product.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import cn.itcast.shop.product.vo.Product;
import cn.itcast.shop.utils.PageHibernateCallback;

public class ProductDao extends HibernateDaoSupport{

	//里显示分页查询
	//离线条件查询
	public List<Product> findHot() {
		//使用离线查询
		DetachedCriteria criteria=DetachedCriteria.forClass(Product.class);
		//查新热门的精品，条件是is_hot=1
		criteria.add(Restrictions.eq("is_hot", 1));
		//倒序排列输出
		criteria.addOrder(Order.desc("pdate"));
		//执行查询
		List<Product> list=this.getHibernateTemplate().findByCriteria(criteria,0,10);
		
		return list;
	}

	public List<Product> findNew() {
		
		DetachedCriteria criteria=DetachedCriteria.forClass(Product.class);
		
		criteria.addOrder(Order.desc("pdate"));
		
		List<Product> list=this.getHibernateTemplate().findByCriteria(criteria,0,10);
		
		
		return list;
	}

	public Product findById(Integer pid) {
		
		return this.getHibernateTemplate().get(Product.class, pid);
	}

	public int findCountCid(int cid) {
		String hql="select count(*) from Product p where p.categorySecond.category.cid=?";
		List<Long> list=this.getHibernateTemplate().find(hql,cid);
		if(list!=null&&list.size()>0){
				return list.get(0).intValue();
		}
		return 0;
	}

	public List<Product> findByPageCid(int cid, int begin, int limit) {
		
		String hql="select p from Product p join p.categorySecond cs join cs.category c where c.cid=?";
		//分页第二种写法，常规query接口写法
		List<Product> list=this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, new Object[]{cid}, begin, limit));
		if(list!=null&&list.size()>0){
			return list;
		}
		return null;
	}

	public int findCountCsid(int csid) {
		 String hql="select count(*) from Product p where p.categorySecond.csid=?";
		 List<Long> list=this.getHibernateTemplate().find(hql,csid);
		 if(list!=null&&list.size()>0){
			 return list.get(0).intValue();
		 }
		return 0;
	}

	public List<Product> findBypageCsid(int csid, int begin, int limit) {
		String hql="select p from Product p join p.categorySecond cs where cs.csid=?";
		List<Product> list=this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, new Object[]{csid}, begin, limit));
		return list;
	}

	public List<Product> findByPageCid(int begin, int limit) {
		String hql="from Product p order by p.pid";
		List<Product> list=this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql,new Object[]{},begin,limit));
		return list;
	}

	public int findCountCid() {
		String hql="select count(*) from Product p ";
		List<Long> list=this.getHibernateTemplate().find(hql);
		if(list!=null&&list.size()>0){
			return list.get(0).intValue();
		}else
			return 0;
	}

	public void save(Product product) {
		this.getHibernateTemplate().save(product);
	}

	public void delete(Product product) {
		this.getHibernateTemplate().delete(product);
	}

	public void update(Product product) {
		this.getHibernateTemplate().update(product);
	}

		
}
