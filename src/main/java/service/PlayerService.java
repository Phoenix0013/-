package service;

import java.util.List;
import java.util.Map;

import pojo.Player;

public interface PlayerService {
	/*
	 * ��ѯ������Ա�ķ���
	 */
	List<Player> queryAllPlayer();

	/*
	 * ɾ����Ա�ķ���
	 */
	boolean deletePlayer(int player_id);

	/*
	 * ������Ա�ķ���
	 */
	boolean insertPlayer(Player player);

	/*
	 * �޸���Ա��Ϣ�ķ���
	 */
	boolean updatePlayer(Player player);

	/*
	 * ģ����ѯ��Ա�ķ���
	 */
	List<Player> dimQuery(String player_Ename);

	/*
	 * ����ɾ����Ա�ķ���
	 */
	boolean deleteAll(Integer[] id);

	/*
	 * ����id��ѯ��Ա�ķ���
	 */
	Player queryPlayerById(Integer id);

	/*
	 * ����ͼ��ķ���������map����
	 */
	List<Map<String, Object>> chart();
    /*
     * ��ѯ������ӵķ���
     */
	List<Map<String,Object>> queryByTeam();
    /*
     * ������Ӳ�ѯ��Ա�ķ���
     */
	List<Player> queryPlayerByTeam(String player_effectiveTeam);


}
