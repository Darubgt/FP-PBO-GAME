package com.cards;

import java.util.List;

import com.characters.Characters;
import com.main.Animations;
import com.main.GamePanel;
import com.main.Sounds;

public class Innervation extends Card{

    public Innervation(GamePanel gps){
        super("images/cardInnervation.png", 0 , 0, Card.SPELLS, 0, gps);
    }

    @Override
    public void cardEffect(List<Characters> e, Characters t, Characters p) {
        animHelper(p, Animations.MAGICARMOR);
        gp.energyUp(2);
        gp.playSFX(Sounds.BUFFS3);
        useEnergy();

    }
}
