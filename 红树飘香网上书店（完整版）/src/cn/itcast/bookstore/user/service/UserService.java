package cn.itcast.bookstore.user.service;

import cn.itcast.bookstore.user.dao.UserDao;
import cn.itcast.bookstore.user.domain.User;

public class UserService {
	private UserDao ud=new UserDao();
	
	public void regist(User form) throws UserException{
		
		User user_username=ud.findByUserName(form.getUsername());
		if(user_username!=null){
			throw new UserException("对不起，用户名已被注册");
		}
		
		User user_email=ud.findByUserEmail(form.getEmail());
		if(user_email!=null){
			throw new UserException("对不起，邮箱已被注册");
		}
		
		ud.add(form);
		
	}
	
	
	public void active(String code) throws UserException{
		
		User user=ud.findByUserCode(code);
		
		if(user==null) throw new UserException("用户不存在");
		
		if(user.isState()) throw new UserException("用户已经激活过了");
		
		ud.updateState(user.getEmail(), true);
	}
	
	public User login(User form) throws UserException{
		User user=ud.findByUserName(form.getUsername());
		if(user==null) throw new UserException("用户不存在");
		if(!user.getPassword().equals(form.getPassword()))  throw new UserException("密码不正确");
		if(!user.isState()) throw new UserException("用户未激活");
		return user;
	}
	
	
}
