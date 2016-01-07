package sysexp.modele;

@SuppressWarnings("serial")
public class ConclusionIncoherenteException extends Exception {
	
	public ConclusionIncoherenteException() {
		super("Une conclusion incohérente à eu lieu");
	}
	
	public ConclusionIncoherenteException(String message){
		super(message);
	}
}
