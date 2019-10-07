
public class Rock extends Player1 {// ROCK

	public Rock(String spielerName, boolean manuel, boolean inRunde) {
		super(spielerName, manuel, inRunde);
		// TODO Auto-generated constructor stub
	}

	public void machZugNachTaktik(Table t) {
		if (t.spielFortschrit == "preFlop") {// Verhalten, raise noch m�glich bei PreFLop
			if (t.raiseMoeglich) {
				if (RockCasePreFlop(t)) {
					raise(50, t);
				} else {
					fold(t);
				}
			} else { // Verhalten, raise unm�glch bei Preflop
				if (RockCasePreFlop(t)) {
					call(t);
				} else {
					fold(t);
				}
			}
		}
		if (t.spielFortschrit == "Flop") {
			call(t);
		} else {
			call(t);
		}

	}

	boolean RockCasePreFlop(Table t) {
		if (blattPairWertMoreThan(8) || ((blatt[0].wert > 10) && (blatt[1].wert > 10))) {
			return true;
		} else {
			return false;
		}
	}

	boolean RockcaseFlop(Table t) {
		if(super.score > 25) {
			return true;
		}
		else {
			return false;
		}
	}
}