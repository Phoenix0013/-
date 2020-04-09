package service;

import java.util.List;

import pojo.Porder;

public interface PorderService {
	/*
	 * ���������ķ���
	 */
	boolean createOrder(Porder porder);

	/*
	 * �鿴���˻��µ����ж���
	 */
	List<Porder> selectByUserPhone(String user_phone);

	/*
	 * ȡ�������ķ���
	 */
	boolean deleteOrder(Integer order_id);

	/*
	 * �޸Ķ���״̬
	 */
	void updateState(String user_phone);

}
