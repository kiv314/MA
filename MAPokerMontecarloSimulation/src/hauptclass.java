import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;

public class hauptclass {

	public static void main(String[] args) {

		Output output = new Output();
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
		Table table = new Table(null, null, null, null, null);
		PassivePlayer Spieler1 = new PassivePlayer("Ich", false, false);
		PassivePlayer Spieler2 = new PassivePlayer("Spieler2", false, false);
		
		int n = 1000;
		int bigBlind = 10;
		int startChips = 100;
		Player1[] spieler = { Spieler1, Spieler2, };
		Player1[] spielerAnfang = spieler;
		for(int i=0; i<spieler.length; i++) {
			spieler[i].chips = startChips;
		}
		
		
		for (int i = 0; i < n; i++) {
			arrayMix(Karte);
			table.spielFortschrit = "preFlop";
			table.topRaise = 0;
			spieler = setzeSpielerInRunde(spieler);
			if (nurNoch1Spieler(spieler)) {
				System.out.println("---------------\nEnde! Nur noch ein Spieler!!\n--------------");
				break;
			}
			
			for(int j=0; j<spieler.length; j++) {
				int kartenNummer1 = 0;
				int kartenNummer2 = kartenNummer1 + 1;
				gibSpielerBlatt(spieler[j], Karte[kartenNummer1], Karte[kartenNummer2]);
				kartenNummer1 = kartenNummer1 + 2;
			}
			System.out.println("------------------\n Runde:" + (i + 1) + "/" + n);
			table.gibBlind(spieler[spieler.length-2], bigBlind/2, "small Blind");
			table.gibBlind(spieler[spieler.length-1], bigBlind, "big Blind");
			table.bigBlind = bigBlind;
			zeigSpielerBlatt(Spieler1);
			zuegeAusführen(spieler, table);

			gibFlop(table, Karte[23], Karte[24], Karte[25]);
			table.spielFortschrit = "flop";
			table.topRaise = 0;
			zuegeAusführen(spieler, table);

			gibRiver(table, Karte[26]);
			table.spielFortschrit = "river";
			table.topRaise = 0;
			zuegeAusführen(spieler, table);

			gibTurn(table, Karte[27]);
			table.spielFortschrit = "turn";
			table.topRaise = 0;
			zuegeAusführen(spieler, table);

			zeigSpielerBlatt(Spieler1);
			zeigSpielerBlatt(Spieler2);
			Card[] tableCardsTurn = table.tischKarten(table.flop1, table.flop2, table.flop3, table.river, table.turn);
			Card[][][] spielerHaende = new Card[spieler.length][][];
			for(int j=0; j<spieler.length; j++) {
				spielerHaende[j] = table.HaendeVonSpieler(tableCardsTurn, spieler[j].blatt);
			}
			
			for(int j=0; j<spieler.length; j++) {
				spielerHaende[j] = table.sortHands(spielerHaende[j]);
			}
			
			for(int j=0; j<spieler.length; j++) {
				table.gibSpielerBestHandAndScore(spielerHaende[j], spieler[j]);
			}

			for(int j=0; j<spieler.length; j++) {
				gibausKartenStapel(spieler[j].bestHand);
			}
			
			Player1[] winner = table.Gewinner(spieler);
			table.tellGewinner(spieler);
			table.topScore = winner[0].score;
			if (winner.length > 1) {
				table.pot = table.pot / winner.length;
			}
			for(int j=0; j<winner.length; j++) {
				winner[j].chips = winner[j].chips + table.pot;
				winner[j].winner = winner[j].winner + 1;
			}
			


			table.pot = 0;
			
			output.OutputChips(spielerAnfang);

			spieler = ändereSpielerReihenfolge(spieler);
		}
		System.out.println("-------------------------\nDas Spiel ist zu Ende! Punktestand:");
		System.out.println(Spieler1.spielerName + " "  + ", chips: " + Spieler1.chips);
		System.out.println(Spieler2.spielerName + " "  + ", chips: " + Spieler2.chips);
		output.textOutput(spielerAnfang);
	}

	public static boolean nurNoch1Spieler(Player1 Spieler[]) {
		int anzahlSpieler = 0;
		for (int i = 0; i < Spieler.length; i++) {
			if (Spieler[i].inRunde) {
				anzahlSpieler = anzahlSpieler + 1;
			}
		}
		if (anzahlSpieler <= 1) {
			return true;
		} else {
			return false;
		}
	}

	private static Player1[] setzeSpielerInRunde(Player1[] Spieler) {
		Player1[] lebendeSpieler = {};
		for (int i = 0; i < Spieler.length; i++) {
			if (Spieler[i].chips > 0) {
				Spieler[i].setInRunde(true);
				ArrayList<Player1> liste = new ArrayList<Player1>(Arrays.asList(lebendeSpieler));
				liste.add(Spieler[i]);
				lebendeSpieler = liste.toArray(lebendeSpieler);
			}
		}
		return lebendeSpieler;
	}

