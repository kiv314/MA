
public class Player1 {
	String spielerName;
	float anzahlBlattpaare = 0;
	int position;
	Card Blatt1 = null;
	Card Blatt2 = null;;
	Card Flop1  = null;
	
	
	public Player1(String spielerName, Card Blatt1, Card Blatt2) {		// Konstruktor
		this.spielerName = spielerName;
		this.Blatt1 = Blatt1;
		this.Blatt2 = Blatt2;
	}
	public void setBlatt1(Card Blatt) {
		this.Blatt1 = Blatt;
	}
	public void setBlatt2(Card Blatt) {
		this.Blatt2 = Blatt;
	}
	
	public Card getBlatt1() {
		return Blatt1;
	}
	public Card getBlatt2() {
		return Blatt2;
	}
	public float getAnzahlBlattPaare() {
		return anzahlBlattpaare;
	}
	
	
	public void machZug() {
		if ((Blatt1.nummer == 1|| Blatt1.nummer == 13) && (Blatt2.nummer == 1|| Blatt2.nummer == 13)) {
			if(!(Blatt1.nummer == Blatt2.nummer)) {
			//System.out.println(spielerName + ": Habe ein Paar!");
				anzahlBlattpaare+= 1;
			}
		}
		
	}
	
	
}

