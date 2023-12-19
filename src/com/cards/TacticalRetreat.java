package com.cards;

import java.util.List;

import com.characters.Characters;
import com.main.Animations;
import com.main.GamePanel;
import com.main.Sounds;

public class TacticalRetreat extends Card{

    public TacticalRetreat(GamePanel gps){
        super("images/cardTacticalRetreat.png", 5 , 0, Card.TARGET, 2, gps);
    }

    @Override
    public void cardEffect(List<Characters> e, Characters t, Characters p) {
        animHelper(t, Animations.SLASH);
        animHelper(p, Animations.HEALS);
        gp.playSFX(Sounds.HEAL);
        gp.playSFX(Sounds.SLASH);
        attackHelper(t);
        p.heal(10);
        useEnergy();

    }
}
