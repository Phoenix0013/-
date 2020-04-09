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
	 * 根据赛事id查询当前比赛的评论
	 */
	@Override
	public List<Message> queryMessageById(int schedule_id) {
		return messageMapper.queryMessageById(schedule_id);
	}
	/*
	 * 用户发表评论的方法
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
	 * 查询评论总数的方法
	 */
	@Override
	public int totalMessage(int schedule_id) {
		return messageMapper.totalMessage(schedule_id);
	}

}
