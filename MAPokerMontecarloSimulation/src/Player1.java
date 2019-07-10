
abstract class Player1 {
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
	int winner;
	String blind; // null, big, small
	
	int statraise;
	int statfold;
	int statcall;
	int statcheck;
	
	public void raise(int raise, Table t) {
		if ((raise > t.topRaise) && (raise <= chips)) {
			chips = chips - raise;
			t.pot = t.pot + raise;
			t.topRaise = raise;
			this.raise = raise;
			System.out.println(spielerName + ": raise auf " + raise);
			statraise = statraise +1;
		} else {
			System.out.println("Fehler! zu wenig chips oder raise zu gering!");
		}
	}
	
	public void call(Table t) {
		chips = chips - (t.topRaise - this.raise);
		t.pot = t.pot + (t.topRaise - this.raise);
		this.raise = (t.topRaise);
		System.out.println(spielerName + ": call");
		if(0 == (t.topRaise - this.raise)) {
			statcheck += 1;
		}
		else {
			statcall += 1;
		}	
	}

	public void fold() {
		inRunde = false;
		System.out.println(spielerName + " fold");
		statfold = statfold + 1;
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
}
