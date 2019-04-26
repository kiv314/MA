import java.util.Random;
//import java.util.Scanner;
//import java.util.Arrays;
//import java.util.ArrayList;

public class hauptclass {

	public static void main(String[] args) {

		Card[] Karte;
		Karte = new Card[52];
		for (int i = 0; i < 52; i++) {
			Karte[i] = new Card((i % 13) + 1, i % 4);
		}
		// 52 Karten erstellt mit Nummern: 1 =Ass, 2=2, etc bis 13=K, und farben
		// 0=Heart(H), 1=Dimond(D), 2=Spade(S), 3clubs(C)
		/*
		 * for(int i = 0; i<20; i++) { System.out.println(Karte[i].getfarbe()); }
		 */
		Table Tisch = new Table(null, null, null, null, null);
		Player1 Spieler1 = new Player1("Ich", true, false, 0);
		Player1 Spieler2 = new Player1("Spieler2", false, false, 1);
		int WinnerSpieler1 = 0;
		int WinnerSpieler2 = 0;
		int n = 10;
		
		Spieler1.chips = 500;
		Spieler2.chips = 500;
		
		for (int i = 0; i < n; i++) {
			arrayMix(Karte);
			setzeSpielerInRunde(Spieler1, Spieler2);
			gibSpielerBlatt(Spieler1, Karte[1], Karte[2]);
			gibSpielerBlatt(Spieler2, Karte[3], Karte[4]);
			System.out.println("------------------\n Runde:" + (i+1) + "/" + n);
			zeigSpielerBlatt(Spieler1);
			zugAusführen(Spieler1, Tisch);
			zugAusführen(Spieler2, Tisch);

			gibFlop(Tisch, Karte[23], Karte[24], Karte[25]);
			zugAusführen(Spieler1, Tisch);
			zugAusführen(Spieler2, Tisch);

			gibRiver(Tisch, Karte[26]);
			zugAusführen(Spieler1, Tisch);
			zugAusführen(Spieler2, Tisch);

			gibTurn(Tisch, Karte[27]);
			zugAusführen(Spieler1, Tisch);
			zugAusführen(Spieler2, Tisch);

			zeigSpielerBlatt(Spieler1);
			zeigSpielerBlatt(Spieler2);
			Card[] tableCardsTurn = Tisch.tischKarten(Tisch.flop1, Tisch.flop2, Tisch.flop3, Tisch.river, Tisch.turn);
			Card[][] Spieler1Hände = Tisch.HändeVonSpieler(tableCardsTurn, Spieler1.blatt);
			Card[][] Spieler2Hände = Tisch.HändeVonSpieler(tableCardsTurn, Spieler2.blatt);
			Spieler1Hände = Tisch.sortHands(Spieler1Hände);
			Spieler2Hände = Tisch.sortHands(Spieler2Hände);
			Tisch.gibSpielerBestHandAndScore(Spieler1Hände, Spieler1);
			Tisch.gibSpielerBestHandAndScore(Spieler2Hände, Spieler2);

			gibausKartenStapel(Spieler1.bestHand);
			gibausKartenStapel(Spieler2.bestHand);
			Player1[] winner = Tisch.Gewinner(Spieler1, Spieler2);
			Tisch.tellGewinner(Spieler1, Spieler2);
			Tisch.topScore = winner[0].score;
			if (winner.length > 1) {
				Tisch.pot = Tisch.pot / winner.length;
			}
			if (Spieler1.score == Tisch.topScore) {
				WinnerSpieler1 = WinnerSpieler1 + 1;
				Spieler1.chips = Spieler1.chips + Tisch.pot;
			}
			if (Spieler2.score == Tisch.topScore) {
				WinnerSpieler2 = WinnerSpieler2 + 1;
				Spieler2.chips = Spieler2.chips + Tisch.pot;
			}

			if ((Spieler1.chips <= 0) || (Spieler2.chips <= 0)) {
				break;
			}
			
			Tisch.pot = 0;
			// System.out.println("Wer hat gewonnen?");

			// System.out.println("---\\nComp: Tisch: " + tellCard(Tisch.getflop1()) + ", "
			// + tellCard(Tisch.getflop2()) + ", " +
			// tellCard(Tisch.getflop3()) + ", " + tellCard(Tisch.getRiver()) + ", " +
			// tellCard(Tisch.getTurn()));
			// System.out.println("Comp: Dein Blatt: " + tellCard(Spieler1.getBlatt1()) + ",
			// " + tellCard(Spieler1.getBlatt2()));
			// System.out.println("Comp: " + Spieler2.spielerName + " Blatt: " +
			// tellCard(Spieler2.getBlatt1()) + ", "+
			// tellCard(Spieler2.getBlatt2()));
			// scanner = new Scanner(System.in);
			// String inputSpieler = scanner.nextLine();
			// if (Spieler1.spielerName == inputSpieler) { Spieler2.setInRunde(false);
			// break;}
			// if (Spieler2.spielerName == inputSpieler) { Spieler1.setInRunde(false);
			// break;}

			/*
			 * float wahrscheinlichkeitFürPaar =
			 * (Spieler1.getAnzahlBlattPaare()/runden)*100;
			 * System.out.println(wahrscheinlichkeitFürPaar); tellCard(Spieler1.Blatt1);
			 */

		}
		System.out.println("-------------------------\nDas Spiel ist zu Ende! Punktestand:");
		System.out.println("Spieler 1: " + WinnerSpieler1 + ", chips: " + Spieler1.chips);
		System.out.println("Spieler 2: " + WinnerSpieler2 + ", chips: " + Spieler2.chips);
		if (Spieler1.chips > Spieler2.chips) {System.out.println("Gratulation, Sie haben Gewonnen!");}
		if (Spieler1.chips < Spieler2.chips) {System.out.println("Sie haben verloren!");}
	}

