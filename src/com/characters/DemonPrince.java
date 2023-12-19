package com.characters;

import java.util.List;

import com.main.Animations;
import com.main.GamePanel;
import com.main.Sounds;

public class DemonPrince extends Characters{
    private int attChoice;

    public DemonPrince (GamePanel gp){
        super("images/demonPrince.png","images/demonPrince2.png", 120, 15, 30, 30, gp);
    }

    @Override
    public void charAttacks(List<Characters> c, Characters p) {
        attChoice = rand.nextInt(4);

        switch (attChoice) {
            case 0:
                animHelper(p, Animations.SLASH);
                animHelper(this, Animations.SLASH);
                gp.playSFX(Sounds.SLASH);
                directAttack(20, 20);
                p.directAttack(0, 60);
                break;
        
            case 1:
                gp.playSFX(Sounds.HEAL);
                animHelper(this, Animations.HEALS);
                heal(10);
                break; 

            case 2:
                animHelper(this, Animations.MAGICARMOR);
                for (Characters characters : c) {
                    gp.playSFX(Sounds.SPARK);
                    animHelper(characters, Animations.MAGIC);
                    characters.directAttack(10, 0);
                }
                magArmorUp(35);
                break;

            case 3:
                if (health < 100){
                    animHelper(this, Animations.HEALS);
                    for (Characters characters : c) {
                        gp.playSFX(Sounds.SPARK);
                        animHelper(characters, Animations.EXPLOSIONS2);
                        characters.directAttack(10, 0);
                    }
                    heal(30);
                }else{
                    animHelper(this, Animations.PHYSICALARMOR);
                    for (Characters characters : c) {
                        gp.playSFX(Sounds.SLASH);
                        animHelper(characters, Animations.SLASH);
                        characters.directAttack(0, 10);
                    }
                    physArmorUp(30);
                }

            default:
                break;
        }
    }

    @Override
    public int offsetX() {
        return x + 78;
    }
}
