
import java.io.FileNotFoundException;
import java.io.PrintWriter;


public class Output {
	public void textOutput(Player1[] spieler, Table t) {
		String fileName = "Daten1.csv";
		String s = ";" ;
		try {
			PrintWriter outputStream = new PrintWriter(fileName);
			outputStream.println("Spielername: ; chips ; winns ; fold; check; call; raise; Teilgenommene Runden; PreFlopFolds; tughtness; winratio");
			for(int i=0; i<spieler.length; i++) {
				float calcstatPreflopFold = spieler[i].statPreflopFold;
				float calcRound = spieler[i].statrounds;
				float calcRoundinGame = calcRound- calcstatPreflopFold;
				float tightness = calcRoundinGame/calcRound;
				outputStream.println(spieler[i].spielerName + s + spieler[i].chips + s + spieler[i].winner + s + spieler[i].statfold 
						+ s + spieler[i].statcheck + s + spieler[i].statcall + s + spieler[i].statraise + s + calcRoundinGame + s + spieler[i].statPreflopFold + s + tightness +s+ spieler[i].winner/calcRoundinGame);
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
	
	public void outputImSpiel(String out)  {
		String fileName = "Daten2.csv";
		try {
			PrintWriter outputStream1 = new PrintWriter(fileName);
			
			outputStream1.print(out);
			outputStream1.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block hhh
			e.printStackTrace();
		}
		
	}
}
