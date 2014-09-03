package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.User;

public class UserDAO {
	private Connection con;

	private PreparedStatement stmtLogin;
	private PreparedStatement stmtSave;
	private PreparedStatement stmtLoadAll;
	

	public UserDAO() throws Exception {
		con = ConnectionFactory.getConnection();

		stmtLoadAll = con.prepareStatement("SELECT `user`.`id`, `user`.`name`, `user`.`email` "
				+ " FROM `bitninja`.`user`");
		stmtLogin = con.prepareStatement("SELECT `user`.`id`, `user`.`name`, `user`.`email`, "
				+ "`user`.`password` "
				+ "FROM `bitninja`.`user` "
				+ "WHERE `user`.`email`=? AND `user`.`password`=MD5(?);");
		stmtSave = con.prepareStatement("INSERT INTO `bitninja`.`user` "
				+ "(`name`, `email`, `password`) "
				+ "VALUES (?, ?, MD5(?));");
	}
	
	public List<User> loadAll() throws SQLException{
		List<User> list = new ArrayList<User>();
		ResultSet rs = stmtLoadAll.executeQuery();
		while(rs.next()){
			User u = new User();
			u.setEmail(rs.getString("email"));
			u.setId(rs.getInt("id"));
			u.setName(rs.getString("name"));
			list.add(u);
		}
		return list;
	}
	
	public User login(String email,String password) throws SQLException{
		User u = null;
		stmtLogin.setString(1, email);
		stmtLogin.setString(2, password);
		ResultSet rs = stmtLogin.executeQuery();
		if(rs.next()){
			u = new User();
			u.setId(rs.getInt("id"));
			u.setEmail(rs.getString("email"));
			u.setName(rs.getString("name"));
		}
		return u;
	}
	
	public void save(User u,String password) throws SQLException{
		stmtSave.setString(1, u.getName());
		stmtSave.setString(2, u.getEmail());
		stmtSave.setString(3, password);
		stmtSave.executeUpdate();
	}
	
	public void close() throws SQLException{
		this.con.close();
	}
}