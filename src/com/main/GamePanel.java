package com.main;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;

import com.characters.*;
import com.cards.*;


public class GamePanel extends JPanel implements ActionListener {
	Image backg, gameOverScreen, stageClearScreen;
	int cardHandPos, handPos, cardCount, scale = 0, frameCounter = 0, enemyTurnHelp = 0, maxEnergy, currentEnergy;
	Card attack, currentCard = null;
	Characters mainPlayer;
	List<Card> hand, deck, used, allCards;
	List<Characters> enemies, allEnemy, deadEnemies;
	List <int[]> animsInfoQueue;
	List <Image> backgrounds;
	JButton drawz, endTurn, test1, test2, gameOverButton, stageClearButton;
	Point deckPoint;
	Boolean drawing = false, isRunning = true, isEnemyTurn = false;
	Random rand;
	Color dBrown, bluer, lBrown;
	Font sanser;
	Rectangle upperBound;
	Sounds sound;
	Animations animator;
	Thread gameThread;

	public GamePanel() {
		setLayout(null);

		initializeEverything();

		Font styles = new Font("Helvetica", Font.BOLD, 25);
        Border border = BorderFactory.createLineBorder(new Color(0x704e2e), 5);
        Color yellows = new Color(0xffdb57);
		
		endTurn.setBackground(Color.CYAN);
		endTurn.setBounds(230, 10, 120, 55);
		endTurn.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
		endTurn.setFocusable(false);
		endTurn.addActionListener(this);

		test1.setBounds(200, 0, 80, 50);
		test1.addActionListener(this);

		test2.setBounds(300, 0, 80, 50);
		test2.addActionListener(this);

		stageClearButton.setBounds(128 + 437, 400, 150, 50);
		stageClearButton.setFocusable(false);
		stageClearButton.addActionListener(this);
		stageClearButton.setFont(styles);
		stageClearButton.setBorder(border);
		stageClearButton.setBackground(yellows);
		
		gameOverButton.setBounds(128 + 437, 440, 150, 50);
		gameOverButton.setFocusable(false);
		gameOverButton.addActionListener(this);
		gameOverButton.setFont(styles);
		gameOverButton.setBorder(border);
		gameOverButton.setBackground(yellows);

		add(stageClearButton);
		add(gameOverButton);
		add(endTurn);
		// add(test1);
		// add(test2);

		MouseHandler handler = new MouseHandler();
		addMouseListener(handler);
		addMouseMotionListener(handler);

		// playMusic();
		gameThread.start();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D) g;
		g2D.drawImage(backg, 0, 0, null);// Draw Background

		mainPlayer.draw(g2D); // draw main player
		for (Characters c : enemies) { //draw all enemies
			c.draw(g2D);
		}

		// Decks and Used Pool
		g2D.setColor(Color.ORANGE);
		g2D.fillRect(1180, 450, 40, 40);
		g2D.setColor(Color.LIGHT_GRAY);
		g2D.fillRect(1120, 450, 40, 40);
		g2D.setColor(Color.WHITE);
		g2D.drawString(String.valueOf(deck.size()), 1192, 470);
		g2D.drawString(String.valueOf(used.size()), 1132, 470);

		//Animation rendering
		if (animator.isAnimating()){
			if(animator.draw(g2D)){
				animsInfoQueue.remove(0);
			}
		}

		//Card Rendering
		for (Card card : hand) {
			card.draw(g2D);
		}

		//Energy GUI
		g2D.setColor(dBrown);
		g2D.fillRect(0, 0, 90, 90);
		g2D.fillRect(0, 0, 370, 75);
		g2D.setColor(bluer);
		g2D.fillRect(10, 10, 70, 70);
		g2D.setColor(lBrown);
		g2D.fillRect(90, 10, 130, 55);
		g2D.setColor(Color.WHITE);
		g2D.setFont(sanser);
		g2D.drawString(String.valueOf(currentEnergy), 32, 60);
		g2D.drawString("Energy", 100, 50);

