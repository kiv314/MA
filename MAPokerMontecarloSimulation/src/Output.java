
import java.io.FileNotFoundException;
import java.io.PrintWriter;


public class Output {
	public void textOutput(Player1[] spieler, Table t) {
		String fileName = "Daten1.csv";
		String s = ";" ;
		try {
			PrintWriter outputStream = new PrintWriter(fileName);
			outputStream.println("Spielername: ; chips ; winns ; fold; check; call; raise; PreFlopFolds; tughtness");
			for(int i=0; i<spieler.length; i++) {
				float calcstatPreflopFold = spieler[i].statPreflopFold;
				float calcRound = t.runde;
				float tightness = (calcRound-calcstatPreflopFold)/calcRound;
				outputStream.println(spieler[i].spielerName + s + spieler[i].chips + s + spieler[i].winner + s + spieler[i].statfold 
						+ s + spieler[i].statcheck + s + spieler[i].statcall + s + spieler[i].statraise + s + spieler[i].statPreflopFold + s + tightness);
			}
			outputStream.println("Runden; Splitpots;");
			outputStream.println(t.runde + s + t.splitPot );
			outputStream.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block hhh
			e.printStackTrace();
		}
	}
	
	
	
	public void OutputChips(Player1[] spieler) {
		for(int i=0; i<spieler.length; i++) {
			System.out.println(spieler[i].spielerName + " chips: " + spieler[i].chips);
		}
			
	}
	
	public void outputImSpiel(Player1[] spieler)  {
		String fileName = "Daten2.csv";
		PrintWriter outputStream1;
		try {
			outputStream1 = new PrintWriter(fileName);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			outputStream1 = new PrintWriter(fileName);
			for(int i=0; i<spieler.length; i++) {
				outputStream1.print(spieler[i].chips + ";");
			}
			outputStream1.println();
			outputStream1.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
