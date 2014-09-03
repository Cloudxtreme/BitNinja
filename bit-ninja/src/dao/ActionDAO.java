package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import bean.Action;
import bean.Ninja;


public class ActionDAO {
	private Connection con;

	private PreparedStatement stmtGetLastMissionRefresh;
	private PreparedStatement stmtSetLastMissionRefresh;
	public PreparedStatement stmtFinish;
	public PreparedStatement stmtLoad;
	private PreparedStatement stmtSaveMission;
	
	public ActionDAO() throws Exception{
		con = ConnectionFactory.getConnection();
		
		stmtFinish = con.prepareStatement("UPDATE `bitninja`.`action` "
				+ "SET `finished` = 1 "
				+ "WHERE `ninja`=? "
				+ "AND `finished`=0 ;");
		stmtLoad = con.prepareStatement("SELECT * FROM `bitninja`.`action` "
				+ "WHERE `ninja`=? "
				+ "AND `finished`=0 ;");
		stmtSaveMission = con.prepareStatement("INSERT INTO `bitninja`.`action` "
				+ "(`ninja`, `mission`, `end`, `finished`) "
				+ "VALUES (?, ?, CURRENT_TIMESTAMP + INTERVAL ? minute, 0); ");
		
		stmtGetLastMissionRefresh = con.prepareStatement("SELECT `ninja`.`lastMissionRefresh` "
				+ "FROM `bitninja`.`ninja` "
				+ "WHERE `ninja`.`id` = ? ;");
		stmtSetLastMissionRefresh = con.prepareStatement("UPDATE `bitninja`.`ninja` "
				+ "SET `lastMissionRefresh` = CURRENT_TIMESTAMP "
				+ "WHERE `id` = ?; ");
	}
	
	public void setMissionAction(int ninjaId,int missionId, int duration) throws SQLException{
		stmtSaveMission.setInt(1, ninjaId);
		stmtSaveMission.setInt(2, missionId);
		stmtSaveMission.setInt(3, duration);
		stmtSaveMission.executeUpdate();
	}
	
	public Action loadAction(Ninja n) throws Exception{
		Action a = new Action();
		stmtLoad.setInt(1, n.getId());
		ResultSet rs = stmtLoad.executeQuery();
		if(rs.next()){
			a.setEnd(new Date(rs.getTimestamp("end").getTime()));
			a.setFinished(rs.getBoolean("finished"));
			MissionDAO mDao = new MissionDAO();
			a.setMission(mDao.load(rs.getInt("mission")));
			mDao.close();
			return a;
		}
		return null;
	}
	
	public Date getLastMissionRefresh(Ninja n) throws SQLException{
		stmtGetLastMissionRefresh.setInt(1, n.getId());
		ResultSet rs = stmtGetLastMissionRefresh.executeQuery();
		if(rs.next()){
			if(rs.getTimestamp("lastMissionRefresh") == null){
				return null;
			}
			Timestamp tmsp = rs.getTimestamp("lastMissionRefresh");
			return new Date(tmsp.getTime());
		}
		return null;
	}
	
	public void setLastMissionRefresh(Ninja n) throws SQLException{
		stmtSetLastMissionRefresh.setInt(1, n.getId());
		stmtSetLastMissionRefresh.executeUpdate();
	}
	
	public void finish(Ninja n) throws SQLException{
		stmtFinish.setInt(1, n.getId());
		stmtFinish.executeUpdate();
	}
	
	public void close() throws SQLException{
		this.con.close();
	}
}
