
public class Chicken extends Player1 {

	public Chicken(String spielerName, boolean manuel, boolean inRunde) {
		super(spielerName, manuel, inRunde);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void machZugNachTaktik(Table t) {
		if (t.spielFortschrit == "preFlop") {//Verhalten, raise noch möglich bei PreFLop
			if (t.raiseMoeglich) {
				if (chickenCasePreFlop(t)) {
					if (t.topRaise > 39) {
						call(t);
					} else {
						raise(400, t);
					}
				}
			} else {					//Verhalten, raise unmöglch bei Preflop
				if (chickenCasePreFlop(t)) {
					call(t);
				} else {
					fold(t);
				}
			}
		if(t.spielFortschrit == "Flop") {
			if (t.raiseMoeglich) {
				if()
			}
		}
		} else {
			call(t);
		}
	}

	boolean chickenCasePreFlop(Table t) {
		if (blattPairWertMoreThan(9) || ((blatt[0].wert > 9) && (blatt[0].wert > 9))) {
			return true;
		} else {
			return false;
		}
	}
	
	@SuppressWarnings("static-access")
	boolean chickenCaseFlop(Table t) {
		Card[] FlopHand = {blatt[0], blatt[1], t.flop1, t.flop2, t.flop3};
		t.sortCards(FlopHand);
		
	}
}

