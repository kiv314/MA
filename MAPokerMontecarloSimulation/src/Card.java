public class Card {
	int nummer;
	int farbe; // Variablen

	public Card(int nummer, int farbe) { // Konstruktor
		this.nummer = nummer;
		this.farbe = farbe;
	}

	public int getNummer() {
		return this.nummer;
	}

	public int getfarbe() {
		return this.farbe;
	}

}
