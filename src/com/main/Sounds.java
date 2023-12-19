package com.main;


import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sounds {

    private Clip clip;
    private URL soundURL[];
    public static final int BGMUSIC = 0,
                            DECKFLICK = 1,
                            SLASH = 2,
                            BUFFS1 = 3,
                            BUFFS2 = 4,
                            BUFFS3 = 5,
                            CLICK = 6,
                            CLINK = 7,
                            COMBUST = 8,
                            GROAN = 9,
                            HEAL = 10,
                            ICE = 11,
                            PDEATH = 12,
                            PHIT = 13,
                            SPARK = 14;

    public Sounds(){
        soundURL = new URL [20];

        soundURL[0] = getClass().getResource("sound/PBOBackgroundMusic.wav");
        soundURL[1] = getClass().getResource("sound/deckFlick.wav");
        soundURL[2] = getClass().getResource("sound/Slash.wav");
        soundURL[3] = getClass().getResource("sound/Buffs_1.wav");
        soundURL[4] = getClass().getResource("sound/Buffs_2.wav");
        soundURL[5] = getClass().getResource("sound/Buffs_3.wav");
        soundURL[6] = getClass().getResource("sound/Click.wav");
        soundURL[7] = getClass().getResource("sound/clink.wav");
        soundURL[8] = getClass().getResource("sound/Combust.wav");
        soundURL[9] = getClass().getResource("sound/GroanDeath.wav");
        soundURL[10] = getClass().getResource("sound/Heal.wav");
        soundURL[11] = getClass().getResource("sound/Ice.wav");
        soundURL[12] = getClass().getResource("sound/PlayerDeath.wav");
        soundURL[13] = getClass().getResource("sound/PlayerHit.wav");
        soundURL[14] = getClass().getResource("sound/Spark.wav");
        
    }

    public void setFile(int i){
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error masuk audio " + i);
        }
    }

    public void play(){
        // System.out.println("Starting Music");
        clip.start();
    }

    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop(){
        clip.stop();
    } 
}
