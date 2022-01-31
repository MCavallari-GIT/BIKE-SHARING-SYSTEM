package utils;
import java.util.Random;

public class cod_generator {
	public cod_generator() {
		
	}
	public String genera_codice() {
		String cod="";
    	String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    	StringBuilder salt = new StringBuilder();
    	Random rnd = new Random();
    	while (salt.length() < 10) { // length of the random string.
    		int index = (int) (rnd.nextFloat() * SALTCHARS.length());
    	    salt.append(SALTCHARS.charAt(index));
    	    cod = salt.toString();
    	}
    	return cod;
	}
}
