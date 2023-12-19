package com.cards;

import java.util.List;

import com.characters.Characters;
import com.main.Animations;
import com.main.GamePanel;
import com.main.Sounds;

public class MagicBolt extends Card{

    public MagicBolt(GamePanel gps){
        super("images/cardMagicBolt.png", 0 , 10, Card.TARGET, 4, gps);
    }

    @Override
    public void cardEffect(List<Characters> e, Characters t, Characters p) {
        animHelper(t, Animations.MAGIC);
        attackHelper(t);
        gp.playSFX(Sounds.SPARK);
        useEnergy();

    }
}
