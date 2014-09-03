package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Mission;

public class MissionDAO {
	private Connection con;

	private PreparedStatement stmtAddToPool;
	private PreparedStatement stmtClearPool;
	private PreparedStatement stmtDeleteFromPool;
	private PreparedStatement stmtLoad;
	private PreparedStatement stmtLoadAll;
	private PreparedStatement stmtLoadFromPool;
	
	public MissionDAO() throws Exception {
		con = ConnectionFactory.getConnection();
		stmtAddToPool = con.prepareStatement("INSERT INTO `bitninja`.`available_missions` (`ninja`, `mission`) "
				+ "VALUES (?, ?); ");
		stmtClearPool = con.prepareStatement("DELETE FROM `bitninja`.`available_missions` "
				+ "WHERE `ninja`=?; ");
		stmtDeleteFromPool = con.prepareStatement("DELETE FROM `bitninja`.`available_missions` "
				+ "WHERE `ninja`=? AND `mission`=?; ");
		stmtLoad = con.prepareStatement("SELECT `mission`.`id`, `mission`.`name`, "
				+ "`mission`.`description`, `mission`.`rank`, `mission`.`rewards_failure`, "
				+ "`mission`.`rewards_success`, `mission`.`duration` FROM `bitninja`.`mission` "
				+ "WHERE `mission`.`id`=?; ");
		stmtLoadAll = con.prepareStatement("SELECT `mission`.`id`, `mission`.`name`, "
				+ "`mission`.`description`, `mission`.`rank`, `mission`.`rewards_failure`, "
				+ "`mission`.`rewards_success`, `mission`.`duration` FROM `bitninja`.`mission`; ");
		stmtLoadFromPool = con.prepareStatement("SELECT `available_missions`.`ninja`, `available_missions`.`mission` "
				+ "FROM `bitninja`.`available_missions` "
				+ "WHERE `ninja`=?; ");
	}
	
	public Mission load(int id) throws SQLException{
		Mission m = new Mission();
		stmtLoad.setInt(1, id);
		ResultSet rs = stmtLoad.executeQuery();
		if(rs.next()){
			m.setDescription(rs.getString("description"));
			m.setDuration(rs.getInt("duration"));
			m.setId(rs.getInt("id"));
			m.setName(rs.getString("name"));
			m.setRank(rs.getString("rank"));
			m.setRankClass(rs.getString("rank").substring(rs.getString("rank").length() -1));
			m.setRewardsFailure(rs.getString("rewards_failure"));
			m.setRewardsSuccess(rs.getString("rewards_success"));
			return m;
		}
		return null;
	}
	
	public List<Mission> loadPool(int id) throws SQLException{
		List<Mission> list = new ArrayList<Mission>();
		stmtLoadFromPool.setInt(1, id);
		ResultSet rs = stmtLoadFromPool.executeQuery();
		while(rs.next()){
			int mission = rs.getInt("mission");
			list.add(load(mission));
		}
		return list;
	}
	
	public void addToPool (int ninjaId, int missionId) throws SQLException{
		stmtAddToPool.setInt(1, ninjaId);
		stmtAddToPool.setInt(2, missionId);
		stmtAddToPool.executeUpdate();
	}
	
	public void removeFromPool (int ninjaId, int missionId) throws SQLException{
		stmtDeleteFromPool.setInt(1, ninjaId);
		stmtDeleteFromPool.setInt(2, missionId);
		stmtDeleteFromPool.executeUpdate();
	}
	
	public List<Mission> loadAll() throws SQLException{
		List<Mission> list = new ArrayList<Mission>();
		ResultSet rs = stmtLoadAll.executeQuery();
		while(rs.next()){
			Mission m = new Mission();
			m.setDescription(rs.getString("description"));
			m.setDuration(rs.getInt("duration"));
			m.setId(rs.getInt("id"));
			m.setName(rs.getString("name"));
			m.setRank(rs.getString("rank"));
			m.setRankClass(rs.getString("rank").substring(rs.getString("rank").length() -1));
			m.setRewardsFailure(rs.getString("rewards_failure"));
			m.setRewardsSuccess(rs.getString("rewards_success"));
			list.add(m);
		}
		return list;
	}
	
	public void close() throws SQLException{
		this.con.close();
	}

	public void clearPool(int id) throws SQLException{
		stmtClearPool.setInt(1, id);
		stmtClearPool.executeUpdate();
	}
}
