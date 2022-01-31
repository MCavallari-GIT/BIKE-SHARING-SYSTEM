package data;

import java.sql.Connection;
import java.util.ArrayList;

import domain.morsa;

public interface morsaDaoInterface {
	public Connection connetti() ;
	public boolean insert(Connection conn,morsa m) ;
	public ArrayList<morsa> select_morse(Connection conn,String r) ;
	public boolean update_morsa(Connection conn,morsa m,String id_bici) ;
}
