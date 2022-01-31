package data;

import java.sql.Connection;

import domain.morsa;
import domain.totem;

public interface totemDaoInterface {
	public Connection connetti() ;
	public boolean insert(Connection conn,totem t) ;
	public boolean delete_stazione(Connection conn,String stazione) ;
	public morsa preleva_bici(Connection conn,String stazione,String tipo) ;
	public int stat_stazioni(Connection conn) ;
}
