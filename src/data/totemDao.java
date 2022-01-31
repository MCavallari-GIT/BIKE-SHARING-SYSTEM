package data;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import domain.*;
public class totemDao implements totemDaoInterface{
	static totemDao obj=new totemDao();
	private totemDao() {}
	public static totemDao get_instance() {
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
	
	public boolean insert(Connection conn,totem t) {
		try {
			if(!conn.isValid(5)) {
				return false;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		String query="INSERT INTO sweng.totem (id,rastrelliera) VALUES (?,?);";
		try {
			PreparedStatement statement=conn.prepareStatement(query);
			statement.setString(1, t.get_id());
			statement.setString(2,t.get_id_rastrelliera());
			statement.executeUpdate();
		} catch(SQLException e){
			e.printStackTrace();
		}
		return true;
	}
	
	public boolean delete_stazione(Connection conn,String stazione) {
		try {
			if(!conn.isValid(5)) {
				return false;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		String query1="DELETE FROM sweng.totem where rastrelliera=?;";
		try {
			PreparedStatement statement=conn.prepareStatement(query1);
			statement.setString(1,stazione);
			statement.executeUpdate();
		} catch(SQLException e){
			e.printStackTrace();
		}
		
		String query3="DELETE FROM sweng.morsa where rastrelliera=?;";
		try {
			PreparedStatement statement=conn.prepareStatement(query3);
			statement.setString(1,stazione);
			statement.executeUpdate();
		} catch(SQLException e){
			e.printStackTrace();
		}
		
		String query2="DELETE FROM sweng.rastrelliera where id=?;";
		try {
			PreparedStatement statement=conn.prepareStatement(query2);
			statement.setString(1,stazione);
			statement.executeUpdate();
		} catch(SQLException e){
			e.printStackTrace();
		}
		
		return true;
	}
	
	public morsa preleva_bici(Connection conn,String stazione,String tipo) {
		try {
			if(!conn.isValid(5)) {
				return null;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		//SELECT * from sweng.morsa WHERE morsa.rastrelliera=? and bici_inserita IS NOT NULL AND tipo=?;
		String query1="SELECT morsa.id,morsa.tipo,morsa.occupata,morsa.rastrelliera,morsa.bici_inserita from sweng.morsa INNER JOIN sweng.bicicletta ON bici_inserita=bicicletta.id WHERE bicicletta.danneggiata=FALSE and morsa.rastrelliera=? and bici_inserita IS NOT NULL AND morsa.tipo=?;";
		try {
			PreparedStatement statement=conn.prepareStatement(query1);
			statement.setString(1,stazione);
			statement.setString(2,tipo);
			ResultSet rs=statement.executeQuery();
			if(rs.next()) {
				morsa m=new morsa();
				m.set_id(rs.getString(1));
				m.set_tipo(rs.getString(2));
				m.set_occupata(rs.getBoolean(3));
				m.set_rastrelliera(rs.getString(4));
				m.set_bici_inserita(rs.getString(5));
				return m;
				
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}
	
	
	public int stat_stazioni(Connection conn) {
		try {
			if(!conn.isValid(5)) {
				return -1;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		String query1="SELECT COUNT(*) FROM sweng.totem;";
		try {
			PreparedStatement statement=conn.prepareStatement(query1);
			ResultSet rs=statement.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return -1;
	}
	
}
