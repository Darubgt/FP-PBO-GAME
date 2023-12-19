package com.cards;

import java.util.List;

import com.characters.Characters;
import com.main.Animations;
import com.main.GamePanel;
import com.main.Sounds;

public class AxeRain extends Card{

    public AxeRain(GamePanel gps){
        super("images/cardAxeRain.png", 20 , 0, Card.SPELLS, 9, gps);
    }

    @Override
    public void cardEffect(List<Characters> e, Characters t, Characters p) {
        gp.playSFX(Sounds.SLASH);
        for (Characters characters : e) {
            animHelper(characters, Animations.SLASH);
            attackHelper(characters);
        }
        gp.drawCards(1);
        useEnergy();

    }
}