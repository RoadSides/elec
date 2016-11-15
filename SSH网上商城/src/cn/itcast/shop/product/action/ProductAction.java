package cn.itcast.shop.product.action;

import java.util.List;

import cn.itcast.shop.category.service.CategoryService;
import cn.itcast.shop.product.service.ProductService;
import cn.itcast.shop.product.vo.Product;
import cn.itcast.shop.utils.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ProductAction extends ActionSupport implements ModelDriven<Product>{
		
		private Product product=new Product();
		private ProductService productService;
		private int cid;
		private int page;
		private int csid;
		
		public void setCsid(int csid) {
			this.csid = csid;
		}

		public void setPage(int page) {
			this.page = page;
		}

		public void setCid(int cid) {
			this.cid = cid;
		}

		public int getCid() {
			return cid;
		}

		public void setProductService(ProductService productService) {
			this.productService = productService;
		}

		public String findById(){
			product=productService.findById(product.getPid());
			return "findByPid";
		}
		
		@Override
		public Product getModel() { 
			return product;
		}
		
		public String findByCid(){ 
			PageBean<Product> pageBean=productService.findByPageCid(cid,page);
			//将pageBean存入到值栈中
			ActionContext.getContext().getValueStack().set("pageBean", pageBean);
			
			return "findByCid";
		}
		
		public String findByCsid(){
			PageBean<Product> pageBean=productService.findByPageCsid(csid, page);
			ActionContext.getContext().getValueStack().set("pageBean", pageBean);
			return "findByCsid";
		}
}
