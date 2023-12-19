package com.cards;

import java.util.List;

import com.characters.Characters;
import com.main.Animations;
import com.main.GamePanel;
import com.main.Sounds;

public class DimensionAnalyst extends Card{

    public DimensionAnalyst(GamePanel gps){
        super("images/cardDimension.png", 0 , 0, Card.TARGET, 14, gps);
    }

    @Override
    public void cardEffect(List<Characters> e, Characters t, Characters p) {
        animHelper(p, Animations.HEALS);
        gp.playSFX(Sounds.HEAL);
        if (t.getMagicArmor() != 0){
            animHelper(t, Animations.MAGIC);
            p.heal(t.getMagicArmor());
        }
        useEnergy();
    }
}