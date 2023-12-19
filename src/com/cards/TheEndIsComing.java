package com.cards;

import java.util.List;

import com.characters.Characters;
import com.main.Animations;
import com.main.GamePanel;
import com.main.Sounds;

public class TheEndIsComing extends Card{

    public TheEndIsComing(GamePanel gps){
        super("images/cardTheEndIsComing.png", 0 , 50, Card.SPELLS, 9, gps);
    }

    @Override
    public void cardEffect(List<Characters> e, Characters t, Characters p) {
        for (Characters characters : e) {
            animHelper(characters, Animations.EXPLOSIONS1);
            attackHelper(characters);
        }
        animHelper(p, Animations.EXPLOSIONS1);
        attackHelper(p);
        gp.playSFX(Sounds.COMBUST);
        useEnergy();

    }
}
