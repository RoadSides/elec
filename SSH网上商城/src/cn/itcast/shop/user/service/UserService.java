package cn.itcast.shop.user.service;

import java.io.UnsupportedEncodingException;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.shop.user.dao.UserDao;
import cn.itcast.shop.user.vo.User;
import cn.itcast.shop.utils.UUIDUtils;

@Transactional
public class UserService {
		private UserDao userDao;

		public UserDao getUserDao() {
			return userDao;
		}

		public void setUserDao(UserDao userDao) {
			this.userDao = userDao;
		}
		
		public User findByUsername(String username) throws UnsupportedEncodingException{
			
			return userDao.findByUsername(username);
		}

		public void save(User user) {
				user.setState(1);//0代表未激活，1代表已经激活
				user.setCode(UUIDUtils.getUUID()+UUIDUtils.getUUID());
				userDao.save(user);
		}

		public User login(User user) {
				return 	userDao.login(user);
		}


}
