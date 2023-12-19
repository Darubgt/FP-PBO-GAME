package com.cards;

import java.util.List;

import com.characters.Characters;
import com.main.Animations;
import com.main.GamePanel;
import com.main.Sounds;

public class Brace extends Card{

    public Brace(GamePanel gps){
        super("images/cardBrace.png", 0 , 0, Card.TARGET, 4, gps);
    }

    @Override
    public void cardEffect(List<Characters> e, Characters t, Characters p) {
        animHelper(p, Animations.PHYSICALARMOR);
        p.physArmorUp(10);
        gp.playSFX(Sounds.CLINK);
        useEnergy();

    }
}
