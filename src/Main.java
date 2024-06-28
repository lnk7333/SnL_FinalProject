/**
 * -----------------------------------------------------
 * ES234211 - Programming Fundamental
 * Genap - 2023/2024
 * Group Capstone Project: Snake and Ladder Game
 * -----------------------------------------------------
 * Class    : D
 * Group    : 09
 * Members  :
 * 1. 5026231089 - Yusuf Acala Sadurjaya Sri Krisna
 * 2. 5026231144 - Krisna Agung Prabowo
 * 3. 5026231092 - Muhammad Fawwaz Al-Amien
 * ------------------------------------------------------
 */
import java.util.Scanner;
import java.io.IOException;
import javax.sound.sampled.*;

public class Main {

    public static void main(String[] args) throws InterruptedException, UnsupportedAudioFileException, IOException, LineUnavailableException {
        Scanner read = new Scanner(System.in);
        System.out.println("(1) Easy ");
        System.out.println("(2) Normal ");
        System.out.println("(3) Hard ");
        System.out.println("=============================");
        System.out.print("Please select a difficulty: ");

        try {
            int bSize = 0;
            int choice;

            choice = read.nextInt();

            if (choice == 1) bSize = 50;
            if (choice == 2) bSize = 100;
            if (choice == 3) bSize = 150;

            System.out.print("Would you like to play with 1 or 2 dice? ");
            int diceCount = read.nextInt();
            read.nextLine();
            System.out.println("Now you are playing with " + diceCount + " dice");

            SnL g1 = new SnL(bSize, diceCount);
            g1.play();
        } catch(Exception e){
            System.out.println("Error!");
        }
    }
}
