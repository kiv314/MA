
public class StoneKiller extends Player1 {// StoneKIller
	boolean permaCall = false;

	public StoneKiller(String spielerName, boolean manuel, boolean inRunde) {
		super(spielerName, manuel, inRunde);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void machZugNachTaktik(Table t) {
		if (t.spielFortschrit == "preFlop") {// Verhalten, raise noch möglich bei PreFLop
			if (t.raiseMoeglich) {
				if (chickenCasePreFlop(t)) {
					raise(100, t);
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
		else if (t.spielFortschrit == "Flop") {
			if (t.raiseMoeglich) {
				if (chickenCaseFlop(t)) {
					raise(1200, t);
				} else if (t.topRaise == 0) {
					check();
				} else {
					fold(t);
				}
			} else {
				if (chickenCaseFlop(t)) {
					call(t);
				} else {
					fold(t);
				}

			}

		} else {
			call(t);
			System.out.println("hier ist der scheiss");
		}

	}

	boolean chickenCasePreFlop(Table t) {
		if (permaCall) {
			return true;
		} else if (blattPairWertMoreThan(7) || ((blatt[0].wert > 9) && (blatt[1].wert > 9))) {
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
			return false;
		}

		else if (tableFlopHand[0].wert == blatt[0].wert || tableFlopHand[0].wert == blatt[1].wert) {// check auf Top
																									// pair or +
			super.hasTopPair = true;
			return true;

		} else if (super.isFlushDrew(hands, 5)) {// check auf Flush
			return true;
		} else if ((isBlattSuited() && super.isFlushDrew(hands, 4)) && blatt[1].farbe == super.flushFarbe) {// check auf
																											// FlushDrew
			return false;
		} else if (super.isStreat(hands)) {// check if is streat
			return true;
		} else if (super.isDoubleEndStreatDrew(hands)) {// check if DESD
			return true;
		} else {
			return false;
		}
	}
}
