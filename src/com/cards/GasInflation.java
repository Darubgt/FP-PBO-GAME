package com.cards;

import java.util.List;

import com.characters.Characters;
import com.main.Animations;
import com.main.GamePanel;
import com.main.Sounds;

public class GasInflation extends Card{

    public GasInflation(GamePanel gps){
        super("images/cardGasInflation.png", 0 , 15, Card.SPELLS, 2, gps);
    }

    @Override
    public void cardEffect(List<Characters> e, Characters t, Characters p) {
        animHelper(p, Animations.SLASH);
        gp.playSFX(Sounds.SLASH);
        attackHelper(p);
        gp.drawCards(3);
        useEnergy();
    }
}