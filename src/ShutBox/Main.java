package ShutBox;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		/*
		 * Scanner sc = new Scanner(System.in);
		 * 
		 * Player pl;
		 * 
		 * int num; int cont = 1; boolean turn = false; boolean winLose = false;
		 * 
		 * do { System.out.println("Turn of player " + cont);
		 * 
		 * while (!turn) { //p1 = new }
		 * 
		 * } while (!winLose);
		 * 
		 * sc.close(); }
		 * 
		 * }
		 */

		Scanner sc = new Scanner(System.in);
		Player player1 = new Player();
		Player player2 = new Player();

		int contador = 1;
		boolean salirBucle = false;

		System.out.println("¡Welcome to SHUTBOX!");
		System.out.println("Player " + contador + " starts.");

		// Ronda del Jugador 1
		while (!salirBucle) {
			int diceSum = player1.getAddition();
			System.out.println("Player " + contador + " threw the dices: " + diceSum);

			// it shows the player that is currently playing
			if (contador == 1) {
				player1.toString();
			} else {
				player2.toString();
			}

			System.out.print("Introduce one or more numbers that sums " + diceSum + ": ");
			String input = sc.nextLine();
			String[] parts = input.trim().split(" ");

			// if its only one number
			if (parts.length == 1) {
				int num = Integer.parseInt(parts[0]);
				if (num == diceSum && player1.checkTile(num)) {
					player1.deleteTiles(num);
				} else {
					System.out.println("Jugada no válida. Turno finalizado.");
					salirBucle = true;
				}
			} else if (parts.length > 1) {
				
				if (Player.checkAddition(parts, diceSum)) {

					//player1.deleteTiles(num1);
					//player1.deleteTiles(num2);
					System.out.println("Jugada válida");

				} else {
					System.out.println("Jugada no válida. Turno finalizado.");
					break;
				}
			} else {
				System.out.println("Entrada no válida. Turno finalizado.");
				break;
			}
		}

		System.out.println("\nJugador 2 comienza.");

		// Ronda del Jugador 2
		while (true) {
			int diceSum = player2.getAddition();
			System.out.println("Jugador 2 tiró los dados: " + diceSum);
			player2.toString();

			System.out.print("Introduce uno o dos números que sumen " + diceSum + ": ");
			String input = sc.nextLine();
			String[] parts = input.trim().split(" ");

			if (parts.length == 1) {
				int num = Integer.parseInt(parts[0]);
				if (num == diceSum && player2.checkTile(num)) {
					player2.deleteTiles(num);
				} else {
					System.out.println("Jugada no válida. Turno finalizado.");
					break;
				}
			} else if (parts.length == 2) {
				int num1 = Integer.parseInt(parts[0]);
				int num2 = Integer.parseInt(parts[1]);
				if ((num1 + num2 == diceSum) && player2.checkTile(num1) && player2.checkTile(num2)) {

					player2.deleteTiles(num1);
					player2.deleteTiles(num2);

					System.out.println("Jugada válida");

				} else {
					System.out.println("Jugada no válida. Turno finalizado.");
					break;
				}
			} else {
				System.out.println("Entrada no válida. Turno finalizado.");
				break;
			}
		}

		// Resultado final
		int points1 = player1.countPoint();
		int points2 = player2.countPoint();

		System.out.println("\nPuntuación final:");
		System.out.println("Jugador 1: " + points1);
		System.out.println("Jugador 2: " + points2);

		if (points1 < points2) {
			System.out.println("¡Jugador 1 gana!");
		} else if (points2 < points1) {
			System.out.println("¡Jugador 2 gana!");
		} else {
			System.out.println("¡Empate!");
		}

		sc.close();
	}
}
