package swingy.controllers;


import lombok.Getter;
import lombok.Setter;
import swingy.factories.EnemyFactory;
import swingy.factories.PlayerFactory;
import swingy.models.*;
import swingy.views.View;
import swingy.views.consoleView.Console;
import swingy.views.guiView.Gui;

import javax.validation.constraints.Null;
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
        else
            view = new Gui(this);
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
                    if (consoleView)
                        initConsoleGameState(true);
                }
                else
                {
                    stage = Stages.SELECTPLAYER;
                    this.renderStage();
                    if (consoleView)
                        initConsoleGameState(false);
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
                if (value == -1)
                {
                    stage = Stages.WELCOME;
                    this.renderStage();
                }
                break;

            case DISPLAYMAP:

                if (value == 5)
                {
                    DataController.savePlayer(gameState.getPlayer());//saves player to db.
                    stage = Stages.SAVEPLAYER;
                    this.renderStage();
                }
                else
                {
                    if (value == 1)
                        this.movePlayer(Directions.NORTH);
                    else if (value == 2)
                        this.movePlayer(Directions.WEST);
                    else if (value == 3)
                        this.movePlayer(Directions.SOUTH);
                    else if (value == 4)
                        this.movePlayer(Directions.EAST);
                    this.updateMap();
                    renderStage();
                }
                if (gameState.isGameOver() == true)
                    stage = Stages.GAMEOVER;
                if (gameState.isEnemyEncountered() == true)
                    stage = Stages.DISPLAYFIGHTORRUN;
                renderStage();
                break;

            case DISPLAYFIGHTORRUN:
                gameState.setEnemyEncountered(false);//we've already encountered the enemy at this stage so its ok to set encountered back to false bcos its purpose is to get us to thus stge
                if (value == 1)
                {
                    fight();
                    stage = Stages.DISPLAYFORCEDFIGHT;
                    renderStage();
                }
                else if (value == 2)
                {
                    if (this.fightOrRun() == true)
                    {
                        fight();
                        stage = Stages.DISPLAYFORCEDFIGHT;
                        renderStage();
                    }
                    else
                    {
                        gameState.getPlayer().setX((int)gameState.getPreviousPosition().getX());
                        gameState.getPlayer().setY((int)gameState.getPreviousPosition().getY());
                        updateMap();
                        stage = Stages.DISPLAYMAP;
                        renderStage();
                    }
                }
                break;

            case DISPLAYFORCEDFIGHT:
                if (value == 1)
                {
                    if (gameState.isGameOver())
                        stage = Stages.GAMEOVER;
                    else
                        stage = Stages.DISPLAYMAP;
                    renderStage();
                }
                break;

            case GAMEOVER:
                if (value == 1)//gong back to welcome
                    stage = Stages.WELCOME;
                else//exiting
                    System.exit(0);
                this.renderStage();
                break;

            case SAVEPLAYER:
                if (value == 1)
                {
                    stage = Stages.DISPLAYMAP;
                    renderStage();
                }
                break;

            case DISPLAYERRORS:
                stage = Stages.WELCOME;
                this.renderStage();
                break;
        }
    }

    public void initGuiGameState(ArrayList<String>  details,boolean isNewPlayer)
    {
        if (isNewPlayer)
        {
            Player hero  = PlayerFactory.createPlayer(details, this);
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
        }
    }

    private void initConsoleGameState(boolean isNewPlayer)
    {
        Player hero = null;
        if (isNewPlayer == true)
        {
            ArrayList<String> playerDetails; // this will be an arraylist that contains the name and type of the plaayer that the user supplied in the console e.g {"Laura",  "SuperGirl"}
            playerDetails = ((Console)view).getPlayerDetailsFromUser();
            hero = PlayerFactory.createPlayer(playerDetails, this);
        }
        else if (isNewPlayer == false)
        {
            ArrayList<Player> savedPlayers = DataController.getAllPlayers();
            hero = ((Console)view).getPlayerFromSavedPlayers(savedPlayers);
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

        for (Enemy enemy : gameState.getEnemies())
        {
            if (enemy.isAlive() == true)
                gameState.getMap()[enemy.getY()][enemy.getX()] = 'E';
        }
        if (gameState.getMap()[gameState.getPlayer().getY()][gameState.getPlayer().getX()] == 'E')
            gameState.setEnemyEncountered(true);
        gameState.getMap()[gameState.getPlayer().getY()][gameState.getPlayer().getX()] = 'H';
    }

   // private boolean isArtifactWon(){}// todo another time
    private void fight()
    {

        Enemy enemyToFight = null;
        Player hero = gameState.getPlayer();
        for (Enemy enemy : gameState.getEnemies())
        {
            if (enemy.getX() == hero.getX() && enemy.getY() == hero.getY())
                enemyToFight = enemy;
        }

        String tempReport = "\t\t"+hero.getName()+ "(" + hero.getType() + " : " + hero.getHitPoints() +"HP)  Verus " + enemyToFight.getName() + "(" + enemyToFight.getType() + " : " + enemyToFight.getHitPoints() +"HP)\n";

         Random randomGenerator = new Random();
        while (hero.getHitPoints() > 0 && enemyToFight.getHitPoints() > 0)
        {
            int chance = randomGenerator.nextInt(2);
            int damage;
            if (chance == 0)
            {
                //hero attacks enemy
                damage = hero.getAttackPoints() - enemyToFight.getDefencePoints();
                tempReport += "\n" + hero.getName() + " attacks " + enemyToFight.getName() + " with " + hero.getAttackPoints() + " attack points.";
                tempReport += "\n" + enemyToFight.getName() + " defends attack from " + hero.getName() + " with " + enemyToFight.getDefencePoints() + " defence points.\n";
                if (damage > 0)
                    enemyToFight.setHitPoints(enemyToFight.getHitPoints() - damage);
                else
                    damage = 0;
                tempReport += "\n" + enemyToFight.getName() + " suffered " + damage + " damage points. Remaining HP = " + enemyToFight.getHitPoints() +"\n\n";
            }
            else
            {
                //enemy attacks and hero
                damage = enemyToFight.getAttackPoints() - hero.getDefencePoints();
                tempReport += "\n" + enemyToFight.getName() + " attacks " + hero.getName() + " with " + enemyToFight.getAttackPoints() + " attack points.";
                tempReport += "\n" + hero.getName() + " defends attack from " + enemyToFight.getName() + " with " + hero.getDefencePoints() + " defence points.\n";
                if (damage > 0)
                    hero.setHitPoints(hero.getHitPoints() - damage);
                else
                    damage = 0;
                tempReport += "\n" + hero.getName() + " suffered " + damage + " damage points. Remaining HP = "+ hero.getHitPoints() +"\n\n";
            }
        }
        if (hero.getHitPoints() > 0)
        {
            enemyToFight.setAlive(false);
            tempReport += "\n\t\t" + hero.getName() + " won the fight";
            //this means hero won the fight
        }
        else
        {
            tempReport += "\n\t\t" + enemyToFight.getName() + " won the fight";
            gameState.setGameOver(true);
            //enemy won the fight
        }
        gameState.setBattleReport(tempReport);
    }

    private boolean fightOrRun()
    {
        int chance;

        Random randomGenerator = new Random();
        chance = randomGenerator.nextInt(2) + 1;
        if (chance == 1)
            return true;
        else
            return false;
    }

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

        //check if player reached boundary of the map

        if (gameState.getPlayer().getX() == 0 || gameState.getPlayer().getX() == gameState.getPlayer().getMapSize() - 1)
            gameState.setGameOver(true);
        if (gameState.getPlayer().getY() == 0 || gameState.getPlayer().getY() == gameState.getPlayer().getMapSize() - 1)
            gameState.setGameOver(true);

    }
}
