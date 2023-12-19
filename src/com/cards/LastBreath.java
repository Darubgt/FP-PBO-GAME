package com.cards;

import java.util.List;

import com.characters.Characters;
import com.main.Animations;
import com.main.GamePanel;
import com.main.Sounds;

public class LastBreath extends Card{

    public LastBreath(GamePanel gps){
        super("images/cardLastBreath.png", 0 , 0, Card.SPELLS, 3, gps);
    }

    @Override
    public void cardEffect(List<Characters> e, Characters t, Characters p) {
        animHelper(p, Animations.HEALS);
        gp.playSFX(Sounds.HEAL);
        if(p.getHealth() <= 20){
            p.heal(20);
            gp.drawCards(1);
        }else{
            p.heal(10);
        }
        useEnergy();

    }
}