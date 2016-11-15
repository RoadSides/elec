package cn.itcast.bookstore.category.service;

import java.util.List;

import cn.itcast.bookstore.book.dao.BookDao;
import cn.itcast.bookstore.category.dao.CategoryDao;
import cn.itcast.bookstore.category.domain.Category;
import cn.itcast.bookstore.category.web.servlet.admin.CategoryException;

public class CategoryService {
	
	private CategoryDao dao=new CategoryDao();
	private BookDao bookdao=new BookDao();
	
	public List<Category> findAll() {
		
		return dao.findAll();
	}

	public void add(Category category) {
		dao.add(category);
	}

	public void delete(String cid) throws CategoryException {
		int count=bookdao.getCountByCid(cid);
		if(count>0) throw new CategoryException("该分类下还有图书");
		dao.delete(cid);
	}

	public Category load(String cid) {
		
		return dao.load(cid);
	}

	public void edit(String cid,String cname) {
		dao.edit(cid,cname);
	}
}
