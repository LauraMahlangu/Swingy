package swingy.controllers;


import lombok.Getter;
import lombok.Setter;
import swingy.factories.EnemyFactory;
import swingy.factories.PlayerFactory;
import swingy.models.*;
import swingy.views.View;
import swingy.views.consoleView.Console;
import swingy.views.guiView.Gui;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

@Getter
@Setter
public class GameController
{
    private GameState gameState;
    public  ArrayList<String> errors;
    private boolean consoleView; //true if console, false if gui
    private View view;
    private Stages stage;

    public GameController(boolean consoleView)
    {
        errors = new ArrayList<>();
        this.consoleView = consoleView;
        if (consoleView)
            view = new Console(this);
        //else
        //    view = new Gui();
        stage = Stages.WELCOME;
    }

    public void processInput(int value)
    {
        switch (stage)
        {
            case WELCOME:
                ArrayList<Player> enemies;
                if (value == 1)
                {
                    stage = Stages.CREATEPLAYER;
                    this.renderStage();
                    initGameState(true);
                }
                else
                {
                    stage = Stages.SELECTPLAYER;
                    this.renderStage();
                    initGameState(false);
                }
                break;

            case SELECTPLAYER:
                if (value == 1)
                {
                    stage = Stages.DISPLAYMAP;
                    this.renderStage();
                }
                break;

            case CREATEPLAYER:
                if (value == 1)
                {
                    stage = Stages.DISPLAYMAP;
                    this.renderStage();
                }
                break;

            case DISPLAYMAP:
                if (value == 1)
                    this.movePlayer(Directions.NORTH);
                else if (value == 2)
                    this.movePlayer(Directions.WEST);
                else if (value == 3)
                    this.movePlayer(Directions.SOUTH);
                else
                    this.movePlayer(Directions.EAST);
                this.updateMap();
                renderStage();
                break;

            case DISPLAYFIGHTORRUN:
                break;

            case DISPLAYFORCEDFIGHT:
                break;

            case GAMEOVER:
                break;

            case SAVEPLAYER:
                break;

            case DISPLAYERRORS:
                stage = Stages.WELCOME;
                this.renderStage();
                break;
        }
    }

    private void initGameState(boolean isNewPlayer)
    {
        Player hero;
        if (isNewPlayer == true)
        {
            ArrayList<String> playerDetails; // this will be an arraylist that contains the name and type of the plaayer that the user supplied in the console e.g {"Laura",  "SuperGirl"}
            playerDetails = view.getPlayerDetailsFromUser();
            hero = PlayerFactory.createPlayer(playerDetails, this);
        }
        else
        {
            ArrayList<Player> savedPlayers = DataController.getAllPlayers();
            hero = view.getPlayerFromSavedPlayers(savedPlayers);
            if (PlayerFactory.isValid(hero, this) == false)// checks if chosen saved players is valid.
                hero = null;
        }
        if (hero == null)
        {
            stage = Stages.DISPLAYERRORS;
            this.renderStage();
        }
        else
        {
            ArrayList<Enemy> enemies = generateEnemies(hero);
            gameState = new GameState(hero, enemies);
            updateMap();
        }
        processInput(1);// this is after creation or successfully select  player, we automatically send 1 to proceed to display map.
    }

    public void renderStage()
    {
        view.displayStage(stage);
    }

    private ArrayList<Enemy> generateEnemies(Player hero)
    {
        ArrayList<Enemy> allEnemies = new ArrayList<>();
        Random randomGenerator = new Random();
        int numberOfEnemies = randomGenerator.nextInt(hero.getMapSize());
        int x, y;
        for (int i  = 0; i < numberOfEnemies; i++)
        {
            do
            {
                x = randomGenerator.nextInt(hero.getMapSize());
                y = randomGenerator.nextInt(hero.getMapSize());
            }
            while (x == hero.getX() && y == hero.getY());
            Enemy enemy = EnemyFactory.createEnemy(x, y);
            allEnemies.add(enemy);
        }
        return (allEnemies);
    }


    private void updateMap()
    {
        for (int row = 0; row < gameState.getPlayer().getMapSize(); row++)
        {
            for (int col = 0; col < gameState.getPlayer().getMapSize(); col++)
            {
                gameState.getMap()[row][col] = '.';
            }
        }
        gameState.getMap()[gameState.getPlayer().getY()][gameState.getPlayer().getX()] = 'H';
        for (Enemy enemy : gameState.getEnemies())
        {
            if (enemy.isAlive() == true)
                gameState.getMap()[enemy.getY()][enemy.getX()] = 'E';
        }
    }

   // private boolean isArtifactWon(){}
   // private String createBattleReport(){} todo will be done in report factory
   // private boolean isGameOver(){}
    private void fight(){}
    private void fightOrRUn(){}
    private void movePlayer(Directions direction)
    {
        int x = gameState.getPlayer().getX();
        int y = gameState.getPlayer().getY();

        Point point = new Point(x, y);
        gameState.setPreviousPosition(point);

        if (direction == Directions.NORTH)
            gameState.getPlayer().setY(y - 1);
        else if (direction == Directions.WEST)
            gameState.getPlayer().setX(x - 1);
        else if (direction == Directions.SOUTH)
            gameState.getPlayer().setY(y + 1);
        else
            gameState.getPlayer().setX(x + 1);
    }

}
