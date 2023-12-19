package com.characters;

import java.util.List;

import com.main.Animations;
import com.main.GamePanel;
import com.main.Sounds;

public class TreeMonster extends Characters {
        private int attChoice;

    public TreeMonster (GamePanel gp){
        super("images/treeMonster.png","images/treeMonster2.png", 120, 12, 30, 30, gp);
    }

    @Override
    public void charAttacks(List<Characters> c, Characters p) {
        attChoice = rand.nextInt(4);

        switch (attChoice) {
            case 0:
                animHelper(p, Animations.MAGIC);
                gp.playSFX(Sounds.SPARK);
                p.directAttack(2, 2);
                break;
        
            case 1:
                animHelper(this, Animations.HEALS);
                gp.playSFX(Sounds.HEAL);
                heal(20);
                break; 

            case 2:
                for (Characters characters : c) {
                    System.out.println(characters);
                    animHelper(characters, Animations.HEALS);
                    gp.playSFX(Sounds.HEAL);
                    characters.heal(10);
                }
                break;

            case 3:
                for (Characters characters : c) {
                    gp.playSFX(Sounds.BUFFS3);
                    animHelper(characters, Animations.PHYSICALARMOR);
                    animHelper(characters, Animations.MAGICARMOR);
                    characters.physArmorUp(5);
                    characters.magArmorUp(5);
                }
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
