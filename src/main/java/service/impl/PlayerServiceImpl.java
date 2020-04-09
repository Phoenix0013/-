package service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import mapper.PlayerMapper;
import pojo.Player;
import service.PlayerService;

@Service
@Transactional
public class PlayerServiceImpl implements PlayerService {
	@Resource
	private PlayerMapper playerMapper;

	/*
	 * 查询所有球员的方法
	 */
	@Override
	public List<Player> queryAllPlayer() {
		// TODO Auto-generated method stub
		return playerMapper.queryAllPlayer();
	}

	/*
	 * 删除球员的方法
	 */
	@Override
	public boolean deletePlayer(int player_id) {
		// TODO Auto-generated method stub
		if (playerMapper.deletePlayer(player_id)) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * 插入球员的方法
	 */
	@Override
	public boolean insertPlayer(Player player) {
		if (playerMapper.insertPlayer(player)) {
			return true;
		} else {
			return false;
		}

	}

	/*
	 * 修改球员信息的方法
	 */
	@Override
	public boolean updatePlayer(Player player) {
		if (playerMapper.updatePlayer(player)) {
			return true;
		} else {
			return false;
		}

	}

	/*
	 * 模糊查询球员的方法
	 */
	@Override
	public List<Player> dimQuery(String player_Ename) {
		return playerMapper.dimQuery(player_Ename);
	}

	/*
	 * 批量删除的方法
	 */
	@Override
	public boolean deleteAll(Integer[] id) {
		if (playerMapper.deleteAll(id)) {
			return true;
		} else {
			return false;
		}

	}

	/*
	 * 根据id查询球员的方法
	 */
	@Override
	public Player queryPlayerById(Integer id) {
		return playerMapper.queryPlayerById(id);

	}

	/*
	 * 绘制图表的方法，返回MAP集合
	 */
	@Override
	public List<Map<String, Object>> chart() {
		return playerMapper.chart();

	}
    /*
     * 查询所有球队的方法
     */
	@Override
	public List<Map<String,Object>> queryByTeam() {
		return playerMapper.queryByTeam();
	}
    /*
     * 根据球队查询球员的方法
     */
	@Override
	public List<Player> queryPlayerByTeam(String player_effectiveTeam) {
		return playerMapper.queryPlayerByTeam(player_effectiveTeam);
	}

}
