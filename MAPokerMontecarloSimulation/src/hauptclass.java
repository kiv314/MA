import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;

public class hauptclass {

	public static void main(String[] args) {
		boolean isBlind;
		
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
		 */ // tstst
		Table table = new Table(null, null, null, null, null);
		Rock rock = new Rock("rock", false, false);
		CallingStation cstation1 = new CallingStation("callingStation1", false, false);
		CallingStation cstation2 = new CallingStation("callingStation2", false, false);
		CallingStation cstation3 = new CallingStation("callingStation3", false, false);
		CallingStation cstation4 = new CallingStation("callingStation4", false, false);
		CallingStation cstation5 = new CallingStation("callingStation5", false, false);

		StoneKiller sKiller1 = new StoneKiller("stoneKiller", false, false);
		StoneKiller sKiller2 = new StoneKiller("stoneKiller", false, false);
		StoneKiller sKiller3 = new StoneKiller("stoneKiller", false, false);
		StoneKiller sKiller4 = new StoneKiller("stoneKiller", false, false);
		
		Maniac maniac1 = new Maniac("Maniac1", false, false);
		Maniac maniac2 = new Maniac("Maniac2", false, false);
		Maniac maniac3 = new Maniac("Maniac3", false, false);
		Maniac maniac4 = new Maniac("Maniac4", false, false);
		
		int n = 10000;
		int bigBlind = 0;
		int startChips = 6000;
		Player1[] spieler = {sKiller1, sKiller2, sKiller3, cstation1};
		Player1[] spielerAnfang = spieler;
		String outputDaten2 = "";
		for (int i = 0; i < spielerAnfang.length; i++) {
			outputDaten2 = outputDaten2 + spielerAnfang[i].spielerName + ";";
		}
		outputDaten2 = outputDaten2 + "\n";
		for (int i = 0; i < spieler.length; i++) {
			spieler[i].chips = startChips;
		}

		for (int i = 0; i < n; i++) {
			table.runde = i;
			arrayMix(Karte);
			table.spielFortschrit = "preFlop";
			table.topRaise = 0;
			table.pot = 0;
			spieler = setzeSpielerInRunde(spieler);
			if (nurNoch1Spieler(spieler)) {
				System.out.println("---------------\nEnde! Nur noch ein Spieler!!\n--------------");
				break;
			}
			int kartenNummer1 = 0;
			table.anzahlSpieler = spieler.length;
			for (int j = 0; j < spieler.length; j++) {
				int kartenNummer2 = kartenNummer1 + 1;
				gibSpielerBlatt(spieler[j], Karte[kartenNummer1], Karte[kartenNummer2]);
				kartenNummer1 = kartenNummer1 + 2;
			}
			table.bigBlind = bigBlind;
			boolean isblind = true;
			System.out.println("------------------\n Runde:" + (i + 1) + "/" + n);
			spieler[spieler.length - 2].chips = spieler[spieler.length - 2].chips - bigBlind / 2;
			spieler[spieler.length - 1].chips = spieler[spieler.length - 1].chips - bigBlind;
			spieler[spieler.length - 2].givenChips = bigBlind / 2;
			spieler[spieler.length - 1].givenChips = bigBlind;
			table.pot = bigBlind / 2 + bigBlind;
			table.topRaise = bigBlind;

			for (int j = 0; j < spieler.length; j++) {
				spieler[j].statrounds += 1;
			}
			zuegeAusführen(spieler, table);
			resetGivenChips(spieler);
			isblind = false;
			gibFlop(table, Karte[23], Karte[24], Karte[25]);
			table.spielFortschrit = "flop";
			table.topRaise = 0;
			Card[] flopcards = { table.flop1, table.flop2, table.flop3 };
			Card[][][] spielerHaendeFlop = new Card[spieler.length][][];
			for (int j = 0; j < spieler.length; j++) {
				spielerHaendeFlop[j] = table.HaendeVonSpieler(flopcards, spieler[j].blatt);
			}

			for (int j = 0; j < spieler.length; j++) {
				spielerHaendeFlop[j] = table.sortHands(spielerHaendeFlop[j]);
			}

			for (int j = 0; j < spieler.length; j++) {
				table.gibSpielerBestHandAndScore(spielerHaendeFlop[j], spieler[j]);
			}
			System.out.println(table.pot);
			zuegeAusführen(spieler, table);
			for (int k = 0; k < spieler.length; k++) {
				spieler[k].score = 0;
			}
			resetGivenChips(spieler);

			gibRiver(table, Karte[26]);
			table.spielFortschrit = "river";
			table.topRaise = 0;
			Card[] riverCards = { table.flop1, table.flop2, table.flop3, table.river };
			Card[][][] spielerHaendeRiver = new Card[spieler.length][][];
			for (int j = 0; j < spieler.length; j++) {
				spielerHaendeRiver[j] = table.HaendeVonSpieler(riverCards, spieler[j].blatt);
			}

			for (int j = 0; j < spieler.length; j++) {
				spielerHaendeRiver[j] = table.sortHands(spielerHaendeRiver[j]);
			}

			for (int j = 0; j < spieler.length; j++) {
				table.gibSpielerBestHandAndScore(spielerHaendeRiver[j], spieler[j]);
			}
			zuegeAusführen(spieler, table);
			for (int k = 0; k < spieler.length; k++) {
				spieler[k].score = 0;
			}
			resetGivenChips(spieler);

			gibTurn(table, Karte[27]);
			table.spielFortschrit = "turn";
			table.topRaise = 0;
			Card[] turnCards = { table.flop1, table.flop2, table.flop3, table.river };
			Card[][][] spielerHaendeTurn = new Card[spieler.length][][];
			for (int j = 0; j < spieler.length; j++) {
				spielerHaendeTurn[j] = table.HaendeVonSpieler(turnCards, spieler[j].blatt);
			}

			for (int j = 0; j < spieler.length; j++) {
				spielerHaendeTurn[j] = table.sortHands(spielerHaendeTurn[j]);
			}

			for (int j = 0; j < spieler.length; j++) {
				table.gibSpielerBestHandAndScore(spielerHaendeTurn[j], spieler[j]);
			}
			zuegeAusführen(spieler, table);
			for (int k = 0; k < spieler.length; k++) {
				spieler[k].score = 0;
			}
			resetGivenChips(spieler);

			zeigSpielerBlatt(spieler[0]);
			zeigSpielerBlatt(spieler[1]);
			Card[] tableCardsTurn = table.tischKarten(table.flop1, table.flop2, table.flop3, table.river, table.turn);
			Card[][][] spielerHaende = new Card[spieler.length][][];
			for (int j = 0; j < spieler.length; j++) {
				spielerHaende[j] = table.HaendeVonSpieler(tableCardsTurn, spieler[j].blatt);
			}

			for (int j = 0; j < spieler.length; j++) {
				spielerHaende[j] = table.sortHands(spielerHaende[j]);
			}

			for (int j = 0; j < spieler.length; j++) {
				table.gibSpielerBestHandAndScore(spielerHaende[j], spieler[j]);
			}

			for (int j = 0; j < spieler.length; j++) {
				gibausKartenStapel(spieler[j].bestHand);
			}

			Player1[] winner = table.Gewinner(spieler);
			table.tellGewinner(spieler);
			table.topScore = winner[0].score;
			if (winner.length > 1) {
				table.pot = table.pot / winner.length;
				table.splitPot += 1;
			}
			for (int j = 0; j < winner.length; j++) {
				winner[j].chips = winner[j].chips + (int) table.pot;
				winner[j].winner = winner[j].winner + 1;
			}

			table.pot = 0;

			output.OutputChips(spielerAnfang);
			for (int q = 0; q < spieler.length; q++) {
				String s = ";";
				outputDaten2 = outputDaten2 + spielerAnfang[q].chips + s;
			}
			outputDaten2 = outputDaten2 + "\n";

			spieler = ändereSpielerReihenfolge(spieler);
		}
		System.out.println("-------------------------\nDas Spiel ist zu Ende! Punktestand:");
		System.out.println(spielerAnfang[0].spielerName + " " + ", chips: " + spielerAnfang[0].chips);
		System.out.println(spielerAnfang[1].spielerName + " " + ", chips: " + spielerAnfang[1].chips);
		output.textOutput(spielerAnfang, table);
		output.outputImSpiel(outputDaten2);
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
			Spieler.machZugNachTaktik(t);
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
		if(lebendeSpieler.length ==1) {
			lebendeSpieler[0].chips += t.pot;
			t.pot = 0;
		}
		t.raiseMoeglich = true;
		for (int i = 0; i < Spieler.length; i++) {
			if (Spieler[i].inRunde) {
				ArrayList<Player1> liste = new ArrayList<Player1>(Arrays.asList(lebendeSpieler));
				liste.add(Spieler[i]);
				lebendeSpieler = liste.toArray(lebendeSpieler);
			}
		}

		for (int j = 0; j < lebendeSpieler.length; j++) {
			zugAusführen(lebendeSpieler[j], t);
		}
		if (t.topRaise != 0) {
			for (int j = 0; j < lebendeSpieler.length; j++) {
				if ((lebendeSpieler[j].raise != t.topRaise) && lebendeSpieler[j].inRunde) {
					zugAusführen(lebendeSpieler[j], t);
				}

			}
		}
		Player1[] lebendeSpieler2 = {};
		for (int k = 0; k < lebendeSpieler2.length; k++) {
			if (Spieler[k].inRunde) {
				ArrayList<Player1> liste = new ArrayList<Player1>(Arrays.asList(lebendeSpieler2));
				liste.add(Spieler[k]);
				lebendeSpieler2 = liste.toArray(lebendeSpieler2);
			}
		}
		if (!(t.topRaise == 0)) {
			t.raiseMoeglich = false;
			for (int l = 0; l < lebendeSpieler2.length; l++) {
				if (lebendeSpieler2[l].inRunde) {
					zugAusführen(lebendeSpieler2[l], t);
				}
			}
		}
	}

	public static Player1[] ändereSpielerReihenfolge(Player1[] Spieler) {
		Player1[] neueReihenfolge = { Spieler[Spieler.length - 1] };
		for (int i = 0; i < Spieler.length - 1; i++) {
			ArrayList<Player1> liste = new ArrayList<Player1>(Arrays.asList(neueReihenfolge));
			liste.add(Spieler[i]);
			neueReihenfolge = liste.toArray(neueReihenfolge);
		}
		return neueReihenfolge;
	}

	public static void resetGivenChips(Player1[] spieler) {
		for (int i = 0; i < spieler.length; i++) {
			spieler[i].givenChips = 0;
		}
	}

}