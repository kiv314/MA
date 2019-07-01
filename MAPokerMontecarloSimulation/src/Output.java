
import java.io.FileNotFoundException;
import java.io.PrintWriter;


public class Output {
	public void textOutput(Player1[] spieler) {
		String fileName = "Daten1.csv";
		String s = ";" ; 
		try {
			PrintWriter outputStream = new PrintWriter(fileName);
			outputStream.println("Spielername: ; chips ; winns ; fold; check; call; raise");
			for(int i=0; i<spieler.length; i++) {
				outputStream.println(spieler[i].spielerName + s + spieler[i].chips + s + spieler[i].winner + s + spieler[i].statfold 
						+ s + spieler[i].statcheck + s + spieler[i].statcall + s + spieler[i].statraise);
			}
			
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
}
