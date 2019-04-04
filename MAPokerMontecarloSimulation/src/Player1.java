
public class Player1 {
	int Position;
	Card Blatt1;
	Card Blatt2;
	
	public Player1(Card Blatt1, Card Blatt2) {		// Konstruktor
		this.Blatt1 = Blatt1;
		this.Blatt2 = Blatt2;
	}
	public void setBlatt1(Card Blatt1) {
		Blatt1 = this.Blatt1;
	}
	
	public Card getBlatt1() {
		return Blatt1;
	}
	
}
