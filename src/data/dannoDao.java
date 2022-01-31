package data;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import domain.*;
public class dannoDao implements dannoDaoInterface{
	static dannoDao obj=new dannoDao();
	private dannoDao() {}
	public static dannoDao get_instance() {
		return obj;
	}
	
	public Connection connetti() {
		Connection connection=null;
		String url="jdbc:postgresql://localhost/bikemi";
		String user="marco_cavallari";
		String password="sweng2021";
		try {
			connection=DriverManager.getConnection(url,user,password);
		} catch(SQLException e){
			e.printStackTrace();
		}
		return connection;
	}
	
	public boolean insert(Connection conn,danno d) {
		try {
			if(!conn.isValid(5)) {
				return false;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		String query="INSERT INTO sweng.danno (id_bicicletta,danno) VALUES (?,?);";
		try {
			PreparedStatement statement=conn.prepareStatement(query);
			statement.setString(1, d.get_bici());
			statement.setString(2,d.get_danno());
			statement.executeUpdate();
		} catch(SQLException e){
			e.printStackTrace();
		}
		return true;
	}
}
