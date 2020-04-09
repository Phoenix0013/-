package mapper;

import java.util.List;

import pojo.Message;

public interface MessageMapper {
	/*
	 * 根据赛事id查询当前比赛的评论
	 */
	List<Message> queryMessageById(int schedule_id);
    /*
     * 用户发表评论
     */
	boolean publicMessage(Message message);
	/*
	 * 查询评论总数的方法
	 */
	int totalMessage(int schedule_id);

}
