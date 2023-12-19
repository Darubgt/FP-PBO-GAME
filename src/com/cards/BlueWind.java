package com.cards;

import java.util.List;

import com.characters.Characters;
import com.main.Animations;
import com.main.GamePanel;
import com.main.Sounds;

public class BlueWind extends Card{

    public BlueWind(GamePanel gps){
        super("images/cardBlueWind.png", 0 , 20, Card.TARGET, 6, gps);
    }

    @Override
    public void cardEffect(List<Characters> e, Characters t, Characters p) {
        animHelper(t, Animations.MAGIC);
        attackHelper(t);
        gp.playSFX(Sounds.SPARK);
        useEnergy();

    }
}
