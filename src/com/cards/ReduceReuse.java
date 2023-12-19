package com.cards;

import java.util.List;

import com.characters.Characters;
import com.main.Animations;
import com.main.GamePanel;
import com.main.Sounds;

public class ReduceReuse extends Card{

    public ReduceReuse(GamePanel gps){
        super("images/cardReduce.png", 20 , 0, Card.TARGET, 13, gps);
    }

    @Override
    public void cardEffect(List<Characters> e, Characters t, Characters p) {
        gp.playSFX(Sounds.SLASH);
        gp.playSFX(Sounds.HEAL);
        animHelper(t, Animations.SLASH);
        if(t.getPhysArmor() != 0){
            animHelper(p, Animations.PHYSICALARMOR);
            p.physArmorUp(t.getPhysArmor());
            t.setPhysArmor(0);
        }
        useEnergy();

    }
}