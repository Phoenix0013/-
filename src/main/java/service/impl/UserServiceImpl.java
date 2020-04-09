package service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import mapper.UserMapper;
import pojo.Member;
import pojo.User;
import service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	@Resource
	private UserMapper userMapper;

	/*
	 * 根据电话查询用户是否存在的方法
	 */
	@Override
	public User findUserByPhone(String user_phone) {
		return userMapper.findUserByPhone(user_phone);
	}

	/*
	 * 根据密码查询用户是否存在
	 */
	@Override
	public User findUserByPassword(String user_password) {
		return userMapper.findUserByPassword(user_password);
	}

	/*
	 * 用户注册的方法
	 */
	@Override
	public boolean userRegist(User user) {
		if (userMapper.userRegist(user)) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * 查询所有用户的方法
	 */
	@Override
	public List<User> queryAll() {
		return userMapper.queryAll();
	}
    /*
     * 用户支付后，修改用户表中的余额
     */
	@Override
	public boolean updatePoints(String pointss, String user_phone) {
	   if(userMapper.updatePoints(pointss,user_phone)) {
		   return true;
	   }else {
		return false;
	   }
	}
    /*
     * 升级会员的方法
     */
	@Override
	public void updateMember(Member member) {
		userMapper.updateMember(member);
		
	}

	@Override
	public Member userPhoto(String user_phone) {
		return userMapper.userPhoto(user_phone);
	}


}
