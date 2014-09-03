package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Trainer;

public class TrainerDAO {
	private Connection con;

	private PreparedStatement stmtLoadAll;
	
	public TrainerDAO() throws Exception {
		con = ConnectionFactory.getConnection();
		stmtLoadAll = con.prepareStatement("SELECT `trainer`.`id`, "
				+ "`trainer`.`name` "
				+ "FROM `bitninja`.`trainer`;");
	}
	
	public List<Trainer> loadAll() throws Exception{
		List<Trainer> list = new ArrayList<Trainer>();
		ResultSet rs = stmtLoadAll.executeQuery();
		while(rs.next()){
			Trainer t = new Trainer();
			t.setId(rs.getInt("id"));
			t.setName(rs.getString("name"));
			t.setType("");
			
			TechniqueDAO dao = new TechniqueDAO();
			t.setTechniques(dao.loadByTrainer(rs.getInt("id")));
			dao.close();
			
			list.add(t);
		}
		return list;
	}
	
	public void close() throws SQLException{
		this.con.close();
	}
}
