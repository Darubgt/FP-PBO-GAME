package com.characters;

import java.util.List;

import com.main.GamePanel;

public class Player extends Characters{

    public Player (GamePanel gp){
        super("images/Warrior.png", "images/Warrior2.png", 200, 5, 20, 20, gp);
    }

    @Override
    public void charAttacks(List<Characters> c, Characters p) {
        System.out.println("Ngapain dipake woi");
        return;
    }

    @Override
    public int offsetX() {
        return x + 50;
    }
}
