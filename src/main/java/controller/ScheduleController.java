package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import pojo.Schedule;
import service.ScheduleService;

@Controller
@RequestMapping("schedule")
public class ScheduleController {
	/*
	 * 西甲联赛的赛程查询
	 */
	@Autowired
	private ScheduleService scheduleService;

	@RequestMapping("Laliga")
	public void queryLaliga(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(defaultValue = "1", required = true, value = "pageNo") Integer pageNo)
			throws ServletException, IOException {
		PageHelper.startPage(pageNo, 4);
		List<Schedule> listLaliga = scheduleService.queryLaliga();
		PageInfo<Schedule> schedule = new PageInfo<Schedule>(listLaliga);
		request.getSession().setAttribute("listLaliga", listLaliga);
		request.getSession().setAttribute("schedule", schedule);
		request.getRequestDispatcher("/jsp/schedule.jsp").forward(request, response);
	}

	/*
	 * 根据id查询赛事的方法
	 */
	@ResponseBody
	@RequestMapping("queryScheduleById")
	public Schedule queryShceduleById(Integer schedule_id, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Schedule scheduleone = scheduleService.queryShceduleById(schedule_id);
		request.getSession().setAttribute("scheduleone", scheduleone);
		return scheduleone;
	}
}
