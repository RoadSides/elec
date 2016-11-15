package cn.itcast.shop.order.vo;

import cn.itcast.shop.product.vo.Product;

public class OrderItem {
		private int itemid;
		private int count;
		private double subtotal;
		private Product product;
		private Orders orders;
		
		public int getItemid() {
			return itemid;
		}
		public void setItemid(int itemid) {
			this.itemid = itemid;
		}
		public int getCount() {
			return count;
		}
		public void setCount(int count) {
			this.count = count;
		}
		public double getSubtotal() {
			return subtotal;
		}
		public void setSubtotal(double subtotal) {
			this.subtotal = subtotal;
		}
		public Product getProduct() {
			return product;
		}
		public void setProduct(Product product) {
			this.product = product;
		}
		public Orders getOrders() {
			return orders;
		}
		public void setOrders(Orders orders) {
			this.orders = orders;
		}
		
		
}
