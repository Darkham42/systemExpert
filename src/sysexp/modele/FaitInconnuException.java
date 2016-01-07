package sysexp.modele;

@SuppressWarnings("serial")
public class FaitInconnuException extends Exception {
	
	public FaitInconnuException() {
		super("Une exception de type FaitInconnuException s'est produite");
	}
	
	public FaitInconnuException(String message) {
		super(message);
	}
}
