package sysexp.modele;

/**
 *
 * @author Darkham and Tanaky
 *	Une Forme Abstraite peut representer une premisse ou bien une conclusion
 */
public abstract class Forme {
	private String fait;
	
	/**
	 * 
	 * Constructeur d'une Forme Abstraite
	 * @param fait Le Symbole d'un Fait
	 */
	public Forme(String fait){
		this.fait = fait;
	}
	
	/**
	 * Accesseur au Symbole du Fait representant la partie gauche d'une premisse ou d'une conclusion
	 * @return
	 */
	public String lireFait(){
		return this.fait;
	}
	
	/**
	 * Autorise un visiteur a effectuer des traitements sur cette forme
	 * @param visitor le visiteur de la forme
	 */
	public abstract void accept(VisiteurForme visitor);
}
