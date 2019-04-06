import java.util.Random;
import java.util.Scanner;

public class hauptclass {

	public static void main(String[] args) {
		Card Flop1 = null;
		Card Flop2 = null;
		Card Flop3 = null;
		int runden = 10;

		Card[] Karte;
		Karte = new Card[52];
		for (int i = 0; i < 52; i++) {
			Karte[i] = new Card((i % 13) + 1, i % 4);
		}
		// 52 Karten erstellt mit Nummern: 1=ass; 2=2, etc bis 13=K, und farben
		// 0=Heart(H), 1=Dimond(D), 2=Spade(S), 3clubs(C)
		/*
		 * for(int i = 0; i<20; i++) { System.out.println(Karte[i].getfarbe()); }
		 */

		Player1 Spieler1 = new Player1("Ich", true, false, null, null);
		Player1 Spieler2 = new Player1("Spieler2", false, false, null, null);
		arrayMix(Karte);

		Flop1 = Karte[2];

		do {
			setzeSpielerInRunde(Spieler1, Spieler2);
			arrayMix(Karte);
			gibSpielerBlatt(Spieler1, Karte[0], Karte[1]);
			spielerBlatt(Spieler1);
			gibSpielerBlatt(Spieler2, Karte[2], Karte[3]);
			zugAusführen(Spieler1);
			zugAusführen(Spieler2);

		} while (Spieler1.inRunde && Spieler2.inRunde);
		if(Spieler1.inRunde) {System.out.println(Spieler1.spielerName + " hat gewonnen!");}
		if(Spieler2.inRunde) {System.out.println(Spieler2.spielerName + " hat gewonnen!");}
		/*
		 * float wahrscheinlichkeitFürPaar =
		 * (Spieler1.getAnzahlBlattPaare()/runden)*100;
		 * System.out.println(wahrscheinlichkeitFürPaar); tellCard(Spieler1.Blatt1);
		 */

	}
	private static void setzeSpielerInRunde(Player1... Spieler) {
		for (int i=0; i < Spieler.length; i++) {
			Spieler[i].inRunde = true;
		}
	}

	private static void spielerBlatt(Player1 Spieler) {
		System.out.println(Spieler.spielerName + ": " + tellCard(Spieler.Blatt1) + ", " + tellCard(Spieler.Blatt2));
	}

	private static void zugAusführen(Player1 Spieler) {
		if (Spieler.inRunde) {
			switch (Spieler.machZug()) {
			case "fold":
				Spieler.inRunde = false;
				System.out.println(Spieler.spielerName + ": fold!");
				break;
			case "check":
				System.out.println(Spieler.spielerName + ": Check!");
				break;
			}
		}
	}

	// private static void findeSieger(Player1 Spieler1, Player1 Spieler2) {
	// }

	/*
	 * private static boolean isflush(Card... Karte) { boolean isflush; if(Karte[2]
	 * == null) { isflush = false; } else if(Karte.length == 5) { //if() } else {
	 * isflush = false; }
	 * 
	 * return isflush; }
	 */
	// private static void

	private static String tellCard(Card card) {
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
		if (nummer > 10) {
			switch (nummer) {
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
		} else if (nummer == 1) {
			nummerBuchstabe = "A";
		} else {
			nummerBuchstabe = Integer.toString(nummer);
		}
		return "Karte: " + farbeBuchstabe + nummerBuchstabe;
	}

	/*
	 * private static void spieleRunde() {
	 * 
	 * }
	 */

	private static void gibSpielerBlatt(Player1 Spieler, Card Karte1, Card Karte2) {
		Spieler.setBlatt1(Karte1);
		Spieler.setBlatt2(Karte2);
	}

	// private static void gibFlop()

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
