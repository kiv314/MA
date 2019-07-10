
public class PassivePlayer extends Player1 {

	public PassivePlayer(String spielerName, boolean manuel, boolean inRunde) {
		super(spielerName, manuel, inRunde);
		// TODO Auto-generated constructor stub
	}

	public void machZugNachTaktik(Table t) {
		if (t.raiseMoeglich) {
			taktik0RM(t);
		}
		else {
			taktik0RNM(t);
		}
	}
	
	public void taktik0RM(Table t) {// taktik 0 wen raise m�glich
		if ((((blatt[0].wert == 14) && (blatt[1].wert > 9)) || ((blatt[1].wert == 14) && (blatt[0].wert > 9)))
				&& (t.spielFortschrit == "preFlop")) {
			raise(t.bigBlind * 3, t);
		} else if ((blattPairWertMoreThan(7)) && t.topRaise < 3 * t.bigBlind) {
			call(t);
		} else if (blattSameColorAndMoreThan(8)) {// kein paar aber suited
			call(t);
		} else {
			fold();
		}
	}

	public void taktik0RNM(Table t) {
		if ((blattPairWertMoreThan(7)) && t.topRaise < 3 * t.bigBlind) {
			call(t);
		} else if (blattSameColorAndMoreThan(8)) {// kein paar aber suited
			call(t);
		} else {
			fold();
		}
	}
	
}