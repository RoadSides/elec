package cn.itcast.bookstore.cart.domain;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {
	private Map<String,CartItem> map=new LinkedHashMap<String, CartItem>();
	
	public void add(CartItem cartitem){
		if(map.containsKey(cartitem.getBook().getBid())){
			CartItem _cartitem=map.get(cartitem.getBook().getBid());
			_cartitem.setCount(_cartitem.getCount()+cartitem.getCount());
			map.put(_cartitem.getBook().getBid(), _cartitem);
		}else{
			map.put(cartitem.getBook().getBid(), cartitem);
		}
	}
	
	public void clear(){
		map.clear();
	}
	
	public void delete(String bid){
		map.remove(bid);
	}
	
	public double getTotal(){
		BigDecimal total=new BigDecimal("0");
		for(CartItem cartitem:map.values()){
			BigDecimal subtotal=new BigDecimal(cartitem.getSubtotal()+"");
			total=total.add(subtotal);	
		}
		return total.doubleValue();
	}
	
	public Collection<CartItem> getcartitems(){
		return map.values();
	}
}
