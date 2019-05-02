import java.util.ArrayList;
import java.util.Arrays;

public class Table {
	Card flop1;
	Card flop2;
	Card flop3;
	Card river;
	Card turn;
	double topScore;
	int pot;
	int topRaise;
	String spielFortschrit; // preFlop, Flop, River, Turn 
	boolean raiseMoeglich;
	
	public Table(Card flop1, Card flop2, Card flop3, Card river, Card turn) { // Konstruktor
		this.flop1 = flop1;
		this.flop2 = flop2;
		this.flop3 = flop3;
		this.river = river;
		this.turn = turn;
	}

	public Card[] tischKarten(Card... Karte) {
		return Karte;
	}

	public Card[][] HändeVonSpieler(Card[] tk, Card[] Blatt) {
		if (tk.length == 3) {
			Card[][] Hände = { { Blatt[0], Blatt[1], tk[0], tk[1], tk[2] } };
			return Hände;
		}
		if (tk.length == 4) {
			Card[][] Hände = { { Blatt[0], Blatt[1], tk[0], tk[1], tk[2] }, { Blatt[0], Blatt[1], tk[0], tk[1], tk[3] },
					{ Blatt[0], Blatt[1], tk[0], tk[2], tk[3] }, { Blatt[0], Blatt[1], tk[1], tk[2], tk[3] },
					{ Blatt[0], tk[0], tk[1], tk[2], tk[3] }, { Blatt[1], tk[0], tk[1], tk[2], tk[3] } };
			return Hände;

		} else {
			Card[][] Hände = { { Blatt[0], Blatt[1], tk[0], tk[1], tk[2] }, { Blatt[0], Blatt[1], tk[0], tk[1], tk[3] },
					{ Blatt[0], Blatt[1], tk[0], tk[1], tk[4] }, { Blatt[0], Blatt[1], tk[0], tk[2], tk[3] },
					{ Blatt[0], Blatt[1], tk[0], tk[2], tk[4] }, { Blatt[0], Blatt[1], tk[0], tk[3], tk[4] },
					{ Blatt[0], Blatt[1], tk[1], tk[2], tk[3] }, { Blatt[0], Blatt[1], tk[1], tk[2], tk[4] },
					{ Blatt[0], Blatt[1], tk[1], tk[3], tk[4] }, { Blatt[0], Blatt[1], tk[2], tk[3], tk[4] },

					{ Blatt[0], tk[0], tk[1], tk[2], tk[3] }, { Blatt[0], tk[0], tk[1], tk[2], tk[4] },
					{ Blatt[0], tk[0], tk[1], tk[3], tk[4] }, { Blatt[0], tk[0], tk[2], tk[3], tk[4] },
					{ Blatt[0], tk[1], tk[2], tk[3], tk[4] },

					{ Blatt[1], tk[0], tk[1], tk[2], tk[3] }, { Blatt[1], tk[0], tk[1], tk[2], tk[4] },
					{ Blatt[1], tk[0], tk[1], tk[3], tk[4] }, { Blatt[1], tk[0], tk[2], tk[3], tk[4] },
					{ Blatt[1], tk[1], tk[2], tk[3], tk[4] }, { tk[0], tk[1], tk[2], tk[3], tk[4] } };
			return Hände;
		}
	}

	public void setflop1(Card flop1) {
		this.flop1 = flop1;
	}

	public void setflop2(Card flop2) {
		this.flop2 = flop2;
	}

	public void setflop3(Card flop3) {
		this.flop3 = flop3;
	}

	public void setRiver(Card river) {
		this.river = river;
	}

	public void setTurn(Card turn) {
		this.turn = turn;
	}

	public Card getflop1() {
		return this.flop1;
	}

	public Card getflop2() {
		return this.flop2;
	}

	public Card getflop3() {
		return this.flop3;
	}

	public Card getRiver() {
		return this.river;
	}

	public Card getTurn() {
		return this.turn;
	}

	public void gibFlopTisch(Card karte1, Card karte2, Card karte3) {
		this.flop1 = karte1;
		this.flop2 = karte2;
		this.flop3 = karte3;
	}

	public static Card[] sortCards(Card[] alteKarte) {

		Card[] sortiert = {};
		for (int k = 0; k < alteKarte.length; k++) {
			if (alteKarte[k].nummer == 1) {
				ArrayList<Card> liste = new ArrayList<Card>(Arrays.asList(sortiert));
				liste.add(alteKarte[k]);
				sortiert = liste.toArray(sortiert);
			}
		}
		// Ass vorne
		for (int i = 13; i > 1; i--) {
			for (int j = 0; j < alteKarte.length; j++) {
				if (alteKarte[j].nummer == i) {
					ArrayList<Card> liste = new ArrayList<Card>(Arrays.asList(sortiert));
					liste.add(alteKarte[j]);
					sortiert = liste.toArray(sortiert);
				}
			}
		}
		return sortiert;
	}

	public Card[][] sortHands(Card[][] alteKarten) {
		for (int i = 0; i < alteKarten.length; i++) {
			alteKarten[i] = Table.sortCards(alteKarten[i]);
		}
		return alteKarten;
	}

