package com.main;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Animations {

    private BufferedImage spriteSheet;

    List<BufferedImage[]> animsList;

    private final int   frameWidth = 150,
                        frameHeight = 150;
    public static final int SLASH         = 0,
                            EXPLOSIONS1   = 1,
                            EXPLOSIONS2   = 2,
                            MAGIC         = 3,
                            HEALS         = 4,
                            PHYSICALARMOR = 5,
                            MAGICARMOR    = 6;

    private int counterFrame = 0, x = 0, y = 0, currentAnimType;
    private boolean isAnimating = false;

    public Animations(){
        animsList = new ArrayList<>();

        animsList.add(new BufferedImage[17]); // Slash
        animsList.add(new BufferedImage[65]); // Explosions1
        animsList.add(new BufferedImage[33]); // Explosions2
        animsList.add(new BufferedImage[37]); // Magic
        animsList.add(new BufferedImage[35]); // Heals
        animsList.add(new BufferedImage[35]); // PhysArmor
        animsList.add(new BufferedImage[35]); // MagArmor

        splitter("images/effectSlash.png", animsList.get(SLASH));
        splitter("images/effectExplode1.png", animsList.get(EXPLOSIONS1));
        splitter("images/effectExplode2.png", animsList.get(EXPLOSIONS2));
        splitter("images/effectMagic.png", animsList.get(MAGIC));
        splitter("images/effectHeal.png", animsList.get(HEALS));
        splitter("images/effectPhysArmor.png", animsList.get(PHYSICALARMOR));
        splitter("images/effectMagArmor.png", animsList.get(MAGICARMOR));
    }

    public void splitter (String path, BufferedImage[] anims){

        try {
            spriteSheet = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        int columns = spriteSheet.getWidth() / frameWidth;
        int rows = spriteSheet.getHeight() / frameHeight;
        // System.out.println(path);
        int index = 0;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                int x = col * frameWidth;
                int y = row * frameHeight;
                anims[index] = spriteSheet.getSubimage(x, y, frameWidth, frameHeight);
                index++;
            }
        }
    }

    public void setAnimationQueue(int[] info){
        this.x = info[0];
        this.y = info[1];
        this.currentAnimType = info[2];
        this.isAnimating = true;
    }

    public boolean draw(Graphics2D g) {
        g.drawImage(animsList.get(currentAnimType)[counterFrame], x, y, null);
        if (counterFrame == animsList.get(currentAnimType).length - 1) {
            counterFrame = 0;
            this.isAnimating = false;
            return true;
        } else {
            counterFrame++;
            return false;
        }
    }

    public boolean isAnimating (){
        return isAnimating;
    }
    
}
