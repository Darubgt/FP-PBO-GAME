package com.main;

import javax.swing.JPanel;
import java.awt.CardLayout;

public class MainPanel extends JPanel{
    private CardLayout cardLayout = new CardLayout();
    private GamePanel gp = new GamePanel();
    private DeckBuildPanel dbp = new DeckBuildPanel(gp);

    public MainPanel (){
        setLayout(cardLayout);
        add(new MainMenuPanel(gp, dbp), "MainMenu");
        add(dbp, "DeckBuild");
        add(gp, "GamePanel");
        add(new StageStartPanel(gp), "StageStart");
    }

    public void showPanel(String name){
        cardLayout.show(this, name);
    }
}