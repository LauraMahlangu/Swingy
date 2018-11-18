package swingy.views.consoleView;

import swingy.controllers.GameController;
import swingy.models.Player;
import swingy.models.Stages;
import swingy.views.View;

import java.util.ArrayList;
import java.util.Scanner;

public class Console implements View
{
    GameController controller;

    public Console(GameController controller)
    {
        this.controller = controller;
    }

    public void displayStage(Stages stages)
    {
        //todo add if-statements for all stages later. the view must know which functions to run for each stage
        if (stages == Stages.WELCOME)
            displayWelcomeView();
        else if (stages == Stages.CREATEPLAYER)
            displayCreatePlayerView();
        else if (stages == Stages.DISPLAYERRORS)
            displayErrorView();
        else if (stages == Stages.SELECTPLAYER)
            displaySelectPlayerView();
        else if (stages == Stages.DISPLAYFORCEDFIGHT)
            ;//displayForcedFightView();
        else if (stages == Stages.DISPLAYFIGHTORRUN)
            ;//displayDisplayFightToRun();
        else if (stages == Stages.DISPLAYMAP)
            displayMapView();
        else if (stages == Stages.GAMEOVER)
            ;//displayGameOver();
        else
           ; //displaySavePlayerView();
    }



    public ArrayList<String> getPlayerDetailsFromUser()
    {
        ArrayList<String> details = new ArrayList<>();
        String name = "";
        String type = "";
        System.out.print("Hero name: ");
        Scanner userInput = new Scanner(System.in);
        if (userInput.hasNextLine())
            name = userInput.nextLine();
        else
            System.exit(0);
        System.out.println("\n               Select Hero Class\n"
                + "1.  SuperGirl\n"
                + "2.  SuperMan\n"
                + "3.  Flash");
        while (type.equals(""))
        {
            System.out.print("\nChoice: ");
            if (userInput.hasNextLine())//checks if we can type in something with the keyboard. if we cant, we exit the game
                type = userInput.nextLine();
            else
                System.exit(0);
            if (type.equals("1") || type.equals("2") || type.equals("3"))
            {
               switch (type)
               {
                   case "1":
                        type = "SuperGirl";
                        break;
                   case "2":
                        type = "SuperMan";
                        break;
                   case "3":
                        type = "Flash";
                        break;
               }
            }
            else
                type = "";
        }
        details.add(name);
        details.add(type);
        return (details);
    }

    public Player getPlayerFromSavedPlayers(ArrayList<Player> savedPlayers)
    {

        Player selectedPlayer = null;

        int index = 1;
        for (Player player : savedPlayers)
        {
            System.out.println(index  +".  "
                    + "Name: " + player.getName()
                    + ", Class: " + player.getType()
                    + ", Level: " + player.getLevel()
                    +  ", EPR: " + player.getExp()
                    +  ", DefencePoints: " + player.getDefencePoints()
                    +  ", AttackPoints: " + player.getAttackPoints()
                    +  ", HitPoints: " + player.getHitPoints()
                    +  ", X coordinates: " + player.getX()
                    +  ", Y coordinates: " + player.getY()
                    +  ", MapSize: " + player.getMapSize());
            index++;
        }
        System.out.println("b - Back");
        String choice = "";
        Scanner userInput = new Scanner(System.in);
        while (choice == "")
        {
            System.out.print("Choice: ");
            if (userInput.hasNextLine())
                choice = userInput.nextLine();
            else
                System.exit(0);
            if (choice.matches("\\d+$"))
            {
                int selectedIndex = Integer.parseInt(choice);
                if (selectedIndex >= 1 &&  selectedIndex <= savedPlayers.size())
                    selectedPlayer = savedPlayers.get(selectedIndex - 1);
                else
                    choice = "";
            }
            else  if (choice.equals("b"))
            {
                selectedPlayer = null;
            }
            else
                choice ="";
        }
        return (selectedPlayer);
    }

