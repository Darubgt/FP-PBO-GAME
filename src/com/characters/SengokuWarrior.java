package com.characters;

import java.util.List;

import com.main.Animations;
import com.main.GamePanel;
import com.main.Sounds;

public class SengokuWarrior extends Characters {
        private int attChoice;

    public SengokuWarrior (GamePanel gp){
        super("images/sengokuWarrior.png","images/sengokuWarrior2.png", 30, 18, 50, 50, gp);
    }

    @Override
    public void charAttacks(List<Characters> c, Characters p) {
        attChoice = rand.nextInt(3);

        switch (attChoice) {
            case 0:
                animHelper(p, Animations.SLASH);
                gp.playSFX(Sounds.SLASH);
                p.directAttack(0, 30);
                break;

            case 1:
                animHelper(p, Animations.MAGIC);
                gp.playSFX(Sounds.SPARK);
                p.directAttack(30, 0);
                break;
        
            case 2:
                physArmorUp(5);
                magArmorUp(5);
                break; 

            default:
                break;
        }
    }

    @Override
    public int offsetX() {
        return x + 75;
    }
}
