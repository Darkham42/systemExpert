package sysexp.modele;

public abstract class FormeEntiere extends Forme {
	private Comparateur comparateur;
	public FormeEntiere(String fait, Comparateur comparateur) {
		super(fait);
		this.comparateur = comparateur;
		// TODO Auto-generated constructor stub
	}
	
	public Comparateur lireComparateur(){
		return this.comparateur;
	}
}
