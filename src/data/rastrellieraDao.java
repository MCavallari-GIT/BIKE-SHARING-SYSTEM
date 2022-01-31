package data;
import domain.*;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
public class rastrellieraDao implements rastrellieraDaoInterface{
	static rastrellieraDao obj=new rastrellieraDao();
	private rastrellieraDao() {}
	public static rastrellieraDao get_instance() {
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
	
	public boolean insert(Connection conn,rastrelliera r) {
		try {
			if(!conn.isValid(5)) {
				return false;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		String query="INSERT INTO sweng.rastrelliera (id,capacita) VALUES (?,?);";
		try {
			PreparedStatement statement=conn.prepareStatement(query);
			statement.setString(1, r.get_id());
			statement.setInt(2,r.get_capacita());
			statement.executeUpdate();
		} catch(SQLException e){
			e.printStackTrace();
		}
		return true;
	}
	
	public ArrayList<String> select_id(Connection conn) {
		ArrayList<String> set= new ArrayList<>();
		try {
			if(!conn.isValid(5)) {
				return set;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		String query="SELECT id from sweng.rastrelliera;";
		try {
			PreparedStatement statement=conn.prepareStatement(query);
			ResultSet rs=statement.executeQuery();
			while(rs.next()) {
				set.add(rs.getString(1));
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return set;
	}

	public rastrelliera find_rast(Connection conn,String id) {
		rastrelliera r=new rastrelliera();
		try {
			if(!conn.isValid(5)) {
				return r;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		String query="SELECT * from sweng.rastrelliera where id=?;";
		try {
			PreparedStatement statement=conn.prepareStatement(query);
			statement.setString(1, id);
			ResultSet rs=statement.executeQuery();
			if(rs.next()) {
				r.set_id(rs.getString(1));
				r.set_capacita(rs.getInt(2));
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return r;
	}
}
