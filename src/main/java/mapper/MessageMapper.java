package mapper;

import java.util.List;

import pojo.Message;

public interface MessageMapper {
	/*
	 * ��������id��ѯ��ǰ����������
	 */
	List<Message> queryMessageById(int schedule_id);
    /*
     * �û���������
     */
	boolean publicMessage(Message message);
	/*
	 * ��ѯ���������ķ���
	 */
	int totalMessage(int schedule_id);

}
