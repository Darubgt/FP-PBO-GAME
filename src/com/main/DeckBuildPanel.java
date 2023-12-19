package com.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import com.cards.Card;

public class DeckBuildPanel extends JPanel implements ActionListener{
    GamePanel gp;
    private List<Card> decks, allCardss, displayCards;
    private JButton next, previous, startRun;
    private JLabel cardCount;
    private int page;
    private Image bg;
    private Rectangle deckPos;
    private Color yellows;

    public DeckBuildPanel (GamePanel gps){
        setLayout(null);
        this.gp = gps;
        this.decks = gp.getDeck();
        this.allCardss = gp.getAllCards();
        displayCards = new ArrayList<>();
        deckPos = new Rectangle(1020, 125, 180, 280);

        next = new JButton("NEXT PAGE");
        previous = new JButton("PREVIOUS PAGE");
        startRun = new JButton("START RUN");
        cardCount = new JLabel();
        bg = new ImageIcon("images/bgDeckBuild.png").getImage();

        yellows = new Color(0xffdb57);
        Border border = BorderFactory.createLineBorder(new Color(0x704e2e), 3);
        Font styles = new Font("Helvetica", Font.BOLD, 15);

        cardCount.setBorder(BorderFactory.createLineBorder(Color.CYAN, 2, true));
        cardCount.setOpaque(true);
        cardCount.setBackground(Color.BLACK);
        cardCount.setForeground(Color.WHITE);
        cardCount.setFont(styles);
        cardCount.setBounds(1050, 420, 120, 30);
        cardCount.setText("  Cards : 0 / 25");

        next.setBounds(1030, 460, 160, 50);
        next.addActionListener(this);
        next.setBorder(border);
        next.setBackground(yellows);
        next.setFont(styles);
        next.setFocusable(false);

        previous.setBounds(1030, 515, 160, 50);
        previous.addActionListener(this);
        previous.setBorder(border);
        previous.setBackground(Color.LIGHT_GRAY);
        previous.setFont(styles);
        previous.setFocusable(false);

        startRun.setBounds(1030, 610, 160, 50);
        startRun.addActionListener(this);
        startRun.setBorder(border);
        startRun.setBackground(yellows);
        startRun.setFont(styles);
        startRun.setFocusable(false);
        startRun.setEnabled(false);

        MouseHandler handler = new MouseHandler();
		addMouseListener(handler);
		addMouseMotionListener(handler);

        add(next);
        add(previous);
        add(startRun);
        add(cardCount);
    }

    @Override
    public void paintComponent (Graphics g){
        g.drawImage(bg, 0, 0, null);
        for (Card card : displayCards) {
            if(card.isPicked()) continue;
            card.draw(g);
        }

        if(!decks.isEmpty()){
            decks.get(decks.size() - 1).draw(g);
        }
        cardCount.setText("  Cards : "+ String.valueOf(decks.size()) +" / 25");

        if(decks.size() == 25){
            startRun.setEnabled(true);
            startRun.setBackground(yellows);
        }else{
            startRun.setEnabled(false);
            startRun.setBackground(Color.lightGray);
        }
    }

    public void start(){
        initializeCardPosition();
        previous.setEnabled(false);
        page = 0;
        pageAssembly();
        repaint();
    }

    public void initializeCardPosition (){
        int row = 0;
        int column = 0;
        int leftCornerX = 90;
        int leftCornerY = 110;

        for (Card card : allCardss) {
            card.setPoint(leftCornerX + 210 * row, leftCornerY + 285 * column);
            row++;
            if(row == 4){
                row = 0;
                column++;
                if(column == 2) column = 0;
            }
        }
    }

    public void pageAssembly (){
        for(int i = 8 * page; i < (8 * page) + 8; i++){
            if (i >= allCardss.size()) continue;
            displayCards.add(allCardss.get(i));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == next){
            previous.setEnabled(true);
            previous.setBackground(yellows);
            page++;
            displayCards.clear();
            // System.out.println(page + " " + page * 8);
            pageAssembly();
            if((page + 2) * 8 > allCardss.size()){
                next.setEnabled(false);
                next.setBackground(Color.LIGHT_GRAY);
            }
            gp.playSFX(Sounds.CLICK);
            repaint();
        }
        if (e.getSource() == previous){
            next.setEnabled(true);
            next.setBackground(yellows);
            page -= 1;
            displayCards.clear();
            pageAssembly();
            gp.playSFX(Sounds.CLICK);

            if (page == 0) {
                previous.setEnabled(false);
                previous.setBackground(Color.LIGHT_GRAY);
            }
            repaint();
        }
        if (e.getSource() == startRun){
            MainPanel parent = (MainPanel) getParent();
            parent.showPanel("StageStart");
            gp.playMusic();
            gp.playSFX(Sounds.CLICK);
        }
    }

    private class MouseHandler extends MouseAdapter{
        public void mousePressed(MouseEvent e){
            for (Card card : displayCards) {
                if (card.contains(e.getPoint()) && !card.isPicked() && decks.size() != 25){
                    card.setPicked(true);
                    decks.add(card);
                    gp.playSFX(Sounds.DECKFLICK);
                    break;
                }
            }

            if (!decks.isEmpty()){
                if(deckPos.contains(e.getPoint())){
                    decks.get(decks.size() - 1).setPicked(false);
                    decks.remove(decks.size()-1);
                    gp.playSFX(Sounds.DECKFLICK);
                }
            }
            repaint();
        }
    }

}
