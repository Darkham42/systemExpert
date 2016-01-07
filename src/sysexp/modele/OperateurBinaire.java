package sysexp.modele;

/**
 * @author Darkham and Tanaky
 *	Represente le Noeud d'une Expression
 */
public abstract class OperateurBinaire implements Noeud {
	protected Noeud gauche;
	protected Noeud droite;
	protected int signe;
	
	/**
	 * 
	 * Un Operateur Binaire possede une partie gauche et droite
	 * @param gauche La partie gauche
	 * @param droite La partie droite
	 */
	public OperateurBinaire(Noeud gauche, Noeud droite) {
		this.gauche = gauche;
		this.droite = droite;
		this.signe = 1;
	}
	
	@Override
	public void attribuerUnSigne(int signe){
		this.signe = signe;
	}
}
