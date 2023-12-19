package com.cards;

import java.util.List;

import com.characters.Characters;
import com.main.Animations;
import com.main.GamePanel;
import com.main.Sounds;

public class AbsoluteLight extends Card{

    public AbsoluteLight(GamePanel gps){
        super("images/cardAbsoluteLight.png", 0 , 40, Card.TARGET, 10, gps);
    }

    @Override
    public void cardEffect(List<Characters> e, Characters t, Characters p) {
        animHelper(t, Animations.MAGIC);
        gp.playSFX(Sounds.SPARK);
        attackHelper(t);
        useEnergy();

    }
}