package data;
import java.sql.Connection;
import domain.*;
public interface carta_creditoDaoInterface {
	public Connection connetti();
	public boolean insert(Connection conn,carta_credito c);
	public boolean update_saldo(Connection conn,carta_credito c);
}
