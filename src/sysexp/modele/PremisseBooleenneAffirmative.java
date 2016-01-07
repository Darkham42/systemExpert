package sysexp.modele;

public class PremisseBooleenneAffirmative extends FormeBooleenne {

	public PremisseBooleenneAffirmative(String fait) {
		super(fait);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void accept(VisiteurForme visitor) {
		visitor.visite(this);
	}

}
