package cn.itcast.shop.user.dao;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.itcast.shop.user.vo.User;

public class UserDao extends HibernateDaoSupport{
		public User findByUsername(String username) throws UnsupportedEncodingException{
			username=new String(username.getBytes("iso8859-1"),"UTF-8");
			System.out.println(username);
			String hql="from User u where u.username = ?";
			List<User> list=this.getHibernateTemplate().find(hql,username);
			if(list!=null&&list.size()>0){
				return list.get(0);
			}else{
				return null;
			}
		}

		public void save(User user) {
			 this.getHibernateTemplate().save(user);
		}

		public User login(User user) {
			String hql="from User u where u.username=? and u.password=? and u.state=1";
			List<User> list=this.getHibernateTemplate().find(hql,user.getUsername(),user.getPassword());
			if(list!=null&&list.size()>0){ 
				return list.get(0);
			}
			return null;
		}
}
