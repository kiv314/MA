
public class Chicken extends Player1 {

	public Chicken(String spielerName, boolean manuel, boolean inRunde) {
		super(spielerName, manuel, inRunde);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void machZugNachTaktik(Table t) {
		if ((t.spielFortschrit == "preFlop") && (t.raiseMoeglich)) {
			if(chickenCase(t)) {
				if (t.topRaise > 39) {
					call(t);
				} else {
					raise(90, t);
				}
			}
			else{
				fold(t);
			}
		}
		else {
			call(t);
		}
	}
	
	boolean chickenCase(Table t) {
			if (blattPairWertMoreThan(9) || ((blatt[0].wert > 9) && (blatt[0].wert > 9))) {
				return true;
			} else {
				return false;
			}
		}

}
