package swingy.controllers;


import swingy.models.*;
import swingy.views.View;

import java.awt.*;

public class GameController
{
    private GameState gameState;
    private boolean consoleView; //true if console, false if gui
    private View view;
    private Stages stage;

    public GameController(boolean consoleView)
    {
        this.consoleView = consoleView;
        stage = Stages.WELCOME;
    }

    private void initPlayer()
    {
        public Player(String name, String type, int level, int exp, int defencePoint, int attackPoint, int hitPoint, int x, int y)
    }

    private void initGameState()
    {

        public GameState(int mapSize, char[][] map, Player player, ArrayList<Player> enemies, Point previous_pos)
    }

    private void createView(){}
    public void playGame(){}
    private void fight(){}
    private void fightOrRUn(){}
    private void movePlayer(Directions direction){}
    private void generateEnemies(){}


    private void updateMap(){}
    private Artifact generateArtifact(){}
    private boolean isArtifactWon(){}
    private String createBattleReport(){}
    private boolean isGameOver(){}
    private Player retrieveSavedPlayer(){}
    private void savePlayer(){}
}
