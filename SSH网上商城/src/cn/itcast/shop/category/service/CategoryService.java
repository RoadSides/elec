package cn.itcast.shop.category.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.shop.category.dao.CategoryDao;
import cn.itcast.shop.category.vo.Category;

@Transactional
public class CategoryService {
		 private CategoryDao categoryDao;

		public void setCategoryDao(CategoryDao categoryDao) {
			this.categoryDao = categoryDao;
		}

		public List<Category> findAll() {
			return categoryDao.findAll();
		}

		public void save(Category category) {
			categoryDao.save(category);
		}

		public Category findByCid(int cid) {
			return categoryDao.findbyCid(cid);
		}

		public void delete(Category exitcategory) {
			categoryDao.delete(exitcategory);
		}

		public void update(Category category) {
			categoryDao.update(category);
		}
		 
		 
}
