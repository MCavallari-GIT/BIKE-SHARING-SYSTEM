package domain;

public class bici_elettrica extends bicicletta{
	protected boolean in_carica;
	private static String tipo="elettrica";

	public bici_elettrica(boolean danneggiata) {
		super(danneggiata);
		in_carica= true;
	}
	public bici_elettrica(String id,boolean danneggiata) {
		super(id,danneggiata);
	}
	public boolean isCharging() {
		return in_carica;
	}

	public void charge(boolean val) {
		in_carica = val;
	}
	public void print_info_bici() {
		super.print_info_bici();
		System.out.println("TIPO: ELETTRICA");
	}
	public String get_tipo() {
		return this.tipo;
	}
}
