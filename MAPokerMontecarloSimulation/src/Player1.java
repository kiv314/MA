import java.util.Scanner;

public class Player1 {
	String spielerName;
	boolean inRunde;
	boolean manuel;
	float anzahlBlattpaare = 0;
	int position;
	Card Flop1 = null;
	Card Flop2 = null;
	Card[] blatt = null;
	Card[] bestHand = null;
	double score = 0;
	int taktik;
	int chips;

	private Scanner scanner;

	public Player1(String spielerName, boolean manuel, boolean inRunde, int taktik) { // Konstruktor
		this.inRunde = inRunde;
		this.manuel = manuel;
		this.spielerName = spielerName;
		this.blatt = blatt;
		this.taktik = taktik;
	}

	public void setBlatt(Card[] blatt) {
		this.blatt = blatt;
	}

	public void setInRunde(boolean inRunde) {
		this.inRunde = inRunde;
	}

	public Card[] getBlatt() {
		return blatt;
	}

	public boolean getInRunde() {
		return this.inRunde;
	}

	public float getAnzahlBlattPaare() {
		return anzahlBlattpaare;
	}

	public String machZug(int taktik) {
		if (manuel) {
			System.out.println("machen Sie einen Zug");
			scanner = new Scanner(System.in);
			String input = scanner.nextLine();
			return input;
		} else {
			return machTaktik(taktik);
		}
	}

	public String machTaktik(int taktik) {
		if (taktik == 0) {
			if ((blatt[0].getNummer() == blatt[1].getNummer()) && (blatt[1].getWert() > 5)) {
				return "c";
			} 
			else if ((blatt[0].getfarbe() == blatt[1].getfarbe()) && (blatt[1].getWert() > 6)) {
				return "c";
			} 
			else {
				return "f";
			}
		}
		else if (taktik == 1) {
			return "c";
		}
		else {return "f";}	
		
	}

}
