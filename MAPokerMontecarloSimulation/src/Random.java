
public class Random extends Player1 {
	
	public Random(String spielerName, boolean manuel, boolean inRunde) {
		super(spielerName, manuel, inRunde);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void machZugNachTaktik(Table t) {
		// TODO Auto-generated method stub
		if(t.raiseMoeglich) {
			raise(100, t);
		}
		call(t);
	}

}
