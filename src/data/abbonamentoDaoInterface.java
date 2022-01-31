package data;
import domain.*;
import java.sql.Connection;

public interface abbonamentoDaoInterface {
	public Connection connetti();
	public boolean insert(Connection conn,abbonamento a);
	public boolean check_presenza(Connection conn,String cod);

}
