public class Card {
	int nummer;
	int farbe; // Variablen
	double wert;
	
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
	
	public double getWert() {
		return this.wert;
	}

}
