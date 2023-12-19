package com.cards;

import java.util.List;

import com.characters.Characters;
import com.main.Animations;
import com.main.GamePanel;
import com.main.Sounds;

public class SoulSucking extends Card{

    public SoulSucking(GamePanel gps){
        super("images/cardSoulSucking.png", 0 , 8, Card.TARGET, 6, gps);
    }

    @Override
    public void cardEffect(List<Characters> e, Characters t, Characters p) {
        attackHelper(t);
        animHelper(t,Animations.MAGIC);
        animHelper(p,Animations.MAGICARMOR);
        p.magArmorUp(8);
        gp.playSFX(Sounds.ICE);
        useEnergy();

    }
}
