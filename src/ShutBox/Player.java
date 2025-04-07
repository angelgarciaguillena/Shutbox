package ShutBox;

import java.util.*;

public class Player {

	private int points = 0;
	private int addition = 0;

	private List<Integer> table = new ArrayList<>();

	Player() {
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getAddition() {
		return addition;
	}

	public void fillTable(List<Integer> tablero) {
		for (int i = 0; i < 12; i++) {
			tablero.add(i + 1);
		}
	}

	public void throwDice() {
		Random rand = new Random();

		addition = rand.nextInt(1, 7) + rand.nextInt(1, 7);
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

	public boolean checkNumbers(int number1) {
		boolean checkedNumbers = false;

		boolean checkNumber1 = checkTile(number1);

		if (checkNumber1) {
			checkedNumbers = true;
		}

		return checkedNumbers;
	}

	public int countPoint() {
		int cont = 0;

		for (int n : table) {
			cont += n;
		}

		return cont;
	}

	@Override
	public String toString() {
		return "Player points: " + points + "\nAddition: " + addition;
	}

	public boolean canThrow(String numbers, int diceAddition) {
		boolean res = true;
		int numero;

		String[] numbersArray;

		numbersArray = numbers.split(" ");

		if (checkAddition(numbersArray, diceAddition))
			for (String num : numbersArray) {
				numero = Integer.parseInt(num);

				if (table.indexOf(numero) < 0) {
					res = false;
				}
			}

		return res;
	}

	/**
	 * 
	 * @param array        Array de cadenas (incluye los números que el usuario
	 *                     quiere tirar, por ej "1 2 4")
	 * @param diceAddition La suma de los dos dados
	 * @return Devuelve si la suma de los números que quiere tirar el usuario es
	 *         igual a la suma de los dos dados
	 */
	public static boolean checkAddition(String[] array, int diceAddition) {

		int[] intArray = new int[array.length];
		int suma = 0;

		for (int i = 0; i < array.length; i++) {
			intArray[i] = Integer.parseInt(array[i]);
		}

		for (int i = 0; i < intArray.length; i++) {
			suma += intArray[i];
		}

		return suma == diceAddition;

	}

}
