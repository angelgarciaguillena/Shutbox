package ShutBox;

import java.util.*;

public class Jugador {
	
	private int dice1;
	private int dice2;
	private int diceAddition;
	private int points;
	
	private List<Integer>tablero = new ArrayList<>();
	
	public void fillTable(List<Integer> tablero) {
		for (int i = 0; i < 12; i++) {
			tablero.add(i+1);
		}
	}
	
	public int throwDice() {
		Random rand = new Random();
		
		return rand.nextInt(1, 7);
	}
	
	
	
	
}
