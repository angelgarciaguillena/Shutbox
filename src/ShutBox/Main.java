package ShutBox;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Inicializar jugadores y sus tablas
        Player player1 = new Player();
        Player player2 = new Player();
        player1.fillTable(player1.getTable());
        player2.fillTable(player2.getTable());

        Scanner scanner = new Scanner(System.in);
        String input;
        String[] parts;
        int sum;
        int total;
        boolean valid;
        List<Integer> toRemove;

        // Turno del Player 1
        System.out.println("\n--- Turno de Player 1 ---");
        boolean canMove = true;
        while (canMove && !player1.getTable().isEmpty()) {
            System.out.println("Fichas: " + player1.getTable());

            // Tirar dados y mostrar suma
            player1.throwDice();
            sum = player1.getAddition();
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
                        if (!player1.checkTile(num)) {
                            valid = false;
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
                            total += num;
                            toRemove.add(num);
                        }
                    }
                }
            }

            // Ejecutar movimiento o terminar turno
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
