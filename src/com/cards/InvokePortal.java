package com.cards;

import java.util.List;

import com.characters.Characters;
import com.main.Animations;
import com.main.GamePanel;
import com.main.Sounds;

public class InvokePortal extends Card{

    public InvokePortal(GamePanel gps){
        super("images/cardInvokePortal.png", 0 , 0, Card.SPELLS, 7, gps);
    }

    @Override
    public void cardEffect(List<Characters> e, Characters t, Characters p) {
        animHelper(p, Animations.MAGICARMOR);
        gp.drawCards(3);
        gp.playSFX(Sounds.BUFFS1);
        useEnergy();

    }
}
