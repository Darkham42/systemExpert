package sysexp.modele;

public class MoteurInference {
	public static void deduire(RegleAbstraite baseDeRegles, BaseDeFaits baseDeFaits){
		try{
			while(baseDeRegles.executerRegle(baseDeFaits)){
				System.out.println("\nIteration\n");
			};
		}
		catch(FaitInconnuException | DivideByZeroException | ConclusionIncoherenteException e){
			System.err.println("Erreur: " + e.getMessage());
			return;
		}
	}
}
