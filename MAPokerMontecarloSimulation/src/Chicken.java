
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
					if (t.topRaise < 199) {
						call(t);
					} else {
						raise(200, t);
					}
				}
				else {
					fold(t);
				}
			} else {					//Verhalten, raise unmöglch bei Preflop
				if (chickenCasePreFlop(t)) {
					call(t);
				} else {
					if(t.topRaise < 200) {
						call(t);
					}
					fold(t);
				}
			}
		if(t.spielFortschrit == "Flop") {
			if (t.raiseMoeglich) {
				if(chickenCaseFlop(t)) {
					if(t.topRaise <199) {
						raise(800, t);
					}
					call(t);
				}
				if(t.topRaise <199) {
					call(t);
				}
			}
			if(!t.raiseMoeglich) {
				if(chickenCaseFlop(t)) {
					call(t);
				}
				if(t.topRaise < 200) {
					call(t);
				}
				fold(t);
			}
		}
		} else {
			call(t);
		}
	}

	boolean chickenCasePreFlop(Table t) {
		if (blattPairWertMoreThan(5) || ((blatt[0].wert > 7) && (blatt[1].wert > 7))) {
			return true;
		} else {
			return false;
		}
	}

	@SuppressWarnings("static-access")
	boolean chickenCaseFlop(Table t) {
		Card[] tableFlopHand = { t.flop1, t.flop2, t.flop3 };

		Card[][] hands = t.HaendeVonSpieler(tableFlopHand, blatt);
		t.sortCards(tableFlopHand);
		if((blatt[0].wert == blatt[1].wert)&& !((t.flop1.farbe == t.flop2.farbe) && (t.flop1.farbe == t.flop3.farbe))) {
			return true;
		}
		else if (tableFlopHand[0].wert == blatt[0].wert || tableFlopHand[0].wert == blatt[1].wert) {// check auf Top pair or +
			super.hasTopPair = true;
			return true;

		} else if (super.isFlushDrew(hands, 5)) {//check auf Flush
			return true;
		} else if ((isBlattSuited() && super.isFlushDrew(hands, 4)) && blatt[1].farbe == super.flushFarbe) {// check auf
																											// FlushDrew
			super.hasFlush = true;
			return true;
		} else if (super.isStreat(hands)) {// check if is streat
			return true;
		} else if (super.isDoubleEndStreatDrew(hands)) {// check if DESD
			return true;
		} else {
			return false;
		}
	}
}
