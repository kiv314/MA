import java.util.Random;
public class Maniac extends Player1 {

	public Maniac(String spielerName, boolean manuel, boolean inRunde) {
		super(spielerName, manuel, inRunde);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void machZugNachTaktik(Table t) {
		Random random = new Random();
		int randomNumber = random.nextInt(10);
		if (t.spielFortschrit == "preFlop") {// Verhalten, raise noch möglich bei PreFLop
			if (t.raiseMoeglich) {
				if (ManiacCasePreFlop(t)) {
						raise(20, t);
				} else {
					fold(t);
				}
			} else { // Verhalten, raise unmöglch bei Preflop
				if (ManiacCasePreFlop(t)) {
					call(t);
				} else {
					fold(t);
				}
			}
		}
		if(t.spielFortschrit == "Flop") {
			if(t.raiseMoeglich) {
				if(randomNumber == 10) {
					fold(t);
				}
				else if(randomNumber <= 5) {
					raise(randomNumber*10,t);
				}
				else{
					call(t);
				}
				
			}
			
			
		}
		else {
			call(t);
		}
		
	}

	boolean ManiacCasePreFlop(Table t) {
		if (blattPairWertMoreThan(3) || ((blatt[0].wert > 5) && (blatt[1].wert > 5))) {
			return true;
		} else {
			return false;
		}
	}

}