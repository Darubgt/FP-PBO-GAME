package com.characters;

import java.util.List;

import com.main.Animations;
import com.main.GamePanel;
import com.main.Sounds;

public class MagicWyvern extends Characters{
    private int attChoice;

    public MagicWyvern (GamePanel gp){
        super("images/magicWyvern.png","images/magicWyvern2.png", 100, 10, 50, 2, gp);
    }

    @Override
    public void charAttacks(List<Characters> c, Characters p) {
        attChoice = rand.nextInt(4);

        switch (attChoice) {
            case 0:
                animHelper(p, Animations.MAGIC);
                gp.playSFX(Sounds.SPARK);
                p.directAttack(25, 0);
                break;
        
            case 1:
                animHelper(this, Animations.HEALS);
                gp.playSFX(Sounds.HEAL);
                heal(15);
                break; 

            case 2:
                animHelper(this, Animations.MAGICARMOR);
                gp.playSFX(Sounds.BUFFS2);
                magArmorUp(12);
                break;

            case 3:
                for (Characters characters : c) {
                    animHelper(characters, Animations.MAGICARMOR);
                    gp.playSFX(Sounds.BUFFS1);
                    characters.magArmorUp(8);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public int offsetX() {
        return x + 50;
    }
}
