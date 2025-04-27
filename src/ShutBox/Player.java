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
	 * Method that we will use to count the points of each player
	 * 
	 * @return a number that represents the points of the player ======= Method that
	 *         will count the points of each player
	 * 
	 * @return A number representing the points of the player >>>>>>>
	 *         refs/remotes/origin/master
	 */
	public int countPoint() {
		int cont = 0;

		for (int n : table) {
			cont += n;
		}

		return cont;
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
		
		for (int num : intArray) {
			suma += num;
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

	@Override
	public String toString() {
		return "Player points: " + points + "\nAddition: " + addition;
	}
}
