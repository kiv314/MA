
public class Rock extends Player1 {// ROCK

	public Rock(String spielerName, boolean manuel, boolean inRunde) {
		super(spielerName, manuel, inRunde);
		// TODO Auto-generated constructor stub
	}

	public void machZugNachTaktik(Table t) {
		if (t.spielFortschrit == "preFlop") {// Verhalten, raise noch möglich bei PreFLop
			if (t.raiseMoeglich) {
				if (rockCasePreFlop(t)) {
					raise(50, t);
				} else {
					fold(t);
				}
			} else { // Verhalten, raise unmöglch bei Preflop
				if (rockCasePreFlop(t)) {
					call(t);
				} else {
					fold(t);
				}
			}
		}
		if (t.spielFortschrit == "Flop") {
			if(rockCaseFlop(t)) {
				call(t);
			}
			else if(t.topRaise == 0) {
				check();
			}
			else {
				fold(t);
			}
		} else {
			call(t);
		}

	}

	boolean rockCasePreFlop(Table t) {
		if (blattPairWertMoreThan(8) || ((blatt[0].wert > 10) && (blatt[1].wert > 10))) {
			return true;
		} else {
			return false;
		}
	}

	boolean rockCaseFlop(Table t) {
		if(super.score > 25) {
			return true;
		}
		else {
			return false;
		}
	}
}
