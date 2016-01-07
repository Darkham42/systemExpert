package sysexp.modele;

public enum Comparateur {
	egale("=") {
		@Override
		public boolean comparer(int valeur, int valeur2) {
			return valeur == valeur2;
		}
	},
	different("/=") {
		@Override
		public boolean comparer(int valeur, int valeur2) {
			return valeur != valeur2;
		}
	},
	superieur(">") {
		@Override
		public boolean comparer(int valeur, int valeur2) {
			return valeur > valeur2;
		}
	},
	inferieur("<") {
		@Override
		public boolean comparer(int valeur, int valeur2) {
			return valeur < valeur2;
		}
	},
	superieurOuEgale(">=") {
		@Override
		public boolean comparer(int valeur, int valeur2) {
			return valeur >= valeur2;
		}
	},
	inferieurOuEgale("<=") {
		@Override
		public boolean comparer(int valeur, int valeur2) {
			return valeur <= valeur2;
		}
	};
	
	private String representation;
	private Comparateur(String representation){
		this.representation = representation;
	}
	
	public String lireRepresentation(){
		return this.representation;
	}
	
	public abstract boolean comparer(int valeur, int valeur2);
}
