package cn.itcast.bookstore.user.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.itcast.bookstore.user.domain.User;
import cn.itcast.jdbc.TxQueryRunner;

public class UserDao {
	private QueryRunner qr=new TxQueryRunner();
	
	public User findByUserName(String username){
		String sql="select * from tb_user where username=?";
		try {
			return qr.query(sql, new BeanHandler<User>(User.class), username);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public User findByUserEmail(String email){
		String sql="select * from tb_user where email=?";
		try {
			return qr.query(sql, new BeanHandler<User>(User.class), email);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void add(User user){
		String sql="insert into tb_user values(?,?,?,?,?,?)";
		try {
			Object[] params={user.getUid(),user.getUsername(),user.getPassword(),user.getEmail(),user.getCode(),user.isState()};
			qr.update(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public User findByUserCode(String code){
		String sql="select * from tb_user where code=?";
		try {
			return qr.query(sql, new BeanHandler<User>(User.class), code);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void updateState(String email,boolean state){
		String sql="update tb_user set state=? where email=?";
		try {
			Object[] params={state,email};
			System.out.println("====="+state+"====="+email);
			qr.update(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	
}
