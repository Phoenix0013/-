package mapper;

import java.util.List;

import pojo.Porder;

public interface PorderMapper {
	/*
	 * ���������ķ���
	 */
	boolean createOrder(Porder porder);

	/*
	 * �����û��绰��ѯ���û������ж���
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
