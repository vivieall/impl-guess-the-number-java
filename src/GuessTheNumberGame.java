import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

public class GuessTheNumberGame {
    private static final Random RANDOM = new Random();
    private static final int TARGET_NUMBER = RANDOM.nextInt(100) + 1;

    public static void main(String[] args) {
        System.out.println("Welcome to the Guess the Number game!");

        Player player1 = new HumanPlayer("Player 1");
        Player player2 = new ComputerPlayer("Computer Player");

        boolean gameOver = false;
        int guess;

        while (!gameOver) {
            System.out.println("\n--- Round: " + player1.name + " ---");
            guess = player1.makeGuess();
            gameOver = checkGuess(guess, player1);

            if (!gameOver) {
                System.out.println("\n--- Round: " + player2.name + " ---");
                guess = player2.makeGuess();
                gameOver = checkGuess(guess, player2);
            }
        }
    }

    private static boolean checkGuess(int guess, Player player) {
        player.getGuesses().add(guess);

        if (guess < TARGET_NUMBER) {
            System.out.println("Too low!");
        } else if (guess > TARGET_NUMBER) {
            System.out.println("Too high!");
        } else {
            System.out.println("Congratulations, " + player.name + "! You guessed the number.");
            System.out.println("Attempts: " + player.getGuesses());
            System.out.println("Total attempts: " + player.getGuesses().size());
            return true;
        }
        return false;
    }
}


abstract class Player {
    protected String name;
    protected ArrayList<Integer> guesses;

    public Player(String name) {
        this.name = name;
        this.guesses = new ArrayList<>();
    }

    public abstract int makeGuess();

    public ArrayList<Integer> getGuesses() {
        return guesses;
    }
}

class HumanPlayer extends Player {
    public HumanPlayer(String name) {
        super(name);
    }

    @Override
    public int makeGuess() {
        Scanner scanner = new Scanner(System.in);
        System.out.print(name + ", enter your guess: ");
        return scanner.nextInt();
    }
}

class ComputerPlayer extends Player {
    private Random random;

    public ComputerPlayer(String name) {
        super(name);
        random = new Random();
        System.out.print(random);
    }

    @Override
    public int makeGuess() {
        int guess = random.nextInt(100) + 1;
        System.out.print(name + ", enter your guess: ");
        System.out.print(guess + "\n");
        return guess;
    }
}