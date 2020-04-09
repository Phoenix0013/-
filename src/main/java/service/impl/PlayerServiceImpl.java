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
	 * ��ѯ������Ա�ķ���
	 */
	@Override
	public List<Player> queryAllPlayer() {
		// TODO Auto-generated method stub
		return playerMapper.queryAllPlayer();
	}

	/*
	 * ɾ����Ա�ķ���
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
	 * ������Ա�ķ���
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
	 * �޸���Ա��Ϣ�ķ���
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
	 * ģ����ѯ��Ա�ķ���
	 */
	@Override
	public List<Player> dimQuery(String player_Ename) {
		return playerMapper.dimQuery(player_Ename);
	}

	/*
	 * ����ɾ���ķ���
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
	 * ����id��ѯ��Ա�ķ���
	 */
	@Override
	public Player queryPlayerById(Integer id) {
		return playerMapper.queryPlayerById(id);

	}

	/*
	 * ����ͼ��ķ���������MAP����
	 */
	@Override
	public List<Map<String, Object>> chart() {
		return playerMapper.chart();

	}
    /*
     * ��ѯ������ӵķ���
     */
	@Override
	public List<Map<String,Object>> queryByTeam() {
		return playerMapper.queryByTeam();
	}
    /*
     * ������Ӳ�ѯ��Ա�ķ���
     */
	@Override
	public List<Player> queryPlayerByTeam(String player_effectiveTeam) {
		return playerMapper.queryPlayerByTeam(player_effectiveTeam);
	}

}
