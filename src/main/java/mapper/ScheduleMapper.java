package mapper;

import java.util.List;

import pojo.Schedule;

public interface ScheduleMapper {
	/*
	 * ��ѯ�������µķ���
	 */
	List<Schedule> queryLaliga();

	/*
	 * ����id��ѯ���µķ���
	 */
	Schedule queryShceduleById(Integer schedule_id);

}
