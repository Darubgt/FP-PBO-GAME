package com.cards;

import java.util.List;

import com.characters.Characters;
import com.main.Animations;
import com.main.GamePanel;
import com.main.Sounds;

public class UnstableEnergy extends Card{

    public UnstableEnergy(GamePanel gps){
        super("images/cardUnstable.png", 0 , 0, Card.TARGET, 15, gps);
    }

    @Override
    public void cardEffect(List<Characters> e, Characters t, Characters p) {
        animHelper(t, Animations.MAGICARMOR);
        animHelper(t, Animations.MAGIC);
        gp.playSFX(Sounds.ICE);
        if(t.getMagicArmor() != 0){
            int a = t.getMagicArmor();
            t.setMagicArmor(0);
            attackHelper(t, 0, a);
        }
        useEnergy();

    }
}