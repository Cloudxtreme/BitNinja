package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Background;

public class BackgroundDAO {
	private Connection con;

	private PreparedStatement stmtSave;
	private PreparedStatement stmtLoadAll;

	public BackgroundDAO() throws Exception {
		con = ConnectionFactory.getConnection();
		
		stmtLoadAll = con.prepareStatement("SELECT `background`.`id`, "
				+ "`background`.`name`, `background`.`description`, `background`.`bonuses` "
				+ "FROM `bitninja`.`background` ORDER BY `background`.`name`;");
		stmtSave = con.prepareStatement("INSERT INTO `bitninja`.`background` "
				+ "(`name`, `description`, `bonuses`) "
				+ "VALUES (?, ?, ?); ");
	}
	
	public List<Background> loadAll() throws SQLException{
		List<Background> list = new ArrayList<Background>();
		ResultSet rs = stmtLoadAll.executeQuery();
		while(rs.next()){
			Background b = new Background();
			b.setId(rs.getInt("id"));
			b.setName(rs.getString("name"));
			b.setDescription(rs.getString("description"));
			b.setBonuses(rs.getString("bonuses"));
			list.add(b);
		}
		return list;
	}
	
	public void save(Background b) throws SQLException{
		stmtSave.setString(1, b.getName());
		stmtSave.setString(2, b.getDescription());
		stmtSave.setString(3, b.getBonuses());
		stmtSave.executeUpdate();
	}
	
	public void close() throws SQLException{
		this.con.close();
	}
}