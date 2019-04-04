import java.util.Random;
public class hauptclass {
		

	public static void main(String[] args) {
		
		
		Card[] Karte;
		Karte = new Card[52];
		for(int i = 0; i < 52; i++) {
			Karte[i] = new Card((i%13)+1,i%4); 
		}
		// 52 Karten erstellt mit Nummern: 1=ass; 2=2, etc bis 13=K, und farben 0=Heart(H), 1=Dimond(D), 2=Spade(S), 3clubs(C)
		/*for(int i = 0; i<20; i++) {
			System.out.println(Karte[i].getfarbe());
		}*/
		
		
		
		tellCard(Karte[42]);
		
		arrayMix(Karte);
		tellCard(Karte[42]);
		
	}

	
	
	
	private static void tellCard(Card card) {
		String farbeBuchstabe = null;
		String nummerBuchstabe = null;
		int nummer = card.getNummer();
		int farbeZahl = card.getfarbe();
		switch (farbeZahl) {
		case 0:
			farbeBuchstabe = "H";
			break;
		case 1:
			farbeBuchstabe = "D";
			break;
		case 2:
			farbeBuchstabe = "S";
			break;
		case 3:
			farbeBuchstabe = "C";
			break;
		}
		if(nummer > 10) {
			switch(nummer) {
			case 11:
				nummerBuchstabe = "J";
				break;
			case 12:
				nummerBuchstabe = "D";
				break;
			case 13:
				nummerBuchstabe = "K";
				break;	
			}
		}
		else if (nummer == 1) {
			nummerBuchstabe = "A";
		}
		else {
			nummerBuchstabe = Integer.toString(nummer);
		}
		System.out.println("Karte: " + farbeBuchstabe + nummerBuchstabe);
		}
	
	
	
	
	

	private static Card[] arrayMix(Card[] karten) { 
		Card tmp; 
		int rand; 
	    Random r = new Random(); 
	    for (int i = 0; i < karten.length; i++) { 
	    	rand = r.nextInt(karten.length); 
	    	tmp = karten[i]; 
            karten[i] = karten[rand]; 
            karten[rand] = tmp; 
	        } 
	    return karten; 
	    } 

	   
	
	
}
