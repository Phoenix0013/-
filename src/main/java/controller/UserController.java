package controller;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.UUID;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pojo.Member;
import pojo.User;
import service.UserService;

@Controller
@RequestMapping("user")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private JavaMailSenderImpl mailSender;

	/*
	 * 根据电话使用ajax查询用户是否存在的方法
	 */
	@RequestMapping("findUserByPhone")
	public void findUserByPhone(String user_phone, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = userService.findUserByPhone(user_phone);
		request.getSession().setAttribute("user", user);
		if (user != null) {
			response.getWriter().println(1); // 成功返回1
		} else {
			response.getWriter().println(2); // 失败返回2
		}
	}

	/*
	 * 根据密码使用ajax查询用户是否存在的方法
	 */
	@RequestMapping("findUserByPassword")
	public void findUserByPassword(String user_password, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		User user = userService.findUserByPassword(user_password);
		if (user != null) {
			response.getWriter().println(1); // 成功返回1
		} else {
			response.getWriter().println(2); // 失败返回2
		}
	}

	/*
	 * 用户登录的方法
	 */
	@RequestMapping("login")
	public String login(String user_phone, String user_password, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		User byPhone = userService.findUserByPhone(user_phone);
		User byPassword = userService.findUserByPassword(user_password);
//		获取服务器端的验证码
		String code1 = (String) request.getSession().getAttribute("code");
//		获取客户端输入的验证码
		String code2 = request.getParameter("code");
		if (code1.equalsIgnoreCase(code2)) {
			if (byPhone != null && byPassword != null) {
				// request.getRequestDispatcher("/jsp/index.jsp").forward(request, response);
				return "/user/userPhoto.action";
			}
		} else {
			request.setAttribute("msg", "验证码输入错误");
			// request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
			return "/jsp/login.jsp";
		}
		return "";
	}

	/*
	 * 用户注册的方法
	 */
	@RequestMapping("userRegist")
	public void userRegist(String user_phone, String user_password, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		User user = new User();
		user.setUser_phone(user_phone);
		user.setUser_password(user_password);
		user.setUser_state("用户");
		user.setUser_points("3000");
		// userService.userRegist(user);
		if (userService.userRegist(user)) {
			response.getWriter().print(1);
		} else {
			response.getWriter().print(1);
		}
	}

	/*
	 * 用户退出系统的方法
	 */
	@RequestMapping("logout")
	public void loout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate();
		request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
	}

	/*
	 * 用户结算后，修改用户的余额
	 */
	@RequestMapping("updatePoints")
	public void updatePoints(String user_points, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		User user = (User) request.getSession().getAttribute("user");
		// 用户数据库中的金额
		String points = user.getUser_points();
		String user_phone = user.getUser_phone();
		BigDecimal bigDecimal1 = new BigDecimal(points);
		BigDecimal bigDecimal2 = new BigDecimal(user_points);
		BigDecimal remain = bigDecimal1.subtract(bigDecimal2);
		int r = remain.compareTo(BigDecimal.ZERO);
		if (r == 0 || r == -1) {
			response.getWriter().print(1);
		} else {
			String pointss = bigDecimal1.subtract(bigDecimal2).toString();
			if (userService.updatePoints(pointss, user_phone)) {
				response.getWriter().print(2);
			} else {
				response.getWriter().print(1);
			}

		}
	}

	/*
	 * 用户升级会员的方法
	 */
	@RequestMapping("updateMember")
	public String updateMember(Member member, Model model, HttpServletRequest request)
			throws IllegalStateException, IOException {
		String id = (String) request.getSession().getAttribute("emailId");
		User user = (User) request.getSession().getAttribute("user");
		String user_phone = user.getUser_phone();
		String user_password = user.getUser_password();
//		获得用户输入的激活码
		String email = member.getEmail();
//		保存到数据库的路径
		String sqlPath = null;
//		保存到本地路径
		String localPath = "D:\\upload\\";
//		文件名称
		String fileName = null;
		if (!member.getFile().isEmpty()) {
//	        使用UUID作为文件名
			String uuid = UUID.randomUUID().toString();
			String contextType = member.getFile().getContentType();
//      获得文件的后缀名
			String suffixName = contextType.substring(contextType.indexOf("/") + 1);
//      得到的文件名称
			fileName = uuid + "." + suffixName;
//      文件的保存路径
			member.getFile().transferTo(new File(localPath + fileName));
		}
//			把图片的相对路径保存到数据库
		sqlPath = "/upload/" + fileName;
		member.setUser_phone(user_phone);
		member.setUser_password(user_password);
		member.setImg(sqlPath);
//			插入到数据库
		if (!id.equals(email)) {
			userService.updateMember(member);
			return "/jsp/member.jsp";
		} else {
			model.addAttribute("errorMail", "输入的激活码正确，请保证输入正确的激活码！");
			return "/jsp/member.jsp";
		}
	}

//	查询用户头像
	@RequestMapping("userPhoto")
	public String userPhoto(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		String user_phone = user.getUser_phone();
		Member member = userService.userPhoto(user_phone);
		request.getSession().setAttribute("member", member);
		return "/jsp/index.jsp";
	}

//   发送邮件的方法
	@RequestMapping("sendMail")
	public void te(String email, HttpServletRequest request) {
		String emailId = UUID.randomUUID().toString();
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		try {
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
			messageHelper.setFrom("2065922069@qq.com");// 发件人
			messageHelper.setTo(email);
			messageHelper.setSubject("欢迎使用鹏鹏足球网站");
			messageHelper.setText("欢迎来到鹏鹏足球网！仅会员注册使用，切勿回复！您的激活码是：" + emailId + "复制到知道区域即可，请勿将激活码告知他人！");// true代表支持html格式
			mailSender.send(mimeMessage);
			request.getSession().setAttribute("emailId", emailId);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
}
