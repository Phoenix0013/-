package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import pojo.Porder;
import pojo.User;
import service.PorderService;

@Controller
@RequestMapping("ord")
public class OrderController {
	@Autowired
	private PorderService orderService;

	/*
	 * 创建订单的方法
	 */
	@RequestMapping("create")
	public void createOrder(String win_team, String order_money, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Porder porder = new Porder();
		User user = (User) request.getSession().getAttribute("user");
		String user_phone = user.getUser_phone();
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String order_time = dateFormat.format(date);
		porder.setUser_phone(user_phone);
		porder.setOrder_money(order_money);
		porder.setWin_team(win_team);
		porder.setOrder_time(order_time);
		porder.setOrder_state("未公开");
		if (orderService.createOrder(porder)) {
			response.getWriter().print(1);
		} else {
			response.getWriter().print(2);
		}
	}

	/*
	 * 查看对应用户的订单
	 */
	@RequestMapping("selectByUserPhone")
	public void selectByUserPhone(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		String user_phone = user.getUser_phone();
		List<Porder> porders = orderService.selectByUserPhone(user_phone);
		request.setAttribute("porders", porders);
		request.getRequestDispatcher("/jsp/orderList.jsp").forward(request, response);
	}

	/*
	 * 取消订单的方法
	 */
	@RequestMapping("deleteOrder")
	public void deleteOrder(Integer order_id, HttpServletResponse response) throws IOException {
		if (orderService.deleteOrder(order_id)) {
			response.getWriter().print(1);
		} else {
			response.getWriter().print(2);
		}
	}

	/*
	 * 修改订单的支付状态
	 */
	@RequestMapping("updateState")
	public String updateState(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		String user_phone = user.getUser_phone();
		orderService.updateState(user_phone);
		return "/ord/selectByUserPhone.action";
	}
}
