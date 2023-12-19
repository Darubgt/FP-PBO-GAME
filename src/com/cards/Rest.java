package com.cards;

import java.util.List;

import com.characters.Characters;
import com.main.Animations;
import com.main.GamePanel;
import com.main.Sounds;

public class Rest extends Card{

    public Rest(GamePanel gps){
        super("images/cardRest.png", 0 , 0, Card.SPELLS, 6, gps);
    }

    @Override
    public void cardEffect(List<Characters> e, Characters t, Characters p) {
        animHelper(p, Animations.HEALS);
        gp.playSFX(Sounds.HEAL);
        p.physArmorUp(50);
        for (Characters characters : e) {
            characters.heal(10);
            animHelper(characters, Animations.HEALS);
        }
        gp.drawCards(1);
        useEnergy();

    }
}