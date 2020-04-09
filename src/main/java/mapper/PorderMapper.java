package mapper;

import java.util.List;

import pojo.Porder;

public interface PorderMapper {
	/*
	 * 创建订单的方法
	 */
	boolean createOrder(Porder porder);

	/*
	 * 根据用户电话查询该用户的所有订单
	 */
	List<Porder> selectByUserPhone(String user_phone);

	/*
	 * 取消订单的方法
	 */
	boolean deleteOrder(Integer order_id);

	/*
	 * 修改订单状态
	 */
	void updateState(String user_phone);

}
