package com.cards;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.List;
import java.util.Random;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import com.characters.*;
import com.main.GamePanel;

public abstract class Card extends Rectangle{
	private int centerX = 180/2,
				centerY = 280/2,
				goalX = 100,
				goalY = 535,
				ogX,
				ogY,
                energyCost;
	protected int physAttVal, magAttVal, cardType;
	private Image img;
	GamePanel gp;
	private boolean picked;
	protected Random rand = new Random();

	public static final int TARGET = 0,
							SPELLS = 1;
	
	public Card(String path, int pAV, int mAV, int cType, int ec, GamePanel gps) {
		super(100,535,180,280);
		this.gp = gps;
		this.physAttVal = pAV;
		this.magAttVal = mAV;
		this.cardType = cType;
        this.energyCost = ec;
		this.picked = false;
		rand = new Random();
		img = new ImageIcon(path).getImage();		
	}
	
	public void setCenter(int newX, int newY) {
		this.x = newX - centerX;
		this.y = newY - centerY;
		this.goalX = newX - centerX;
		this.goalY = newY - centerY;
	}
	
	public void setInitialHand () {
		this.goalX = ogX;
		this.goalY = ogY;
	}

	public void setInitialHandValue(int nX, int nY){
		this.ogX = nX;
		this.ogY = nY;
	}
	
	public void setMouseNewPoint(int newX, int newY) {
		this.x -= newX;
		this.y -= newY;
		this.goalX -= newX;
		this.goalY -= newY;
	}

	public void setPoint(int nX, int nY){
		this.x = nX;
		this.y = nY;
	}

	public void setPoint(int nX, int nY, int oX, int oY){
		this.x = nX;
		this.y = nY;
		this.goalX = oX;
		this.goalY = oY;
	}
	
	public void setGoalPos(int gX, int gY){
		this.goalX = gX;
		this.goalY = gY;
	}

	public void draw (Graphics2D g) {
		if(x != goalX){
			this.x += (goalX - x) / 5;
			this.y += (goalY - y) / 5;

			if (goalX + 5 > x && x > goalX -5){
				this.x = goalX;
				this.y = goalY;
			}
		}
		g.drawImage(img, x, y, 180, 280, null);
	}
	
	public void draw (Graphics g){
		if (picked){
			g.drawImage(img, 1020 , 125 , null);
		}else{
			g.drawImage(img, x, y, null);
		}
	}

	// Setter methods
	public void setPhysAttVal(int physAttVal) {
		this.physAttVal = physAttVal;
	}

	public void setMagAttVal(int magAttVal) {
		this.magAttVal = magAttVal;
	}

	public void setCardType(int cardType) {
		this.cardType = cardType;
	}

	// Getter methods
	public int getPhysAttVal() {
		return physAttVal;
	}

	public int getMagAttVal() {
		return magAttVal;
	}

	public int getCardType() {
		return cardType;
	}

    public int getEnergyCost(){
        return energyCost;
    }

    public void attackHelper(Characters c){
        c.directAttack(magAttVal, physAttVal);
    }

    public void attackHelper(Characters c, int physatt, int magatt){
        c.directAttack(magatt, physatt);
    }

	public void useEnergy(){
		gp.energyDown(energyCost);
	}

	public void animHelper(Characters c, int type){
		gp.queueAnimation(new int[]{c.offsetX(), c.offsetY(), type});
	}

	public void setPicked(boolean state){
		this.picked = state;
	}

	public boolean isPicked(){
		return picked;
	}

    abstract public void cardEffect(List<Characters> e, Characters t, Characters p);

}

