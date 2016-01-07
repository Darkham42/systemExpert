package sysexp.modele;

public class ConstanteSymbolique {
	private String constante;
	public ConstanteSymbolique(String symbole) {
		this.constante = symbole;
	}
	
	public String lireConstante(){
		return this.constante;
	}
}
