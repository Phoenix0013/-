package service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import mapper.MessageMapper;
import pojo.Message;
import service.MessageService;

@Service
@Transactional
public class MessageServiceImpl implements MessageService{
	@Resource
	private MessageMapper messageMapper;
	/*
	 * ��������id��ѯ��ǰ����������
	 */
	@Override
	public List<Message> queryMessageById(int schedule_id) {
		return messageMapper.queryMessageById(schedule_id);
	}
	/*
	 * �û��������۵ķ���
	 */
	@Override
	public boolean publicMessage(Message message) {
        if(messageMapper.publicMessage(message)) {
        	return true;
        }else {
        	return false;
        }
		
	}
	/*
	 * ��ѯ���������ķ���
	 */
	@Override
	public int totalMessage(int schedule_id) {
		return messageMapper.totalMessage(schedule_id);
	}

}
