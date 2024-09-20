package opgave01;

import java.util.Scanner;
import java.util.Arrays;

public class RollTwoDice {
    private static int rollCount = 0;

    // Task 2
    private static int sum_of_dice = 0;

    // Task 5
    private static int[] occurrence_array = new int[6]; // Global array declaration with 6 elements

    public static void main(String[] args) {
        System.out.println("Velkommen til spillet, rul en terning.");
        printRules();
        System.out.println();

        playOneDie();

        System.out.println();
        System.out.println("Tak for at spille, rul en terning.");
    }

    private static void printRules() {
        System.out.println("=====================================================");
        System.out.println("Regler for rul en terning");
        System.out.println("Spilleren ruller en terning, så længde man lyster.");
        System.out.println("=====================================================");
    }

    private static void playOneDie() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Rul en terning? ('ja/nej') ");
        String answer = scanner.nextLine();

        while (!answer.equals("nej")) {
            int[] faces = rollDice(); // Faces invoke rollDice method
            System.out.println("Du rullede: " + Arrays.toString(faces)); // Array class to string (presents in console)

            updateStatistics(faces);

            System.out.print("Rul en terning? ('Indtast 'nej' for afslut spil') ");
            answer = scanner.nextLine();
        }

        printStatistics();
        scanner.close();
    }

    // Task 1
    private static int[] rollDice() {
        // Constant variables because we don't manipulate the structure of the array
        final int [] dice_arr = new int[2]; // Creates array with two index'
        final int dice_arr_length = dice_arr.length; // array length calculated in variable

        // Loop for iterating over value of dice
        for(int index = 0; index < dice_arr_length; index++){
            int dice_value = (int) (Math.random() * 6 + 1); // Random no. between 1 and 6
            dice_arr[index] = dice_value; // Index of outer_index is set to value of dice_1
        }

        equal_eyes(dice_arr); // Method invoke after dice roll

        return dice_arr;
    }

    // Task 2
    private static int sum_of_dice(int[] two_dice_array){
        final int[] dice_array = two_dice_array;
        final int dice_arr_length = two_dice_array.length; // array length calculated in variable

        int total_sum = 0;

        // Loop for iterating over value of dice
        for(int index = 0; index < dice_arr_length; index++){
            int dice_sum = dice_array[index];
            total_sum += dice_sum;
        }

        return total_sum;
    }

    // Task 3
    private static boolean equal_eyes (int[] two_dice_array){

        final int dice_arr_length = two_dice_array.length; // array length calculated in variable

        // Loop for iterating over value of dice
        if(two_dice_array[0] == two_dice_array[1]){
            return true;
        }
        return false;
    }

    // Task 4
    private static int greatest_value(int[] two_dice_array){
        return Math.max(two_dice_array[0], two_dice_array[1]); // Greatest of the two indexes is returned
    }

    // Task 5
    private static int[] occurrence (int[] two_dice_array){
        // For loop for iterating over element of array
        for (int index = 1; index <= 6; index++){
            if (two_dice_array[0] == index){ // if first index of array equals index
                occurrence_array[index - 1]++; // Add 1 to occurrence_array
            } else if (two_dice_array[1] == index){ // If second array equals index
                occurrence_array[index - 1]++; // Add 1 to occurence_array
            }
        }
        return occurrence_array; // Return value of occurrence_array to global array
    }

    private static void updateStatistics(int[] faces) {
        // Task 3
        if(equal_eyes(faces)){ // Returned value true...
            System.out.println("Both dices have matching values!"); // Print output to user
        }

        // Task 4 - determines the greatest value of the rolls
        System.out.println("Greatest value of roll: " + greatest_value(faces));

        // Task 2
        int sum = sum_of_dice(faces); // int variable for calculating sum of rolled dice
        System.out.println("Du rullede i alt: " + sum); // Output to user
        sum_of_dice += sum; // value of sum_of_dice equals to sum_of_dice + calculated sum

        occurrence(faces); // method invoke with parameter of array parameter of updateStatistics

        rollCount++;
    }

    private static void printStatistics() {
        System.out.println("\nResults:");
        System.out.println("-------");
        System.out.println("Antal rul: " + rollCount);
        System.out.println("-------");
        // Task 2
        System.out.println("Total sum for alle kast: " + sum_of_dice);
        System.out.println("-------");
        // Task 5
        for(int index = 0; index < 6; index++){
            System.out.println("Antal " + (index + 1) + "'ere: " + occurrence_array[index]);
        }
        System.out.println("-------");
    }
}
