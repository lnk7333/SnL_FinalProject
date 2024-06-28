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

public class Ladder{
    private int topPosition;
    private int bottomPosition;

    public Ladder(int t, int b){
        this.topPosition = t;
        this.bottomPosition = b;
    }

    public void setTopPosition(int t){
        this.topPosition = t;
    }

    public void setBottomPosition(int b){
        this.bottomPosition = b;
    }

    public int getTopPosition() {
        return this.topPosition ;
    }

    public int getBottomPosition() {
        return this.bottomPosition ;
    }
}