package data;

import java.sql.Connection;
import java.util.ArrayList;

import domain.rastrelliera;

public interface rastrellieraDaoInterface {
	public Connection connetti() ;
	public boolean insert(Connection conn,rastrelliera r) ;
	public ArrayList<String> select_id(Connection conn) ;
	public rastrelliera find_rast(Connection conn,String id) ;
}
