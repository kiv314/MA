
public class Player1 {
	String spielerName;
	boolean inRunde;
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
	Zug zug;
	int raise;
	
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
				if ((blatt[0].getNummer() == blatt[1].getNummer()) && (blatt[1].getWert() > 5)) {
					call(t);
				} else if ((blatt[0].getfarbe() == blatt[1].getfarbe()) && (blatt[1].getWert() > 6)) {
					call(t);
				} else {
					fold();
				}
			} else if (taktik == 1) {
				if(t.spielFortschrit == "preFlop") {
					if((blatt[0].getNummer() == blatt[1].getNummer()) && (blatt[0].getWert() > 8)) {
						raise(20, t);
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
			} else if (taktik == 2) {
				if((t.spielFortschrit == "preFlop") && (t.topRaise == 0)) {
					raise(10, t);
				}
				else {
					if(t.topRaise == 0) {
						check();
					}
					else {
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
			zug.art = "raise";
			zug.raise = raise;
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
		zug.art = "call";
		zug.raise = 0;
		chips = chips - t.topRaise;
		t.pot = t.pot + t.topRaise;
		this.raise = t.topRaise;
		System.out.println(spielerName + ": call");
	}

	public void fold() {
		inRunde = false;
		System.out.println(spielerName + " fold");
	}

	public void check() {
		System.out.println(spielerName + " check");
	}
}
