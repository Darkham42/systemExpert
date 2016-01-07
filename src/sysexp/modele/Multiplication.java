package sysexp.modele;

public class Multiplication extends OperateurBinaire {

	public Multiplication(Noeud gauche, Noeud droite) {
		super(gauche, droite);
		// TODO Auto-generated constructor stub
	}

	@Override
	public long interpreter(BaseDeFaits baseDeFait) throws FaitInconnuException, DivideByZeroException {
		// TODO Auto-generated method stub
		return signe * (this.gauche.interpreter(baseDeFait) * this.droite.interpreter(baseDeFait));
	}
	
	public String toString(){
		return "(" + this.gauche + " x " + this.droite + ")";
	}
}
