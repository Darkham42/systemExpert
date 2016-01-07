package sysexp.modele;

public class FaitEntier extends Fait<Integer> implements Noeud {
	private int signe;
	public FaitEntier(String symbole) {
		super(symbole);
		this.signe = 1;
	}
	
	@Override
	public long interpreter(BaseDeFaits baseDeFait) throws FaitInconnuException {
		
		if(baseDeFait.rechercher(this.symbole) == null)			
			throw new FaitInconnuException("Le Symbole " + this.symbole + " n'est pas présent dans la base de fait");
		
		return ((FaitEntier)baseDeFait.rechercher(this.symbole)).lireValeur() * signe;
	}
	
	public void attribuerUnSigne(int signe){
		this.signe = signe;
	}
	
	public String toString(){
		if(this.lireValeur() != null)
			return this.symbole + "(" + Integer.toString(this.lireValeur() * this.signe) + ")";
		
		return this.symbole;
	}
}