		//Player Death aka Game Over detection
		if (mainPlayer.isDead()) {
			playSFX(Sounds.PDEATH);
			gameOver();
			g2D.drawImage(gameOverScreen, 128, 55, null);
			g2D.setFont(new Font("Helvetica", Font.BOLD, 50));
			g2D.setColor(Color.YELLOW);
			g2D.drawString(String.valueOf(scale + 1), 650, 400);
		}
		//Enemies Death aka Win detection
		else if (enemies.isEmpty()) {
			stageWin();
			g2D.drawImage(stageClearScreen, 128, 55, null);
		}
	}

	public void renderHelp() {
		//Enemy attacks
		if (isEnemyTurn && frameCounter % 120 == 0){
			if (enemyTurnHelp == enemies.size()) {
				isEnemyTurn = false;
				enemyTurnHelp = 0;
				endTurn.setEnabled(true);
				endTurn.setBackground(Color.CYAN);
				endTurn.setText("END TURN");
				frameCounter = 0;
				if (maxEnergy < 15)maxEnergy++;
				currentEnergy = maxEnergy;
				drawCards(1);
				deathCheck();
			}else{
				enemies.get(enemyTurnHelp).setAttacking(false);
				enemies.get(enemyTurnHelp).charAttacks(enemies, mainPlayer);
				if (enemyTurnHelp + 1 != enemies.size()) enemies.get(enemyTurnHelp+1).setAttacking(true);
				enemyTurnHelp++;
				System.out.println("enemy attacking");
			}
		}

		//Animation queuing
		if (!animator.isAnimating() && !animsInfoQueue.isEmpty()){
			animator.setAnimationQueue(animsInfoQueue.get(0));
		}
		//To count Dem Frames
		frameCounter++;
	}

	private class MouseHandler extends MouseAdapter {
		private int startX, startY;

		public void mousePressed(MouseEvent e) {
			startX = e.getX();
			startY = e.getY();
			if (!isEnemyTurn){
				for (Card card : hand) {
					if (card.contains(e.getX(), e.getY())) {
						currentCard = card;
						currentCard.setCenter(e.getX(), e.getY());
					}
				}
			}
		}

		public void mouseDragged(MouseEvent e) {
			if (currentCard != null) {
				currentCard.setMouseNewPoint(startX - e.getX(), startY - e.getY());
				startX = e.getX();
				startY = e.getY();

			}
		}

		public void mouseReleased(MouseEvent e) {
			if(currentCard != null){
				if (currentCard.getEnergyCost() > currentEnergy){
					currentCard.setInitialHand();
					currentCard = null;
					System.out.println("Not Enough Energy");
					return;
				}
				if(currentCard.getCardType() == Card.TARGET){
					for (Characters c : enemies) {
						if (c.contains(e.getPoint())) {
							System.out.println("Target Card Played!");
							used.add(currentCard);
							hand.remove(currentCard);
							currentCard.cardEffect(enemies , c , mainPlayer);
							System.out.println("Remaining health " + c.getHealth());
							handCardDistribution();
						} else if (!c.contains(e.getPoint())) {
							currentCard.setInitialHand();
						}
					}
				}
				
				if(currentCard.getCardType() == Card.SPELLS){
					if( upperBound.contains(e.getPoint())){
						System.out.println("Spells Card Played!");
						used.add(currentCard);
						hand.remove(currentCard);
						currentCard.cardEffect(enemies , null , mainPlayer);
						handCardDistribution();
					}else if (!upperBound.contains(e.getPoint())) {
						currentCard.setInitialHand();
					}
				}
			}
			deathCheck();
			currentCard = null;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == drawz) {
			drawCards(1);
		}
		if(e.getSource() == endTurn){
			endTurn.setEnabled(false);
			endTurn.setBackground(Color.GRAY);
			endTurn.setText("ENEMIES TURN");
			enemies.get(0).setAttacking(true);
			isEnemyTurn = true;
			frameCounter = 2;
			playSFX(Sounds.CLICK);
		}
		if(e.getSource() == test1){
			mainPlayer.setHealth(0);
		}
		if(e.getSource() == test2){
			enemies.clear();
		}
		if(e.getSource() == stageClearButton){
			MainPanel parent = (MainPanel) getParent();
			parent.showPanel("StageStart");
			stopMusic();
			playSFX(Sounds.CLICK);
		}
		if(e.getSource() == gameOverButton){
			System.out.println("insert quit logic");
			MainPanel parent = (MainPanel) getParent();
			parent.showPanel("MainMenu");
			stopMusic();
			playSFX(Sounds.CLICK);
			// System.exit(0);
		}
	}

	public void deathCheck(){
		for (Characters characters : enemies) {
			if(characters.isDead()) deadEnemies.add(characters);
		}
		if (!deadEnemies.isEmpty()) {
			enemies.removeAll(deadEnemies);
			playSFX(Sounds.GROAN);
			deadEnemies.clear();
		}
	}

	public void startPreparation(){
		isRunning = true;
		scale++;
		int counter = 0;
		maxEnergy = 10;
		currentEnergy = maxEnergy;
		backg = backgrounds.get(rand.nextInt(5));

		stageClearButton.setEnabled(false);
		stageClearButton.setVisible(false);
		gameOverButton.setEnabled(false);
		gameOverButton.setVisible(false);
		endTurn.setEnabled(true);

		drawCards(3);
		mainPlayer.setInitialStart(scale);

		//initiate Enemies onto the Encounter
		for(int h = 0;h<3;h++){
			int r = rand.nextInt(allEnemy.size());
			if(enemies.contains(allEnemy.get(r))) continue;
			allEnemy.get(r).setX(500 + 220 * counter);
			allEnemy.get(r).setInitialStart(scale);
			enemies.add(allEnemy.get(r));
			counter++;
		}
	}

	public void encounterEndCleaning(){
		if (!enemies.isEmpty()) enemies.clear();
		endTurn.setEnabled(false);
		deck.addAll(used);
		deck.addAll(hand);
		hand.clear();
		used.clear();
		isRunning = false;
	}

	public void gameOver(){
		encounterEndCleaning();
		gameOverButton.setVisible(true);
		gameOverButton.setEnabled(true);
		deck.clear();
		scale = 0;
		for (Card card : allCards) {
			card.setPicked(false);
		}
	}

	public void stageWin(){
		encounterEndCleaning();
		stageClearButton.setVisible(true);
		stageClearButton.setEnabled(true);
	}

	public void drawCards (int amount){
		
		if (deck.isEmpty()){
			deck.addAll(used);
			used.clear();
		}

		for(;amount > 0; amount--){
			playSFX(Sounds.DECKFLICK);
			int num = rand.nextInt(deck.size());
			deck.get(num).setPoint(1180, 470);
			if (hand.size() == 6){
				used.add(deck.get(num));
				deck.remove(num);
				playSFX(Sounds.COMBUST);
				continue;
			}
			hand.add(deck.get(num));
			deck.remove(num);
		}
		handCardDistribution();
		
	}

	public void handCardDistribution (){
		cardCount = 0;
		for (Card card : hand) {
			card.setGoalPos(50 + (200 * cardCount), 535);
			card.setInitialHandValue(50 + (200 * cardCount), 535);
			cardCount++;
		}
	}

	public void energyUp (int amount){
		this.currentEnergy += amount;
	}

	public void energyDown (int amount){
		this.currentEnergy -= amount;
	}

    public void menu (ActionEvent e){
        MainPanel parent = (MainPanel) getParent();
        parent.showPanel("MainMenu");
    }

	public void playMusic(){
		System.out.println("Lah kok jalan");
		sound.setFile(Sounds.BGMUSIC);
		sound.play();
		sound.loop();
	}
	
	public void stopMusic(){
		sound.stop();
	}

	public void playSFX(int i){
		sound.setFile(i);
		sound.play();
	}

	public void queueAnimation(int[] infoh){
		animsInfoQueue.add(infoh);
	}

	public int getScale (){
		return scale;
	}

	public List<Card> getAllCards (){
		return allCards;
	}

	public List<Card> getDeck(){
		return deck;
	}

	public void initializeEverything(){

		backgrounds = new ArrayList<>();

		//Background Assembly
		backgrounds.add(new ImageIcon("images/bgTekken.png").getImage());
		backgrounds.add(new ImageIcon("images/bgBeach.png").getImage());
		backgrounds.add(new ImageIcon("images/bgColumbus.png").getImage());
		backgrounds.add(new ImageIcon("images/bgHeaven.png").getImage());
		backgrounds.add(new ImageIcon("images/bgDragons.png").getImage());

		gameOverScreen = new ImageIcon("images/screenGameOver.png").getImage();
		stageClearScreen = new ImageIcon("images/screenStageClear.png").getImage();
		mainPlayer = new Player(this);
		mainPlayer.setX(90);
		sound = new Sounds();
		animator = new Animations();

		upperBound = new Rectangle(0, 0, 1280, 480);

		drawz = new JButton("DRAW");
		endTurn = new JButton("END TURN");
		stageClearButton = new JButton("Next Stage");
		gameOverButton = new JButton("Return");
		test1 = new JButton("kill");
		test2 = new JButton("enrmy");
		
		deckPoint = new Point(1200, 470);
		rand = new Random();
		dBrown = new Color(0x301401);
		bluer = new Color(0x5ba5c5);
		lBrown = new Color(0xffcca9);
		sanser = new Font("Helvetica", Font.PLAIN, 30);

		//List initiation
		hand = new ArrayList<>();
		deck = new ArrayList<>();
		used = new ArrayList<>();
		enemies = new ArrayList<>();
		allEnemy = new ArrayList<>();
		deadEnemies = new ArrayList<>();
		animsInfoQueue = new ArrayList<>();
		allCards = new ArrayList<>();

		//Enemy Instanciation
		allEnemy.add(new DarkWarrior(this));
		allEnemy.add(new MagicWyvern(this));
		allEnemy.add(new DemonPrince(this));
		allEnemy.add(new SengokuWarrior(this));
		allEnemy.add(new TreeMonster(this));

		allEnemy.add(new DarkWarrior(this));
		allEnemy.add(new MagicWyvern(this));
		allEnemy.add(new DemonPrince(this));
		allEnemy.add(new SengokuWarrior(this));
		allEnemy.add(new TreeMonster(this));

		//card instanciation
		allCards.add(new AbsoluteLight(this));
		allCards.add(new AbsoluteLight(this));
		allCards.add(new AngelBlessing(this));
		allCards.add(new AngelBlessing(this));
		allCards.add(new ArcaneMissiles(this));
		allCards.add(new ArcaneMissiles(this));
		allCards.add(new AxeRain(this));
		allCards.add(new AxeRain(this));
		allCards.add(new BlueCore(this));
		allCards.add(new BlueCore(this));
		allCards.add(new BlueWind(this));
		allCards.add(new BlueWind(this));
		allCards.add(new Brace(this));
		allCards.add(new Brace(this));
		allCards.add(new CrystalGrenade(this));
		allCards.add(new CrystalGrenade(this));
		allCards.add(new Decimate(this));
		allCards.add(new Decimate(this));
		allCards.add(new DecisiveStrike(this));
		allCards.add(new DecisiveStrike(this));
		allCards.add(new DimensionAnalyst(this));
		allCards.add(new DimensionAnalyst(this));
		allCards.add(new GasInflation(this));
		allCards.add(new GasInflation(this));
		allCards.add(new Innervation(this));
		allCards.add(new Innervation(this));
		allCards.add(new InvokePortal(this));
		allCards.add(new InvokePortal(this));
		allCards.add(new LastBreath(this));
		allCards.add(new LastBreath(this));
		allCards.add(new LifeBeam(this));
		allCards.add(new LifeBeam(this));
		allCards.add(new MagicBolt(this));
		allCards.add(new MagicBolt(this));
		allCards.add(new Preparation(this));
		allCards.add(new Preparation(this));
		allCards.add(new ReduceReuse(this));
		allCards.add(new ReduceReuse(this));
		allCards.add(new Rest(this));
		allCards.add(new Rest(this));
		allCards.add(new RustedDagger(this));
		allCards.add(new RustedDagger(this));
		allCards.add(new ShieldBash(this));
		allCards.add(new ShieldBash(this));
		allCards.add(new Smite(this));
		allCards.add(new Smite(this));
		allCards.add(new SoulSucking(this));
		allCards.add(new SoulSucking(this));
		allCards.add(new Stacking(this));
		allCards.add(new Stacking(this));
		allCards.add(new SwordOfMartin(this));
		allCards.add(new SwordOfMartin(this));
		allCards.add(new TacticalRetreat(this));
		allCards.add(new TacticalRetreat(this));
		allCards.add(new TheEndIsComing(this));
		allCards.add(new TheEndIsComing(this));
		allCards.add(new ToeToToe(this));
		allCards.add(new ToeToToe(this));
		allCards.add(new UnstableEnergy(this));
		allCards.add(new UnstableEnergy(this));
		allCards.add(new WhipAndTake(this));
		allCards.add(new WhipAndTake(this));
		allCards.add(new WhirlwindSlash(this));
		allCards.add(new WhirlwindSlash(this));


		gameThread = new Thread() {
			public void run() {
				while (true) {
					if(isRunning){
						renderHelp();
						repaint();
					}
					try {
						Thread.sleep(1000 / 60);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};


	}
}
