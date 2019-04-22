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

	public Card[][] H�ndeVonSpieler(Card[] tk, Card[] Blatt) {
		if (tk.length == 3) {
			Card[][] H�nde = { { Blatt[0], Blatt[1], tk[0], tk[1], tk[2] } };
			return H�nde;
		}
		if (tk.length == 4) {
			Card[][] H�nde = { { Blatt[0], Blatt[1], tk[0], tk[1], tk[2] }, { Blatt[0], Blatt[1], tk[0], tk[1], tk[3] },
					{ Blatt[0], Blatt[1], tk[0], tk[2], tk[3] }, { Blatt[0], Blatt[1], tk[1], tk[2], tk[3] },
					{ Blatt[0], tk[0], tk[1], tk[2], tk[3] }, { Blatt[1], tk[0], tk[1], tk[2], tk[3] } };
			return H�nde;

		} else {
			Card[][] H�nde = { { Blatt[0], Blatt[1], tk[0], tk[1], tk[2] }, { Blatt[0], Blatt[1], tk[0], tk[1], tk[3] },
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
			return H�nde;
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

	public double scoreHand(Card[] Karte) {

		double scoreWert = 0;
		// -------- RoyalFlush und Straight flush inlusive Wheel
		if ((Karte[0].farbe == Karte[1].farbe) && (Karte[0].farbe == Karte[2].farbe)
				&& (Karte[0].farbe == Karte[3].farbe) && (Karte[0].farbe == Karte[4].farbe)) { // pr�fen auf Flush

			if ((Karte[0].nummer == 1) && (Karte[1].nummer == 13) && (Karte[2].nummer == 12) && (Karte[3].nummer == 11)
					&& (Karte[4].nummer == 10)) { // pr�fen auf Royal flush
				scoreWert = 14 * 10 ^ 9;
			}

			else if ((Karte[0].nummer > 1) && (Karte[1].nummer == (Karte[0].nummer) - 1) // Straight-FLush
					&& (Karte[1].nummer == (Karte[2].nummer) - 1) && (Karte[2].nummer == (Karte[3].nummer) - 1)
					&& (Karte[3].nummer == (Karte[4].nummer) - 1)) {

				scoreWert = Karte[0].nummer * 10 ^ 9;

			} else if ((Karte[0].nummer == 1) && (Karte[1].nummer == 2) && (Karte[2].nummer == 3)// Wheel-flush
					&& (Karte[3].nummer == 4) && (Karte[4].nummer == 5)) {

				scoreWert = 5 * 10 ^ 9;
			}
		}

		// ------ 4 of a Kind --------------
		else if ((Karte[0].nummer == Karte[1].nummer) && (Karte[1].nummer == Karte[2].nummer)
				&& (Karte[1].nummer == Karte[3].nummer)) {// 4er mit 0,1,2,3
			scoreWert = (Karte[0].wert * 10 ^ 8) + (Karte[5].wert * 10 ^ 5);
		} else if ((Karte[1].nummer == Karte[2].nummer) && (Karte[2].nummer == Karte[3].nummer)
				&& (Karte[3].nummer == Karte[4].nummer)) {// 4er mit 1,2,3,4
			scoreWert = (Karte[1].wert * 10 ^ 8) + (Karte[0].wert * 10 ^ 5);
		}

		// ----- full House ---------------------
		else if ((Karte[0].nummer == Karte[1].nummer) && (Karte[1].nummer == Karte[2].nummer)
				&& (Karte[3].nummer == Karte[4].nummer)) {// P1,P2,P3,Q1,Q2
			scoreWert = (Karte[0].wert * 10 ^ 7) + (Karte[3].wert * 10 ^ 5);
		} else if ((Karte[2].nummer == Karte[3].nummer) && (Karte[3].nummer == Karte[4].nummer)
				&& (Karte[0].nummer == Karte[1].nummer)) {// P1,P2,Q1,Q2,Q3
			scoreWert = (Karte[2].wert * 10 ^ 7) + (Karte[0].wert * 10 ^ 5);
		}

		// ---- Flush --------------
		else if ((Karte[0].farbe == Karte[1].farbe) && (Karte[0].farbe == Karte[2].farbe) // testen auf Flush
				&& (Karte[0].farbe == Karte[3].farbe) && (Karte[0].farbe == Karte[4].farbe)) {
			if (Karte[0].nummer == 1) {// testen auf Ass-Flush
				scoreWert = (14 * 10 ^ 6)
						+ ((Karte[1].nummer + Karte[2].nummer + Karte[3].nummer + Karte[4].nummer) * 10 ^ 4);
			} else {// restliche FLush
				scoreWert = (Karte[0].nummer * 10 ^ 6)
						+ ((Karte[1].nummer + Karte[2].nummer + Karte[3].nummer + Karte[4].nummer) * 10 ^ 4);
			}
		}

		// ----- Straight, inklusive Wheel------
		else if ((Karte[0].nummer == 1) && (Karte[1].nummer == 13) && (Karte[2].nummer == 12) && (Karte[3].nummer == 11)
				&& (Karte[4].nummer == 10)) {// Ass straight
			scoreWert = 14 * 10 ^ 5;
		} else if ((Karte[0].nummer > 1) && (Karte[1].nummer == (Karte[0].nummer) - 1) // Straight K-2
				&& (Karte[1].nummer == (Karte[2].nummer) - 1) && (Karte[2].nummer == (Karte[3].nummer) - 1)
				&& (Karte[3].nummer == (Karte[4].nummer) - 1)) {

			scoreWert = Karte[0].nummer * 10 ^ 5;
		} else if ((Karte[0].nummer == 1) && (Karte[1].nummer == 2) && (Karte[2].nummer == 3)// Wheel
				&& (Karte[3].nummer == 4) && (Karte[4].nummer == 5)) {
			scoreWert = 5 * 10 ^ 5;
		}
		
		// ----- 3er -----
		else if((Karte[0].nummer == Karte[1].nummer)&&(Karte[1].nummer == Karte[2].nummer)) {//T1,T2,T3,X,Y
			
			scoreWert = (Karte[0].wert*10^4)+((Karte[3].wert+Karte[4].wert)*10^2);
		}
		else if((Karte[1].nummer == Karte[2].nummer)&&(Karte[2].nummer == Karte[3].nummer)) {//X,T1,T2,T3,Y
			scoreWert = (Karte[1].wert*10^4)+((Karte[0].wert+Karte[4].wert)*10^2);
		}
		else if((Karte[2].nummer == Karte[3].nummer)&&(Karte[3].nummer == Karte[4].nummer)) {//X,Y,T1,T2,T3
			scoreWert = (Karte[2].wert*10^4)+((Karte[0].wert+Karte[1].wert)*10^2);
		}
		
		// ---- 2 Pair ----------
		else if((Karte[0].nummer == Karte[1].nummer)&&(Karte[2].nummer == Karte[3].nummer)) {//P1,P2,Q1,Q2,X
			scoreWert = (((Karte[0].wert*10)+(Karte[2].wert*10))*10)+Karte[4].wert;
		}
		else if((Karte[0].nummer == Karte[1].nummer)&&(Karte[3].nummer == Karte[4].nummer)) {//P1,P2,X,Q1,Q2
			scoreWert = (((Karte[0].wert*10)+(Karte[3].wert*10))*10)+Karte[2].wert;
		}
		else if((Karte[1].nummer == Karte[2].nummer)&&(Karte[3].nummer == Karte[4].nummer)) {//X,P1,P2,Q1,Q2
			scoreWert = (((Karte[1].wert*10)+(Karte[3].wert*10))*10)+Karte[0].wert;
		}
		
		//---- Pair ----------
		else if(Karte[0].nummer == Karte[1].nummer) {//P1,P2,X,Y,Z
			scoreWert = (Karte[0].wert*10)+(Karte[2].wert+Karte[3].wert+Karte[4].wert);
		}
		else if(Karte[1].nummer == Karte[2].nummer) {//X,P1,P2,Y,Z
			scoreWert = (Karte[1].wert*10)+(Karte[0].wert+Karte[3].wert+Karte[4].wert);
		}
		else if(Karte[2].nummer == Karte[3].nummer) {//X,Y,P1,P2,Z
			scoreWert = (Karte[2].wert*10)+(Karte[0].wert+Karte[1].wert+Karte[4].wert);
		}
		else if(Karte[3].nummer == Karte[4].nummer) {//X,Y,Z,P1,P2,
			scoreWert = (Karte[3].wert*10)+(Karte[0].wert+Karte[1].wert+Karte[2].wert);
		}
		
		//----- HighCrad----
		else {
			double wert1 = Karte[1].wert;
			double wert2 = Karte[2].wert;
			double wert3 = Karte[3].wert;
			double wert4 = Karte[4].wert;
			
			scoreWert = Karte[0].wert+((wert1+wert2+wert3+wert4)/10);
		}
		
		return scoreWert;
	}
	
	public Card[] bestHand(Card[][] Hand) {
		Card[] aktuelleHand = Hand[0];
		for(int i=0; i<Hand.length;i++) {
				
			}
	}
	
}
