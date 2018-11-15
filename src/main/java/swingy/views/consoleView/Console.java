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
        ; //displayWelcomeView();
        else if (stages == Stages.CREATEPLAYER)
            displayCreatePlayerView();
        else if (stages == Stages.DISPLAYERRORS)
            displayErrorView();
        else if (stages == Stages.SELECTPLAYER)
            ;//displaySelectPlayerView()
        else if (stages == Stages.DISPLAYFORCEDFIGHT)
            ;//displayForcedFightView();
        else if (stages == Stages.DISPLAYFIGHTORRUN)
            ;//displayDisplayFightToRun();
        else if (stages == Stages.DISPLAYMAP)
            ;//displayMapView();
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
        Scanner userInput = new Scanner(System.in);
        if (userInput.hasNext())
        {
            System.out.print("Hero name: ");
            name = userInput.nextLine();
            System.out.println();
        }
        else
            System.exit(0);
        System.out.println("\n               Select Hero Class\n"
                + "1.  SuperGirl\n"
                + "2.  SuperMan\n"
                + "3.  Flash");
        while (type.equals(""))
        {
            System.out.print("\nChoice: ");
            if (userInput.hasNext())//checks if we can type in something with the keyboard. if we cant, we exit the game
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

        Player player = null;

        System.out.println("\n*********************************************************************************\n" +
                                 "*                              Select a saved player                           *\n" +
                                 "********************************************************************************\n");
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
        }
        String choice = "";
        Scanner userInput = new Scanner(System.in);
        while (choice == "")
        {
            if (userInput.hasNext())
                choice = userInput.nextLine();
            else
                System.exit(0);
            if (choice.matches("\\d+$"))
            {
                int selectedIndex = Integer.parseInt(choice);
                if (selectedIndex >= 1 &&  selectedIndex <= savedPlayers.size())
                    player = savedPlayers.get(selectedIndex - 1);
                else
                    choice = "";
            }
            else
                choice ="";
        }
        return (player);
    }

    public void displayWelcomeView(){}

    private void displayCreatePlayerView()
    {
        System.out.println("\n*********************************************\n"
                +   "*               Create Player               *\n"
                +   "*********************************************\n\n");
    }

    public void displaySelectPlayerView(){}
    public void displayMapView(){}
    public void displayErrorView()
    {
        System.out.println("\n*******************************************************\n" +
                           "*      Errors encountered when creating player        *\n" +
                           "*******************************************************\n");
        for (String error : GameController.errors)
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
        controller.processInput(0);
    }
    public void displayForcedFightView(){}
    public void displayDisplayFightToRun(){}
    public void displayGameOver(){}
    public void displaySavePlayerView(){}

}
