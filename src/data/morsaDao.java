package data;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import domain.*;
public class morsaDao implements morsaDaoInterface{
	static morsaDao obj=new morsaDao();
	private morsaDao() {}
	public static morsaDao get_instance() {
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
	
	public boolean insert(Connection conn,morsa m) {
		try {
			if(!conn.isValid(5)) {
				return false;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		String query="INSERT INTO sweng.morsa (id,tipo,occupata,rastrelliera) VALUES (?,?,?,?);";
		try {
			PreparedStatement statement=conn.prepareStatement(query);
			statement.setString(1, m.get_id());
			statement.setString(2,m.get_tipo());
			statement.setBoolean(3,m.get_occupata());
			statement.setString(4,m.get_rastrelliera());
			statement.executeUpdate();
		} catch(SQLException e){
			e.printStackTrace();
		}
		return true;
	}
	public ArrayList<morsa> select_morse(Connection conn,String r) {
		ArrayList<morsa> set= new ArrayList<>();
		try {
			if(!conn.isValid(5)) {
				return set;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		String query="SELECT * from sweng.morsa where rastrelliera=?;";
		try {
			PreparedStatement statement=conn.prepareStatement(query);
			statement.setString(1, r);
			ResultSet rs=statement.executeQuery();
			while(rs.next()) {
				morsa m=new morsa();
				m.set_id(rs.getString(1));
				m.set_tipo(rs.getString(2));
				m.set_occupata(rs.getBoolean(3));
				m.set_rastrelliera(rs.getString(4));
				set.add(m);
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return set;
	}
	
	public boolean update_morsa(Connection conn,morsa m,String id_bici) {
		try {
			if(!conn.isValid(5)) {
				return false;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		String query="UPDATE sweng.morsa SET occupata=? WHERE rastrelliera=? and id=?;";
		try {
			PreparedStatement statement=conn.prepareStatement(query);
			statement.setBoolean(1, m.get_occupata());
			statement.setString(2, m.get_rastrelliera());
			statement.setString(3, m.get_id());
			statement.executeUpdate();
		} catch(SQLException e){
			e.printStackTrace();
		}
		
		String query2="UPDATE sweng.morsa SET bici_inserita=? WHERE rastrelliera=? and id=?;";
		try {
			PreparedStatement statement=conn.prepareStatement(query2);
			statement.setString(1, id_bici);
			statement.setString(2, m.get_rastrelliera());
			statement.setString(3, m.get_id());
			statement.executeUpdate();
		} catch(SQLException e){
			e.printStackTrace();
		}
		return true;
	}
	
	
	
}
