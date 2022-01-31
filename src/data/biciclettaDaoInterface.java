package data;

import java.sql.Connection;
import java.util.ArrayList;

import domain.bicicletta;

public interface biciclettaDaoInterface {
	public Connection connetti();
	public boolean insert(Connection conn,bicicletta b);
	public ArrayList<String> select_id(Connection conn);
	public boolean delete_bici(Connection conn,String id);
	public bicicletta get_bici(Connection conn,String id) ;
	public boolean delete_bici_from_rast(Connection conn,String id_bici,String id_rast) ;
	public boolean is_presente(Connection conn,String id_bici,String id_rast) ;
	public boolean update(Connection conn,boolean danno,String id_bici) ;
	public String stat_bici(Connection conn) ;
}
