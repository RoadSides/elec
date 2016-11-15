package cn.itcast.shop.product.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.shop.product.dao.ProductDao;
import cn.itcast.shop.product.vo.Product;
import cn.itcast.shop.utils.PageBean;

@Transactional
public class ProductService {
		private ProductDao productDao;

		public void setProductDao(ProductDao productDao) {
			this.productDao = productDao;
		}

		public List<Product> findHot() {
			
			return productDao.findHot();
		}

		public List<Product> findNew() {
			return productDao.findNew();
		}

		public Product findById(Integer pid) {
			return productDao.findById(pid);
		}

		public PageBean<Product> findByPageCid(int cid, int page) {		
			PageBean<Product> pageBean=new PageBean<Product>();
			//设置当前页数
			pageBean.setPage(page);
			//设置每页记录数
			int limit=12;
			pageBean.setLimit(limit);
			//设置总记录数
			int totalCount=0;
			totalCount=productDao.findCountCid(cid);
			pageBean.setTotalCount(totalCount);
			//设置总页数
			int totalPage=0;
			if(totalCount%limit==0){
				totalPage=totalCount/limit;
			}else{
				totalPage=totalCount/limit+1;
			}
			pageBean.setTotalPage(totalPage);
			//每页显示的数据集合
			//从哪开始
			int begin=(page-1)*limit;
			List<Product> list=productDao.findByPageCid(cid,begin,limit);
			pageBean.setList(list);
			return pageBean;
		}

		public PageBean<Product> findByPageCsid(int csid, int page) {
			PageBean<Product> pageBean=new PageBean<Product>();
			pageBean.setPage(page);
			int limit=12;
			pageBean.setLimit(limit);
			int totalCount=productDao.findCountCsid(csid);
			pageBean.setTotalCount(totalCount);
			int totalPage=0;
			if(totalCount%limit==0){
				totalPage=totalCount/limit;
			}else{
				totalPage=totalCount/limit+1;
			}
			pageBean.setTotalPage(totalPage);
			int begin=(page-1)*limit;
			List<Product> list=productDao.findBypageCsid(csid,begin,limit);
			pageBean.setList(list);
			return pageBean;
		}

		public PageBean<Product> findByPage(int page) {
			PageBean<Product> pageBean=new PageBean<Product>();
			pageBean.setPage(page);
			int limit=15;
			pageBean.setLimit(limit);
			int totalCount=productDao.findCountCid();
			pageBean.setTotalCount(totalCount);
			int totalPage;
			if(totalCount%limit==0){
				totalPage=totalCount/limit;
			}else{
				totalPage=totalCount/limit+1;
			}
			pageBean.setTotalPage(totalPage);
			int begin=(page-1)*limit;
			List<Product> list=productDao.findByPageCid(begin, limit);
			pageBean.setList(list);
			return pageBean;
		}

		public void save(Product product) {
			productDao.save(product);
		}

		public void delete(Product product) {
			productDao.delete(product);
		}

		public void update(Product product) {
			productDao.update(product);
		}
		
		
}
