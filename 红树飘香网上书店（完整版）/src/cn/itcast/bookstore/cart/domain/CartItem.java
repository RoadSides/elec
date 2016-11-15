package cn.itcast.bookstore.cart.domain;

import java.math.BigDecimal;

import cn.itcast.bookstore.book.domain.Book;

public class CartItem {

	private Book book;
	private int count;
	
	public CartItem(Book book, int count) {
		super();
		this.book = book;
		this.count = count;
	}
	
	
	public CartItem() {
		super();
	}
	
	public double getSubtotal(){
		BigDecimal price=new BigDecimal(book.getPrice()+"");
		BigDecimal _count=new BigDecimal(count+"");
		return price.multiply(_count).doubleValue();
	}
	
	public String toString() {
		return "CartItem [book=" + book + ", count=" + count + "]";
	}
	
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}
