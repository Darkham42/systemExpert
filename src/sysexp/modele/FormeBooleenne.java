package sysexp.modele;

public abstract class FormeBooleenne extends Forme {

	public FormeBooleenne(String fait) {
		super(fait);
		// TODO Auto-generated constructor stub
	}
	
	public String toString(){
		return this.lireFait();
	}
}
