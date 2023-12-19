package com.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class StageStartPanel extends JPanel {

    private Image backg;
    GamePanel gp;

    public StageStartPanel (GamePanel gp){
        setLayout(null);
        this.gp = gp;
        backg = new ImageIcon("images/screenBetweenStages.png").getImage();
        Color yellows = new Color(0xffdb57);
        Border border = BorderFactory.createLineBorder(new Color(0x704e2e), 3);
        Font styles = new Font("Helvetica", Font.BOLD, 15);
        JButton proceed = new JButton("Proceed");

        proceed.setBounds(128 + 437, 400, 150, 50);
        proceed.addActionListener(this::proceeeeeed);
        proceed.setBorder(border);
        proceed.setBackground(yellows);
        proceed.setFont(styles);

        add(proceed);
        repaint();
    }

    public void proceeeeeed(ActionEvent e){
        repaint();
        MainPanel parent = (MainPanel) getParent();
        parent.showPanel("GamePanel");
        gp.startPreparation();
    }

    @Override
    public void paintComponent(Graphics g){
        g.drawImage(backg, 0, 0, null);
        g.setColor(Color.YELLOW);
        g.setFont(new Font("Helvetica", Font.PLAIN, 120));
        g.drawString(String.valueOf(gp.getScale() + 1), 850, 300);
    }
}
