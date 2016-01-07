package sysexp.modele;

public class VisiteurFormeMoteur extends VisiteurForme {

	public VisiteurFormeMoteur(BaseDeFaits baseDeFaits) {
		super(baseDeFaits);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void visite(PremisseBooleenneAffirmative forme) {
		if(!this.baseDeFaits.existe(forme.lireFait()))
			return;
		
		FaitBooleen fait = (FaitBooleen) this.baseDeFaits.rechercher(forme.lireFait());
		if(fait.lireValeur() == true){
			this.dernierePremisseVerifier = true;
			return;
		}
	}

	@Override
	public void visite(PremisseBooleenneNegative forme) {
		
		if(!this.baseDeFaits.existe(forme.lireFait()))
			return;
		
		FaitBooleen fait = (FaitBooleen) this.baseDeFaits.rechercher(forme.lireFait());
		if(fait.lireValeur() == false){
			this.dernierePremisseVerifier = true;
			return;
		}
	}

	@Override
	public void visite(PremisseEntiereFait forme) {
		if(!this.baseDeFaits.existe(forme.lireFait()))
			return;
		
		if(!this.baseDeFaits.existe(forme.lireValeur().lireSymbole()))
			return;
		
		FaitEntier fait = (FaitEntier) this.baseDeFaits.rechercher(forme.lireFait());
		FaitEntier fait2 = (FaitEntier) this.baseDeFaits.rechercher(forme.lireValeur().lireSymbole());
		
		if(forme.lireComparateur().comparer(fait.lireValeur(), fait2.lireValeur()))
			this.dernierePremisseVerifier = true;
	}

	@Override
	public void visite(PremisseEntiereExpression forme) {
		if(!this.baseDeFaits.existe(forme.lireFait()))
			return;
		
		FaitEntier fait = (FaitEntier) this.baseDeFaits.rechercher(forme.lireFait());
		Noeud valeur = forme.lireValeur();
		
		try {
			if(forme.lireComparateur().comparer(fait.lireValeur(), (int) valeur.interpreter(this.baseDeFaits)))
				this.dernierePremisseVerifier = true;
		} 
		catch (FaitInconnuException e){
			this.code = CodeErreur.ConclusionIncoherente;
		} catch(DivideByZeroException e) {
			this.code = CodeErreur.DivisionParZero;
		}
	}

	@Override
	public void visite(PremisseSymboliqueFait forme) {
		if(!this.baseDeFaits.existe(forme.lireFait()))
			return;
		
		if(!this.baseDeFaits.existe(forme.lireValeur()))
			return;
		
		FaitSymbolique fait = (FaitSymbolique) this.baseDeFaits.rechercher(forme.lireFait());
		FaitSymbolique fait2 = (FaitSymbolique) this.baseDeFaits.rechercher(forme.lireValeur());
		
		if(!fait.lireValeur().equals(fait2.lireValeur()))
			this.dernierePremisseVerifier = true;
	}

	@Override
	public void visite(PremisseSymboliqueConstante forme) {
		if(!this.baseDeFaits.existe(forme.lireFait()))
			return;
		
		FaitSymbolique fait = (FaitSymbolique) this.baseDeFaits.rechercher(forme.lireFait());

		if(fait.lireValeur().equals(forme.lireValeur()))
			this.dernierePremisseVerifier = true;
	}

	@Override
	public void visite(ConclusionBooleenneAffirmative forme) {
		
		FaitBooleen fait = new FaitBooleen(forme.lireFait());
		fait.mettreAJourValeur(true);
		if(!this.baseDeFaits.existe(fait.lireSymbole()))
			this.baseDeFaits.ajouter(fait.lireSymbole(), fait);
		else
			((FaitBooleen) this.baseDeFaits.rechercher(fait.lireSymbole())).mettreAJourValeur(fait.lireValeur());
		
		this.derniereConclusionExecuter = true;
	}

	@Override
	public void visite(ConclusionBooleenneNegative forme) {
		FaitBooleen fait = new FaitBooleen(forme.lireFait());
		fait.mettreAJourValeur(false);

		if(!this.baseDeFaits.existe(fait.lireSymbole()))
			this.baseDeFaits.ajouter(fait.lireSymbole(), fait);
		else
			((FaitBooleen) this.baseDeFaits.rechercher(fait.lireSymbole())).mettreAJourValeur(fait.lireValeur());
		
		this.derniereConclusionExecuter = true;
	}

	@Override
	public void visite(ConclusionEntiereFait forme) {
		
		if(!this.baseDeFaits.existe(forme.lireValeur().lireSymbole())){
			this.code = CodeErreur.ConclusionIncoherente;
		}
		
		FaitEntier fait = new FaitEntier(forme.lireFait());
		fait.mettreAJourValeur( (Integer) this.baseDeFaits.rechercher(forme.lireValeur().lireSymbole()).lireValeur());

		if(!this.baseDeFaits.existe(fait.lireSymbole()))
			this.baseDeFaits.ajouter(fait.lireSymbole(), fait);
		else
			((FaitEntier) this.baseDeFaits.rechercher(fait.lireSymbole())).mettreAJourValeur(fait.lireValeur());
		
		this.derniereConclusionExecuter = true;	
	}

	@Override
	public void visite(ConclusionEntiereExpression forme) {
		try {
			FaitEntier fait = new FaitEntier(forme.lireFait());
			int valeur = (int) forme.lireValeur().interpreter(this.baseDeFaits);
			fait.mettreAJourValeur(valeur);
			
			if(!this.baseDeFaits.existe(fait.lireSymbole()))
				this.baseDeFaits.ajouter(fait.lireSymbole(), fait);
			else
				((FaitEntier) this.baseDeFaits.rechercher(fait.lireSymbole())).mettreAJourValeur(fait.lireValeur());
			
			this.derniereConclusionExecuter = true;
		} 
		catch (FaitInconnuException e){
			this.code = CodeErreur.ConclusionIncoherente;
		} catch(DivideByZeroException e) {
			this.code = CodeErreur.DivisionParZero;
		}
	}

	@Override
	public void visite(ConclusionSymboliqueFait forme) {
		if(!this.baseDeFaits.existe(forme.lireValeur())){
			this.code = CodeErreur.ConclusionIncoherente;
		}
		
		FaitSymbolique fait = new FaitSymbolique(forme.lireFait());
		fait.mettreAJourValeur( (String) this.baseDeFaits.rechercher(forme.lireValeur()).lireValeur());
		
		if(!this.baseDeFaits.existe(fait.lireSymbole()))
			this.baseDeFaits.ajouter(fait.lireSymbole(), fait);
		else
			((FaitSymbolique) this.baseDeFaits.rechercher(fait.lireSymbole())).mettreAJourValeur(fait.lireValeur());
		
		this.derniereConclusionExecuter = true;		
	}

	@Override
	public void visite(ConclusionSymboliqueConstante forme) {
		FaitSymbolique fait = new FaitSymbolique(forme.lireFait());
		fait.mettreAJourValeur(forme.lireValeur());
		
		if(!this.baseDeFaits.existe(fait.lireSymbole()))
			this.baseDeFaits.ajouter(fait.lireSymbole(), fait);
		else
			((FaitSymbolique) this.baseDeFaits.rechercher(fait.lireSymbole())).mettreAJourValeur(fait.lireValeur());
		
		this.derniereConclusionExecuter = true;
	}
}
