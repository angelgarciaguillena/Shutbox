package ShutBox;

import java.util.*;

public class Main {
	public static void main(String[] args) {
		// initialization of the 2 objects that we will use
		Player player1 = new Player();
		Player player2 = new Player();

		// initialization of the variables that will store the points of each player
		int score1;
		int score2;

		// we fill the board of the players with numbers from 1 to 12
		player1.fillTable(player1.getTable());
		player2.fillTable(player2.getTable());

		// we call the function and each player plays
		playTurn(player1, 1);
		playTurn(player2, 2);

		// we store the points of each player into the respective variables
		score1 = player1.countPoint();
		score2 = player2.countPoint();

		// we announce the points of each player
		System.out.println("\n--- End of the game ---");
		System.out.println("Player 1 points: " + score1);
		System.out.println("Player 2 points: " + score2);

		// and decide the winner
		System.out.println(Player.winner(score1, score2));
	}

	private static void playTurn(Player player, int playerNumber) {

		// Initialization of the scanner to introduce data
		Scanner read = new Scanner(System.in);

		// variable to store if the movement can be made
		boolean canMove = true;
		// variable to store the numbers that the user introduces
		String input;
		// String array to store each number the user introduced
		String[] parts;
		// variable that stores the addition of the 2 dicess
		int sum;
		// if the movement is valid or not
		boolean valid;
		// a list to store all the numbers to remove from the board
		List<Integer> toRemove;

		System.out.println("\n--- Turn of player " + playerNumber + " ---");

		do {
			System.out.println("Tiles: " + player.getTable());

			// the player throws the dice
			player.throwDice();
			// the addition of the dices gets stored in the variable
			sum = player.getAddition();
			System.out.println("Addition of the dices: " + sum);

			// initialize the variables for the next move
			valid = true;
			toRemove = new ArrayList<>();

			// ask the user for the tiles to put down
			System.out.print("Introduce the tiles to put down (the addition must be " + sum + "): ");
			// store it in the variable
			input = read.nextLine().trim();
			// store it into the array
			parts = input.split("\\s+");

			// check if something was introduced
			if (parts.length == 0 || (parts.length == 1 && parts[0].isEmpty())) {
				valid = false;
			}

			// check each part
			for (String part : parts) {
				// if the movement is valid
				if (valid) {
					// it checks if the number is a positive number without decimals
					if (!(Integer.parseInt(part) > 0)) {
						// if its not then the movement is not valid
						valid = false;
						// if it is
					} else {
						// we store the number in a separated variable
						int num = Integer.parseInt(part);
						// and we check if the number is still in the board
						if (!player.checkTile(num)) {
							// if its not in the board then the movement isnt valid
							valid = false;
							// if it is
						} else {
							// and to the list to remove the numbers
							toRemove.add(num);
						}
					}
				}
			}

			// if the move is valid and if what the method returns is true
			if (valid && Player.checkAddition(parts, sum)) {
				// we travel the list of the numbers to remove
				for (int num : toRemove) {
					// and we remove each one from the board
					player.getTable().remove(Integer.valueOf(num));
				}
				System.out.println("Deleted tiles: " + toRemove);

				// if the board is empty
				if (player.getTable().isEmpty()) {
					System.out.println("Player " + playerNumber + " has played all their tiles.");
					canMove = false;
				}
				// if the movement wasnt valid
			} else {
				System.out.println("You can't make any more moves. End of Player " + playerNumber + "'s turn.");
				canMove = false;
			}
		} while (canMove && !player.getTable().isEmpty());
		
		//Close the Scanner
		read.close();
	}
}
