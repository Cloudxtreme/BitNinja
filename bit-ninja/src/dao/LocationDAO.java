package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Location;

public class LocationDAO {
	private Connection con;

	private PreparedStatement stmtLoad;
	private PreparedStatement stmtSave;
	private PreparedStatement stmtLoadAll;

	public LocationDAO() throws Exception {
		con = ConnectionFactory.getConnection();

		stmtLoad = con.prepareStatement("SELECT `location`.`id`," + 
				" `location`.`name`," + 
				" `location`.`description`" + 
				" FROM `bitninja`.`location` "
				+ "WHERE `location`.`id` = ? ;");
		stmtSave = con.prepareStatement("INSERT INTO `bitninja`.`location` "
				+ "(`name`, `description`) "
				+ "VALUES (?, ?); ");
		stmtLoadAll = con.prepareStatement("SELECT `location`.`id`," + 
				" `location`.`name`," + 
				" `location`.`description`" + 
				" FROM `bitninja`.`location`;");
	}
	
	public void save(Location l) throws SQLException{
		stmtSave.setString(1, l.getName());
		stmtSave.setString(2, l.getDescription());
		stmtSave.executeUpdate();
	}
	
	public List<Location> loadAll() throws SQLException{
		List<Location> list = new ArrayList<Location>();
		ResultSet rs = stmtLoadAll.executeQuery();
		while(rs.next()){
			Location l = new Location();
			l.setId(rs.getInt("id"));
			l.setName(rs.getString("name"));
			l.setDescription(rs.getString("description"));
			list.add(l);
		}
		return list;
	}
	
	public Location load(int id) throws SQLException{
		Location l = null;
		stmtLoad.setInt(1, id);
		ResultSet rs = stmtLoad.executeQuery();
		if(rs.next()){
			l = new Location();
			l.setId(rs.getInt("id"));
			l.setName(rs.getString("name"));
			l.setDescription(rs.getString("description"));
		}
		return l;
	}
	
	public void close() throws SQLException{
		this.con.close();
	}
}