package opgave03;

import javax.annotation.processing.SupportedSourceVersion;
import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.Arrays;

public class Pigs {
    public static int pointsPlayer1 = 0;
    public static int pointsPlayer2 = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        greeting_users ();
        printRules();
        System.out.println("Ønsker du at starte spillet? (y/n)");
        String startGameAnswer = scanner.next();
        while (startGameAnswer.equals("y")) {

            playPigs();


            System.out.println("Ønsker du at prøve igen?");
            startGameAnswer = scanner.next();
            pointsPlayer1=0;
            pointsPlayer2=0;
        }
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

// Her er metoden til selve spillet
    private static void playPigs() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hvor mange point ønsker du at spillet til?");
        int roundsMaxPoint = maxPoints();
        System.out.println("I er to spillere. Spiller 1 starter!");
        System.out.println("===========================================");
        while (pointsPlayer1 < roundsMaxPoint || pointsPlayer2 < roundsMaxPoint) {

            System.out.println("Vil spiller 1 kaste terningen? (y/n)");
            String rollDiceAnswer1 = scanner.next();

            int player1Sum = 0;
            int player2Sum = 0;


            // Spiller 1 while loop

            while (rollDiceAnswer1.equals("y")) {

                int[] player1Roll = rollDice();

                System.out.println("Du slog: " + Arrays.toString(player1Roll));
                System.out.println("===========================================");

                if (player1Roll[0]==1 || player1Roll[1]==1 ){
                    System.out.println("Du slog desværre 1 så ingen point til dig denne runde");
                    System.out.println("===========================================");
                    pointsPlayer1-=player1Sum;
                    player1Sum=0;
                    break;
                }
                if (player1Roll[0]==1 && player1Roll[1]==1){
                    System.out.println("Du slog desværre 2 1ere så du mister alle dine point :(");
                    System.out.println("===========================================");
                    player1Sum=0;
                    pointsPlayer1=0;
                    break;
                }

                for (int i = 0; i < player1Roll.length; i++) {
                    player1Sum += player1Roll[i];
                    pointsPlayer1 += player1Roll[i];
                }

                System.out.println("Du har nu " + pointsPlayer1 + "point!");

                if (pointsPlayer1>=roundsMaxPoint){
                    System.out.println("Spiller 1 vandt! Tillykke!!!!");
                    return;
                }

                System.out.println("Ønsker du at slå igen? (y/n)");
                rollDiceAnswer1 = scanner.next();
            }



            System.out.println("Nu er det spiller 2s tur!");
            System.out.println("===========================================");
            System.out.println("Vil spiller 2 kaste terningen?");
            String rollDiceAnswer2 = scanner.next();

            // Spiller 2 while loop

            while (rollDiceAnswer2.equals("y")) {

                int[] player2Roll = rollDice();

                System.out.println("Du slog: " + Arrays.toString(player2Roll));
                System.out.println("===========================================");

                if (player2Roll[0]==1 || player2Roll[1]==1){
                    System.out.println("Du slog desværre 1 så ingen point til dig denne runde");
                    System.out.println("===========================================");
                    pointsPlayer2-=player2Sum;
                    player2Sum=0;
                    break;
                }
                if (player2Roll[0]==1 && player2Roll[1]==1){
                    System.out.println("Du slog desværre 2 1ere så du mister alle dine point :(");
                    System.out.println("===========================================");
                    player2Sum=0;
                    pointsPlayer2=0;
                    break;
                }

                for (int i = 0; i < player2Roll.length; i++) {
                    player2Sum += player2Roll[i];
                    pointsPlayer2 += player2Roll[i];
                }

                System.out.println("Du har nu " + pointsPlayer2 + "point!");

                if (pointsPlayer2>=roundsMaxPoint){
                    System.out.println("Spiller 2 vandt! Tillykke!!!!");
                    return;
                }

                System.out.println("Ønsker du at slå igen? (y/n)");
                rollDiceAnswer2 = scanner.next();
            }


//            /*-ERROR- Hvis begge spillere trykker nej lige meget om de har  -ERROR-
//              -ERROR- slået med terningen eller ej så går den ud af spillet -ERROR- */
//            if (rollDiceAnswer1.equals("n") && rollDiceAnswer2.equals("n")){
//                System.out.println("Okay fint så lad værd");
//                return;
//            }


            printStatistics();

            System.out.println("===========================================");
            System.out.println("Nu er det spiller 1 igen!");
            System.out.println("===========================================");

        }



    }

// Her er "RollDice" metoden som står for selve terningerne

    private static int[] rollDice() {

// Ønsker man at spille med en eller to terninger kan man rette array længden på "diceArr"

        int[] diceArr = new int[2];
        int arrayLength = diceArr.length;
        for (int i = 0; i < arrayLength; i++) {
            int diceValue = (int) (Math.random() * 6 + 1);
            diceArr[i] = diceValue;
        }

        return diceArr;
    }

    private static int maxPoints() {
        Scanner scanner = new Scanner(System.in);
        int numberOfRounds = scanner.nextInt();

        return numberOfRounds;
    }

    private static void printStatistics (){
        System.out.println("Spiller 1 har " + pointsPlayer1 + "Point");
        System.out.println("Spiller 2 har " + pointsPlayer2 + "Point");
    }
}
