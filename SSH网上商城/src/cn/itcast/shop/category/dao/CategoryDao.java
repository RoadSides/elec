package cn.itcast.shop.category.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.itcast.shop.category.vo.Category;

public class CategoryDao extends HibernateDaoSupport{

	public List<Category> findAll() {
		String hql="from Category";
		List<Category> list=this.getHibernateTemplate().find(hql);
		return list;
	}

	public void save(Category category) {
		this.getHibernateTemplate().save(category);
	}

	public Category findbyCid(int cid) {
		return this.getHibernateTemplate().get(Category.class,cid);
	}

	public void delete(Category exitcategory) {
		this.getHibernateTemplate().delete(exitcategory);
	}
		
	public void update(Category category){
		this.getHibernateTemplate().update(category);
	}
}
