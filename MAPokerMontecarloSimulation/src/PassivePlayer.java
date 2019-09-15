
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
	
	public void taktik0RM(Table t) {// taktik 0 wen raise möglich
		if ((((blatt[0].wert == 14) && (blatt[1].wert > 9)) || ((blatt[1].wert == 14) && (blatt[0].wert > 9)))
				&& (t.spielFortschrit == "preFlop")) {//geht mit bei Ass und Kicker >9
			raise(t.bigBlind * 10, t);
		} else if ((blattPairWertMoreThan(7)) && t.topRaise < 3 * t.bigBlind) {
			if(t.spielFortschrit == "preFlop") {
				raise(t.bigBlind * 10, t);
			}
			if(t.spielFortschrit == "Flop") {
				raise(t.bigBlind * 10, t);
			}
			call(t);
		} else if((blatt[0].wert > 9) && (blatt[1].wert > 9)) {
			call(t);
		} else if (blattSameColorAndMoreThan(9)) {// kein paar aber suited
			call(t);
		} else {
			if(bigblind || t.topRaise == 0) {
				call(t);
			}
			fold(t);
		}
	}

	public void taktik0RNM(Table t) {
		if ((blattPairWertMoreThan(9)) && t.topRaise < 3 * t.bigBlind) {
			call(t);
		} else if (blattSameColorAndMoreThan(9)) {// kein paar aber suited
			call(t);
		} else {
			fold(t);
		}
	}
	
}
