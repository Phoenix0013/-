package service;

import java.util.List;
import java.util.Map;

import pojo.Player;

public interface PlayerService {
	/*
	 * 查询所有球员的方法
	 */
	List<Player> queryAllPlayer();

	/*
	 * 删除球员的方法
	 */
	boolean deletePlayer(int player_id);

	/*
	 * 插入球员的方法
	 */
	boolean insertPlayer(Player player);

	/*
	 * 修改球员信息的方法
	 */
	boolean updatePlayer(Player player);

	/*
	 * 模糊查询球员的方法
	 */
	List<Player> dimQuery(String player_Ename);

	/*
	 * 批量删除球员的方法
	 */
	boolean deleteAll(Integer[] id);

	/*
	 * 根据id查询球员的方法
	 */
	Player queryPlayerById(Integer id);

	/*
	 * 绘制图表的方法，返回map集合
	 */
	List<Map<String, Object>> chart();
    /*
     * 查询所有球队的方法
     */
	List<Map<String,Object>> queryByTeam();
    /*
     * 根据球队查询球员的方法
     */
	List<Player> queryPlayerByTeam(String player_effectiveTeam);


}
