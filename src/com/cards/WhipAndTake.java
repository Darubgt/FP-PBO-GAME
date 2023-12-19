package com.cards;

import java.util.List;

import com.characters.Characters;
import com.main.Animations;
import com.main.GamePanel;
import com.main.Sounds;

public class WhipAndTake extends Card{

    public WhipAndTake(GamePanel gps){
        super("images/cardWhipAndTake.png", 10 , 0, Card.TARGET, 6, gps);
    }

    @Override
    public void cardEffect(List<Characters> e, Characters t, Characters p) {
        gp.playSFX(Sounds.SLASH);
        animHelper(t, Animations.SLASH);
        animHelper(p, Animations.MAGICARMOR);
        attackHelper(t);
        gp.drawCards(1);
        useEnergy();

    }
}