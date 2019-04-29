

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
		if (taktik == 0) {
			if ((blatt[0].getNummer() == blatt[1].getNummer()) && (blatt[1].getWert() > 5)) {
				call(t);
			} else if ((blatt[0].getfarbe() == blatt[1].getfarbe()) && (blatt[1].getWert() > 6)) {
				call(t);
			} else {
				fold();
			}
		} else if (taktik == 1) {
			call(t);
		} else {
			fold();
		}
		
	}

	public void raise(int raise, Table t) {
		if((raise > t.topRaise) && (raise <= chips)) {
			zug.art = "raise";
			zug.raise = raise;
			chips = chips - raise;
			t.pot = t.pot + raise;
			t.topRaise = raise;
			System.out.println(spielerName + ": rasie auf " + raise);
		}
		else {
			System.out.println("Fehler! zu wenig chips oder raise zu gering!");
		}		
	}
	
	public void call(Table t) {
		zug.art = "call";
		zug.raise = 0;
		chips = chips - t.topRaise;
		t.pot = t.pot + t.topRaise;
		System.out.println(spielerName + ": call");
	}
	
	public void fold() {
		inRunde = false;
		System.out.println(spielerName + "fold");
	}
	
	public void check(){
		System.out.println(spielerName + "check");
	}
}
