package ShutBox;

import java.util.*;

public class Main {
	public static void main(String[] args) {
		// inicialization of the objects
		Player player1 = new Player();
		Player player2 = new Player();
		// we fill the board of both players
		player1.fillTable(player1.getTable());
		player2.fillTable(player2.getTable());

		// inicialization of the scanner to be able to introduce data
		Scanner scanner = new Scanner(System.in);
		// variable that will store the numbers to remove from the board
		String input;
		// an array that stores the numbers that will be deleted from the board
		String[] parts;
		// the addition of the dices
		int sum;
		// variable that stores the sum of the numbers the user introduced
		int total;
		// boolean variable that checks if the move is valid
		boolean valid;
		// list that stores the numbers to remove from the board
		List<Integer> toRemove;
		boolean canMove = true; // quitar

		// turn of the player 1
		System.out.println("\n--- Turn of player 1 ---");
		while (canMove && !player1.getTable().isEmpty()) {
			System.out.println("Tokens: " + player1.getTable());

			// the player 1 throws the dices
			player1.throwDice();
			// the variable sum stores the addition of the dices
			sum = player1.getAddition();
			System.out.println("Addition of the dices: " + sum);

			// initialize the variables for the next move
			total = 0;
			valid = true;
			toRemove = new ArrayList<>();

			// ask the user for the tiles to down from the board
			System.out.print("Introduce the tiles to put down (the addition must be " + sum + "): ");
			// stores the numbers in the variable
			input = scanner.nextLine().trim();
			// it stores the numbers that are in the variable input into the array
			parts = input.split("\\s+");

			// firstly it checks if the user introduced something
			if (parts.length == 0 || (parts.length == 1 && parts[0].isEmpty())) {
				// if its true then the validation of the move is false
				valid = false;
			}
			// for each to travel through the array of the numbers that will be deleted
			for (String part : parts) {
				// if the move was valid (true)
				if (valid) {
					// it checks if the number is between 0 and 9
					if (!part.matches("\\d+")) {
						// if it isnt then the move isnt valid (false)
						valid = false;
						// if the number IS between 0 and 9
					} else {
						// it turns the String into a number and stores it into the variable num
						int num = Integer.parseInt(part);

						// if the number doesnt exists in the board
						if (!player1.checkTile(num)) {
							// then the move is not valid (false)
							valid = false;
							// if the number exists
						} else {

							total += num;
							toRemove.add(num);
						}
					}
				}
			}

			// Ejecutar movimiento o terminar turno
			if (valid && total == sum) {
				for (int num : toRemove) {
					player1.getTable().remove(Integer.valueOf(num));
				}
				System.out.println("Fichas removidas: " + toRemove);
				if (player1.getTable().isEmpty()) {
					System.out.println("Player 1 ha eliminado todas las fichas.");
					canMove = false;
				}
			} else {
				System.out.println("Ya no puedes mover. Fin turno Player 1.");
				canMove = false;
			}
		}

		// Turno del Player 2
		System.out.println("\n--- Turno de Player 2 ---");
		canMove = true;
		while (canMove && !player2.getTable().isEmpty()) {
			System.out.println("Fichas: " + player2.getTable());

			// Tirar dados y mostrar suma
			player2.throwDice();
			sum = player2.getAddition();
			System.out.println("Suma de los dados: " + sum);

			// Inicializar variables del intento
			total = 0;
			valid = true;
			toRemove = new ArrayList<>();

			// Leer entrada del usuario
			System.out.print("Introduce las fichas a remover (deben sumar " + sum + "): ");
			input = scanner.nextLine().trim();
			parts = input.split("\\s+");

			// Validar la suma y disponibilidad de fichas
			if (parts.length == 0 || (parts.length == 1 && parts[0].isEmpty())) {
				valid = false;
			}
			for (String part : parts) {
				if (valid) {
					if (!part.matches("\\d+")) {
						valid = false;
					} else {
						int num = Integer.parseInt(part);
						if (!player2.checkTile(num)) {
							valid = false;
						} else {
							// it adds the current number to the variable
							total += num;
							// it adds the number the number to the list to remove
							toRemove.add(num);
						}
					}
				}
			}

			// Ejecutar movimiento o terminar turno
			// if the movement is valid and if the total of the numbers introduced are the
			// same as the addition of the numbers
			if (valid && total == sum) {
				for (int num : toRemove) {
					player2.getTable().remove(Integer.valueOf(num));
				}
				System.out.println("Fichas removidas: " + toRemove);
				if (player2.getTable().isEmpty()) {
					System.out.println("Player 2 ha eliminado todas las fichas.");
					canMove = false;
				}
			} else {
				System.out.println("Ya no puedes mover. Fin turno Player 2.");
				canMove = false;
			}
		}

		// Fin del juego: cálculo de puntuaciones
		int score1 = player1.countPoint();
		int score2 = player2.countPoint();
		System.out.println("\n--- Fin del juego ---");
		System.out.println("Puntuación Player 1: " + score1);
		System.out.println("Puntuación Player 2: " + score2);

		if (score1 < score2) {
			System.out.println("¡Player 1 gana!");
		} else if (score2 < score1) {
			System.out.println("¡Player 2 gana!");
		} else {
			System.out.println("¡Empate!");
		}

		scanner.close();
	}
}
