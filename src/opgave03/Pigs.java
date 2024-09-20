package opgave03;

import java.util.Scanner;
import java.util.Arrays;

public class Pigs {
    // Point counter of players
    public static int pointsPlayer1 = 0; // Global variable for player 1
    public static int pointsPlayer2 = 0; // Global varibale for player 2

    // Value of total rolls (for each player)
    public static int player1_roll = 0;
    public static int player2_roll = 0;

    // Value of rounds
    public static int player1Round = 0;
    public static int player2Round = 0;

    // Array of sum of value per round - exstra task
    // Incomplete
    public static int[] sum_player1RoundArr = new int[1000];
    public static int[] sum_player2RoundArr = new int[1000];

    // Main method of the program
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // scanner for user input

        // Information
        greeting_users(); // Greeting the users
        printRules(); // Printing the rules for the game

        // Request
        System.out.println("Would you like to play a game of Pigs? (y/n)");
        String startGameAnswer = scanner.next(); // User input (scanner) stored in variable

        // While loop for user promt yes to wanting to play the game
        while (startGameAnswer.equals("y")) { // player input equals to string value

            playPigs(); // Method invoke containing the game
            endGameStatistics(); // Method invoke - incomplete

            System.out.println("Do you wish to proceed?"); // Output to user...

            //... Requesting respons from user
            startGameAnswer = scanner.next();

            // Score counter
            pointsPlayer1 = 0; // Player_1 score counter
            pointsPlayer2 = 0; // Player_2 score counter
        }
    }

    // Method for greeting user
    private static void greeting_users() {
        System.out.println("=====================================================");
        System.out.println("Welcome to a game of Pigs");
    }

    // Method for presenting the rules of the game to the users
    private static void printRules() {
        System.out.println("=====================================================");
        System.out.println("The rules is as followed:");
        System.out.println("Each player gets one dice, which they choose to throw.");
        System.out.println("Each player will throw until the value of the dice is 1.");
        System.out.println("If he value of 1 is thrown, the players total points is reset.");
        System.out.println("The play can choose to end the game at any point -");
        System.out.println("the total value of the dices thrown will be summed together.");
        System.out.println("The player to first get 100 points win.");
        System.out.println("Good luck.");
        System.out.println("=====================================================");
    }

    // Method for the game
    private static void playPigs() {
        Scanner scanner = new Scanner(System.in); // Scanner for user input

        // Request user to input max points of the game
        System.out.println("How many points do you wish to play to?");
        final int roundsMaxPoint = maxPoints(); // Final varibale - never changes throughout the game

        // Output to user - informs the user of the beginning of the game
        System.out.println("The game consists of two players. Player 1 starts!");
        System.out.println("===========================================");

        // While loop for iterating over points of players in comparison to variable roundsMaxPoint
        while (pointsPlayer1 < roundsMaxPoint || pointsPlayer2 < roundsMaxPoint) {

            // Request feedback from user - want to roll the dice
            System.out.println("Would player 1 want to roll the dice? (y/n)");
            String rollDiceAnswer1 = scanner.next();

            // Counting player points in sum
            int player1Sum = 0;
            int player2Sum = 0;

            // Player 1 while loop
            while (rollDiceAnswer1.equals("y")) { // While player 1 wants to play

                // Number of rolls players has accounted for
                player1_roll++;

                int[] player1Roll = rollDice(); // Value of current dice roll

                // Informing user of value
                System.out.println("You rolled: " + Arrays.toString(player1Roll));
                System.out.println("===========================================");

                // Selection: if players does not land value 1
                if (rollDiceOne(player1Roll, player1Sum, pointsPlayer1)) {

                    // Iteration: index in player role array
                    for (int i = 0; i < player1Roll.length; i++) {
                        pointsPlayer1 += player1Roll[i];
                        player1Sum += player1Roll[i];
                    }

                    // Output to user - number of points at current point
                    System.out.println("You currently have " + pointsPlayer1 + " points!");

                    // Has the player won?
                    if (determining_win(pointsPlayer1, roundsMaxPoint)) {
                        player1Round++; // Round added with one
                        sum_player1RoundArr[player1Round - 1] = player1_roll; // array in index of current round = sum of player
                        player1_roll = 0; // method invoke for resetting score for current round.
                        return;
                    }

                    // Request for prompt from user
                    System.out.println("Do you wish to roll another round? (y/n)");
                    rollDiceAnswer1 = scanner.next();
                } else { // else, player has landed on 1
                    pointsPlayer1 -= player1Sum; // player point taken from sum
                    break; // breaking the continuation
                }
            }

            // Round added with one
            player1Round++;
            sum_player1RoundArr[player1Round - 1] = player1_roll; // array in index of current round = sum of player
            reset_score(player1_roll); // method invoke for resetting score for current round.

            //
            System.out.println("It's player 2's round!");
            System.out.println("===========================================");
            System.out.println("Would player 2 want to roll the dice?");
            String rollDiceAnswer2 = scanner.next();

            // Spiller 2 while loop

            while (rollDiceAnswer2.equals("y")) {

                // Number of rolls players has accounted for
                player2_roll++;

                int[] player2Roll = rollDice();

                System.out.println("You rolled: " + Arrays.toString(player2Roll));
                System.out.println("===========================================");

                // Selection: if players does not land value 1
                if (rollDiceOne(player2Roll, player2Sum, pointsPlayer2)) {

                    // Iteration: index in player role array
                    for (int i = 0; i < player2Roll.length; i++) {
                        pointsPlayer2 += player2Roll[i];
                        player2Sum += player2Roll[i];
                    }

                    // Output to user - number of points at current point
                    System.out.println("You currently have " + pointsPlayer2 + " points!");

                    // Has the player won?
                    if (determining_win(pointsPlayer2, roundsMaxPoint)) {
                        player2Round++; // Round added with one
                        sum_player2RoundArr[player2Round - 1] = player2_roll; // array in index of current round = sum of player
                        player2_roll = 0; // method invoke for resetting score for current round.
                        return;
                    }

                    // Request for prompt from user
                    System.out.println("Do you wish to roll another round? (y/n)");
                    rollDiceAnswer2 = scanner.next();
                } else { // else, player has landed on 1
                    pointsPlayer2 -= player2Sum; // player point taken from sum
                    break; // breaking the continuation
                }
            }

            player2Round++; // Round added with one
            sum_player2RoundArr[player2Round - 1] = player2_roll; // array in index of current round = sum of player
            reset_score(player2_roll); // method invoke for resetting score for current round.

            printStatistics();

            System.out.println("===========================================");
            System.out.println("It's now Player 1's turn");
            System.out.println("===========================================");
        }
    }

    // Method for determining value of dices rolled
    private static int[] rollDice() {
        int[] diceArr = new int[2]; // Array with two index
        int arrayLength = diceArr.length; // length of array
        for (int i = 0; i < arrayLength; i++) { // Iteration over index in array
            int diceValue = (int) (Math.random() * 6 + 1); // dice value - random number (using math method) 1 to 6
            diceArr[i] = diceValue; // array value in index i is equal to value of diceValue
        }
        return diceArr; // Returning variable to method invoke
    }

    // Method for max points of the game
    private static int maxPoints() {
        Scanner scanner = new Scanner(System.in); // New scanner
        int numberOfRounds = scanner.nextInt(); // no of rounds equal to user input
        return numberOfRounds; // Returning value of rounds to method invoke
    }

    // Method for if dice rolled value of one
    private static boolean rollDiceOne(int[] player_roll, int sum, int points) {

        // If dice 1 or 2 is equal to 1
        if (player_roll[0] == 1 || player_roll[1] == 1) {
            // Output to user
            System.out.println("You unfortunabley landed on 1 - no points for you this round");
            System.out.println("===========================================");
            sum = 0; // No points the current round
            return false; // returning false to method
        } else if (player_roll[0] == 1 && player_roll[1] == 1) { // else if both values of dice equal 1
            // output to user
            System.out.println("You unfortunabley rolled two 1's in one roll - all of your points is lost: ");
            System.out.println("===========================================");
            reset_score(points); // Method invoke for resetting score of player
            return false; // returning false to method
        }
        return true; // return true to method if non of selection occured
    }

    // Method for determining win
    private static boolean determining_win(int playerpoint, int rounds) {
        if (pointsPlayer1 >= rounds) {
            System.out.println("Congratulations, Player 1 WON!");
            return true;
        } else if (pointsPlayer2 >= rounds) {
            System.out.println("Congratulations, Player 2 WON!");
            return true;
        }
        return false;
    }

    // Method for resetting player score - in case of landing 1
    private static int reset_score(int current_player) {
        return current_player = 0;
    }

    // method for calculating average roll per round for each player
    private static double average_roll_per_round(int[] player_arr, int round_value) {
        double sum = 0.0; // sum of calculation
        double result = 0.0; // result of calculation

        if (round_value > 0) { // if number of rounds exceed 0 (error: divide by 0)
            int length = player_arr.length; // variable for storing length of array

            for (int index = 0; index < length; index++) { // iterating over each index in array
                sum += player_arr[index]; // sum additional assignment operator to current index of player array
            }
            result = sum / round_value; // sum set to result
        }
        return result; // return result to method
    }

    // Print statistics to player (score)
    private static void printStatistics() {
        System.out.println("Player 1 have " + pointsPlayer1 + " points");
        System.out.println("Player 2 have " + pointsPlayer2 + " points");
    }

    // Method to present end game statistics to players
    // Incomplete
    private static void endGameStatistics() {
        // average roll for player 1
        System.out.print("Player 1's average roll per round: ");
        System.out.println(average_roll_per_round(sum_player1RoundArr, player1Round)); // Method invoke

        // average roll for player 2
        System.out.print("Player 2's average roll per round: ");
        System.out.println(average_roll_per_round(sum_player2RoundArr, player2Round)); // Method invoke
    }
}