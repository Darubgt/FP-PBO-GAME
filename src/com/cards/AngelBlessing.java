package com.cards;

import java.util.List;

import com.characters.Characters;
import com.main.Animations;
import com.main.GamePanel;
import com.main.Sounds;

public class AngelBlessing extends Card{

    public AngelBlessing(GamePanel gps){
        super("images/cardAngelBlessing.png", 0 , 0, Card.SPELLS, 13, gps);
    }

    @Override
    public void cardEffect(List<Characters> e, Characters t, Characters p) {
        animHelper(p, Animations.HEALS);
        gp.playSFX(Sounds.HEAL);
        p.heal(50);
        useEnergy();

    }
}