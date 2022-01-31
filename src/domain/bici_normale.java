package domain;

public class bici_normale extends bicicletta {
	private String tipo="normale";
	public bici_normale(boolean danneggiata) {
		super(danneggiata);
	}
	public bici_normale(String id,boolean danneggiata) {
		super(id,danneggiata);
	}
	public void print_info_bici() {
		super.print_info_bici();
		System.out.println("TIPO: NORMALE");
	}
	public String get_tipo() {
		return this.tipo;
	}
}
