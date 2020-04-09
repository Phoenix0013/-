package service;

import java.util.List;

import pojo.Schedule;

public interface ScheduleService {
    /*
     * 查询西甲联赛赛程
     */
	List<Schedule> queryLaliga();
    /*
     * 根据id查询赛事的方法
     */
	Schedule queryShceduleById(Integer schedule_id);

}
