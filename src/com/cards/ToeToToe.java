package com.cards;

import java.util.List;

import com.characters.Characters;
import com.main.Animations;
import com.main.GamePanel;
import com.main.Sounds;

public class ToeToToe extends Card{

    public ToeToToe(GamePanel gps){
        super("images/cardToe.png", 0 , 0, Card.TARGET, 10, gps);
    }

    @Override
    public void cardEffect(List<Characters> e, Characters t, Characters p) {
        gp.playSFX(Sounds.CLINK);
        if ((p.getPhysArmor() != 0 || p.getMagicArmor() != 0) && (t.getPhysArmor() != 0 || t.getMagicArmor() != 0)){
            animHelper(p, Animations.EXPLOSIONS1);
            animHelper(t, Animations.EXPLOSIONS1);
            p.setPhysArmor(0);
            p.setMagicArmor(0);
            t.setPhysArmor(0);
            t.setMagicArmor(0);
        }
        useEnergy();

    }
}