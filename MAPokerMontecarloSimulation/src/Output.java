
public class Output {
	
	public void OutputChips(Player1[] spieler) {
		for(int i=0; i<spieler.length; i++) {
			System.out.println(spieler[i].spielerName + " chips: " + spieler[i].chips);
		}
	}
}
