package data;
import java.sql.Connection;
import domain.*;
public interface prelievoDaoInterface {
	public Connection connetti();
	public boolean insert(Connection conn,prelievo p);
}
