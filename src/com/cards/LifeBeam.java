package com.cards;

import java.util.List;

import com.characters.Characters;
import com.main.Animations;
import com.main.GamePanel;
import com.main.Sounds;

public class LifeBeam extends Card{

    public LifeBeam(GamePanel gps){
        super("images/cardLifeBeam.png", 0 , 20, Card.TARGET, 9, gps);
    }

    @Override
    public void cardEffect(List<Characters> e, Characters t, Characters p) {
        animHelper(t, Animations.MAGIC);
        animHelper(p, Animations.HEALS);
        gp.playSFX(Sounds.SPARK);
        gp.playSFX(Sounds.HEAL);
        attackHelper(t);
        p.heal(20);
        gp.drawCards(1);
        useEnergy();

    }
}
