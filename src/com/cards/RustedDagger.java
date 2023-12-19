package com.cards;

import java.util.List;

import com.characters.Characters;
import com.main.Animations;
import com.main.GamePanel;
import com.main.Sounds;

public class RustedDagger extends Card{

    public RustedDagger(GamePanel gps){
        super("images/cardRusted.png", 40 , 0, Card.TARGET, 3, gps);
    }

    @Override
    public void cardEffect(List<Characters> e, Characters t, Characters p) {
        animHelper(p, Animations.SLASH);
        animHelper(t, Animations.SLASH);
        gp.playSFX(Sounds.SLASH);
        p.directAttack(0, 20);
        attackHelper(t);
        useEnergy();

    }
}