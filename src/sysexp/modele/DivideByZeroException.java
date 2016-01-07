package sysexp.modele;

@SuppressWarnings("serial")
public class DivideByZeroException extends Exception {
	
	public DivideByZeroException() {
		super("Impossible de diviser par 0");
	}
	
	public DivideByZeroException(String message) {
		super(message);
	}
}
