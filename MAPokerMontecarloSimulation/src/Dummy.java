
public class Dummy extends Player1 {

	public Dummy(String spielerName, boolean manuel, boolean inRunde) {
		super(spielerName, manuel, inRunde);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void machZugNachTaktik(Table t) {
		call(t);

	}

}
