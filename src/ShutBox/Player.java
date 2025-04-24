package ShutBox;

import java.util.*;

/**
 * Class that represents a player with all the attributes necessaries to be a
 * player
 */
public class Player {

	/**
	 * Attribute that represents the points of the player
	 */
	private int points = 0;
	/**
	 * Attribute that represents the addition of two dices
	 */
	private int addition = 0;

	/**
	 * A list that stores the board of the player
	 */
	private List<Integer> table = new ArrayList<>();

	/**
	 * Get method to obtain the number of points that the player has
	 * 
	 * @return a number that represents the number of points
	 */
	public int getPoints() {
		return points;
	}

	/**
	 * Set method to update the points of the player
	 * 
	 * @param points New points that the player will have
	 */
	public void setPoints(int points) {
		this.points = points;
	}

	/**
	 * Get method to obtain the addition of the dices
	 * 
	 * @return a number that represents the addition of the dices
	 */
	public int getAddition() {
		return addition;
	}

	/**
	 * Get method that obtains the board of the player
	 * 
	 * @return the list of the board
	 */
	public List<Integer> getTable() {
		return table;
	}

	/**
	 * Method that will be used to fill the board with all the tiles
	 * 
	 * @param board the list to fill
	 */
	public void fillTable(List<Integer> board) {
		for (int i = 0; i < 12; i++) {
			board.add(i + 1);
		}
	}

	/**
	 * Method that will be used to throw two dices and assign the addition of these
	 * two to the atribute
	 */
	public void throwDice() {
		Random rand = new Random();

		addition = rand.nextInt(1, 7) + rand.nextInt(1, 7);
	}

	/**
	 * 
	 * @param dice1
	 * @param dice2
	 * @return
	 */
	public boolean compareNumbers(int dice1, int dice2) {
		return this.table.contains(dice1 + dice2);
	}

	/**
	 * Method to delete the tiles from the board
	 * 
	 * @param num The number of the tile that will be removed
	 * @return true or false
	 */
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

	/**
	 * Method that will count the points of each player
	 * 
	 * @return A number representing the points of the player
	 */
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

	/**
	 * Method that checks if the tile can be thrown
	 * 
	 * @param numbers      The numbers or number that we will check
	 * @param diceAddition The addition of the dices
	 * @return true or false depending on if the movement is valid or not
	 */
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
	 * @param array        String array (contains the number the user wants to
	 *                     throw, for example: 1, 2, 3)
	 * @param diceAddition The addition of the two dices
	 * @return It returns true if the addition of the numbers is the same as the
	 *         addition and false if its otherwise
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

	/**
	 * Method to check who wins
	 * 
	 * @param player1Score The points of the player 1
	 * @param player2Score The points of the player 2
	 * @return A string announcing who wins
	 */
	public static String winner(int player1Score, int player2Score) {
		String whoWins = "";
		// if the score of the player 2 is bigger than the score of the player 1
		if (player1Score < player2Score) {
			// the player 1 wins
			whoWins = "Player 1 wins!";
			// if its the other way around
		} else if (player2Score < player1Score) {
			// the player 2 wins
			whoWins = "Player 2 wins!";
			// if they have the same score its a tie
		} else {
			whoWins = "Tie!";
		}

		// return the variable
		return whoWins;
	}

}
