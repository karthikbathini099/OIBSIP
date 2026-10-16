import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        int round = 0;
        int roundsWon = 0;
        boolean playAgain = true;

        System.out.println("=== NUMBER GUESSING GAME ===");

        while (playAgain) {
            round++;

            // Difficulty selection (bonus)
            System.out.println("\nSelect difficulty:");
            System.out.println("1. Easy   (1-50,  10 attempts)");
            System.out.println("2. Medium (1-100,  7 attempts)");
            System.out.println("3. Hard   (1-200,  5 attempts)");
            int choice = readInt(sc, "Choice: ", 1, 3);

            int max, maxAttempts;
            switch (choice) {
                case 1:  max = 50;  maxAttempts = 10; break;
                case 3:  max = 200; maxAttempts = 5;  break;
                default: max = 100; maxAttempts = 7;
            }

            int target = rand.nextInt(max) + 1;
            int attempts = 0;
            boolean guessed = false;

            System.out.println("\nRound " + round + ": I'm thinking of a number between 1 and " + max + ".");

            while (attempts < maxAttempts && !guessed) {
                System.out.println("Attempts left: " + (maxAttempts - attempts));
                int guess = readInt(sc, "Your guess: ", 1, max);
                attempts++;

                if (guess == target) {
                    System.out.println("Correct!");
                    guessed = true;
                } else if (guess > target) {
                    System.out.println("Too High!");
                } else {
                    System.out.println("Too Low!");
                }
            }

            if (guessed) {
                roundsWon++;
                System.out.println("Round " + round + " - guessed in " + attempts + " attempts");
            } else {
                System.out.println("You Lost! The number was " + target);
            }

            System.out.println("Score: " + roundsWon + " won out of " + round + " rounds");

            System.out.print("\nPlay again? (yes/no): ");
            String ans = sc.next().trim().toLowerCase();
            playAgain = ans.equals("yes") || ans.equals("y");
        }

        System.out.println("Thanks for playing! Final score: " + roundsWon + "/" + round);
        sc.close();
    }

    // Reads an integer within a range; keeps asking until valid
    private static int readInt(Scanner sc, String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            if (sc.hasNextInt()) {
                int value = sc.nextInt();
                if (value >= min && value <= max) return value;
                System.out.println("Please enter a number between " + min + " and " + max + ".");
            } else {
                System.out.println("That's not a valid number.");
                sc.next(); // discard bad input
            }
        }
    }
}
