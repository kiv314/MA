
public class PassivePlayer extends Player1 {//ROCK

	public PassivePlayer(String spielerName, boolean manuel, boolean inRunde) {
		super(spielerName, manuel, inRunde);
		// TODO Auto-generated constructor stub
	}

	public void machZugNachTaktik(Table t) {
		if (t.spielFortschrit == "preFlop") {// Verhalten, raise noch möglich bei PreFLop
			if (t.raiseMoeglich) {
				if (RockCasePreFlop(t)) {
						raise(50, t);
				} else {
					fold(t);
				}
			} else { // Verhalten, raise unmöglch bei Preflop
				if (RockCasePreFlop(t)) {
					call(t);
				} else {
					fold(t);
				}
			}
		}
		if(t.spielFortschrit == "Flop") {
			
		else {
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
}
