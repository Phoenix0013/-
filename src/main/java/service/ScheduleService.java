package service;

import java.util.List;

import pojo.Schedule;

public interface ScheduleService {
    /*
     * ��ѯ������������
     */
	List<Schedule> queryLaliga();
    /*
     * ����id��ѯ���µķ���
     */
	Schedule queryShceduleById(Integer schedule_id);

}