	private static void zeigSpielerBlatt(Player1 Spieler) {
		System.out.println(Spieler.spielerName + ": " + tellCard(Spieler.blatt[0]) + ", " + tellCard(Spieler.blatt[1]));
	}

	private static void zugAusführen(Player1 Spieler, Table t) {
		if (Spieler.inRunde) {
			if (t.raiseMoeglich) {
				if (Spieler.manuel) {
					System.out.println("Machen sie einen Zug --- Ihre chips:          " + Spieler.chips);
					Scanner eingabewert = new Scanner(System.in);
					if (eingabewert.hasNext("fold")) {// fold als zug
						Spieler.fold();
					}
					if (eingabewert.hasNext("raise")) {// raise als Zug
						System.out.println("Wieviel wollen sie raisen?");
						Scanner eingabewert2 = new Scanner(System.in);
						int raise = eingabewert2.nextInt();
						if (!(raise > Spieler.chips) && (raise > t.topRaise)) {
							Spieler.raise(raise, t);
						} else {
							System.out.println("Fehler! Zu wenig chips oder raise zu Hoch! Try again");
							zugAusführen(Spieler, t);
						}
					}
					else if (eingabewert.hasNext("check")) {// check als Zug
						if (t.topRaise == 0 || t.topRaise == Spieler.raise) {
							Spieler.check();
						} else {
							System.out.println("check nicht möglich!!! try again");
							zugAusführen(Spieler, t);
						}
					}
					else if (eingabewert.hasNext("call")) {
						Spieler.call(t);
					}
					else {
						System.out.println("Fehler! Kein gültiger Zug! Try again");
						zugAusführen(Spieler, t);
					}
				} else {
					Spieler.machZugNachTaktik(t);
				}
			}
			if (!t.raiseMoeglich && !Spieler.manuel && t.topRaise != 0 && !(Spieler.raise == t.topRaise)) {
				Spieler.machZugNachTaktik(t);
			} else if (!t.raiseMoeglich && t.topRaise != Spieler.raise) {
				if (Spieler.manuel) {
					System.out.println("raise ist bei: " + t.topRaise + ", callen oder folden");
					Scanner eingabewert = new Scanner(System.in);
					if (eingabewert.hasNext("fold")) {// fold als zug
						Spieler.fold();
					} else if (eingabewert.hasNext("call")) {
						Spieler.call(t);
					} else {
						System.out.println("Fehler! checken oder Folden! Try again");
						zugAusführen(Spieler, t);
					}
				}
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

	/*
	 * private static boolean nur1Spieler(Player1 Spieler1, Player1 Spieler2) { if
	 * (Spieler1.inRunde && Spieler2.inRunde) { return false; } else { return true;
	 * } }
	 */

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

	public static void zuegeAusführen(Player1[] Spieler, Table t) {
		Player1[] lebendeSpieler = {};
		for (int i = 0; i < Spieler.length; i++) {
			if (Spieler[i].inRunde) {
				ArrayList<Player1> liste = new ArrayList<Player1>(Arrays.asList(lebendeSpieler));
				liste.add(Spieler[i]);
				lebendeSpieler = liste.toArray(lebendeSpieler);
			}
		}
		t.raiseMoeglich = true;
		for (int j = 0; j < lebendeSpieler.length; j++) {
			zugAusführen(lebendeSpieler[j], t);
		}
		Player1[] lebendeSpieler2 = {};
		for (int k = 0; k < lebendeSpieler.length; k++) {
			if (Spieler[k].inRunde) {
				ArrayList<Player1> liste = new ArrayList<Player1>(Arrays.asList(lebendeSpieler2));
				liste.add(Spieler[k]);
				lebendeSpieler2 = liste.toArray(lebendeSpieler2);
			}
		}
		if (!(t.topRaise == 0)) {
			t.raiseMoeglich = false;
			for (int l = 0; l < lebendeSpieler2.length; l++) {
				zugAusführen(lebendeSpieler2[l], t);
			}
		}
	}
	
	public static Player1[] ändereSpielerReihenfolge(Player1[] Spieler) {
		Player1[] neueReihenfolge = {Spieler[Spieler.length-1]};
		for(int i=0; i<Spieler.length-1; i++) {
			ArrayList<Player1> liste = new ArrayList<Player1>(Arrays.asList(neueReihenfolge));
			liste.add(Spieler[i]);
			neueReihenfolge = liste.toArray(neueReihenfolge);
		}
		return neueReihenfolge;
	}
	

}