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
	 * ���ݵ绰ʹ��ajax��ѯ�û��Ƿ���ڵķ���
	 */
	@RequestMapping("findUserByPhone")
	public void findUserByPhone(String user_phone, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = userService.findUserByPhone(user_phone);
		request.getSession().setAttribute("user", user);
		if (user != null) {
			response.getWriter().println(1); // �ɹ�����1
		} else {
			response.getWriter().println(2); // ʧ�ܷ���2
		}
	}

	/*
	 * ��������ʹ��ajax��ѯ�û��Ƿ���ڵķ���
	 */
	@RequestMapping("findUserByPassword")
	public void findUserByPassword(String user_password, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		User user = userService.findUserByPassword(user_password);
		if (user != null) {
			response.getWriter().println(1); // �ɹ�����1
		} else {
			response.getWriter().println(2); // ʧ�ܷ���2
		}
	}

	/*
	 * �û���¼�ķ���
	 */
	@RequestMapping("login")
	public String login(String user_phone, String user_password, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		User byPhone = userService.findUserByPhone(user_phone);
		User byPassword = userService.findUserByPassword(user_password);
//		��ȡ�������˵���֤��
		String code1 = (String) request.getSession().getAttribute("code");
//		��ȡ�ͻ����������֤��
		String code2 = request.getParameter("code");
		if (code1.equalsIgnoreCase(code2)) {
			if (byPhone != null && byPassword != null) {
				// request.getRequestDispatcher("/jsp/index.jsp").forward(request, response);
				return "/user/userPhoto.action";
			}
		} else {
			request.setAttribute("msg", "��֤���������");
			// request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
			return "/jsp/login.jsp";
		}
		return "";
	}

	/*
	 * �û�ע��ķ���
	 */
	@RequestMapping("userRegist")
	public void userRegist(String user_phone, String user_password, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		User user = new User();
		user.setUser_phone(user_phone);
		user.setUser_password(user_password);
		user.setUser_state("�û�");
		user.setUser_points("3000");
		// userService.userRegist(user);
		if (userService.userRegist(user)) {
			response.getWriter().print(1);
		} else {
			response.getWriter().print(1);
		}
	}

	/*
	 * �û��˳�ϵͳ�ķ���
	 */
	@RequestMapping("logout")
	public void loout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate();
		request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
	}

	/*
	 * �û�������޸��û������
	 */
	@RequestMapping("updatePoints")
	public void updatePoints(String user_points, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		User user = (User) request.getSession().getAttribute("user");
		// �û����ݿ��еĽ��
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
	 * �û�������Ա�ķ���
	 */
	@RequestMapping("updateMember")
	public String updateMember(Member member, Model model, HttpServletRequest request)
			throws IllegalStateException, IOException {
		String id = (String) request.getSession().getAttribute("emailId");
		User user = (User) request.getSession().getAttribute("user");
		String user_phone = user.getUser_phone();
		String user_password = user.getUser_password();
//		����û�����ļ�����
		String email = member.getEmail();
//		���浽���ݿ��·��
		String sqlPath = null;
//		���浽����·��
		String localPath = "D:\\upload\\";
//		�ļ�����
		String fileName = null;
		if (!member.getFile().isEmpty()) {
//	        ʹ��UUID��Ϊ�ļ���
			String uuid = UUID.randomUUID().toString();
			String contextType = member.getFile().getContentType();
//      ����ļ��ĺ�׺��
			String suffixName = contextType.substring(contextType.indexOf("/") + 1);
//      �õ����ļ�����
			fileName = uuid + "." + suffixName;
//      �ļ��ı���·��
			member.getFile().transferTo(new File(localPath + fileName));
		}
//			��ͼƬ�����·�����浽���ݿ�
		sqlPath = "/upload/" + fileName;
		member.setUser_phone(user_phone);
		member.setUser_password(user_password);
		member.setImg(sqlPath);
//			���뵽���ݿ�
		if (!id.equals(email)) {
			userService.updateMember(member);
			return "/jsp/member.jsp";
		} else {
			model.addAttribute("errorMail", "����ļ�������ȷ���뱣֤������ȷ�ļ����룡");
			return "/jsp/member.jsp";
		}
	}

//	��ѯ�û�ͷ��
	@RequestMapping("userPhoto")
	public String userPhoto(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		String user_phone = user.getUser_phone();
		Member member = userService.userPhoto(user_phone);
		request.getSession().setAttribute("member", member);
		return "/jsp/index.jsp";
	}

//   �����ʼ��ķ���
	@RequestMapping("sendMail")
	public void te(String email, HttpServletRequest request) {
		String emailId = UUID.randomUUID().toString();
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		try {
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
			messageHelper.setFrom("2065922069@qq.com");// ������
			messageHelper.setTo(email);
			messageHelper.setSubject("��ӭʹ������������վ");
			messageHelper.setText("��ӭ��������������������Աע��ʹ�ã�����ظ������ļ������ǣ�" + emailId + "���Ƶ�֪�����򼴿ɣ����𽫼������֪���ˣ�");// true����֧��html��ʽ
			mailSender.send(mimeMessage);
			request.getSession().setAttribute("emailId", emailId);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
}
