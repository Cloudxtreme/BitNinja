package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Attributes;
import bean.Bits;
import bean.Ninja;

public class NinjaDAO {
	private Connection con;
	
	private PreparedStatement stmtLoad;
	private PreparedStatement stmtLoadAll;
	private PreparedStatement stmtNextId;
	private PreparedStatement stmtSave;
	private PreparedStatement stmtUpdate;
	
	private PreparedStatement stmtLoadAttributes;
	private PreparedStatement stmtSaveAttributes;
	private PreparedStatement stmtUpdateAttributes;
	
	private PreparedStatement stmtLoadBits;
	private PreparedStatement stmtSaveBits;
	private PreparedStatement stmtUpdateBits;
	
	public NinjaDAO() throws Exception {
		con = ConnectionFactory.getConnection();

		stmtNextId = con.prepareStatement("SELECT AUTO_INCREMENT as id FROM information_schema.tables "
				+ "WHERE table_name = 'ninja' "
				+ "AND table_schema = DATABASE( ) ;");
		stmtLoad = con.prepareStatement("SELECT `ninja`.`id`, `ninja`.`user`, `ninja`.`name`, "
				+ "`ninja`.`level`, `ninja`.`exp_current`, `ninja`.`exp_needed`, `ninja`.`health_current`, "
				+ "`ninja`.`health_max`, `ninja`.`location`, `ninja`.`isHardcore`, `ninja`.`graduation`, "
				+ "`ninja`.`academy` "
				+ "FROM `bitninja`.`ninja` "
				+ "WHERE `ninja`.`id` = ? ;");
		stmtLoadAll = con.prepareStatement("SELECT `ninja`.`id`, `ninja`.`user`, `ninja`.`name`, "
				+ "`ninja`.`level`, `ninja`.`exp_current`, `ninja`.`exp_needed`, `ninja`.`health_current`, "
				+ "`ninja`.`health_max`, `ninja`.`location`, `ninja`.`isHardcore`, `ninja`.`graduation`, "
				+ "`ninja`.`academy` "
				+ "FROM `bitninja`.`ninja` "
				+ "WHERE `ninja`.`user` = ? ;");
		stmtSave = con.prepareStatement("INSERT INTO `bitninja`.`ninja` "
				+ "( `user`, `name`, `level`, `exp_current`, `exp_needed`, "
				+ "`health_current`, `health_max`, `location`, `isHardcore`, "
				+ "`graduation`, `academy` ) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?); ");
		stmtUpdate = con.prepareStatement("UPDATE `bitninja`.`ninja` "
				+ "SET `name` = ?, `level` = ?, `exp_current` = ?, `exp_needed` = ?, "
				+ "`health_current` = ?, `health_max` = ?, `location` = ?, `isHardcore` = ?, "
				+ "`graduation` = ?, `academy` = ? "
				+ "WHERE `id` = ?; ");
		
		stmtLoadAttributes = con.prepareStatement("SELECT `attributes`.`ninja`, `attributes`.`strength`, "
				+ "`attributes`.`speed`, `attributes`.`resistance`, `attributes`.`endurance`, "
				+ "`attributes`.`intelligence`, `attributes`.`wisdom` "
				+ "FROM `bitninja`.`attributes` "
				+ "WHERE `attributes`.`ninja` = ? ;");
		stmtSaveAttributes = con.prepareStatement("INSERT INTO `bitninja`.`attributes` "
				+ "(`ninja`, `strength`, `speed`, `resistance`, `endurance`, `intelligence`, `wisdom`) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?); ");
		stmtUpdateAttributes = con.prepareStatement("UPDATE `bitninja`.`attributes` "
				+ "SET `strength` = ?, `speed` = ?, `resistance` = ?, `endurance` = ?, "
				+ "`intelligence` = ?, `wisdom` = ? "
				+ "WHERE `ninja` = ?;");
		
		stmtLoadBits = con.prepareStatement("SELECT `bits`.`ninja`, `bits`.`red`, `bits`.`black`, "
				+ "`bits`.`blue`, `bits`.`gold`, `bits`.`gray`, `bits`.`green` "
				+ "FROM `bitninja`.`bits` "
				+ "WHERE `bits`.`ninja` = ? ;");
		stmtSaveBits = con.prepareStatement("INSERT INTO `bitninja`.`bits` "
				+ "(`ninja`, `red`, `black`, `blue`, `gold`, `gray`, `green`) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?); ");
		stmtUpdateBits = con.prepareStatement("UPDATE `bitninja`.`bits` "
				+ "SET `red` = ?, `black` = ?, `blue` = ?, `gold` = ?, `gray` = ?, `green` = ? "
				+ "WHERE `ninja` = ?; ");
		
		
	}
	
