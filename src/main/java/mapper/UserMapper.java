package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import pojo.Member;
import pojo.User;

public interface UserMapper {
	/*
	 * ���ݵ绰��ѯ�û��Ƿ����
	 */
	User findUserByPhone(@Param("user_phone") String user_phone);

	/*
	 * ���������ѯ�û��Ƿ����
	 */
	User findUserByPassword(@Param("user_password") String user_password);

	/*
	 * �û�ע��ķ���
	 */
	boolean userRegist(User user);

	/*
	 * ��ѯ�����û��ķ���
	 */
	List<User> queryAll();

	/*
	 * �����û����ֵķ���
	 */
	boolean updatePoints(@Param("user_points") String pointss, @Param("user_phone") String user_phone);

	/*
	 * �û�������Ա�ķ���
	 */
	void updateMember(Member member);
    /*
     * ��ѯ�û�ͷ��ķ���
     */
	Member userPhoto(String user_phone);

}
