package sysexp.modele;

public class Division extends OperateurBinaire {

	public Division(Noeud gauche, Noeud droite) {
		super(gauche, droite);
		// TODO Auto-generated constructor stub
	}

	@Override
	public long interpreter(BaseDeFaits baseDeFait) throws FaitInconnuException, DivideByZeroException {
		// TODO Auto-generated method stub
		long value = this.droite.interpreter(baseDeFait);
		if(value == 0){
			throw new DivideByZeroException();
		}
			
		return signe * (this.gauche.interpreter(baseDeFait) / value);
	}
	
	public String toString(){
		return "(" + this.gauche + " / " + this.droite + ")";
	}

}
