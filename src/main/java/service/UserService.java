package service;

import java.util.List;

import pojo.Member;
import pojo.User;

public interface UserService{
	/*
	 * ���ݵ绰��ѯ�û��Ƿ���ڵķ���
	 */
	User findUserByPhone(String user_phone);

	/*
	 * ���������ѯ�û��Ƿ���ڵķ���
	 */
	User findUserByPassword(String user_password);

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
	boolean updatePoints(String pointss, String user_phone);
    /*
     * �û�������Ա�ķ���
     */
	void updateMember(Member member);
    /*
     * ��ѯ�û�ͷ��ķ���
     */
	Member userPhoto(String user_phone);

}
