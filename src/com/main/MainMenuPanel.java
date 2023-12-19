package com.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

public class MainMenuPanel extends JPanel{
    private GamePanel gps;
    private DeckBuildPanel dbp;

    public MainMenuPanel(GamePanel gp, DeckBuildPanel dbp){
        setLayout(null);
        this.gps = gp;
        this.dbp = dbp;

        JButton play = new JButton("PLAY");
        JButton quit = new JButton("QUIT");

        Font styles = new Font("Helvetica", Font.BOLD, 25);

        Border border = BorderFactory.createLineBorder(new Color(0x704e2e), 5);
        Color yellows = new Color(0xffdb57);

        quit.setFocusable(false);
        quit.setBounds(170, 400, 200, 80);
        quit.addActionListener(this::quits);
        quit.setBorder(border);
        quit.setBackground(yellows);
        quit.setFont(styles);

        play.setFocusable(false);
        play.setBounds(170, 300, 200, 80);
        play.addActionListener(this::play);
        play.setBorder(border);
        play.setBackground(yellows);
        play.setFont(styles);

        add(quit);
        add(play);
        repaint();
    }

    @Override
    public void paintComponent(Graphics g){
        Image bg = new ImageIcon("images/bgMainMenu.png").getImage();
        g.drawImage(bg, 0, 0, null);
    }

    public void play (ActionEvent e){
        MainPanel parent = (MainPanel) getParent();
        parent.showPanel("DeckBuild");
        gps.playSFX(Sounds.CLICK);
        dbp.start();
    }

    public void quits(ActionEvent e){
        MainPanel parent = (MainPanel) getParent();
        gps.playSFX(Sounds.CLICK);
        JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(parent);
        parentFrame.dispose();
        System.exit(0);
    }
}