	public static double scoreHand(Card[] Karte, Player1 Spieler) {
		//überprüfen ob er noch lebt
		double scoreWert = 0;
		if ((!Spieler.inRunde) || (Spieler.chips <= 0)) {
			scoreWert = 0;
		}
		// -------- RoyalFlush und Straight flush inlusive Wheel
		else if ((Karte[0].farbe == Karte[1].farbe) && (Karte[0].farbe == Karte[2].farbe)
				&& (Karte[0].farbe == Karte[3].farbe) && (Karte[0].farbe == Karte[4].farbe)) { // prüfen auf Flush

			if ((Karte[0].nummer == 1) && (Karte[1].nummer == 13) && (Karte[2].nummer == 12) && (Karte[3].nummer == 11)
					&& (Karte[4].nummer == 10)) { // prüfen auf Royal flush
				scoreWert = 14 * Math.pow(10, 9);
			}

			else if ((Karte[0].nummer > 1) && (Karte[1].nummer == (Karte[0].nummer) - 1) // Straight-FLush
					&& (Karte[1].nummer == (Karte[2].nummer) - 1) && (Karte[2].nummer == (Karte[3].nummer) - 1)
					&& (Karte[3].nummer == (Karte[4].nummer) - 1)) {

				scoreWert = Karte[0].nummer * Math.pow(10, 9);

			} else if ((Karte[0].nummer == 1) && (Karte[1].nummer == 2) && (Karte[2].nummer == 3)// Wheel-flush
					&& (Karte[3].nummer == 4) && (Karte[4].nummer == 5)) {

				scoreWert = 5 * Math.pow(10, 9);
			}
		}

		// ------ 4 of a Kind --------------
		else if ((Karte[0].nummer == Karte[1].nummer) && (Karte[1].nummer == Karte[2].nummer)
				&& (Karte[1].nummer == Karte[3].nummer)) {// 4er mit 0,1,2,3
			scoreWert = (Karte[0].wert * Math.pow(10, 8)) + (Karte[4].wert * Math.pow(10, 6));
		} else if ((Karte[1].nummer == Karte[2].nummer) && (Karte[2].nummer == Karte[3].nummer)
				&& (Karte[3].nummer == Karte[4].nummer)) {// 4er mit 1,2,3,4
			scoreWert = (Karte[1].wert * Math.pow(10, 8)) + (Karte[0].wert * Math.pow(10, 6));
		}

		// ----- full House ---------------------
		else if ((Karte[0].nummer == Karte[1].nummer) && (Karte[1].nummer == Karte[2].nummer)
				&& (Karte[3].nummer == Karte[4].nummer)) {// P1,P2,P3,Q1,Q2
			scoreWert = (Karte[0].wert * Math.pow(10, 7)) + (Karte[3].wert * Math.pow(10, 5));
		} else if ((Karte[2].nummer == Karte[3].nummer) && (Karte[3].nummer == Karte[4].nummer)
				&& (Karte[0].nummer == Karte[1].nummer)) {// P1,P2,Q1,Q2,Q3
			scoreWert = (Karte[2].wert * Math.pow(10, 7)) + (Karte[0].wert * Math.pow(10, 5));
		}

		// ---- Flush --------------
		else if ((Karte[0].farbe == Karte[1].farbe) && (Karte[0].farbe == Karte[2].farbe) // testen auf Flush
				&& (Karte[0].farbe == Karte[3].farbe) && (Karte[0].farbe == Karte[4].farbe)) {
			if (Karte[0].nummer == 1) {// testen auf Ass-Flush
				scoreWert = (14 * Math.pow(10, 6))
						+ ((Karte[1].nummer + Karte[2].nummer + Karte[3].nummer + Karte[4].nummer) * Math.pow(10, 4));
			} else {// restliche FLush
				scoreWert = (Karte[0].nummer * Math.pow(10, 6))
						+ ((Karte[1].nummer + (Karte[2].nummer)/10 + (Karte[3].nummer)/100 + (Karte[4].nummer)/1000) * Math.pow(10, 4));
			}
		}

		// ----- Straight, inklusive Wheel------
		else if ((Karte[0].nummer == 1) && (Karte[1].nummer == 13) && (Karte[2].nummer == 12) && (Karte[3].nummer == 11)
				&& (Karte[4].nummer == 10)) {// Ass straight
			scoreWert = 14 * Math.pow(10, 5);

		} else if ((Karte[0].nummer > 1) && (Karte[1].nummer == (Karte[0].nummer) - 1) // Straight K-2
				&& (Karte[1].nummer == (Karte[2].nummer) - 1) && (Karte[2].nummer == (Karte[3].nummer) - 1)
				&& (Karte[3].nummer == (Karte[4].nummer) - 1)) {

			scoreWert = Karte[0].nummer * Math.pow(10, 5);

		} else if ((Karte[0].nummer == 1) && (Karte[1].nummer == 2) && (Karte[2].nummer == 3)// Wheel
				&& (Karte[3].nummer == 4) && (Karte[4].nummer == 5)) {
			scoreWert = 5 * Math.pow(10, 5);
		}

		// ----- 3er -----
		else if ((Karte[0].nummer == Karte[1].nummer) && (Karte[1].nummer == Karte[2].nummer)) {// T1,T2,T3,X,Y

			scoreWert = (Karte[0].wert * Math.pow(10, 4)) + ((Karte[3].wert + (Karte[4].wert)/10) * Math.pow(10, 2));
		} else if ((Karte[1].nummer == Karte[2].nummer) && (Karte[2].nummer == Karte[3].nummer)) {// X,T1,T2,T3,Y
			scoreWert = (Karte[1].wert * Math.pow(10, 4)) + ((Karte[0].wert + (Karte[4].wert)/10) * Math.pow(10, 2));
		} else if ((Karte[2].nummer == Karte[3].nummer) && (Karte[3].nummer == (Karte[4].nummer))) {// X,Y,T1,T2,T3
			scoreWert = (Karte[2].wert * Math.pow(10, 4)) + ((Karte[0].wert + (Karte[1].wert)/10) * Math.pow(10, 2));
		}

		// ---- 2 Pair ----------
		else if ((Karte[0].nummer == Karte[1].nummer) && (Karte[2].nummer == Karte[3].nummer)) {// P1,P2,Q1,Q2,X
			scoreWert = (((Karte[0].wert * 10) + (Karte[2].wert)) * 10) + Karte[4].wert;
		} else if ((Karte[0].nummer == Karte[1].nummer) && (Karte[3].nummer == Karte[4].nummer)) {// P1,P2,X,Q1,Q2
			scoreWert = (((Karte[0].wert * 10) + (Karte[3].wert)) * 10) + Karte[2].wert;
		} else if ((Karte[1].nummer == Karte[2].nummer) && (Karte[3].nummer == Karte[4].nummer)) {// X,P1,P2,Q1,Q2
			scoreWert = (((Karte[1].wert * 10) + (Karte[3].wert )) * 10) + Karte[0].wert;
		}

		// ---- Pair ----------
		else if (Karte[0].nummer == Karte[1].nummer) {// P1,P2,X,Y,Z
			scoreWert = (Karte[0].wert * 10) + ((Karte[2].wert/10) + (Karte[3].wert/100) + (Karte[4].wert/1000));
		} else if (Karte[1].nummer == Karte[2].nummer) {// X,P1,P2,Y,Z
			scoreWert = (Karte[1].wert * 10) + ((Karte[0].wert/10) + (Karte[3].wert/100) + (Karte[4].wert/1000));
		} else if (Karte[2].nummer == Karte[3].nummer) {// X,Y,P1,P2,Z
			scoreWert = (Karte[2].wert * 10) + (Karte[0].wert/10) + (Karte[1].wert/100) + (Karte[4].wert/1000);
		} else if (Karte[3].nummer == Karte[4].nummer) {// X,Y,Z,P1,P2,
			scoreWert = (Karte[3].wert * 10) + ((Karte[0].wert/10) + (Karte[1].wert/100) + (Karte[2].wert/1000));
		}

		// ----- HighCrad----
		else {
			double wert1 = Karte[1].wert;
			double wert2 = Karte[2].wert;
			double wert3 = Karte[3].wert;
			double wert4 = Karte[4].wert;

			scoreWert = Karte[0].wert + ((wert1/3 + (wert2/10) + (wert3/100) + (wert4/1000)));
		}

		return scoreWert;
	}

