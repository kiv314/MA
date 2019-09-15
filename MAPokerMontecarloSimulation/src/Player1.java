
abstract class Player1 {
	String spielerName;
	boolean inRunde;
	boolean inSpiel;
	boolean manuel;
	boolean bigblind;
	boolean hasTopPair;
	boolean hasFlush;
	boolean hasFlushDrew;
	boolean hasDoubleEndStreat;
	int flushFarbe;
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
	int winner;
	String blind; // null, big, small

	int statraise;
	int statfold;
	int statcall = 0;
	int statcheck = 0;
	int statPreflopFold = 0;

	public void raise(int raise, Table t) {
		if ((raise > t.topRaise) && (raise <= chips)) {
			chips = chips - raise;
			t.pot = t.pot + raise;
			t.topRaise = raise;
			this.raise = raise;
			System.out.println(spielerName + ": raise auf " + raise);
			statraise = statraise + 1;
		} else {
			System.out.println("Fehler! zu wenig chips oder raise zu gering!");
		}
	}

	public void call(Table t) {
		chips = chips - (t.topRaise - this.raise);
		t.pot = t.pot + (t.topRaise - this.raise);
		this.raise = (t.topRaise);
		System.out.println(spielerName + ": call");
		if (0 == this.raise) {
			statcheck += 1;
		} else {
			statcall += 1;
		}
	}

	public void fold(Table t) {
		inRunde = false;
		System.out.println(spielerName + " fold");
		statfold = statfold + 1;
		t.anzahlSpieler = t.anzahlSpieler - 1;
		if (t.spielFortschrit == "preFlop") {
			statPreflopFold += 1;
		}
	}

	public void check() {
		System.out.println(spielerName + " check");
	}

	public Player1(String spielerName, boolean manuel, boolean inRunde) { // Konstruktor
		this.inRunde = inRunde;
		this.manuel = manuel;
		this.spielerName = spielerName;
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

	public abstract void machZugNachTaktik(Table t);

	public boolean blattPairWertMoreThan(int wert) {
		if ((blatt[0].getWert() > wert) && (blatt[0].getWert() == blatt[1].getWert())) {
			return true;
		} else {
			return false;
		}
	}

	public boolean blattSameColorAndMoreThan(double wert) {
		if ((blatt[0].farbe == blatt[1].farbe) && ((blatt[0].wert > wert) || (blatt[1].wert > wert))) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isBlattSuited() {
		if (blatt[0].farbe == blatt[1].farbe) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isFlushDrew(Card[][] card, int anzahl) {
		int countFarbe0 = 0;
		int countFarbe1 = 0;
		int countFarbe2 = 0;
		int countFarbe3 = 0;

		for (int j = 0; j < card.length; j++) {
			for (int i = 0; i < card[j].length; i++) {
				if (card[j][i].farbe == 0) {
					countFarbe0 += 1;
				}
				if (card[j][i].farbe == 1) {
					countFarbe1 += 1;
				}
				if (card[j][i].farbe == 2) {
					countFarbe2 += 1;
				}
				if (card[j][i].farbe == 3) {
					countFarbe3 += 1;
				}
			}

		}

		if (countFarbe0 > anzahl) {
			flushFarbe = 0;
			return true;
		} else if (countFarbe1 > anzahl) {
			flushFarbe = 1;
			return true;
		} else if (countFarbe2 > anzahl) {
			flushFarbe = 2;
			return true;
		} else if (countFarbe3 > anzahl) {
			flushFarbe = 3;
			return true;
		} else {
			return false;
		}
	}

	public boolean isStreat(Card[][] card) {
		for (int i = 0; i < card.length; i++) {
			Table.sortCards(card[i]);
		}
		for (int i = 0; i < card.length; i++) {
			if ((card[i][0].wert == card[i][1].wert + 1) && (card[i][1].wert == card[i][2].wert + 1)
					&& (card[i][2].wert == card[i][3].wert + 1) && (card[i][3].wert == card[i][4].wert + 1)) {
				return true;
			}
		}
		return false;
	}

	public boolean isDoubleEndStreatDrew(Card[][] card) {
		for (int i = 0; i < card.length; i++) {
			Table.sortCards(card[i]);
		}
		for (int i = 0; i < card.length; i++) {
			if ((card[i][0].wert == card[i][1].wert + 1) && (card[i][1].wert == card[i][2].wert + 1)//check doubleendStraightdrew 
					&& (card[i][2].wert == card[i][3].wert + 1) && (card[i][0].wert != 14)) {       //0123X
				return true;
			}
			else if((card[i][1].wert == card[i][2].wert + 1) && (card[i][2].wert == card[i][3].wert + 1)//check doubleendStraightdrew 
					&& (card[i][3].wert == card[i][4].wert + 1) && (card[i][1].wert != 14)) {
				return true;
			}
		}
		return false;
	}
}
