package com.cards;

import java.util.List;

import com.characters.Characters;
import com.main.Animations;
import com.main.GamePanel;
import com.main.Sounds;

public class Preparation extends Card{

    public Preparation(GamePanel gps){
        super("images/cardPreparation.png", 0 , 0, Card.SPELLS, 15, gps);
    }

    @Override
    public void cardEffect(List<Characters> e, Characters t, Characters p) {
        animHelper(p, Animations.HEALS);
        gp.playSFX(Sounds.HEAL);
        p.heal(30);
        p.physArmorUp(50);
        p.magArmorUp(50);
        useEnergy();
    }
}