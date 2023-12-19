package com.cards;

import java.util.List;

import com.characters.Characters;
import com.main.Animations;
import com.main.GamePanel;
import com.main.Sounds;

public class ShieldBash extends Card{

    public ShieldBash(GamePanel gps){
        super("images/cardBash.png", 0 , 0, Card.SPELLS, 7, gps);
    }

    @Override
    public void cardEffect(List<Characters> e, Characters t, Characters p) {
        gp.playSFX(Sounds.SLASH);
        animHelper(p, Animations.PHYSICALARMOR);
        animHelper(t, Animations.SLASH);
        if(p.getPhysArmor() != 0){
            attackHelper(p, p.getPhysArmor(), 0);
            p.setPhysArmor(0);
        }
        useEnergy();

    }
}