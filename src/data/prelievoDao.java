package data;
import java.sql.Connection;
import domain.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.ResultSet;
public class prelievoDao implements prelievoDaoInterface  {
	static prelievoDao obj=new prelievoDao();
	private prelievoDao() {}
	public static prelievoDao get_instance() {
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
	
	public boolean insert(Connection conn,prelievo p) {
		try {
			if(!conn.isValid(5)) {
				return false;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		String query="INSERT INTO sweng.prelievo (inizio,utente,bicicletta) VALUES (?,?,?);";
		try {
			PreparedStatement statement=conn.prepareStatement(query);
			statement.setTimestamp(1, Timestamp.valueOf(p.get_inizio()));
			statement.setString(2, p.get_cod_utente());
			statement.setString(3, p.get_bici());
			statement.executeUpdate();
		} catch(SQLException e){
			e.printStackTrace();
		}
		return true;
	}
	
	
	public boolean update(Connection conn,String bici,String user) {
		try {
			if(!conn.isValid(5)) {
				return false;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		String query="update sweng.prelievo set fine=? where utente=? and bicicletta=? and fine is null;";
		try {
			PreparedStatement statement=conn.prepareStatement(query);
			statement.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
			statement.setString(2, user);
			statement.setString(3, bici);
			statement.executeUpdate();
		} catch(SQLException e){
			e.printStackTrace();
		}
		return true;
	}
	
	
	public boolean check_ultimo_prelievo(Connection conn,String utente) {
		//LocalTime t=LocalTime.of(, 00, 00, 0000000);
		try {
			if(!conn.isValid(5)) {
				return false;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		String query="select MAX(fine) from sweng.prelievo where utente=?;";
		try {
			PreparedStatement statement=conn.prepareStatement(query);
			statement.setString(1, utente);
			ResultSet rs=statement.executeQuery();
			while(rs.next()) {
				if(rs.getTime(1)==null) {
					return false;
				} else {
					LocalDateTime t = rs.getTimestamp(1).toLocalDateTime();
					if(t.plusMinutes(5).isAfter(LocalDateTime.now())){
						return true;
					}else {
						return false;
					}
				}
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean esiste_prelievo_aperto(Connection conn,String utente) {
		try {
			if(!conn.isValid(5)) {
				return false;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		String query="select * from sweng.prelievo where utente=? and fine is null;";
		try {
			PreparedStatement statement=conn.prepareStatement(query);
			statement.setString(1, utente);
			ResultSet rs=statement.executeQuery();
			while(rs.next()) {
				return true;
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return false;
	}

	public String select_bici_prelevata(Connection conn,String utente) {
		try {
			if(!conn.isValid(5)) {
				return "";
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		String query="select bicicletta from sweng.prelievo where utente=? and fine is null;";
		
		try {
			PreparedStatement statement=conn.prepareStatement(query);
			statement.setString(1, utente);
			ResultSet rs=statement.executeQuery();
			while(rs.next()) {
				return rs.getString(1);
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return "";
	}
	
	public prelievo get_prelievo(Connection conn,String utente,String bici) {
		prelievo p=new prelievo();
		try {
			if(!conn.isValid(5)) {
				return p;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		String query="select inizio from sweng.prelievo where utente=? and bicicletta=? and fine is null;";
		
		try {
			PreparedStatement statement=conn.prepareStatement(query);
			statement.setString(1, utente);
			statement.setString(2, bici);
			ResultSet rs=statement.executeQuery();
			while(rs.next()) {
				utenteDao uD=utenteDao.get_instance();
				utente u=uD.get_utente(uD.connetti(), utente);
				biciclettaDao bD=biciclettaDao.get_instance();
				bicicletta b=bD.get_bici(bD.connetti(), bici);
				p.set_utente(u);
				p.set_bicicletta(b);
				p.set_inizio(rs.getTimestamp(1).toLocalDateTime());
				return p;
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return p;
	}
	
	public String stat_prelievi(Connection conn) {
		try {
			if(!conn.isValid(5)) {
				return "-1";
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		String query1="select AVG(fine-inizio) from sweng.prelievo where fine is not null;";
		try {
			PreparedStatement statement=conn.prepareStatement(query1);
			ResultSet rs=statement.executeQuery();
			if(rs.next()) {
				return rs.getString(1);
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return "-1";
	}
}
