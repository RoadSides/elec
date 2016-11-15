package cn.itcast.shop.cart.vo;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {
		//购物项集合
		private Map<Integer,CartItem> map=new LinkedHashMap<Integer, CartItem>();
		//购物总计
		private double total;
		//购物车的功能
		//1.将购物项添加到购物车
		public void addCart(CartItem cartItem){
				Integer pid=cartItem.getProduct().getPid();
				//判断购物车中是否已经存在该商品了
				if(map.containsKey(pid)){
					CartItem _cartItem=map.get(pid);
					_cartItem.setCount(_cartItem.getCount()+cartItem.getCount());
				}else{
					map.put(pid, cartItem);
				}
				total+=cartItem.getSubtotal();
		}
		
		//cart对象中有一个叫cartItems属性
		public Collection<CartItem> getCartItems(){
			return map.values();
		}
		
		//2.从购物车移除购物项
		public void removeCart(Integer pid){
				CartItem cartItem=map.remove(pid);
				total=total-cartItem.getSubtotal();
		}
		
		//3.清空购物车
		public void clearCart(){
				map.clear();
				total=0;
		}

		public double getTotal() {
			return total;
		}  
		
		
}
