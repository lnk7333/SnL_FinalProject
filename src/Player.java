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

public class Player {
    private String name;
    private int position;

    Player(String name){
        this.name=name;
        this.position=0;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setPosition(int position){
        this.position = position;
    }

    public String getName(){
        return this.name;
    }

    public int getPosition(){
        return this.position;
    }

    public int[] rollDice(int diceCount) {
        int[] rolls = new int[diceCount];
        for (int i = 0; i < diceCount; i++) {
            rolls[i] = (int)(Math.random() * 6 + 1);
        }
        return rolls;
    }

    public void moveAround(int y, int boardSize) {
        if (this.position + y > boardSize) {
            this.position = boardSize - (this.position + y) % boardSize;
        } else {
            this.position = this.position + y;
        }
    }
}
