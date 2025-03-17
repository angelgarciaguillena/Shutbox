package ShutBox;

import java.util.*;

public class Jugador {

	private int dice1;
	private int dice2;
	private int diceAddition;
	private int points;

	private List<Integer> tablero = new ArrayList<>();

	public void fillTable(List<Integer> tablero) {
		for (int i = 0; i < 12; i++) {
			tablero.add(i + 1);
		}
	}

	public int throwDice() {
		Random rand = new Random();

		return rand.nextInt(1, 7);
	}

	public boolean compareNumbers(int dice1, int dice2) {
		return this.tablero.contains(dice1 + dice2);
	}

	public boolean deleteTiles(int num) {
		boolean valido = false;
		if (tablero.contains(num)) {
			tablero.remove(num - 1);
			valido = true;
		}
		return valido;
	}

	/**
	 * Method to check if the tile is still in the board
	 * 
	 * @param numberTile
	 * @return true or false
	 */
	public boolean checkTile(int numberTile) {
		boolean exists = false;

		for (int n : tablero) {
			if (n == numberTile) {
				exists = true;
			}
		}

		return exists;
	}
}
