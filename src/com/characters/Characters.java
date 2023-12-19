package com.characters;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;

import com.main.GamePanel;

public abstract class Characters extends Rectangle {
	protected int maxHealth, baseHealth ,health, magicArmor, physArmor, scaler, maxMA, maxPA, spriteCount = 0;
	Image img1, img2, healthIcon, pArmorIcon, mArmorIcon;
	Font h1, h2, a1, a2;
    Random rand;
	GamePanel gp;
	boolean isAttacking = false;
	private int[] triangleCoordsX, triangleCoordsY;

	public Characters(String path1, String path2, int hel, int sclaer, int ma, int pa, GamePanel gps) {
		super(0,178,240,300);
		this.health = hel;
		this.baseHealth = hel;
		this.scaler = sclaer;
		this.maxMA = ma;
		this.maxPA = pa;
		this.gp = gps;
		img1 = new ImageIcon(path1).getImage();
		img2 = new ImageIcon(path2).getImage();
        rand = new Random();
		healthIcon = Assets.HEALTHICON;
		pArmorIcon = Assets.PARMORICON;
		mArmorIcon = Assets.MARMORICON;

		h1 = new Font("Helvetica", Font.PLAIN, 22);
		h2 = new Font("Helvetica", 0,26);
		a1 = new Font("Helvetica", 0, 18);
		a2 = new Font("Helvetica", 0, 22);
	}
	
	public void setX(int x) {
	this.x = x;
	}

	public void setY(int y) {
	this.y = y;
	}

	public void setInitialStart(int scale){
		this.health     = baseHealth + scaler * scale;
        this.maxHealth  = health;
		this.magicArmor = maxMA + (scaler/2) * scale;
		this.physArmor  = maxPA + (scaler/2) * scale;
		triangleCoordsX = new int[]{x + 95 + 15,  x + 95 + 55, x + 95 + 35};
		triangleCoordsY = new int[]{y - 85 - 60, y - 85 - 60, y - 85 - 30};
	}

	/**
	 * Draw the Characters based on their x and y value,
	 * Also draw their health and armor value 
	 */
	public void draw(Graphics2D g) {

		if (isAttacking){
			g.setColor(Color.RED);
			g.fillPolygon(triangleCoordsX, triangleCoordsY, 3);
			// System.out.println(health);
		}

		g.drawImage(healthIcon,x + 95,  y - 85, null);
		g.setColor(Color.BLACK);
		g.setFont(h2);
		g.drawString(String.valueOf(health), x + 95 - 1 + ((70 - g.getFontMetrics().stringWidth(String.valueOf(health)))/2), (y - 85 + 60 + 1) - ((70 - g.getFontMetrics(h2).getHeight())/2));
		
		g.setColor(Color.WHITE);
		g.setFont(h1);
		g.drawString(String.valueOf(health), x + 95 + ((70 - g.getFontMetrics().stringWidth(String.valueOf(health)))/2), (y - 85 + 60) - ((70 - g.getFontMetrics(h1).getHeight())/2));

		if(magicArmor > 0){
			g.drawImage(mArmorIcon,x + 175, y - 75, null);
			g.setColor(Color.BLACK);
			g.setFont(a2);
			g.drawString(String.valueOf(magicArmor), x + 175 - 1 + ((50 - g.getFontMetrics().stringWidth(String.valueOf(magicArmor)))/2), y - 75 + 30 + 1);
			g.setColor(Color.WHITE);
			g.setFont(a1);
			g.drawString(String.valueOf(magicArmor), x + 175 + ((50 - g.getFontMetrics().stringWidth(String.valueOf(magicArmor)))/2), y - 75 + 30);
		}

		if(physArmor > 0){
			g.drawImage(pArmorIcon,x + 35,  y - 75, null);
			g.setColor(Color.BLACK);
			g.setFont(a2);
			g.drawString(String.valueOf(physArmor), x + 35 - 1 + ((50 - g.getFontMetrics().stringWidth(String.valueOf(physArmor)))/2), y - 75 + 30 + 1);
			g.setColor(Color.WHITE);
			g.setFont(a1);
			g.drawString(String.valueOf(physArmor), x + 35 + ((50 - g.getFontMetrics().stringWidth(String.valueOf(physArmor)))/2), y - 75 + 30);
		}

        if (spriteCount < 90){
            g.drawImage(img1, x, y, null);  
        }else if (spriteCount >= 90){
            g.drawImage(img2, x, y, null);
            if (spriteCount >= 180) spriteCount = 0;
        }
		g.setColor(null);
		g.setFont(null);

		// g.fillRect(x, y, 150, 150);
        spriteCount++;
	}

	// Setter methods
		public void setHealth(int health) {
            if (health > maxHealth) health = maxHealth;
            if(health < 0) health = 0;
			this.health = health;
		}

		public void setMagicArmor(int magicArmor) {
			if (magicArmor < 0 ) magicArmor = 0;
			this.magicArmor = magicArmor;
		}

		public void setPhysArmor(int physArmor) {
			if(physArmor < 0) physArmor = 0;
			this.physArmor = physArmor;
		}

		// Getter methods
		public int getHealth() {
			return health;
		}

		public int getMagicArmor() {
			return magicArmor;
		}

		public int getPhysArmor() {
			return physArmor;
		}

        public boolean isDead(){
            return health == 0;
        }

		public void setAttacking(boolean state){
			this.isAttacking = state;
		}

		public boolean isAttacking(){
			return isAttacking;
		}

        public void directAttack(int magAttVal, int physAttVal){
            if (magicArmor == 0) this.setHealth(health - magAttVal);
            if (physArmor == 0) this.setHealth(health - physAttVal);
    
            if(physArmor > 0){
                this.setPhysArmor(physArmor - physAttVal);
            }
            if(magicArmor > 0){
                this.setMagicArmor(magicArmor - magAttVal);
            }
            System.out.println("attacked directly " + health);
        }

        public void heal(int amount){
            this.setHealth(health += amount);
        }

        public void physArmorUp(int amount){
            this.setPhysArmor(physArmor += amount);
        }

        public void magArmorUp(int amount){
            this.setMagicArmor(magicArmor += amount);
        }

		public void animHelper(Characters c, int type){
			gp.queueAnimation(new int[]{c.offsetX(),c.offsetY(), type});
		}

		public int offsetY(){
			return y + 45;
		}

		abstract public int offsetX ();
		/**
		 * The method to make the enemy attack based on the perimeters.
		 * Every Characters have diffrent type of attack that why it is abstract
		 * @param c All the enemy
		 * @param p The main Player
		 * @author Daru Fadhilah Nahdi
		 */
        abstract public void charAttacks(List<Characters> c, Characters p);
}

