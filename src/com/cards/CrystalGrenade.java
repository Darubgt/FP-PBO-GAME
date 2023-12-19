package com.cards;

import java.util.List;

import com.characters.Characters;
import com.main.Animations;
import com.main.GamePanel;
import com.main.Sounds;

public class CrystalGrenade extends Card{

    public CrystalGrenade(GamePanel gps){
        super("images/cardCrystalGrenade.png", 0 , 20, Card.SPELLS, 8, gps);
    }

    @Override
    public void cardEffect(List<Characters> e, Characters t, Characters p) {
        gp.playSFX(Sounds.ICE);
        for (Characters ch : e) {
            animHelper(ch, Animations.MAGIC);
            attackHelper(ch);
        }
        useEnergy();

    }
}