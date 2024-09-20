package opgave03;

import java.util.Scanner;
import java.util.Arrays;

public class Pigs {
    // Two variables for the players of the game... to be continued
    public static String player_1_name;
    public static String player_2_name;
    // Two variables for the total score of each player
    public static int player_1_score = 0;
    public static int player_2_score = 0;

    public static void main(String[] args) {
        greeting_users(); // Method call to invoke a greeting for both users
        printRules(); // Method call to invoke the rules of the game
        play_Pigs(); // Method invoke to begin the game
    }

    // Method for greeting user
    private static void greeting_users (){
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

    // Method for the structure of the game
    private static void play_Pigs(){
        Scanner user_input = new Scanner(System.in); // Scanner for user input to console
        System.out.print("Press enter to begin the game: "); // Request to user
        String user_prompt = user_input.nextLine(); // Saving user prompt in user_prompt variable

        String shift_round = "Save"; // String variable for prompt to shift rounds between players

        while(!user_input.equals(shift_round)) {
            int dice_value = rollDice();
            System.out.println("Value of dice: " + dice_value);
            System.out.println();

            boolean one_was_roled = rollOne(dice_value); // True or false: value of dice equals 1

            if (!one_was_roled) { // If dice wass not 1...
                player_total_score(dice_value); // The score of the player is updated with the value
            } else {
                // reset_player_score(player_1_name); parameter of current player
            }

            printStatistics();

            // Control (otherwise infinity loop)
            System.out.println("Continue by pressing 'enter'");
            System.out.print("Shift the round, prompt 'Save': ");
            user_prompt = user_input.nextLine();
            System.out.println("=====================================================");

        }
        user_input.close(); // Cloding scanner off, because user has exit the round
    }
    /*
    // Method for registration of users... to be continued
    private static void user_registration() {


    }
    */

    // Method for rolling dice
    private static int rollDice() {
        return (int) (Math.random() * 6 + 1);
    }
    // Method for rolling 1 - therefore losing points
    private static boolean rollOne(int value){
        if(value != 1){
            return false;
        }
        return true;
    }
    // Method for calculating player score
    private static int player_total_score(int value){
        return player_1_score += value;
    }

    // Method for resetting player score
    /*
    private static int reset_player_score(String player){
        int reset_value = 0;

        if(player == player_1_name){
            return player_1_score = reset_value;
        } else if (player == player_2_name){
            return player_2_score = reset_value;
        }

        return reset_value;
    }
     */

    // Method for printing statistics after each round
    private static void printStatistics(){
        System.out.println(player_1_score);
        System.out.println(player_2_score);
    }
}
