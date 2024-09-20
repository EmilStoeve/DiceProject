package opgave02;

import java.util.Arrays;
import java.util.Scanner;

public class Craps {
    private static int rollCount = 0;
    private static int total_user_points = 0;

    public static void main(String[] args) {
        System.out.println("===========================");
        System.out.println("Welcome to a game of Craps");
        System.out.println("===========================");
        System.out.print("Please enter the game by pressing 'enter': ");
        Scanner scanner = new Scanner(System.in);
        String user_input = scanner.nextLine();

        // While loop for managing game
        while(!user_input.equals("break")){
            playCraps(); // Game method invoke

            updateStatistics(); // updating rollCount

            System.out.print("Roll a dice? ('skip by writting 'break') ");
            user_input = scanner.nextLine();
        }
        System.out.println("Thank your for playing Craps - see you soon");
        printStatistics();
    }

    // Method for the structure of the games mechanics
    public static void playCraps(){

        System.out.print("Do you wise to through the dice? ");
        Scanner scanner = new Scanner(System.in);
        String user_input = scanner.nextLine();

        // If user has not breaked the code by entering string value 'break'...
        if(!user_input.equals("break")) {
            int[] faces = rollDice();
            System.out.println("Value of dice: " + Arrays.toString(faces)); // Array class to string (presents in console)

            int sum_of_roll = sum(faces); // Calculating sum of rolls - used in instant_win and instant_loss

            // determining within the first throw, if the player instant won or instant lost
            if (rollCount == 0) { // If it's the users first roll...
                if (instant_win(sum_of_roll)) { // if statement with method invoke for determining instant win
                    System.out.println("Congratulations! Instant win!");
                    System.out.println("The game will be reset");
                    return;
                } else if (instant_loss(sum_of_roll)) { // else if statement with method invoke for determining instant lost
                    System.out.println("Immediate lost...");
                    System.out.println("The game will be reset");
                    return;
                }
            }

            // Otherwise the following code is executed
            boolean point_given = rollforPoint(sum_of_roll); // method invoke with parameter of sum_of_rule

            if (point_given) { // if point_given true...
                total_user_points += sum_of_roll; // the users total point is updated with sum of the current roll
                return;
            } else {
                System.out.println("You lost...");
                System.out.println("The game will be reset");
                return;
            }
            // The code is determining within the first throw, if the player instant won or instant lost

        } else { // User has prompted 'break'
            System.out.println("Thanks your for playing"); // Output to user
            return; // return program
        }
    }

    // Method for calculating sum
    public static int sum (int[] dice_array){
        return dice_array[0] + dice_array[1]; // return value of both indexes
    }

    // Boolean method for determining instant win
    private static boolean instant_win(int sum_of_roll){
        if(sum_of_roll == 7 || sum_of_roll == 11){ // if sum equal 7 or 12
            return true; // return true
        }
        return false; // otherwise return false
    }

    // Boolean method for determining instant loss
    private static boolean instant_loss(int sum_of_roll){
        // Selection: if sum not equal 2,3,12
        if (sum_of_roll == 2 || sum_of_roll == 3 || sum_of_roll ==  12){
            return true; // return true
        }
        return false; // otherwise return false
    }

    // Method for Rolling dice
    private static int[] rollDice() {
        // Constant variables because we don't manipulate the structure of the array
        final int [] dice_arr = new int[2]; // Creates array with two index'
        final int dice_arr_length = dice_arr.length; // array length calculated in variable

        // Loop for iterating over value of dice
        for(int index = 0; index < dice_arr_length; index++){
            int dice_value = (int) (Math.random() * 6 + 1); // Random no. between 1 and 6
            dice_arr[index] = dice_value; // Index of outer_index is set to value of dice_1
        }

        return dice_arr; // return array to method invoke
    }

    public static boolean rollforPoint(int point){
        int new_sum = sum(rollDice()); // Method call of sum - stored in variable new_sum

        while(new_sum != 7 || new_sum != point){ // While point not equal to 7 or equal to point ...
            return true; // ... the point is given
        }
        return false; // return false if not point not given
    }

    // Method for printing game statistics
    private static void printStatistics(){
        System.out.println("Total points: " + total_user_points);
    }

    // Method for updating number of rolls
    private static void updateStatistics(){
        rollCount++;
    }

}
