package sysexp.modele;

public class ConclusionBooleenneNegative extends FormeBooleenne {

	public ConclusionBooleenneNegative(String fait) {
		super(fait);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void accept(VisiteurForme visitor) {
		visitor.visite(this);
	}

}
