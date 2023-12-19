package com.cards;

import java.util.List;

import com.characters.Characters;
import com.main.Animations;
import com.main.GamePanel;
import com.main.Sounds;

public class DecisiveStrike extends Card{

    public DecisiveStrike(GamePanel gps){
        super("images/cardDecisiveStrike.png", 10 , 0, Card.TARGET, 4, gps);
    }

    @Override
    public void cardEffect(List<Characters> e, Characters t, Characters p) {
        animHelper(t, Animations.SLASH);
        gp.playSFX(Sounds.SLASH);
        attackHelper(t);
        useEnergy();
    }
}