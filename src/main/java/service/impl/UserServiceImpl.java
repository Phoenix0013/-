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
	 * ���ݵ绰��ѯ�û��Ƿ���ڵķ���
	 */
	@Override
	public User findUserByPhone(String user_phone) {
		return userMapper.findUserByPhone(user_phone);
	}

	/*
	 * ���������ѯ�û��Ƿ����
	 */
	@Override
	public User findUserByPassword(String user_password) {
		return userMapper.findUserByPassword(user_password);
	}

	/*
	 * �û�ע��ķ���
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
	 * ��ѯ�����û��ķ���
	 */
	@Override
	public List<User> queryAll() {
		return userMapper.queryAll();
	}
    /*
     * �û�֧�����޸��û����е����
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
     * ������Ա�ķ���
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
