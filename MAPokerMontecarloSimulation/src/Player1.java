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
	
	private Scanner scanner;

	public Player1(String spielerName, boolean manuel, boolean inRunde, Card[] blatt) { // Konstruktor
		this.inRunde = inRunde;
		this.manuel = manuel;
		this.spielerName = spielerName;
		this.blatt = blatt;
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

	public String machZug() {
		if (manuel) {
			System.out.println("machen Sie einen Zug");
			scanner = new Scanner(System.in);
			String input = scanner.nextLine();
			return input;
		}
		if ((blatt[0].getNummer() == blatt[1].getNummer()) && blatt[1].getNummer() > 5) {
			return "check";
		}
		/*else if((Blatt1.getNummer() <= 8 && !(Blatt1.getNummer() == 1)) && (Blatt2.getNummer() <= 8 || !(Blatt1.getNummer() == 1))) {
			return "fold";
		}*/ else {
			return "check";
		}

	}

}
