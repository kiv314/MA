import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;
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
		Player1 Spieler1 = new Player1("Ich", true, false, null, null);
		Player1 Spieler2 = new Player1("Spieler2", false, false, null, null);
		arrayMix(Karte);
		
		setzeSpielerInRunde(Spieler1, Spieler2);
		while (Spieler1.getInRunde() && Spieler2.getInRunde())
		{
			gibSpielerBlatt(Spieler1, Karte[0], Karte[1]);
			gibSpielerBlatt(Spieler2, Karte[2], Karte[3]);
			zeigSpielerBlatt(Spieler1);
			zugAusführen(Spieler1);
			zugAusführen(Spieler2);
			if(nur1Spieler(Spieler1, Spieler2)) {break;}
			gibFlop(Tisch, Karte[4], Karte[5], Karte[6]);
			zugAusführen(Spieler1);
			zugAusführen(Spieler2);
			if(nur1Spieler(Spieler1, Spieler2)) {break;}
			gibRiver(Tisch, Karte[7]);
			zugAusführen(Spieler1);
			zugAusführen(Spieler2);
			if(nur1Spieler(Spieler1, Spieler2)) {break;}
			gibTurn(Tisch, Karte[8]);
			zugAusführen(Spieler1);
			zugAusführen(Spieler2);
			if(nur1Spieler(Spieler1, Spieler2)) {break;}
			zeigSpielerBlatt(Spieler1);
			zeigSpielerBlatt(Spieler2);
			Card[] tableCardsTurn = Tisch.tischKarten(Tisch.flop1, Tisch.flop2, Tisch.flop3, Tisch.river, Tisch.turn);
			Card[][] Spieler1Hände = Tisch.HändeVonSpieler(tableCardsTurn, Spieler1.Blatt());
			gibausKartenStapel(Spieler1Hände[0]);
			Spieler1Hände = Tisch.sortHands(Spieler1Hände);
			gibausKartenStapel(Spieler1Hände[0]);
			//System.out.println("Wer hat gewonnen?");
			
			//System.out.println("---\\nComp: Tisch: " + tellCard(Tisch.getflop1()) + ", " + tellCard(Tisch.getflop2()) + ", " + 
			//tellCard(Tisch.getflop3()) + ", " + tellCard(Tisch.getRiver()) + ", " + tellCard(Tisch.getTurn()));
			//System.out.println("Comp: Dein Blatt: " + tellCard(Spieler1.getBlatt1()) + ", " + tellCard(Spieler1.getBlatt2()));
			//System.out.println("Comp: " + Spieler2.spielerName + " Blatt: " + tellCard(Spieler2.getBlatt1()) + ", "+ 
			//tellCard(Spieler2.getBlatt2()));
			//scanner = new Scanner(System.in);
			//String inputSpieler = scanner.nextLine();
			//if (Spieler1.spielerName == inputSpieler)  { Spieler2.setInRunde(false); break;}
			//if (Spieler2.spielerName == inputSpieler)  { Spieler1.setInRunde(false); break;} 
			break;
		}	
		
		if (Spieler1.inRunde) {
			System.out.println("Comp: " + Spieler1.spielerName + " hat gewonnen!");
		}
		
		if (Spieler2.inRunde) {
			System.out.println("Comp: " + Spieler2.spielerName + " hat gewonnen!");
		}
		/*
		 * float wahrscheinlichkeitFürPaar =
		 * (Spieler1.getAnzahlBlattPaare()/runden)*100;
		 * System.out.println(wahrscheinlichkeitFürPaar); tellCard(Spieler1.Blatt1);
		 */

	}

	private static void setzeSpielerInRunde(Player1... Spieler) {
		for (int i = 0; i < Spieler.length; i++) {
			Spieler[i].setInRunde(true);
			// System.out.println(Spieler[i].spielerName + ": " + Spieler[i].inRunde);
		}
	}

	private static void zeigSpielerBlatt(Player1 Spieler) {
		System.out.println(Spieler.spielerName + ": " + tellCard(Spieler.Blatt1) + ", " + tellCard(Spieler.Blatt2));
	}

	private static void zugAusführen(Player1 Spieler) {
		if (Spieler.getInRunde()) {
			switch (Spieler.machZug()) {
			case "fold":
				Spieler.setInRunde(false);
				System.out.println(Spieler.spielerName + ": fold!");
				
				break;
			case "check":
				System.out.println(Spieler.spielerName + ": Check!");
				break;
			default:
				System.out.println("Fehler!");
			}	
		}
		else{}
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
	private static boolean nur1Spieler(Player1 Spieler1, Player1 Spieler2) {
		if(Spieler1.inRunde && Spieler2.inRunde) { return false; }
		else {return true;}
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
		return " " + farbeBuchstabe + nummerBuchstabe;
	}

	
	private static void gibSpielerBlatt(Player1 Spieler, Card karte1, Card karte2) {
		Spieler.setBlatt1(karte1);
		Spieler.setBlatt2(karte2);
	}

	private static void gibausKartenStapel(Card[] Karte) {
		String Ausgabe = "";
		for(int i = 0; i<Karte.length; i++) {
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
