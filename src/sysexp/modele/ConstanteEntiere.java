package sysexp.modele;

public class ConstanteEntiere implements Noeud {
	private int value, signe;
	
	public ConstanteEntiere(int value) {
		this.value = value;
		this.signe = 1;
	}

	@Override
	public long interpreter(BaseDeFaits baseDeFait) {
		// TODO Auto-generated method stub
		return this.signe * this.value;
	}

	@Override
	public void attribuerUnSigne(int signe) {
		this.signe = signe;
	}
	
	public String toString(){
		return Integer.toString(this.value* signe);
	}
}
