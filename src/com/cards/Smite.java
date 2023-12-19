package com.cards;

import java.util.List;

import com.characters.Characters;
import com.main.Animations;
import com.main.GamePanel;
import com.main.Sounds;

public class Smite extends Card{

    public Smite(GamePanel gps){
        super("images/cardSmite.png", 5 , 0, Card.TARGET, 5, gps);
    }

    @Override
    public void cardEffect(List<Characters> e, Characters t, Characters p) {
        gp.playSFX(Sounds.SLASH);
        animHelper(t, Animations.SLASH);
        animHelper(p, Animations.PHYSICALARMOR);
        attackHelper(t);
        p.physArmorUp(7);
        useEnergy();

    }
}