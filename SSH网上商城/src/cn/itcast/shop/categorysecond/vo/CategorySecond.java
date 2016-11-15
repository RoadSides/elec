package cn.itcast.shop.categorysecond.vo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import cn.itcast.shop.category.vo.Category;
import cn.itcast.shop.product.vo.Product;

public class CategorySecond implements Serializable {
		private int csid;
		private String csname;
		private Category category;
		private Set<Product> products=new HashSet<Product>();
		
		public Category getCategory() {
			return category;
		}
		public void setCategory(Category category) {
			this.category = category;
		}
		public int getCsid() {
			return csid;
		}
		public void setCsid(int csid) {
			this.csid = csid;
		}
		public String getCsname() {
			return csname;
		}
		public void setCsname(String csname) {
			this.csname = csname;
		}
		public Set<Product> getProducts() {
			return products;
		}
		public void setProducts(Set<Product> products) {
			this.products = products;
		}

}
