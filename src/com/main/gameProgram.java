package com.main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class gameProgram {
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame("Cardist Conquest");
				try {
					BufferedImage iconImage = ImageIO.read(new File("images/iconsz.jpeg"));
					frame.setIconImage(iconImage);
				} catch (IOException e) {
					e.printStackTrace();
				}

				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				// frame.setSize(1280, 750);
				frame.setSize(1290, 750);
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
				frame.setResizable(false);
				// frame.add(new GamePanel());
				frame.add(new MainPanel());
			}
		});
	}
}

/*
 * Todo Lists :::
 * -Music and sound effect BIG DICK PROGRESS
 * -animations GIGA BIG DICK PROGRESS
 * -additional stage background DONE
 * - deck building panel DONE
 * - progression panel BIG DICK PROGRESS
 * - decks and used graphics prolly add sqares honestly
 * main menu panel (Everything) DONE
 * play DONE
 * background DONE
 * quit DONE
 * optionally a scoreboard HOW ABOUT NO
 */