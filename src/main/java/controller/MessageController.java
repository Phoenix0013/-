package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pojo.Message;
import pojo.User;
import service.MessageService;

@Controller
@RequestMapping("message")
public class MessageController {
	@Autowired
	private MessageService messageService;

	/*
	 * 根据赛事id查询当前比赛的评论
	 */
	@ResponseBody
	@RequestMapping("queryMessageById")
	public List<Message> queryMessageById(int schedule_id, HttpServletRequest request) {
		List<Message> messageList = messageService.queryMessageById(schedule_id);
		request.getSession().setAttribute("messageList", messageList);
		return messageList;
	}

	/*
	 * 用户发表评论
	 */
	@RequestMapping("publicMessage")
	public void publicMessage(HttpServletRequest request, int schedule_id, String messgae_context,HttpServletResponse response) throws IOException {
		User attribute = (User) request.getSession().getAttribute("user");
		int user_id = attribute.getUser_id();
		String user_phone = attribute.getUser_phone();
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String message_date = dateFormat.format(date);
		Message message = new Message();
		message.setUser_id(user_id);
		message.setSchedule_id(schedule_id);
		message.setUser_phone(user_phone);
		message.setMessgae_context(messgae_context);
		message.setMessage_date(message_date);
		boolean flag=messageService.publicMessage(message);
		if(flag) {
			response.getWriter().print(1);
		}else {
			response.getWriter().print(2);	
		}
	}
	/*
	 * 查询评论数量的方法
	 */
	@ResponseBody
	@RequestMapping("totalMessage")
	public int totalMessage(int schedule_id) {
		int total=messageService.totalMessage(schedule_id);
		return total;
	}
}