    public void displayWelcomeView()
    {
        System.out.println("\n\n" +
                " _       _  ___    _      ___    _____         ___       _____  _____     _      _____  _   _  ___    _____  _  ___   \n" +
                "( )  _  ( )(  _`\\ ( )    (  _`\\ (  _  )/'\\_/`\\(  _`\\    (_   _)(  _  )   ( )    (  _  )( ) ( )|  _`\\ (  _  )( )(  _`\\ \n" +
                "| | ( ) | || (_(_)| |    | ( (_)| ( ) ||     || (_(_)     | |  | ( ) |   | |    | (_) || | | || (_) )| (_) ||/ | (_(_)\n" +
                "| | | | | ||  _)_ | |  _ | |  _ | | | || (_) ||  _)_      | |  | | | |   | |  _ |  _  || | | || ,  / |  _  |   `\\__ \\ \n" +
                "| (_/ \\_) || (_( )| |_( )| (_( )| (_) || | | || (_( )     | |  | (_) |   | |_( )| | | || (_) || |\\ \\ | | | |   ( )_) |\n" +
                "`\\___x___/'(____/'(____/'(____/'(_____)(_) (_)(____/'     (_)  (_____)   (____/'(_) (_)(_____)(_) (_)(_) (_)   `\\____)\n" +
                "                                                                                                                      \n" +
                "                                                                                                                      \n" +
                "                                           ___    _       _  _  _   _  ___    _     _                                 \n" +
                "                                          (  _`\\ ( )  _  ( )(_)( ) ( )(  _`\\ ( )   ( )                                \n" +
                "                                          | (_(_)| | ( ) | || || `\\| || ( (_)`\\`\\_/'/'                                \n" +
                "                                          `\\__ \\ | | | | | || || , ` || |___   `\\ /'                                  \n" +
                "                                          ( )_) || (_/ \\_) || || |`\\ || (_, )   | |                                   \n" +
                "                                          `\\____)`\\___x___/'(_)(_) (_)(____/'   (_)                                   \n" +
                "                                                                                                                      \n" +
                "                                                                                                                      \n");
        System.out.println("1. Create Player\n"+
                           "2. Select Saved Player\n");
        String choice = "";
        Scanner userInput = new Scanner(System.in);
        int option = 0;
        while (choice.equals(""))
        {
            System.out.print("Choice: ");
            if (userInput.hasNextLine())
            {
                choice = userInput.nextLine();
                if (choice.equals("1"))
                    option = 1;
                else if (choice.equals("2"))
                    option = 2;
                else
                {
                    System.out.println("Invalid option selected");
                    choice = "";
                }
            }
            else
                System.exit(0);
        }
        controller.processInput(option);
    }

    private void displayCreatePlayerView()
    {
        System.out.println("\n*********************************************\n"
                +   "*               Create Player               *\n"
                +   "*********************************************\n\n");
    }

    public void displaySelectPlayerView()
    {
        System.out.println("\n*********************************************************************************\n" +
                "*                              Select a saved player                           *\n" +
                "********************************************************************************\n");
    }
    public void displayMapView()
    {
        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_GREEN = "\u001B[32m";
        //we print hero details
        System.out.println(
                "Name: " + controller.getGameState().getPlayer().getName()
                + ", Class: " + controller.getGameState().getPlayer().getType()
                + ", Level: " + controller.getGameState().getPlayer().getLevel()
                +  ", EPR: " + controller.getGameState().getPlayer().getExp()
                +  ", DefencePoints: " + controller.getGameState().getPlayer().getDefencePoints()
                +  ", AttackPoints: " + controller.getGameState().getPlayer().getAttackPoints()
                +  ", HitPoints: " + controller.getGameState().getPlayer().getHitPoints()
                +  ", X coordinates: " + controller.getGameState().getPlayer().getX()
                +  ", Y coordinates: " + controller.getGameState().getPlayer().getY()
                +  ", MapSize: " + controller.getGameState().getPlayer().getMapSize()+"\n");

        // we print the grid
        for(int row = 0; row < controller.getGameState().getPlayer().getMapSize(); row++)
        {
            for(int col = 0; col < controller.getGameState().getPlayer().getMapSize(); col++)
            {
                if (controller.getGameState().getMap()[row][col] == '.')
                    System.out.print("[ ]");
                else if (controller.getGameState().getMap()[row][col] == 'H')
                    System.out.print("[" + ANSI_GREEN + "H" + ANSI_RESET+ "]");
                else
                    System.out.print("[" + ANSI_RED + "E" + ANSI_RESET+ "]");
            }
            System.out.println();
        }

        // we print movement instructiions;
        System.out.println("\nNorth: w\n" +
                            "West: a\n" +
                            "South: s\n" +
                            "East: d\n"
        );

        //getting the choice
        String choice = "";
        Scanner userInput = new Scanner(System.in);
        while (choice.equals(""))
        {
            System.out.print("Choice: ");
            if (userInput.hasNextLine())
            {
                choice = userInput.nextLine();
                if (choice.equals("w") || choice.equals("a") || choice.equals("s") || choice.equals("d"))
                    break;
            }
            else
            {
                System.exit(0);
            }
        }
        if (choice.equals("w"))
            controller.processInput(1);
        else if (choice.equals("a"))
            controller.processInput(2);
        else if (choice.equals("s"))
            controller.processInput(3);
        else
            controller.processInput(4);
    }
    public void displayErrorView()
    {
        if (controller.getErrors().size() != 0)//in case the user went back when selecting to go back.
        {
            System.out.println("\n*******************************************************\n" +
                    "*      Errors encountered when creating player        *\n" +
                    "*******************************************************\n");
        }
        for (String error : controller.getErrors())
        {
            System.out.println("#  " + error);
        }
        System.out.print("Press any key and enter to continue: ");
        Scanner input = new Scanner(System.in);
        String pressed = "";
        if (input.hasNext())//checks if we can type in something
            pressed = input.nextLine();
        else
            System.exit(0);
        controller.getErrors().clear();
        controller.processInput(0);
    }

    public void displayForcedFightView(){}
    public void displayDisplayFightToRun(){}
    public void displayGameOver(){}
    public void displaySavePlayerView(){}

}
