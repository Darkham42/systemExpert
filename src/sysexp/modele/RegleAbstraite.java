package sysexp.modele;

/**
 * @author Darkham and Tanaky
 */
public abstract class RegleAbstraite {
	private RegleAbstraite successeur;
	private boolean declenchee;
	protected long numeroRegle;
	protected Forme conclusion;
	
	public RegleAbstraite(Forme conclusion) {
		this.conclusion = conclusion;
		this.declenchee = false;
	}
	
	public void ajouterSuccesseur(RegleAbstraite regleAbstraite){
		this.successeur = regleAbstraite;
	}
	public boolean possedeSuccesseur(){
		return this.successeur != null;
	}
	
	/**
	 * 
	 * @return vrai si la regle a deja ete declenche
	 */
	public boolean estDeclenchee(){
		return this.declenchee;
	}
	
	/**
	 * Fait executer la regle par le visiteur
	 * @param visiteur
	 */
	public void declenchee(VisiteurForme visiteur){
		this.conclusion.accept(visiteur);
		this.declenchee = true;
	}
	
	/**
	 * Verifie si toutes les premisses sont verifier
	 * @param baseDeFaits
	 * @return vrai si la regle est declenchable
	 */
	public abstract boolean estDeclenchable(BaseDeFaits baseDeFaits);
	
	/**
	 * 
	 * @param baseDeFaits la base de fait representant les deductions du moteur d'inferences
	 * @return vrai si la regle a ete executer
	 * @throws DivideByZeroException si une division par zero a eut lieu
	 * @throws ConclusionIncoherenteException si une conclusion est incoherente
	 * @throws FaitInconnuException si le fait est inconnu
	 * 
	 * @see VisiteurFormeMoteur
	 */
	public boolean executerRegle(BaseDeFaits baseDeFaits) throws DivideByZeroException, ConclusionIncoherenteException, FaitInconnuException {
		VisiteurForme visiteur = new VisiteurFormeMoteur(baseDeFaits);
		boolean regleExecuter = false;
		
		if(!this.estDeclenchee() && this.estDeclenchable(baseDeFaits))
			this.declenchee(visiteur);
		
		if(!(regleExecuter = visiteur.derniereConclusionExecuter)){
			switch(visiteur.code){
			case AucuneErreur:
				break;
			case ConclusionIncoherente:
				throw new ConclusionIncoherenteException();
			case DivisionParZero:
				throw new DivideByZeroException();
			case FaitInconnu:
				throw new FaitInconnuException();
			
			}
		}
		
		System.out.println(this + " Executer: " + regleExecuter);
		if(this.possedeSuccesseur()){
			boolean regleSuccesseurExecuter = this.lireSuccesseur().executerRegle(baseDeFaits);
			return regleExecuter || regleSuccesseurExecuter; 
		}
		else
			return regleExecuter;
	}
	
	/**
	 * Permet de passer a la regle suivante de la base de regles que represente une RegleAbstraite
	 * @return
	 */
	public RegleAbstraite lireSuccesseur() {
		return this.successeur;
	}
}
