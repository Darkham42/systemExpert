package sysexp.modele;

public class ConclusionBooleenneAffirmative extends FormeBooleenne {

	public ConclusionBooleenneAffirmative(String fait) {
		super(fait);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void accept(VisiteurForme visitor) {
		visitor.visite(this);
	}

}
