
public class hauptclass {

	public static void main(String[] args) {
		
		Card[] Karte;
		Karte = new Card[52];
		for(int i = 0; i < 52; i++) {
			Karte[i] = new Card((i+1)%13,i%4); 
		}
		
		for(int i = 0; i<10; i++) {
			System.out.println(Karte[i].getfarbe());
		}
	}

}
