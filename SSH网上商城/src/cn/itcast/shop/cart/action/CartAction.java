package cn.itcast.shop.cart.action;

import org.apache.struts2.ServletActionContext;

import cn.itcast.shop.cart.vo.Cart;
import cn.itcast.shop.cart.vo.CartItem;
import cn.itcast.shop.product.service.ProductService;
import cn.itcast.shop.product.vo.Product;

import com.opensymphony.xwork2.ActionSupport;

public class CartAction extends ActionSupport{
		
		private int pid;
		private ProductService productService;
		private int count;
	
		
		public String myCart(){
			return "myCart";
		}
		
		public void setPid(int pid) {
			this.pid = pid;
		}
		
		public void setCount(int count) {
			this.count = count;
		}
		
		public void setProductService(ProductService productService) {
			this.productService = productService;
		}

		public String addCart() {
			
				CartItem cartItem=new CartItem();
				cartItem.setCount(count);
				Product product=productService.findById(pid);
				cartItem.setProduct(product);
				//将购物项添加到购物车
				//购物车应该存在于session中
				Cart cart=getCart();
				cart.addCart(cartItem);
				return "addCart";
		}

		private Cart getCart() {
			Cart cart=(Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");
			if(cart==null){
				cart=new Cart();
				ServletActionContext.getRequest().getSession().setAttribute("cart", cart);
			}
			return cart;
		}
		
		public String clearCart(){
			Cart cart=getCart();
			cart.clearCart();
			return "clearCart"; 
		}
		
		public String removeCart(){
			Cart cart=getCart();
			cart.removeCart(pid);
			return "removeCart";
		}
}
