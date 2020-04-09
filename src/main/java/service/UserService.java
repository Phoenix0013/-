package service;

import java.util.List;

import pojo.Member;
import pojo.User;

public interface UserService{
	/*
	 * 根据电话查询用户是否存在的方法
	 */
	User findUserByPhone(String user_phone);

	/*
	 * 根据密码查询用户是否存在的方法
	 */
	User findUserByPassword(String user_password);

	/*
	 * 用户注册的方法
	 */
	boolean userRegist(User user);

	/*
	 * 查询所有用户的方法
	 */
	List<User> queryAll();
    /*
     * 更新用户积分的方法
     */
	boolean updatePoints(String pointss, String user_phone);
    /*
     * 用户升级会员的方法
     */
	void updateMember(Member member);
    /*
     * 查询用户头像的方法
     */
	Member userPhoto(String user_phone);

}
