package com.characters;

import java.util.List;

import com.main.Animations;
import com.main.GamePanel;
import com.main.Sounds;

public class DarkWarrior extends Characters{
    private int attChoice;

    public DarkWarrior (GamePanel gp){
        super("images/darkWarrior1.png","images/darkWarrior2.png", 70, 12, 20, 20, gp);
    }

    @Override
    public void charAttacks(List<Characters> c, Characters p) {
        attChoice = rand.nextInt(4);

        switch (attChoice) {
            case 0:
                animHelper(p, Animations.SLASH);
                p.directAttack(0, 20);
                gp.playSFX(Sounds.SLASH);
                break;
        
            case 1:
                animHelper(this, Animations.HEALS);
                gp.playSFX(Sounds.HEAL);
                heal(12);
                break; 

            case 2:
                animHelper(this, Animations.PHYSICALARMOR);
                gp.playSFX(Sounds.BUFFS1);
                physArmorUp(10);
                break;

            case 3:
                animHelper(this, Animations.MAGICARMOR);
                gp.playSFX(Sounds.BUFFS2);
                magArmorUp(10);
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
