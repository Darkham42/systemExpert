package sysexp.modele;

public class PremisseBooleenneNegative extends FormeBooleenne {

	public PremisseBooleenneNegative(String fait) {
		super(fait);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void accept(VisiteurForme visitor) {
		visitor.visite(this);
	}

}
