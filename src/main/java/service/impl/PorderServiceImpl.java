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
	 * �û����������ķ���
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
	 * �����û��绰��ѯ���û������ж���
	 */
	@Override
	public List<Porder> selectByUserPhone(String user_phone) {
		return orderMapper.selectByUserPhone(user_phone);
	}

	/*
	 * ȡ�������ķ���
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
     * �޸Ķ���״̬�ķ���
     */
	@Override
	public void updateState(String user_phone) {
		orderMapper.updateState(user_phone);
		
	}

}
