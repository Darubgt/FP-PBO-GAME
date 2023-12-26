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
