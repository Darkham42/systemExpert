package sysexp.builders;

import java.io.LineNumberReader;

import sysexp.modele.RegleAbstraite;

/**
 * 
 * @author Darkham and Tanaky
 * Builder Abstrait qui represente tout les builders des grammaires
 *
 */
public abstract class Builder {
	private LineNumberReader reader;
	private RegleAbstraite baseDeRegles;
	public Builder(LineNumberReader reader) {
		this.reader = reader;
		this.baseDeRegles = null;
	}
	
	/**
	 * Construit la base de regles
	 */
	public abstract void build();
	
	/**
	 * Accesseur de la base de regles
	 * @return
	 */
	public RegleAbstraite lireBaseDeRegles(){
		return this.baseDeRegles;
	}
	
	/**
	 * Mutateur de la base de regles
	 * @param baseDeRegles
	 */
	protected void ecrireBaseDeRegle(RegleAbstraite baseDeRegles) {
		this.baseDeRegles = baseDeRegles;
	}
}
