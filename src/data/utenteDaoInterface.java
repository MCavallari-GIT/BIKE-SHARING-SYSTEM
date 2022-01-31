package data;
import java.sql.Connection;
import domain.*;
public interface utenteDaoInterface {
	public Connection connetti();
	public boolean insert(Connection conn,utente u);
	public boolean check_presenza(Connection conn,String cod,String psw);
	public boolean update_abbonamento(Connection conn,String cod);
	
}