	public Player1[] Gewinner(Player1... Spieler) {
		Player1[] gewinner = {};
		double aktScore = Spieler[0].score;
		
		for (int i=0; i<Spieler.length; i++) {// besten Score finden
			if(Spieler[i].score > aktScore) {
				aktScore = Spieler[i].score;
			}
		}
		for(int k=0; k<Spieler.length; k++) {//beste Spieler aussortieren
			if(Spieler[k].score == aktScore) {
				ArrayList<Player1> liste = new ArrayList<Player1>(Arrays.asList(gewinner));
				liste.add(Spieler[k]);
				gewinner = liste.toArray(gewinner);
			}
		}
		
		return gewinner;
	}
	
	public void tellGewinner(Player1... Spieler) {
		Player1[] gewinner = Gewinner(Spieler);
		for(int i=0; i<gewinner.length; i++) {
			System.out.println(gewinner[i].spielerName + " hat gewonnen!");
		}
	}
	
	
	public void gibSpielerBestHandAndScore(Card[][] Hand, Player1 Spieler) {
		double aktScore = 0;
		Card[] aktHand = Hand[0];

		for (int i = 0; i < Hand.length; i++) {
			if (Table.scoreHand(Hand[i], Spieler) > aktScore) {
				aktScore = Table.scoreHand(Hand[i], Spieler);
				aktHand = Hand[i];
			}
		}
		Spieler.score = aktScore;
		Spieler.bestHand = aktHand;
	}

}
