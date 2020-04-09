package service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import mapper.ScheduleMapper;
import pojo.Schedule;
import service.ScheduleService;
@Service
@Transactional
public class ScheduleServiceImpl implements ScheduleService{
	@Resource
	private ScheduleMapper scheduleMapper ;
    /*
     * 查询西甲联赛赛程
     */
	@Override
	public List<Schedule> queryLaliga() {
		return scheduleMapper.queryLaliga();
	}
	/*
	 * 根据赛程id查询
	 */
	@Override
	public Schedule queryShceduleById(Integer schedule_id) {
		return scheduleMapper.queryShceduleById(schedule_id);
	}

}
