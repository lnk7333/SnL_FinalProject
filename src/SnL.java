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

import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileWriter;
import javax.sound.sampled.*;

public class SnL{
    private ArrayList<Player> players;
    private ArrayList<Snake> snakes;
    private ArrayList<Ladder> ladders;
    private int boardSize;
    private int gameStatus;
    private int nowPlaying;
    private int diceCount;
    private Sound voice;

    public SnL(int s, int diceCount){
        this.boardSize = s;
        this.diceCount = diceCount;
        this.players = new ArrayList<Player>();
        this.snakes = new ArrayList<Snake>();
        this.ladders = new ArrayList<Ladder>();
        this.gameStatus = 0;
    }

    public void setBoardSize(int s){
        this.boardSize = s;
    }

    public void setGameStatus(int s){
        this.gameStatus = s;
    }

    public int getGameStatus(){
        return this.gameStatus;
    }

    public int getDiceCount() {
        return this.diceCount;
    }

    public void play() throws InterruptedException, UnsupportedAudioFileException, IOException, LineUnavailableException {
        this.voice = new Sound();
        this.voice.background(1);
        Player playerInTurn;
        Scanner read = new Scanner(System.in);
        System.out.print("Please enter Player 1: ");
        String player1 = read.nextLine();
        System.out.print("Please enter Player 2: ");
        String player2 = read.nextLine();
        System.out.println();

        //object instantiation
        Player p1 = new Player(player1);
        Player p2 = new Player(player2);
        initiateGame();
        addPlayer(p1);
        addPlayer(p2);

        do {
            playerInTurn = getWhoseTurn();
            boolean sameRoll = false;
            do {
                this.voice.background(1);
                System.out.println("Now Playing " + playerInTurn.getName());
                System.out.println(playerInTurn.getName() + " please press enter to roll the dice");
                String enter = read.nextLine();
                int[] rolls = new int[diceCount];
                if (enter.isEmpty()) {
                    rolls = playerInTurn.rollDice(this.diceCount);
                    this.voice.roll();
                }
                int totalRoll = 0;
                System.out.print("Dice Numbers: ");
                for (int roll : rolls) {
                    System.out.print(roll + " ");
                    totalRoll += roll;
                }
                System.out.println("\nTotal Roll: " + totalRoll);
                Thread.sleep(600);
                this.voice.move();
                movePlayerAround(playerInTurn, totalRoll);
                Thread.sleep(500);
                System.out.println("New Position: " + playerInTurn.getPosition());
                System.out.println("==============================================");
                Thread.sleep(250);

                sameRoll = (diceCount == 2 && rolls[0] == rolls[1]);

                if (sameRoll) {
                    System.out.println("You rolled a double! Roll again.");
                }
            } while (sameRoll && getGameStatus() != 2);

        } while (getGameStatus() != 2);

        this.voice.background(0);
        System.out.println("The winner is: " + playerInTurn.getName());
        this.voice.winning();
    }

    public void addPlayer(Player s){
        this.players.add(s);
    }

    public ArrayList<Player> getPlayers(Player s){
        return this.players;
    }

    public void addSnake(Snake s){
        this.snakes.add(s);
    }

    public void addSnakes(int [][] s){
        for (int r = 0; r < s.length; r++){
            Snake snake = new Snake(s[r][0], s[r][1]);
            this.snakes.add(snake);
        }
    }

    public void addLadder(Ladder l){
        this.ladders.add(l);
    }

    public void addLadders(int [][] l){
        for (int r = 0; r < l.length; r++){
            Ladder ladder = new Ladder(l[r][1], l[r][0]);
            this.ladders.add(ladder);
        }
    }

    public int getBoardSize(){
        return this.boardSize;
    }

    public ArrayList<Snake> getSnakes(){
        return this.snakes;
    }

    public ArrayList<Ladder> getLadders(){
        return this.ladders;
    }

    public void initiateGame(){
        if(this.boardSize == 50){
            int [][] l = {
                    {2,23},
                    {8,34},
            };
            addLadders(l);

            int[][] s = {
                    {5, 47},
                    {9, 29},
                    {15, 38},
            };
            addSnakes(s);

        } else {
            int [][] l = {
                    {2,23},
                    {8,34},
                    {20,77},
                    {32,68},
                    {41,79},
                    {74,88},
                    {82,100},
                    {85,95}
            };
            addLadders(l);

            int[][] s = {
                    {5, 47},
                    {9, 29},
                    {15, 38},
                    {25, 97},
                    {33, 53},
                    {37, 62},
                    {54, 86},
                    {70, 92}
            };
            addSnakes(s);
        }
    }

    public void movePlayerAround(Player p, int x) throws InterruptedException, UnsupportedAudioFileException, IOException, LineUnavailableException {
        try{
            p.moveAround(x, this.boardSize);
            for(Ladder l : this.ladders){
                if(p.getPosition() == l.getBottomPosition()) {
                    try {
                        this.voice.up();
                    } catch (IOException e) {
                        System.err.println("Error playing sound effect: " + e.getMessage());
                    }
                    System.out.println(p.getName() + " you got ladder from: " + l.getBottomPosition() + " to: " + l.getTopPosition());
                    p.setPosition(l.getTopPosition());
                }
            }
            for(Snake s : this.snakes){
                if(p.getPosition() == s.getHeadPosition()){
                    try {
                        this.voice.down();
                    } catch (IOException e) {
                        System.out.println("Error playing sound effect: " + e.getMessage());
                    }
                    p.setPosition(s.getTailPosition());
                    System.out.println(p.getName() + " you get snake head from " + s.getHeadPosition() + " slide down to " + s.getTailPosition());
                }
            }
        }catch(UnsupportedAudioFileException e){
            e.printStackTrace();
        }
        if(p.getPosition() == this.boardSize){
            this.gameStatus = 2;
            roundRecord();
        }
    }

    public Player getWhoseTurn(){
        if(this.gameStatus == 0){
            this.gameStatus = 1;
            double r = Math.random();
            if(r <= 0.5){
                this.nowPlaying = 0;
                return this.players.get(0);
            } else {
                this.nowPlaying = 1;
                return this.players.get(1);
            }
        } else {
            if(this.nowPlaying == 0){
                this.nowPlaying = 1;
                return this.players.get(1);
            } else {
                this.nowPlaying = 0;
                return this.players.get(0);
            }
        }
    }

    public void roundRecord(){
        try {
            FileWriter writer = new FileWriter("src/Round_Record.txt");
            writer.write("\n The winner is " + this.players.get(this.nowPlaying).getName() + "!!!\n");
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
