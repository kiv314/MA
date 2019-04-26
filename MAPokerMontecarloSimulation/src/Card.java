public class Card {
	int nummer;
	int farbe; // Variablen
	int wert;
	
	public Card(int nummer, int farbe) { // Konstruktor
		this.nummer = nummer;
		this.farbe = farbe;
		if(nummer == 1) {
			this.wert = 14;
		}
		else {this.wert = nummer;}
	}
	

	public int getNummer() {
		return this.nummer;
	}

	public int getfarbe() {
		return this.farbe;
	}
	
	public int getWert() {
		return this.wert;
	}

}
