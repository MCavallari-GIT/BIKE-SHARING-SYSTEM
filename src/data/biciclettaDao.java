package data;
import domain.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class biciclettaDao implements biciclettaDaoInterface{
	static biciclettaDao obj=new biciclettaDao();
	private biciclettaDao() {}
	public static biciclettaDao get_instance() {
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
	
	public boolean insert(Connection conn,bicicletta b) {
		try {
			if(!conn.isValid(5)) {
				return false;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		String query="INSERT INTO sweng.bicicletta (id,tipo,danneggiata) VALUES (?,?,?);";
		try {
			PreparedStatement statement=conn.prepareStatement(query);
			statement.setString(1, b.get_id());
			statement.setString(2,b.get_tipo());
			statement.setBoolean(3,b.get_danneggiata());
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
		String query="SELECT id from sweng.bicicletta;";
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
	
	public boolean delete_bici(Connection conn,String id) {
		try {
			if(!conn.isValid(5)) {
				return false;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		String query7="SELECT * FROM sweng.prelievo where bicicletta=? and fine IS NULL;";
		try {
			PreparedStatement statement=conn.prepareStatement(query7);
			statement.setString(1,id);
			ResultSet rs=statement.executeQuery();
			while(rs.next()) {
				return false;
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		
		String query3="UPDATE sweng.morsa set occupata=FALSE where bici_inserita=?;";
		try {
			PreparedStatement statement=conn.prepareStatement(query3);
			statement.setString(1,id);
			statement.executeUpdate();
		} catch(SQLException e){
			e.printStackTrace();
		}
		String query="UPDATE sweng.morsa set bici_inserita=NULL where bici_inserita=?;";
		try {
			PreparedStatement statement=conn.prepareStatement(query);
			statement.setString(1,id);
			statement.executeUpdate();
		} catch(SQLException e){
			e.printStackTrace();
		}
		
		
		String query2="DELETE FROM sweng.danno where id_bicicletta=?;";
		try {
			PreparedStatement statement=conn.prepareStatement(query2);
			statement.setString(1,id);
			statement.executeUpdate();
		} catch(SQLException e){
			e.printStackTrace();
		}
		
		String query5="DELETE FROM sweng.prelievo where bicicletta=?;";
		try {
			PreparedStatement statement=conn.prepareStatement(query5);
			statement.setString(1,id);
			statement.executeUpdate();
		} catch(SQLException e){
			e.printStackTrace();
		}
		
		String query4="DELETE FROM sweng.bicicletta where id=?;";
		try {
			PreparedStatement statement=conn.prepareStatement(query4);
			statement.setString(1,id);
			statement.executeUpdate();
		} catch(SQLException e){
			e.printStackTrace();
		}
		return true;
	}
	
	public bicicletta get_bici(Connection conn,String id) {
		try {
			if(!conn.isValid(5)) {
				return null;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		String query="SELECT * FROM sweng.bicicletta WHERE id=?;";
		try {
			PreparedStatement statement=conn.prepareStatement(query);
			statement.setString(1, id);
			ResultSet rs=statement.executeQuery();
			if(rs.next()) {
				String tipo=rs.getString(2);
				String id_bici=rs.getString(1);
				boolean b=rs.getBoolean(3);
				if(b==false) {
					if(tipo.equals("normale")) {
						bici_normale bici=new bici_normale(id,b);
						return bici;
					}
					if(tipo.equals("elettrica")) {
						bici_elettrica bici=new bici_elettrica(id,b);
						return bici;
					}
					if(tipo.equals("elettrica_con_seggiolino")) {
						bici_elettrica_con_seggiolino bici=new bici_elettrica_con_seggiolino(id,b);
						return bici;
					}
				}
				
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean delete_bici_from_rast(Connection conn,String id_bici,String id_rast) {
		try {
			if(!conn.isValid(5)) {
				return false;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		String query3="UPDATE sweng.morsa set occupata=FALSE where bici_inserita=? and rastrelliera<>?;";
		try {
			PreparedStatement statement=conn.prepareStatement(query3);
			statement.setString(1,id_bici);
			statement.setString(2,id_rast);
			statement.executeUpdate();
		} catch(SQLException e){
			e.printStackTrace();
		}
		String query="UPDATE sweng.morsa set bici_inserita=NULL where bici_inserita=? and rastrelliera<>?;";
		try {
			PreparedStatement statement=conn.prepareStatement(query);
			statement.setString(1,id_bici);
			statement.setString(2,id_rast);
			statement.executeUpdate();
		} catch(SQLException e){
			e.printStackTrace();
		}
		return true;
	}
	
	public boolean is_presente(Connection conn,String id_bici,String id_rast) {
		try {
			if(!conn.isValid(5)) {
				return false;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		String query3="SELECT * FROM  sweng.morsa where bici_inserita=? and rastrelliera=?;";
		try {
			PreparedStatement statement=conn.prepareStatement(query3);
			statement.setString(1,id_bici);
			statement.setString(2,id_rast);
			ResultSet rs=statement.executeQuery();
			while(rs.next()) {
				return true;
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean update(Connection conn,boolean danno,String id_bici) {
		try {
			if(!conn.isValid(5)) {
				return false;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		String query="UPDATE sweng.bicicletta set danneggiata=? where id=?;";
		try {
			PreparedStatement statement=conn.prepareStatement(query);
			statement.setBoolean(1, danno);
			statement.setString(2,id_bici);
			statement.executeUpdate();
		} catch(SQLException e){
			e.printStackTrace();
		}
		return true;
	}
	
	
	public String stat_bici(Connection conn) {
		try {
			if(!conn.isValid(5)) {
				return "null";
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		String query1="SELECT tipo \r\n"
				+ "FROM sweng.bicicletta\r\n"
				+ "GROUP BY tipo\r\n"
				+ "HAVING COUNT(tipo) = (SELECT COUNT(tipo) \r\n"
				+ "                         FROM   sweng.bicicletta\r\n"
				+ "                         GROUP  BY tipo\r\n"
				+ "                         ORDER  BY COUNT(tipo) DESC \r\n"
				+ "                         LIMIT  1) ;";
		try {
			PreparedStatement statement=conn.prepareStatement(query1);
			ResultSet rs=statement.executeQuery();
			if(rs.next()) {
				return rs.getString(1);
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return "null";
	}
}
