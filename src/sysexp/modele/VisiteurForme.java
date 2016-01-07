package sysexp.modele;

/**
 * @author Darkham and Tanaky
 *	Visiteur Abstrait
 *	Un VisiteurForme est charger de visiter une Forme et d'indiquer si cette forme est verifier ou executable
 *  en fonction de s'il s'agit d'une premisse ou d'une conclusion
 */
public abstract class VisiteurForme {
	protected BaseDeFaits baseDeFaits;
	protected boolean dernierePremisseVerifier;
	protected boolean derniereConclusionExecuter;
	protected CodeErreur code;
	
	enum CodeErreur{
		AucuneErreur,
		DivisionParZero,
		ConclusionIncoherente,
		FaitInconnu;
	}
	
	public VisiteurForme(BaseDeFaits baseDeFaits) {
		this.baseDeFaits = baseDeFaits;
		this.derniereConclusionExecuter = false;
		this.dernierePremisseVerifier = false;
		
		this.code = CodeErreur.AucuneErreur;
	}
	
	public abstract void visite(PremisseBooleenneAffirmative forme);
	public abstract void visite(PremisseBooleenneNegative forme);
	public abstract void visite(PremisseEntiereFait forme);
	public abstract void visite(PremisseEntiereExpression forme);
	public abstract void visite(PremisseSymboliqueFait forme);
	public abstract void visite(PremisseSymboliqueConstante forme);

	public abstract void visite(ConclusionBooleenneAffirmative forme);
	public abstract void visite(ConclusionBooleenneNegative forme);
	public abstract void visite(ConclusionEntiereFait forme);
	public abstract void visite(ConclusionEntiereExpression forme);
	public abstract void visite(ConclusionSymboliqueFait forme);
	public abstract void visite(ConclusionSymboliqueConstante forme);

	public void reinitialiser() {
		this.code = CodeErreur.AucuneErreur;
		this.derniereConclusionExecuter = false;
		this.dernierePremisseVerifier = false;
	}
}
