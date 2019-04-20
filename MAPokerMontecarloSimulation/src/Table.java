import java.util.ArrayList;
import java.util.Arrays;

public class Table {
	Card flop1;
	Card flop2;
	Card flop3;
	Card river;
	Card turn;

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

	public Card[] sortCards(Card[] alteKarte) {

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

	public int score(Card[] Karte) {
		int flush = 100;
		int score = 0;
		if ((Karte[0].farbe == Karte[1].farbe) && (Karte[0].farbe == Karte[2].farbe)
				&& (Karte[0].farbe == Karte[3].farbe) && (Karte[0].farbe == Karte[4].farbe)) { // prüfen auf Flush
			flush = 10000;
		}
		if((Karte[0].nummer == 1) && (Karte[1].nummer == 13) && (Karte[2].nummer == 12) && 
				(Karte[3].nummer == 11) && (Karte[4].nummer == 10) ) { // prüfen auf Ass-Straight
			score = 140000*(flush/100);
		}
		if((Karte[0].nummer > 1) && (Karte[1].nummer == (Karte[0].nummer)+1) && (Karte[1].nummer == (Karte[2].nummer)+1) && 
				(Karte[2].nummer == (Karte[3].nummer)+1) && (Karte[3].nummer == (Karte[4].nummer)+1)) {//prüfen auf sonstige straight
			score = Karte[0].nummer*10000*(flush/100);
		}
		else {
			if(Karte[0].nummer == 1) {score = 14;}
			else {score = Karte[0].nummer;}// Highcrad 
		}
		return score;
	}
}
