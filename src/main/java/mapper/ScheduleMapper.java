package mapper;

import java.util.List;

import pojo.Schedule;

public interface ScheduleMapper {
	/*
	 * 查询所有赛事的方法
	 */
	List<Schedule> queryLaliga();

	/*
	 * 根据id查询赛事的方法
	 */
	Schedule queryShceduleById(Integer schedule_id);

}
