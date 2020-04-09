package controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import pojo.Player;
import pojo.User;
import service.PlayerService;

@Controller
@RequestMapping("player")
public class PlayerController {
	@Autowired
	private PlayerService playerService;

	/*
	 * 查询所有球员的方法
	 */
	@RequestMapping("queryAllPlayer")
	public void queryAllPlayer(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(defaultValue = "1", required = true, value = "pageNo") Integer pageNo)
			throws ServletException, IOException {
		PageHelper.startPage(pageNo, 8);
		List<Player> player = playerService.queryAllPlayer();
		PageInfo<Player> pageInfo = new PageInfo<Player>(player);
		request.getSession().setAttribute("player", player);
		request.getSession().setAttribute("pageInfo", pageInfo);
		request.getRequestDispatcher("/jsp/playerList.jsp").forward(request, response);
	}

	/*
	 * 删除球员的方法
	 */
	@RequestMapping("deletePlayer")
	public void deletePlayer(int player_id, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		User user = (User) request.getSession(true).getAttribute("user");
		String state = user.getUser_state();
		if (state.equals("用户")) {
			return;
		} else {
			if (playerService.deletePlayer(player_id)) {
				response.getWriter().print(1);
			} else {
				response.getWriter().print(2);
			}
		}

	}

	/*
	 * 插入球员的方法
	 */
	@RequestMapping("insertPlayer")
	public void insertPlayer(Player player, HttpServletResponse response, HttpServletRequest request)
			throws IOException {
		User user = (User) request.getSession(true).getAttribute("user");
		String state = user.getUser_state();
		if (state.equals("用户")) {
			return;
		} else {
			if (playerService.insertPlayer(player)) {
				response.getWriter().print(1);
			} else {
				response.getWriter().print(2);
			}
		}
	}

	/*
	 * 修改球员的方法
	 */
	@RequestMapping("updatePlayer")
	public void updatePlayer(Player player, HttpServletResponse response, HttpServletRequest request)
			throws IOException {
		User user = (User) request.getSession(true).getAttribute("user");
		String state = user.getUser_state();
		if (state.equals("用户")) {
			return;
		} else {
			if (playerService.updatePlayer(player)) {
				response.getWriter().print(1);
			} else {
				response.getWriter().print(2);
			}
		}

	}

	/*
	 * 模糊查询球员AJAX的方法
	 */
	@RequestMapping("dimQueryPlayer")
	public void dimQuery(String player_Ename, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Player> dimList = playerService.dimQuery(player_Ename);
		request.getSession().setAttribute("dimList", dimList);
		response.setContentType("text/html;charset=utf-8");
		request.getRequestDispatcher("/jsp/searchList.jsp").forward(request, response);
	}

	/*
	 * 批量删除球员的方法
	 */
	@RequestMapping("deleteAll")
	public void deleteAll(Integer[] id, HttpServletResponse response, HttpServletRequest request) throws IOException {
		User user = (User) request.getSession(true).getAttribute("user");
		String state = user.getUser_state();
		if (state.equals("用户")) {
			return;
		} else {
			if (playerService.deleteAll(id)) {
				response.getWriter().print(1);
			} else {
				response.getWriter().print(2);
			}
		}
	}

	/*
	 * 根据球员id查询球员的方法
	 */
	@RequestMapping("queryPlayerById")
	public void queryPlayerById(Integer id, HttpServletRequest request) {
		System.out.println(id);
		Player player = playerService.queryPlayerById(id);
		request.getSession().setAttribute("player", player);
	}

	/*
	 * 绘制图表的方法，返回map集合数据
	 */
	@RequestMapping("chart")
	@ResponseBody
	public List<Map<String, Object>> chart() {
		List<Map<String, Object>> list = playerService.chart();
		return list;
	}

	/*
	 * 导出全部球员excel的方法
	 */
	@RequestMapping("outExcel")
	public void outExcel(String name, HttpServletRequest request, HttpServletResponse response) throws IOException {
		String path = name + ".xlsx";
		List<Player> player = playerService.queryAllPlayer();
//		创建excel文件
		XSSFWorkbook workbook = new XSSFWorkbook();
//		在工作薄中创建一个sheet
		XSSFSheet sheet = workbook.createSheet();
		XSSFCell cell = null;
		// 创建第一行
		XSSFRow row = sheet.createRow(0);
		String title[] = { "球员ID", "球员英文名", "球员中文名", "进球数", "助攻数", "拦截数", "球衣号码", "效力球队" };
		for (int i = 0; i < title.length; i++) {
			// 创建一行的一格
			cell = row.createCell(i);
			// 赋值
			cell.setCellValue(title[i]);
		}
		for (Player p : player) {
			XSSFRow nextrow = sheet.createRow(sheet.getLastRowNum() + 1);
			nextrow.createCell(0).setCellValue(p.getPlayer_id());
			nextrow.createCell(1).setCellValue(p.getPlayer_Ename());
			nextrow.createCell(2).setCellValue(p.getPlayer_Cname());
			nextrow.createCell(3).setCellValue(p.getPlayer_goal());
			nextrow.createCell(4).setCellValue(p.getPlayer_assists());
			nextrow.createCell(5).setCellValue(p.getPlayer_interceptions());
			nextrow.createCell(6).setCellValue(p.getPlayer_number());
			nextrow.createCell(7).setCellValue(p.getPlayer_effectiveTeam());
		}
		response.setContentType("application/upload/; charset=utf-8");// 自定义路径
		response.setHeader("Content-disposition", "attachment;filename=" + new String((path).getBytes(), "iso-8859-1"));
		OutputStream ouputStream = null;
		ouputStream = response.getOutputStream();
		workbook.write(ouputStream);
		ouputStream.flush();
		ouputStream.close();
		workbook.close();
	}

