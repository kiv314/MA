
public class Player1 {
	String spielerName;
	boolean inRunde;
	boolean inSpiel;
	boolean manuel;
	float anzahlBlattpaare = 0;
	int position;
	Card Flop1 = null;
	Card Flop2 = null;
	Card[] blatt = null;
	Card[] bestHand = null;
	double score = 0;
	int taktik;
	int chips;
	int raise;
	String blind; // null, big, small

	public Player1(String spielerName, boolean manuel, boolean inRunde, int taktik) { // Konstruktor
		this.inRunde = inRunde;
		this.manuel = manuel;
		this.spielerName = spielerName;
		this.taktik = taktik;
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

	public void machZugNachTaktik(Table t) {
		if (t.raiseMoeglich) {
			if (taktik == 0) {
				taktik0RM(t);
			} else if (taktik == 1) {

			} else if (taktik == 2) {
				if ((t.spielFortschrit == "preFlop") && (t.topRaise <= 5)) {
					raise(10, t);
				} else {
					if (t.topRaise == 0) {
						check();
					} else {
						call(t);
					}
				}
			} else {
				fold();
			}
		}
		if (!t.raiseMoeglich) {
			if (taktik == 0) {
				if (blatt[0].getNummer() == blatt[1].getNummer()) {
					call(t);
				} else {
					fold();
				}
			}
			if (taktik == 1) {
				call(t);
			}
			if (taktik == 2) {
				call(t);
			} else {
				fold();
			}
		}
	}

	public void raise(int raise, Table t) {
		if ((raise > t.topRaise) && (raise <= chips)) {
			chips = chips - raise;
			t.pot = t.pot + raise;
			t.topRaise = raise;
			this.raise = raise;
			System.out.println(spielerName + ": raise auf " + raise);
		} else {
			System.out.println("Fehler! zu wenig chips oder raise zu gering!");
		}
	}

	public void call(Table t) {
		chips = chips - (t.topRaise - this.raise);
		t.pot = t.pot + (t.topRaise - this.raise);
		this.raise = (t.topRaise);
		System.out.println(spielerName + ": call");
	}

	public void fold() {
		inRunde = false;
		System.out.println(spielerName + " fold");
	}

	public void check() {
		System.out.println(spielerName + " check");
	}

	public void taktik0RM(Table t) {
		if ((blatt[0].getNummer() == blatt[1].getNummer()) && (blatt[1].getWert() > 5)) {
			call(t);
		} else if ((blatt[0].getfarbe() == blatt[1].getfarbe()) && (blatt[1].getWert() > 6)) {
			call(t);
		} else {
			fold();
		}
	}

	public void taktik1RM(Table t) {//Passiv
		if(t.spielFortschrit == "preFlop") {//preflop verhalten
			if (t.topRaise == t.bigBlind) {//nur Blind
				if(blattPairWertMoreThan(12)) {//bei AA, KK,
					raise(t.bigBlind,t);
				}
				else if(blattPairWertMoreThan(6)) {// 77-DD
					call(t);
				}
				else {
					fold();
				}
			}
			if (t.topRaise < t.bigBlind) {
				
			}
		}
		else {
			if((blatt[0].getNummer() == blatt[1].getNummer()) && (blatt[0].getWert() > 8)) {
				raise(20, t);
			}
			if(blatt[0].getWert() > 8) {
				if(t.topRaise == 0) {
					check();
				}
			}
		} 
	}

	public boolean blattPairWertMoreThan(int wert) {
		if ((blatt[0].getWert() > wert) && (blatt[0].getWert() == blatt[1].getWert())) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean blattSameColorAndMoreThan(double wert) {
		if((blatt[0].farbe == blatt[1].farbe)&& ((blatt[0].wert > wert)||(blatt[1].wert > wert))) {
			return true;
		}
		else {
		return false;
	}
	}
	
}