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

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.*;


public class Sound  {
    private File upFile;
    private File downFile;
    private File walkFile;
    private File diceFile;
    private File backsoundFile;
    private File winFile;
    private AudioInputStream upAudio;
    private AudioInputStream downAudio;
    private AudioInputStream walkAudio;
    private AudioInputStream diceAudio;
    private AudioInputStream backsoundAudio;
    private AudioInputStream winAudio;
    private Clip upClip;
    private Clip downClip;
    private Clip walkClip;
    private Clip diceClip;
    private Clip backsoundClip;
    private Clip winClip;

    public Sound() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        this.upFile = new File("src/slide_up.wav");
        this.downFile = new File("src/slide_down.wav");
        this.walkFile = new File("src/move.wav");
        this.diceFile = new File("src/dice.wav");
        this.backsoundFile = new File("src/backsound.wav");
        this.winFile = new File("src/win.wav");
        this.backsoundClip = AudioSystem.getClip();
        this.upClip = AudioSystem.getClip();
        this.downClip = AudioSystem.getClip();
        this.walkClip = AudioSystem.getClip();
        this.diceClip = AudioSystem.getClip();
        this.winClip = AudioSystem.getClip();

        this.backsoundAudio = AudioSystem.getAudioInputStream(backsoundFile);
        this.backsoundClip.open(backsoundAudio);
        this.upAudio = AudioSystem.getAudioInputStream(upFile);
        this.upClip.open(upAudio);
        this.downAudio = AudioSystem.getAudioInputStream(downFile);
        this.downClip.open(downAudio);
        this.walkAudio = AudioSystem.getAudioInputStream(walkFile);
        this.walkClip.open(walkAudio);
        this.diceAudio = AudioSystem.getAudioInputStream(diceFile);
        this.diceClip.open(diceAudio);
        this.winAudio = AudioSystem.getAudioInputStream(winFile);
        this.winClip.open(winAudio);
    }


    public void background(int i) throws UnsupportedAudioFileException, IOException, LineUnavailableException{

        if(i == 1) {
            if(!backsoundClip.isRunning()) {
                backsoundClip.setMicrosecondPosition(0);
                backsoundClip.start();
            }
        } else {
            backsoundClip.close();
            upClip.close();
            downClip.close();
            walkClip.close();
            diceClip.close();
        }
    }

    //getting a ladder
    public void up() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        upClip.stop();
        upClip.setMicrosecondPosition(0);
        upClip.start();
    }

    // getting a snake
    public void down() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        downClip.stop();
        downClip.setMicrosecondPosition(0);
        downClip.start();
    }

    // moving
    public void move() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        walkClip.stop();
        walkClip.setMicrosecondPosition(0);
        walkClip.start();
    }

    public void roll() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        diceClip.stop();
        diceClip.setMicrosecondPosition(0);
        diceClip.start();
    }

    public void winning() throws InterruptedException, UnsupportedAudioFileException, IOException, LineUnavailableException{
        winClip.start();
        Thread.sleep(1000);
        winClip.close();
    }
}