	private static void setzeSpielerInRunde(Player1... Spieler) {
		for (int i = 0; i < Spieler.length; i++) {
			Spieler[i].setInRunde(true);
			// System.out.println(Spieler[i].spielerName + ": " + Spieler[i].inRunde);
		}
	}

	private static void zeigSpielerBlatt(Player1 Spieler) {
		System.out.println(Spieler.spielerName + ": " + tellCard(Spieler.blatt[0]) + ", " + tellCard(Spieler.blatt[1]));
	}

	private static void zugAusführen(Player1 Spieler, Table t) {
		if (Spieler.getInRunde()) {
			switch (Spieler.machZug(Spieler.taktik)) {
			case "f":
				Spieler.setInRunde(false);
				System.out.println(Spieler.spielerName + ": fold!" + " chips: " + Spieler.chips);

				break;
			case "c":
				System.out.println(Spieler.spielerName + ": Check!" + " chips: " + Spieler.chips);
				Spieler.chips = Spieler.chips - 10;
				t.pot = t.pot + 10;
				break;
			default:
				System.out.println("Fehler! Try again!");
				zugAusführen(Spieler, t);
			}
		}
	}

	private static void gibFlop(Table t, Card karte1, Card karte2, Card karte3) {
		t.gibFlopTisch(karte1, karte2, karte3);
		System.out.println("Comp: Flop: " + hauptclass.tellCard(karte1) + ", " + hauptclass.tellCard(karte2) + ", "
				+ hauptclass.tellCard(karte3));
	}

	private static void gibRiver(Table t, Card karte) {
		t.setRiver(karte);
		System.out.println("Comp: River: " + hauptclass.tellCard(karte));
	}

	private static void gibTurn(Table t, Card karte) {
		t.setTurn(karte);
		System.out.println("Comp: Turn: " + hauptclass.tellCard(karte));
	}

	/*private static boolean nur1Spieler(Player1 Spieler1, Player1 Spieler2) {
		if (Spieler1.inRunde && Spieler2.inRunde) {
			return false;
		} else {
			return true;
		}
	}*/

	

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
		return " " + farbeBuchstabe + nummerBuchstabe;
	}

	private static void gibSpielerBlatt(Player1 Spieler, Card blatt1, Card blatt2) {
		Card[] blatt = { blatt1, blatt2 };
		Spieler.blatt = blatt;
	}

	private static void gibausKartenStapel(Card[] Karte) {
		String Ausgabe = "";
		for (int i = 0; i < Karte.length; i++) {
			Ausgabe = Ausgabe + hauptclass.tellCard(Karte[i]);
		}
		System.out.println(Ausgabe);
	}

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
