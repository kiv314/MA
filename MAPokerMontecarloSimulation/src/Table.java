public class Table {
	Card flop1;
	Card flop2;
	Card flop3;
	Card river;
	Card turn;

	public Table(Card flop1, Card flop2, Card flop3, Card river, Card turn) { // Konstruktor
		this.flop1 = flop1;
		this.flop2 = flop2;
		this.flop3 = flop3;
		this.river = river;
		this.turn = turn;
	}
	
	public Card[] tischKarten(Card... Karte){
		return Karte;
	}

	public Card[][] HändeVonSpieler(Card[] tk, Card[] Blatt){
		Card[][] Hände = {  {Blatt[0], Blatt[1], tk[0], tk[1], tk[2]},
							{Blatt[0], Blatt[1], tk[0], tk[1], tk[3]},
							{Blatt[0], Blatt[1], tk[0], tk[1], tk[4]},
							{Blatt[0], Blatt[1], tk[0], tk[2], tk[3]},
							{Blatt[0], Blatt[1], tk[0], tk[2], tk[4]},
							{Blatt[0], Blatt[1], tk[0], tk[3], tk[4]},
							{Blatt[0], Blatt[1], tk[1], tk[2], tk[3]},
							{Blatt[0], Blatt[1], tk[1], tk[2], tk[4]},
							{Blatt[0], Blatt[1], tk[1], tk[3], tk[4]},
							{Blatt[0], Blatt[1], tk[2], tk[3], tk[4]},				
		}; 		
		return Hände;	
	}
	
	
	
	
	public void setflop1(Card flop1) {
		this.flop1 = flop1;
	}

	public void setflop2(Card flop2) {
		this.flop2 = flop2;
	}

	public void setflop3(Card flop3) {
		this.flop3 = flop3;
	}

	public void setRiver(Card river) {
		this.river = river;
	} 
	
	public void setTurn(Card turn) {
		this.turn = turn;
	}
	
	public Card getflop1() {
		return this.flop1;
	}

	public Card getflop2() {
		return this.flop2;
	}
	
	public Card getflop3() {
		return this.flop3;
	}

	public Card getRiver() {
		return this.river;
	}
	
	public Card getTurn() {
		return this.turn;
	}
	
	public void gibFlopTisch(Card karte1, Card karte2, Card karte3) {
		this.flop1 = karte1;
		this.flop2 = karte2;
		this.flop3 = karte3;
	}
	
}
