import java.util.Scanner;
public class Player1 {
	String spielerName;
	boolean inRunde = false;
	boolean manuel;
	float anzahlBlattpaare = 0;
	int position;
	Card Blatt1 = null;
	Card Blatt2 = null;;
	Card Flop1 = null;
	Card Flop2 = null;

	public Player1(String spielerName, boolean manuel, boolean inRunde, Card Blatt1, Card Blatt2) { // Konstruktor
		this.inRunde = inRunde;
		this.manuel = manuel;
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

	public void setInRunde(boolean inrunde) {
		this.inRunde = inRunde;
	}

	public Card getBlatt1() {
		return Blatt1;
	}

	public Card getBlatt2() {
		return Blatt2;
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
			Scanner scanner = new Scanner(System.in);
			String input = scanner.nextLine();
			return input;
		}
		if ((Blatt1.nummer == 2 || Blatt1.nummer == 3) && (Blatt2.nummer == 2 || Blatt2.nummer == 3)) {
			return "fold";
		} else {
			return "check";
		}

	}

}
