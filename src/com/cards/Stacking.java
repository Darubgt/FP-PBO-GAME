package com.cards;

import java.util.List;

import com.characters.Characters;
import com.main.Animations;
import com.main.GamePanel;
import com.main.Sounds;

public class Stacking extends Card{

    public Stacking(GamePanel gps){
        super("images/cardStacking.png", 0 , 0, Card.SPELLS, 6, gps);
    }

    @Override
    public void cardEffect(List<Characters> e, Characters t, Characters p) {
        animHelper(p, Animations.MAGICARMOR);
        animHelper(p, Animations.PHYSICALARMOR);
        gp.playSFX(Sounds.BUFFS2);
        gp.drawCards(2);
        p.magArmorUp(15);
        p.physArmorUp(15);
        useEnergy();

    }
}