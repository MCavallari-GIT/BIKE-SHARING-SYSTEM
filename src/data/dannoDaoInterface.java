package data;

import java.sql.Connection;

import domain.danno;

public interface dannoDaoInterface {
	public Connection connetti() ;
	public boolean insert(Connection conn,danno d) ;
}
