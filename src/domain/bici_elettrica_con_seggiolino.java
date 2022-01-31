package domain;

public class bici_elettrica_con_seggiolino extends bici_elettrica {
	private static String tipo="elettrica_con_seggiolino";
	public bici_elettrica_con_seggiolino(boolean danneggiata) {
		super(danneggiata);
		in_carica= true;
	}
	public bici_elettrica_con_seggiolino(String id,boolean danneggiata) {
		super(id,danneggiata);
	}
	public void print_info_bici() {
		super.print_info_bici();
		System.out.println("TIPO: ELETTRICA CON SEGGIOLINO");
	}
	public String get_tipo() {
		return this.tipo;
	}
}
