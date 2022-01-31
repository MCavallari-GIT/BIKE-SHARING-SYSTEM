package data;
import domain.*;
import java.sql.Connection;

import java.time.LocalDate;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
public class carta_creditoDao  implements carta_creditoDaoInterface{
	static carta_creditoDao obj=new carta_creditoDao();
	private carta_creditoDao() {}
	public static carta_creditoDao get_instance() {
		return obj;
	}
	
	public Connection connetti() {
		Connection connection=null;
		String url="jdbc:postgresql://localhost/bikemi";
		String user="marco_cavallari";
		String password="sweng2021";
		try {
			connection=DriverManager.getConnection(url,user,password);
			if(connection!=null) {
				System.out.println("Connessione Stabilita");
			} else {
				System.out.println("Connessione NON Stabilita");
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return connection;
	}
	
	public boolean insert(Connection conn,carta_credito c) {
		try {
			if(!conn.isValid(5)) {
				return false;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		String query="INSERT INTO sweng.Carta_credito (numero,scadenza,cod,saldo) VALUES (?,?,?,?);";
		try {
			PreparedStatement statement=conn.prepareStatement(query);
			statement.setString(1, c.get_numero());
			//java.sql.LocalDate d = convertUtilToSql(c.get_scadenza());
			statement.setDate(2,java.sql.Date.valueOf( c.get_scadenza() ));
			statement.setInt(3, c.get_cod());
			statement.setDouble(4, c.get_saldo());
			statement.executeUpdate();
		} catch(SQLException e){
			e.printStackTrace();
		}
		return true;
	}
	
	public boolean update_saldo(Connection conn,carta_credito c) {
		try {
			if(!conn.isValid(5)) {
				return false;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		String query="UPDATE sweng.Carta_credito set saldo=? WHERE NUMERO=? ;";
		try {
			PreparedStatement statement=conn.prepareStatement(query);
			statement.setDouble(1, c.get_saldo());
			statement.setString(2, c.get_numero());
			statement.executeUpdate();
		} catch(SQLException e){
			e.printStackTrace();
		}
		return true;
	}
	
	public boolean check_presenza(Connection conn,carta_credito c) {
		try {
			if(!conn.isValid(5)) {
				return false;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		String query="SELECT carta_credito.cod from sweng.carta_credito where carta_credito.numero=?;";
		try {
			PreparedStatement statement=conn.prepareStatement(query);
			statement.setString(1, c.get_numero());
			ResultSet rs=statement.executeQuery();
			if(rs.next()) {
				return true;
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return false;
	}
}

