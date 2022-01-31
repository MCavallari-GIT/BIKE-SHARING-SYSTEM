package data;
import domain.*;



import java.sql.Date;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
//import java.util.Date;
public class abbonamentoDao implements abbonamentoDaoInterface{
	static abbonamentoDao obj=new abbonamentoDao();
	private abbonamentoDao() {}
	public static abbonamentoDao get_instance() {
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
	
	public boolean insert(Connection conn,abbonamento a) {
		try {
			if(!conn.isValid(5)) {
				return false;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		String query;
		query="INSERT INTO sweng.abbonamento (id,durata,ammonizioni,carta,is_attivo,scadenza) VALUES (?,?,?,?,?,?);";
		try {
			PreparedStatement statement=conn.prepareStatement(query);
			statement.setString(1, a.get_id());
			if(a.get_durata().equals(durata.annuale)) {
				statement.setString(2,"annuale");
			}
			if(a.get_durata().equals(durata.settimanale)) {
				statement.setString(2,"settimanale");
			}
			if(a.get_durata().equals(durata.giornaliero)) {
				statement.setString(2,"giornaliero");
			}
			statement.setInt(3, a.get_ammonizioni());
			statement.setString(4, a.get_carta().get_numero());
			statement.setBoolean(5, a.get_attivo());
			if(a.get_scadenza()!=null) {
				statement.setDate(6, Date.valueOf(a.get_scadenza()));
			} else {
				statement.setDate(6, null);
			}
			statement.executeUpdate();
		} catch(SQLException e){
			e.printStackTrace();
		}
		return true;
	}
	
	public boolean check_presenza(Connection conn,String cod) {
		try {
			if(!conn.isValid(5)) {
				return false;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		String query="SELECT * from sweng.abbonamento INNER JOIN sweng.utente ON abbonamento.id=utente.abbonamento where  utente.cod_utente=? and (abbonamento.scadenza>now() or scadenza is null);";
		try {
			PreparedStatement statement=conn.prepareStatement(query);
			statement.setString(1, cod);
			ResultSet rs=statement.executeQuery();
			if(rs.next()) {
				return true;
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return false;
	}
	public boolean update(Connection conn,abbonamento a) {
		try {
			if(!conn.isValid(5)) {
				return false;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		String query="UPDATE sweng.abbonamento set is_attivo=?, ammonizioni=?,scadenza=? WHERE id=? ;";
		try {
			PreparedStatement statement=conn.prepareStatement(query);
			statement.setBoolean(1, a.get_attivo());
			statement.setInt(2, a.get_ammonizioni());
			System.out.println("--------------------------------"+a.get_scadenza());
			statement.setDate(3, Date.valueOf(a.get_scadenza()));
			statement.setString(4, a.get_id());
			statement.executeUpdate();
		} catch(SQLException e){
			e.printStackTrace();
		}
		return true;
	}
	
	public boolean delete_abbonamento(Connection conn,utente u) {
		try {
			if(!conn.isValid(5)) {
				return false;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		String query4="DELETE FROM sweng.prelievo where utente=? ;";
		try {
			PreparedStatement statement=conn.prepareStatement(query4);
			statement.setString(1,u.get_codice());
			statement.executeUpdate();
		} catch(SQLException e){
			e.printStackTrace();
		}
		
		String query3="DELETE FROM sweng.utente where cod_utente=? ;";
		try {
			PreparedStatement statement=conn.prepareStatement(query3);
			statement.setString(1,u.get_codice());
			statement.executeUpdate();
		} catch(SQLException e){
			e.printStackTrace();
		}
		
		
		
		
		
		
		String query2="DELETE FROM sweng.abbonamento where id=? ;";
		try {
			PreparedStatement statement=conn.prepareStatement(query2);
			statement.setString(1,u.get_abbobamento().get_id());
			statement.executeUpdate();
		} catch(SQLException e){
			e.printStackTrace();
		}
		String query="DELETE FROM sweng.carta_credito where numero=? ;";
		try {
			PreparedStatement statement=conn.prepareStatement(query);
			statement.setString(1,u.get_abbobamento().get_carta().get_numero());
			statement.executeUpdate();
		} catch(SQLException e){
			e.printStackTrace();
		}
		return true;
	}
	private static java.sql.Date convertUtilToSql(java.util.Date uDate) {
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
    }
}

