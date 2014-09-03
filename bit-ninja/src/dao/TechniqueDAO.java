package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Technique;

public class TechniqueDAO {
	private Connection con;

	private PreparedStatement stmtLoadAll;
	private PreparedStatement stmtLoadAllByNinja;
	private PreparedStatement stmtLoadAllByTrainer;
	private PreparedStatement stmtUpdateMastery;

	public TechniqueDAO() throws Exception {
		con = ConnectionFactory.getConnection();

		stmtLoadAll = con.prepareStatement("SELECT `technique`.`id`, `technique`.`type`, `technique`.`name`, "
				+ "`technique`.`power`, `technique`.`effects`, `technique`.`cooldown`, `technique`.`duration`, "
				+ "`technique`.`cost` "
				+ "FROM `bitninja`.`technique`; ");
		stmtLoadAllByNinja = con.prepareStatement("SELECT `technique`.`id`, `technique`.`type`, `technique`.`name`, "
				+ "`technique`.`power`, `technique`.`effects`, `technique`.`cooldown`, `technique`.`duration`, "
				+ "`skill`.`mastery`, `technique`.`cost` "
				+ "FROM `bitninja`.`technique`, `bitninja`.`skill` "
				+ "WHERE `skill`.`technique` = `technique`.`id` AND `skill`.`ninja` = ?; ");
		stmtLoadAllByTrainer = con.prepareStatement("SELECT `technique`.`id`, `technique`.`type`, `technique`.`name`, "
				+ "`technique`.`power`, `technique`.`effects`, `technique`.`cooldown`, `technique`.`duration`, "
				+ "`training`.`requisites`, `technique`.`cost` "
				+ "FROM `bitninja`.`technique`, `bitninja`.`training` "
				+ "WHERE `technique`.`id` = `training`.`technique` AND `training`.`trainer` = ? "
				+ "ORDER BY `training`.`order`; ");
		stmtUpdateMastery = con.prepareStatement("UPDATE `bitninja`.`skill` SET `mastery` = ? "
				+ "WHERE `ninja` = ? AND `technique` = ?; ");
	}
	
	public List<Technique> loadAll() throws SQLException{
		List<Technique> list = new ArrayList<Technique>();
		ResultSet rs = stmtLoadAll.executeQuery();
		while(rs.next()){
			Technique t = new Technique();
			t.setCooldown(rs.getInt("cooldown"));
			t.setDuration(rs.getInt("duration"));
			t.setEffects(rs.getString("effects"));
			t.setId(rs.getInt("id"));
			t.setName(rs.getString("name"));
			t.setPower(rs.getInt("power"));
			t.setType(rs.getString("type"));
			t.setCost(rs.getInt("cost"));
			list.add(t);
		}
		return list;
	}
	
	public List<Technique> loadByNinja(int ninjaId) throws SQLException{
		List<Technique> list = new ArrayList<Technique>();
		stmtLoadAllByNinja.setInt(1, ninjaId);
		ResultSet rs = stmtLoadAllByNinja.executeQuery();
		while(rs.next()){
			Technique t = new Technique();
			t.setCooldown(rs.getInt("cooldown"));
			t.setDuration(rs.getInt("duration"));
			t.setEffects(rs.getString("effects"));
			t.setId(rs.getInt("id"));
			t.setMastery(rs.getInt("mastery"));
			t.setName(rs.getString("name"));
			t.setPower(rs.getInt("power"));
			t.setType(rs.getString("type"));
			t.setCost(rs.getInt("cost"));
			list.add(t);
		}
		return list;
	}
	
	public List<Technique> loadByTrainer(int trainerId) throws SQLException{
		List<Technique> list = new ArrayList<Technique>();
		stmtLoadAllByTrainer.setInt(1, trainerId);
		ResultSet rs = stmtLoadAllByTrainer.executeQuery();
		while(rs.next()){
			Technique t = new Technique();
			t.setCooldown(rs.getInt("cooldown"));
			t.setDuration(rs.getInt("duration"));
			t.setEffects(rs.getString("effects"));
			t.setId(rs.getInt("id"));
			t.setRequirements(rs.getString("requisites"));
			t.setName(rs.getString("name"));
			t.setPower(rs.getInt("power"));
			t.setType(rs.getString("type"));
			t.setCost(rs.getInt("cost"));
			list.add(t);
		}
		return list;
	}
	
	public void updateMastery(int ninjaId, int techniqueId) throws SQLException{
		stmtUpdateMastery.setInt(1, ninjaId);
		stmtUpdateMastery.setInt(2, techniqueId);
		stmtUpdateMastery.executeUpdate();
	}
	
	public void close() throws SQLException{
		this.con.close();
	}
}