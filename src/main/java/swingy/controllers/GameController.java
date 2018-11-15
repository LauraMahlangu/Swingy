package swingy.controllers;


import swingy.factories.PlayerFactory;
import swingy.models.*;
import swingy.views.View;
import swingy.views.consoleView.Console;
import swingy.views.guiView.Gui;

import java.awt.*;
import java.util.ArrayList;

public class GameController
{
    private GameState gameState;
    public static ArrayList<String> errors = new ArrayList<>();
    private boolean consoleView; //true if console, false if gui
    private View view;
    private Stages stage;

    public GameController(boolean consoleView)
    {
        this.consoleView = consoleView;
        if (consoleView)
            view = new Console();
        else
            view = new Gui();
        stage = Stages.WELCOME;

    }

    public void processInput(int value)
    {
        switch (stage)
        {
            case WELCOME:
                Player hero = null;
                ArrayList<Player> enemies;
                if (value == 1)
                {
                    stage = Stages.CREATEPLAYER;
                    this.renderStage();
                    hero = initPlayer(true);
                }
                else
                {
                    stage = Stages.SELECTPLAYER;
                    this.renderStage();
                    initGameState(false);
                }
                break;

            case SELECTPLAYER:
                break;

            case CREATEPLAYER:
                break;

            case DISPLAYMAP:
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
                stage = Stages.CREATEPLAYER;
                this.renderStage();
                initGameState(true);
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
            hero = PlayerFactory.createPlayer(playerDetails);
        }
        else
        {
            ArrayList<Player> savedPlayers = DataController.getAllPlayers();
            hero = view.getPlayerFromSavedPlayers(savedPlayers);
            if (PlayerFactory.isValid(hero) == false)// checks if chosen saved players is valid.
                hero = null;
        }
        if (hero == null)
        {
            stage = Stages.DISPLAYERRORS;
            this.renderStage();
        }
        else
        {
            ArrayList<Player> enemies = generateEnemies();
            gameState = new GameState(hero, enemies);
        }
    }

    public void renderStage()
    {
        view.displayStage(stage);
    }

    private void fight(){}
    private void fightOrRUn(){}
    private void movePlayer(Directions direction){}
    private ArrayList<Player> generateEnemies()
    {
        //todo
    }


    private void updateMap(){}
    private boolean isArtifactWon(){}
   // private String createBattleReport(){} todo will be done in report factory
    private boolean isGameOver(){}
    private Player retrieveSavedPlayer(){}
    private void savePlayer(){}
}
