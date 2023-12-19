package com.cards;

import java.util.List;

import com.characters.Characters;
import com.main.Animations;
import com.main.GamePanel;
import com.main.Sounds;

public class Decimate extends Card{

    public Decimate(GamePanel gps){
        super("images/cardDecimate.png", 0 , 0, Card.TARGET, 8, gps);
    }

    @Override
    public void cardEffect(List<Characters> e, Characters t, Characters p) {
        gp.playSFX(Sounds.SLASH);
        if(t.getHealth() <= 20){
            animHelper(t, Animations.SLASH);
            t.setHealth(0);
        }
        useEnergy();

    }
}