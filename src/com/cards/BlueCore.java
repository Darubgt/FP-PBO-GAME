package com.cards;

import java.util.List;

import com.characters.Characters;
import com.main.Animations;
import com.main.GamePanel;
import com.main.Sounds;

public class BlueCore extends Card{

    public BlueCore(GamePanel gps){
        super("images/cardBlueCore.png", 0 , 0, Card.SPELLS, 4, gps);
    }

    @Override
    public void cardEffect(List<Characters> e, Characters t, Characters p) {
        animHelper(p, Animations.MAGICARMOR);
        p.magArmorUp(10);
        gp.playSFX(Sounds.BUFFS1);
        useEnergy();

    }
}
