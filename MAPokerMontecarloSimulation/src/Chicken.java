
public class Chicken extends Player1 {

	public Chicken(String spielerName, boolean manuel, boolean inRunde) {
		super(spielerName, manuel, inRunde);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void machZugNachTaktik(Table t) {
		if (t.spielFortschrit == "preFlop") {// Verhalten, raise noch möglich bei PreFLop
			if (t.raiseMoeglich) {
				if (chickenCasePreFlop(t)) {
						raise(400, t);
				} else {
					fold(t);
				}
			} else { // Verhalten, raise unmöglch bei Preflop
				if (chickenCasePreFlop(t)) {
					call(t);
				} else {
					fold(t);
				}
			}
		}
		if(t.spielFortschrit == "preFlop") {
			
		}
		else {
			call(t);
		}
		
	}

	boolean chickenCasePreFlop(Table t) {
		if (blattPairWertMoreThan(7) || ((blatt[0].wert > 10) && (blatt[1].wert > 10))) {
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

		if ((blatt[0].wert == blatt[1].wert)
				&& !((t.flop1.farbe == t.flop2.farbe) && (t.flop1.farbe == t.flop3.farbe))) {
			return true;
		}

		if (super.hasPair(hands)) {
			return true;
		}

		else if (tableFlopHand[0].wert == blatt[0].wert || tableFlopHand[0].wert == blatt[1].wert) {// check auf Top
																									// pair or +
			super.hasTopPair = true;
			return true;

		} else if (super.isFlushDrew(hands, 5)) {// check auf Flush
			return true;
		} else if ((isBlattSuited() && super.isFlushDrew(hands, 4)) && blatt[1].farbe == super.flushFarbe) {// check auf
																											// FlushDrew
			super.hasFlush = true;
			return false;
		} else if (super.isStreat(hands)) {// check if is streat
			return true;
		} else if (super.isDoubleEndStreatDrew(hands)) {// check if DESD
			return false;
		} else {
			return false;
		}
	}
}
