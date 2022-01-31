package data;
import java.sql.Connection;
import java.time.*;
import domain.*;
import java.time.LocalDate;
import java.text.SimpleDateFormat;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.time.ZoneId;
import java.sql.Date;
public class utenteDao implements utenteDaoInterface {
	
	static utenteDao obj=new utenteDao();
	private utenteDao() {}
	public static utenteDao get_instance() {
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
	
	public boolean insert(Connection conn,utente u) {
		try {
			if(!conn.isValid(5)) {
				return false;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		String query="INSERT INTO sweng.Utente (cod_utente,nome,cognome,password,studente,abbonamento) VALUES (?,?,?,?,?,?);";
		try {
			PreparedStatement statement=conn.prepareStatement(query);
			statement.setString(1, u.get_codice());
			statement.setString(2, u.get_nome());
			statement.setString(3, u.get_cognome());
			statement.setString(4, u.get_psw());
			statement.setBoolean(5, u.get_studente());
			statement.setString(6, u.get_abbobamento().get_id());
			statement.executeUpdate();
		} catch(SQLException e){
			e.printStackTrace();
		}
		return true;
	}
	
	public boolean check_presenza(Connection conn,String cod,String psw) {
		try {
			if(!conn.isValid(5)) {
				return false;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		String query="SELECT * from sweng.Utente where  cod_utente=? AND password=?;";
		try {
			PreparedStatement statement=conn.prepareStatement(query);
			statement.setString(1, cod);
			statement.setString(2, psw);
			ResultSet rs=statement.executeQuery();
			if(rs.next()) {
				return true;
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean update_abbonamento(Connection conn,String cod) {
		try {
			if(!conn.isValid(5)) {
				return false;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		String query="UPDATE sweng.abbonamento SET is_attivo = true FROM sweng.utente WHERE abbonamento.id = utente.abbonamento AND utente.cod_utente=?;";
		try {
			PreparedStatement statement=conn.prepareStatement(query);
			statement.setString(1, cod);
			statement.executeUpdate();
		} catch(SQLException e){
			e.printStackTrace();
		}
		return true;
	}
	
	public utente get_utente(Connection conn,String cod_utente) {
		utente u=new utente();
		u.print_utente();
		try {
			if(!conn.isValid(5)) {
				return u;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		String query="SELECT * FROM sweng.utente INNER JOIN sweng.abbonamento on utente.abbonamento=abbonamento.id INNER JOIN sweng.carta_credito on abbonamento.carta=carta_credito.numero where utente.cod_utente=?;";
		try {
			PreparedStatement statement=conn.prepareStatement(query);
			statement.setString(1, cod_utente);
			ResultSet rs=statement.executeQuery();
			if(rs.next()) {
				u.set_cod(rs.getString(1));
				u.set_nome(rs.getString(2));
				u.set_cognome(rs.getString(3));
				u.set_psw(rs.getString(4));
				u.set_studente(rs.getBoolean(5));
				String d=rs.getString(7);
				//u.set_studente(rs.getBoolean(5));
				abbonamento a=new abbonamento();
				if(d.equals("annuale")) {
					a.set_durata(durata.annuale);
					a.set_costo(36);
				}
				if(d.equals("settimanale")) {
					a.set_durata(durata.settimanale);
					a.set_costo(9);
				}
				if(d.equals("giornaliero")) {
					a.set_durata(durata.giornaliero);
					a.set_costo(4.5);
				}
				a.set_ammonizioni(rs.getInt(8));
				a.set_attivo(rs.getBoolean(10));
				a.set_id(rs.getString(11));
				if(rs.getDate(12)==null) {
					a.set_scadenza(null);
				} else {
					a.set_scadenza(rs.getDate(12).toLocalDate());
				}
				

				carta_credito c=new carta_credito();
				c.set_numero(rs.getString(13));

				c.set_scadenza(rs.getDate(14).toLocalDate());

				    //return safeDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				
				c.set_cod(rs.getInt(15));
				c.set_saldo(rs.getDouble(16));
				c.print_carta();
				
				
				a.set_carta(c);
				u.set_abbonamento(a);
			}else {
				u=null;
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return u;
	}
	
	public int stat_utenti(Connection conn) {
		try {
			if(!conn.isValid(5)) {
				return -1;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		String query1="SELECT COUNT(*) FROM sweng.utente;";
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