	/*
	 * 导入球员Excel的方法
	 */
	@RequestMapping("inExcel")
	public String inExcel(MultipartFile file) throws IOException {
		List<Player> playerList = new ArrayList<Player>();
		// xlsx和xls的区别在于xlsx使用的是XSSFWorkbook
		// 建立Excel对象
		XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
		XSSFSheet sheet = workbook.getSheetAt(0);
		int lastNum = sheet.getLastRowNum();
		for (int i = 1; i <= lastNum; i++) {
			XSSFRow row = sheet.getRow(i);
			short cellNum = row.getLastCellNum();
			if (row != null) {
				List<String> list = new ArrayList<String>();
				for (int j = 1; j <= cellNum; j++) {
					XSSFCell cell = row.getCell(j);
					if (cell != null) {
						cell.setCellType(CellType.STRING);
						String value = cell.getStringCellValue();
						list.add(value);
					}
				}
				if (list.size() > 0) {

					Player player = new Player(list.get(0), list.get(1), list.get(2), list.get(3), list.get(4),
							list.get(5), list.get(6));
					playerList.add(player);
					for (Player pl : playerList) {
						playerService.insertPlayer(pl);
					}
				}
			}
		}
		workbook.close();
		return "/player/queryAllPlayer.action";
	}

	/*
	 * 查询所有球队的方法
	 */
	@ResponseBody
	@RequestMapping("queryByTeam")
	public List<Map<String, Object>> queryByTeam(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Map<String, Object>> p = playerService.queryByTeam();
		return p;
	}

	/*
	 * 根据球队查询球员的方法
	 */
	@ResponseBody
	@RequestMapping("queryPlayerByTeam")
	public List<Player> queryPlayerByTeam(String player_effectiveTeam, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<Player> byTeamList = playerService.queryPlayerByTeam(player_effectiveTeam);
		request.getSession().setAttribute("byTeamList", byTeamList);
		return byTeamList;
	}

	/*
	 * 分类导出球员的方法
	 */
	@RequestMapping("outPartExcel")
	public void outPartExcel(String name, HttpServletRequest request, HttpServletResponse response) throws Exception {
		@SuppressWarnings("unchecked")
		List<Player> attribute = (List<Player>) request.getSession().getAttribute("byTeamList");
		String path = name + ".xlsx";
//		创建excel文件
		XSSFWorkbook workbook = new XSSFWorkbook();
//		在工作薄中创建一个sheet
		XSSFSheet sheet = workbook.createSheet();
		XSSFCell cell = null;
		// 创建第一行
		XSSFRow row = sheet.createRow(0);
		String title[] = { "球员ID", "球员英文名", "球员中文名", "进球数", "助攻数", "拦截数", "球衣号码", "效力球队" };
		for (int i = 0; i < title.length; i++) {
			// 创建一行的一格
			cell = row.createCell(i);
			// 赋值
			cell.setCellValue(title[i]);
		}
		for (Player p : attribute) {
			XSSFRow nextrow = sheet.createRow(sheet.getLastRowNum() + 1);
			nextrow.createCell(0).setCellValue(p.getPlayer_id());
			nextrow.createCell(1).setCellValue(p.getPlayer_Ename());
			nextrow.createCell(2).setCellValue(p.getPlayer_Cname());
			nextrow.createCell(3).setCellValue(p.getPlayer_goal());
			nextrow.createCell(4).setCellValue(p.getPlayer_assists());
			nextrow.createCell(5).setCellValue(p.getPlayer_interceptions());
			nextrow.createCell(6).setCellValue(p.getPlayer_number());
			nextrow.createCell(7).setCellValue(p.getPlayer_effectiveTeam());
		}
		response.setContentType("application/upload/; charset=utf-8");// 自定义路径
		response.setHeader("Content-disposition", "attachment;filename=" + new String((path).getBytes(), "iso-8859-1"));
		OutputStream ouputStream = null;
		ouputStream = response.getOutputStream();
		workbook.write(ouputStream);
		ouputStream.flush();
		ouputStream.close();
		workbook.close();
	}
}
