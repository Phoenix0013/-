package service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import mapper.PorderMapper;
import pojo.Porder;
import service.PorderService;

@Service
@Transactional
public class PorderServiceImpl implements PorderService {
	@Resource
	private PorderMapper orderMapper;

	/*
	 * 用户创建订单的方法
	 */
	@Override
	public boolean createOrder(Porder porder) {
		if (orderMapper.createOrder(porder)) {
			return true;
		} else {
			return false;
		}

	}

	/*
	 * 根据用户电话查询该用户的所有订单
	 */
	@Override
	public List<Porder> selectByUserPhone(String user_phone) {
		return orderMapper.selectByUserPhone(user_phone);
	}

	/*
	 * 取消订单的方法
	 */
	@Override
	public boolean deleteOrder(Integer order_id) {
		if (orderMapper.deleteOrder(order_id)) {
			return true;
		} else {
			return false;
		}

	}
    /*
     * 修改订单状态的方法
     */
	@Override
	public void updateState(String user_phone) {
		orderMapper.updateState(user_phone);
		
	}

}
