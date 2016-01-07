package sysexp.modele;

public class ConclusionSymboliqueFait extends FormeSymbolique {

	public ConclusionSymboliqueFait(String fait, Comparateur comparateur, String valeur) {
		super(fait, comparateur, valeur);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void accept(VisiteurForme visitor) {
		visitor.visite(this);
	}

}
