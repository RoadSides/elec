package cn.itcast.shop.category.vo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import cn.itcast.shop.categorysecond.vo.CategorySecond;

public class Category implements Serializable{
		private int cid;
		private String cname;
		private Set<CategorySecond> categorySeconds=new HashSet<CategorySecond>();
		 
		public Set<CategorySecond> getCategorySeconds() {
			return categorySeconds;
		}
		public void setCategorySeconds(Set<CategorySecond> categorySeconds) {
			this.categorySeconds = categorySeconds;
		}
		public int getCid() {
			return cid;
		}
		public void setCid(int cid) {
			this.cid = cid;
		}
		public String getCname() {
			return cname;
		}
		public void setCname(String cname) {
			this.cname = cname;
		}
		
}
