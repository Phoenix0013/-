package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import pojo.Member;
import pojo.User;

public interface UserMapper {
	/*
	 * 根据电话查询用户是否存在
	 */
	User findUserByPhone(@Param("user_phone") String user_phone);

	/*
	 * 根据密码查询用户是否存在
	 */
	User findUserByPassword(@Param("user_password") String user_password);

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
	boolean updatePoints(@Param("user_points") String pointss, @Param("user_phone") String user_phone);

	/*
	 * 用户升级会员的方法
	 */
	void updateMember(Member member);
    /*
     * 查询用户头像的方法
     */
	Member userPhoto(String user_phone);

}
