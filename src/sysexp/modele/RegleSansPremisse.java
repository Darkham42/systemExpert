package sysexp.modele;

public class RegleSansPremisse extends RegleAbstraite {

	public RegleSansPremisse(Forme conclusion) {
		super(conclusion);
	}

	@Override
	public String toString() {
		return "> " + this.conclusion;
	}

	@Override
	public boolean estDeclenchable(BaseDeFaits baseDeFaits) {
		return true;
	}

}
