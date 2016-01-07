package sysexp.modele;

public class Addition extends OperateurBinaire {

	public Addition(Noeud gauche, Noeud droite) {
		super(gauche, droite);
		// TODO Auto-generated constructor stub
	}

	@Override
	public long interpreter(BaseDeFaits baseDeFait) throws FaitInconnuException, DivideByZeroException {
		return signe * (this.gauche.interpreter(baseDeFait) + this.droite.interpreter(baseDeFait) );
	}
	
	public String toString(){
		return "(" + this.gauche + " + " + this.droite + ")";
	}

}
