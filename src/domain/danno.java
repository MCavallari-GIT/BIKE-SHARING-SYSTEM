package domain;

public class danno {
	String danno;
	String id_bici;
	public danno(String id) {
		this.id_bici=id;
	}
	public danno(String id,String danno) {
		this.id_bici=id;
		this.danno=danno;
	}
	public String get_bici() {
		return this.id_bici;
	}
	public String get_danno() {
		return this.danno;
	}
}
