package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.New;

public class NewDAO {
	private Connection con;

	private PreparedStatement stmtLoadAllRecent;

	public NewDAO() throws Exception {
		con = ConnectionFactory.getConnection();

		stmtLoadAllRecent = con.prepareStatement("SELECT * FROM bitninja.new ORDER BY timestamp;");
	}
	
	public List<New> loadMainNews() throws SQLException{
		List<New> list = new ArrayList<New>();
		ResultSet rs = stmtLoadAllRecent.executeQuery();
		while(rs.next()){
			New n = new New();
			n.setDate(rs.getString("date"));
			n.setId(rs.getInt("id"));
			n.setContent(rs.getString("content"));
			n.setSubtitle(rs.getString("subtitle"));
			n.setTitle(rs.getString("title"));
			list.add(n);
		}
		return list;
	}
	
	public void close() throws SQLException{
		this.con.close();
	}
}