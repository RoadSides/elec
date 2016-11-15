package cn.itcast.bookstore.book.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.itcast.bookstore.book.domain.Book;
import cn.itcast.bookstore.category.domain.Category;
import cn.itcast.commons.CommonUtils;
import cn.itcast.jdbc.TxQueryRunner;

public class BookDao {
	
	private QueryRunner qr=new TxQueryRunner();
	public List<Book> findAll(){
		try {
			String sql="select * from book where del=false";	
			return qr.query(sql, new BeanListHandler<Book>(Book.class));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Book> findByCategory(String cid) {
		String sql="select * from book where cid=? and del=false";
		try {
			return qr.query(sql, new BeanListHandler<Book>(Book.class),cid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Book load(String bid) {
		String sql="select * from book where bid=?";
		
		try {
			Map<String,Object> map= qr.query(sql,new MapHandler(),bid);
			Book book=CommonUtils.toBean(map, Book.class);
			Category category=CommonUtils.toBean(map, Category.class);
			book.setCategory(category);
			return book;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public int getCountByCid(String cid) {
		String sql="select count(*) from book where cid=?";
		try {
			Number number=(Number) qr.query(sql, new ScalarHandler(), cid);
			return number.intValue();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void add(Book book) {
		String sql="insert into book values(?,?,?,?,?,?)";
		
		try {
			qr.update(sql,book.getBid(),book.getBname(),book.getPrice(),book.getAuthor(),book.getImage(),book.getCategory().getCid());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void delete(String bid){
		String sql="update book set del=true where bid=?";
		try {
			qr.update(sql,bid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void edit(Book book) {
		String sql="update book set bname=?,price=?,author=?,image=?,cid=?  where bid=?";
		Object[] object={book.getBname(),book.getPrice(),book.getAuthor(),book.getImage(),book.getCategory().getCid(),book.getBid()};
		try {
			qr.update(sql, object);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
}
