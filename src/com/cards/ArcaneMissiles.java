package com.cards;

import java.util.List;
import java.util.Random;

import com.characters.Characters;
import com.main.Animations;
import com.main.GamePanel;
import com.main.Sounds;

public class ArcaneMissiles extends Card{

    public ArcaneMissiles(GamePanel gps){
        super("images/cardArcaneMissiles.png", 0 , 30, Card.SPELLS, 5, gps);
    }

    @Override
    public void cardEffect(List<Characters> e, Characters t, Characters p) {
        gp.playSFX(Sounds.SPARK);
        rand = new Random();
        int i = rand.nextInt(e.size());
        animHelper(e.get(i), Animations.MAGIC);
        attackHelper(e.get(i));
        useEnergy();

    }
}