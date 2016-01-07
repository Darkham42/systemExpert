package sysexp.modele;

/**
 * @author Darkham and Tanaky
 * Represente un Noeud Abstrait d'une Expression Entiere
 */
public interface Noeud {
	public long interpreter(BaseDeFaits baseDeFait) throws FaitInconnuException, DivideByZeroException;
	/**
	 * Attribue un signe aux resultat du Noeud
	 * @param signe (-1 ou +1)
	 */
	public void attribuerUnSigne(int signe);
	
}