	public List<Ninja> loadAll(int user) throws SQLException{
		List<Ninja> list = new ArrayList<Ninja>();
		stmtLoadAll.setInt(1, user);
		ResultSet rs = stmtLoadAll.executeQuery();
		while(rs.next()){
			Ninja n = new Ninja();
			n.setAttributes(loadAttributes(rs.getInt("id")));
			n.setBits(loadBits(rs.getInt("id")));
			n.setExpCurrent(rs.getInt("exp_current"));
			n.setExpNeeded(rs.getInt("exp_needed"));
			n.setHealthCurrent(rs.getInt("health_current"));
			n.setHealthMax(rs.getInt("health_max"));
			n.setId(rs.getInt("id"));
			n.setLevel(rs.getInt("level"));
			n.setName(rs.getString("name"));
			n.setHardcore(rs.getBoolean("isHardcore"));
			n.setGraduation(rs.getString("graduation"));
			n.setAcademyLevel(rs.getInt("academy"));
			
			try{
				LocationDAO dao = new LocationDAO();
				n.setLocation(dao.load(rs.getInt("location")));
				dao.close();
			}catch(Exception e){
				e.printStackTrace();
			}
			list.add(n);
		}
		return list;
	}
	
	public Ninja load(int ninja) throws SQLException{
		Ninja n = null;
		stmtLoad.setInt(1, ninja);
		ResultSet rs = stmtLoad.executeQuery();
		if(rs.next()){
			n = new Ninja();
			n.setAttributes(loadAttributes(rs.getInt("id")));
			n.setBits(loadBits(rs.getInt("id")));
			n.setExpCurrent(rs.getInt("exp_current"));
			n.setExpNeeded(rs.getInt("exp_needed"));
			n.setHealthCurrent(rs.getInt("health_current"));
			n.setHealthMax(rs.getInt("health_max"));
			n.setId(rs.getInt("id"));
			n.setLevel(rs.getInt("level"));
			n.setName(rs.getString("name"));
			n.setHardcore(rs.getBoolean("isHardcore"));
			n.setGraduation(rs.getString("graduation"));
			n.setAcademyLevel(rs.getInt("academy"));
			
			try{
				LocationDAO dao = new LocationDAO();
				n.setLocation(dao.load(rs.getInt("location")));
				dao.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return n;
	}
	
	public Attributes loadAttributes(int id) throws SQLException{
		Attributes a = null;
		stmtLoadAttributes.setInt(1, id);
		ResultSet rs = stmtLoadAttributes.executeQuery();
		if(rs.next()){
			a = new Attributes();
			a.setEndurance(rs.getInt("endurance"));
			a.setIntelligence(rs.getInt("intelligence"));
			a.setResistance(rs.getInt("resistance"));
			a.setSpeed(rs.getInt("speed"));
			a.setStrength(rs.getInt("strength"));
			a.setWisdom(rs.getInt("wisdom"));
		}
		return a;
	}
	
	public Bits loadBits(int id) throws SQLException{
		Bits b = null;
		stmtLoadBits.setInt(1, id);
		ResultSet rs = stmtLoadBits.executeQuery();
		if(rs.next()){
			b = new Bits();
			b.setBlack(rs.getInt("black"));
			b.setBlue(rs.getInt("blue"));
			b.setGold(rs.getInt("gold"));
			b.setGray(rs.getInt("gray"));
			b.setGreen(rs.getInt("green"));
			b.setRed(rs.getInt("red"));
		}
		return b;
	}
	
	public void save(Ninja n, int user, int location) throws SQLException{
		ResultSet rs = stmtNextId.executeQuery();
		if(rs.next()){
			int id = rs.getInt("id");
			stmtSave.setInt(1, user);
			stmtSave.setString(2, n.getName());
			stmtSave.setInt(3, n.getLevel());
			stmtSave.setInt(4, n.getExpCurrent());
			stmtSave.setInt(5, n.getExpNeeded());
			stmtSave.setInt(6, n.getHealthCurrent());
			stmtSave.setInt(7, n.getHealthMax());
			stmtSave.setInt(8, location);
			stmtSave.setBoolean(9, n.isHardcore());
			stmtSave.setString(10, n.getGraduation());
			stmtSave.setInt(11, n.getAcademyLevel());
			stmtSave.executeUpdate();
			
			stmtSaveAttributes.setInt(1, id);
			stmtSaveAttributes.setInt(2, n.getAttributes().getStrength());
			stmtSaveAttributes.setInt(3, n.getAttributes().getSpeed());
			stmtSaveAttributes.setInt(4, n.getAttributes().getResistance());
			stmtSaveAttributes.setInt(5, n.getAttributes().getEndurance());
			stmtSaveAttributes.setInt(6, n.getAttributes().getIntelligence());
			stmtSaveAttributes.setInt(7, n.getAttributes().getWisdom());
			stmtSaveAttributes.executeUpdate();
			
			stmtSaveBits.setInt(1, id);
			stmtSaveBits.setInt(2, n.getBits().getRed());
			stmtSaveBits.setInt(3, n.getBits().getBlack());
			stmtSaveBits.setInt(4, n.getBits().getBlue());
			stmtSaveBits.setInt(5, n.getBits().getGold());
			stmtSaveBits.setInt(6, n.getBits().getGray());
			stmtSaveBits.setInt(7, n.getBits().getGreen());
			stmtSaveBits.executeUpdate();
		}
	}
	
	public void update(Ninja n) throws SQLException{
		stmtUpdate.setString(1, n.getName());
		stmtUpdate.setInt(2, n.getLevel());
		stmtUpdate.setInt(3, n.getExpCurrent());
		stmtUpdate.setInt(4, n.getExpNeeded());
		stmtUpdate.setInt(5, n.getHealthCurrent());
		stmtUpdate.setInt(6, n.getHealthMax());
		stmtUpdate.setInt(7, n.getLocation().getId());
		stmtUpdate.setBoolean(8, n.isHardcore());
		stmtUpdate.setString(9, n.getGraduation());
		stmtUpdate.setInt(10, n.getAcademyLevel());
		stmtUpdate.setInt(11, n.getId());
		stmtUpdate.executeUpdate();
		
		stmtUpdateAttributes.setInt(1, n.getAttributes().getStrength());
		stmtUpdateAttributes.setInt(2, n.getAttributes().getSpeed());
		stmtUpdateAttributes.setInt(3, n.getAttributes().getResistance());
		stmtUpdateAttributes.setInt(4, n.getAttributes().getEndurance());
		stmtUpdateAttributes.setInt(5, n.getAttributes().getIntelligence());
		stmtUpdateAttributes.setInt(6, n.getAttributes().getWisdom());
		stmtUpdateAttributes.setInt(7, n.getId());
		stmtUpdateAttributes.executeUpdate();
		
		stmtUpdateBits.setInt(1, n.getBits().getRed());
		stmtUpdateBits.setInt(2,  n.getBits().getBlack());
		stmtUpdateBits.setInt(3, n.getBits().getBlue());
		stmtUpdateBits.setInt(4,  n.getBits().getGold());
		stmtUpdateBits.setInt(5,  n.getBits().getGray());
		stmtUpdateBits.setInt(6,  n.getBits().getGreen());
		stmtUpdateBits.setInt(7, n.getId());
		stmtUpdateBits.executeUpdate();
	}
	
	public void close() throws SQLException{
		this.con.close();
	}
}
