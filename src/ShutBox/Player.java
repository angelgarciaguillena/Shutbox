package ShutBox;

import java.util.*;

public class Player {

	private int points;

	private List<Integer> table = new ArrayList<>();

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
		return this.table.contains(dice1 + dice2);
	}

	public boolean deleteTiles(int num) {
		boolean valido = false;
		if (table.contains(num)) {
			table.remove(num - 1);
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

		for (int n : table) {
			if (n == numberTile) {
				exists = true;
			}
		}

		return exists;
	}
	
	public boolean checkNumbers (int number1, int number2) {
		boolean checkedNumbers = false;
		
		boolean checkNumber1 = checkTile(number1);
		boolean checkNumber2 = checkTile(number2);
		
		if (checkNumber1 && checkNumber2) {
			checkedNumbers = true;
		}
		
		return checkedNumbers;
	}
}
