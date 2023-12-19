package com.cards;

import java.util.List;

import com.characters.Characters;
import com.main.Animations;
import com.main.GamePanel;
import com.main.Sounds;

public class SwordOfMartin extends Card{

    public SwordOfMartin(GamePanel gps){
        super("images/cardSwordOfMartin.png", 10 , 10, Card.TARGET, 7, gps);
    }

    @Override
    public void cardEffect(List<Characters> e, Characters t, Characters p) {
        animHelper(t, Animations.SLASH);
        gp.playSFX(Sounds.SLASH);
        attackHelper(t);
        useEnergy();

    }
}
