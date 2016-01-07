package sysexp.builders;

/**
 * 
 * @author Darkham and Tanaky
 * Dirige la construction de la baseDeRegles sans se soucier du builder utilise et donc de la grammaire 
 */
public class Director {
	private Builder builder;
	
	/**
	 * 
	 * Constructeur de Director
	 * @param builder Le Builder concret
	 */
	public Director(Builder builder) {
		// TODO Auto-generated constructor stub
		this.builder = builder;
	}

	/**
	 * Accesseur du Builder
	 * @return
	 */
	public Builder getBuilder() {
		return builder;
	}

	/**
	 * Mutateur du Builder
	 * @param builder
	 */
	public void setBuilder(Builder builder) {
		this.builder = builder;
	}
	
	/**
	 * Dirige la construction de la base de regles
	 */
	public void construct(){
		this.builder.build();
	}
}
