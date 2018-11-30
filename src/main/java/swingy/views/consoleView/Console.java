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
        if (stages == Stages.WELCOME)
            displayWelcomeView();
        else if (stages == Stages.CREATEPLAYER)
            displayCreatePlayerView();
        else if (stages == Stages.DISPLAYERRORS)
            displayErrorView();
        else if (stages == Stages.SELECTPLAYER)
            displaySelectPlayerView();
        else if (stages == Stages.DISPLAYFORCEDFIGHT)
            displayForcedFightView();
        else if (stages == Stages.DISPLAYFIGHTORRUN)
            displayDisplayFightOrRun();
        else if (stages == Stages.DISPLAYMAP)
            displayMapView();
        else if (stages == Stages.GAMEOVER)
            displayGameOver();
        else
            displaySavePlayerView();
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

    public void displayCreatePlayerView()
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
                            "East: d\n" +
                            "Save player: x"
        );

        //getting the movement choice only if enemy is not encountered, else just the map is drawn to show player movement
        if (controller.getGameState().isEnemyEncountered() == false && controller.getGameState().isGameOver() == false)
        {
            String choice = "";
            Scanner userInput = new Scanner(System.in);
            while (choice.equals(""))
            {
                System.out.print("Choice: ");
                if (userInput.hasNextLine())
                {
                    choice = userInput.nextLine();
                    if (choice.equals("w") || choice.equals("a") || choice.equals("s") || choice.equals("d") || choice.equals("x"))
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
            else if (choice.equals("d"))
                controller.processInput(4);
            else
                controller.processInput(5);
        }
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

    public void displayForcedFightView()
    {
          System.out.println("\n********************************************************** \n" +
                               "*                       Battle Results                   *\n" +
                               "**********************************************************\n");

          System.out.println(controller.getGameState().getBattleReport());
          System.out.println("\nPress enter any key to continue to game: ");
          Scanner userInput = new Scanner((System.in));
          String pressed = "";
          if (userInput.hasNextLine())
              pressed = userInput.nextLine();
          else
              System.exit(0);
          controller.processInput(1);
    }

    public void displayDisplayFightOrRun()
    {
        System.out.print("\n*************************************************************\n" +
                           "*                      Enemy Encountered                    *\n" +
                           "*************************************************************\n\n" +
                           "   1. Fight\n" +
                           "   2. Run\n\n"
        );

        String choice = "";
        int selectedInput = 0;
        Scanner userInput = new Scanner(System.in);
        while (choice.equals(""))
        {
            System.out.print("Choice: ");
            if (userInput.hasNextLine())
            {
                choice = userInput.nextLine();
                if (choice.equals("1"))
                    selectedInput = 1;
                else if (choice.equals("2"))
                    selectedInput = 2;
                else
                    choice = "";
            }
            else
                System.exit(0);
        }
        controller.processInput(selectedInput);
    }

    public void displayGameOver()
    {
        System.out.println("\n\n   _____                             ____                      _ \n" +
                "  / ____|                           / __ \\                    | |\n" +
                " | |  __   __ _  _ __ ___    ___   | |  | |__   __ ___  _ __  | |\n" +
                " | | |_ | / _` || '_ ` _ \\  / _ \\  | |  | |\\ \\ / // _ \\| '__| | |\n" +
                " | |__| || (_| || | | | | ||  __/  | |__| | \\ V /|  __/| |    |_|\n" +
                "  \\_____| \\__,_||_| |_| |_| \\___|   \\____/   \\_/  \\___||_|    (_)\n" +
                "                                                                ");

        if (controller.getGameState().getPlayer().getHitPoints() > 0)
            System.out.println("\n                                                _ \n" +
                    "                                                | |\n" +
                    "  _   _   ___   _   _   __      __ ___   _ __   | |\n" +
                    " | | | | / _ \\ | | | |  \\ \\ /\\ / // _ \\ | '_ \\  | |\n" +
                    " | |_| || (_) || |_| |   \\ V  V /| (_) || | | | |_|\n" +
                    "  \\__, | \\___/  \\__,_|    \\_/\\_/  \\___/ |_| |_| (_)\n" +
                    "   __/ |                                           \n" +
                    "  |___/           \n");
        else
            System.out.println("         _              _     _ \n" +
                    "                        | |            | |   | |\n" +
                    "  _   _   ___   _   _   | |  ___   ___ | |_  | |\n" +
                    " | | | | / _ \\ | | | |  | | / _ \\ / __|| __| | |\n" +
                    " | |_| || (_) || |_| |  | || (_) |\\__ \\| |_  |_|\n" +
                    "  \\__, | \\___/  \\__,_|  |_| \\___/ |___/ \\__| (_)\n" +
                    "   __/ |                                        \n" +
                    "  |___/                                        \n");
        System.out.println("Continue to main screen: c\n" +
                           "Quit : q"
        );
        Scanner userInput = new Scanner((System.in));
        String pressed = "";
        int selectedInput = 0;
        while (pressed.equals(""))
        {
            System.out.print("Choice: ");
            if (userInput.hasNextLine())
            {
                pressed = userInput.nextLine();
                if (pressed.equals("c"))
                    selectedInput = 1;//back o menu
                else if (pressed.equals("q"))
                    selectedInput = 2;//quit game
                else
                    pressed = "";

            }
            else
                System.exit(0);
        }
        controller.processInput(selectedInput);
    }

    public void displaySavePlayerView()
    {
        System.out.println(" _____   _                                                         _   _ \n" +
                " |  __ \\ | |                                                       | | | |\n" +
                " | |__) || |  __ _  _   _   ___  _ __   ___   __ _ __   __ ___   __| | | |\n" +
                " |  ___/ | | / _` || | | | / _ \\| '__| / __| / _` |\\ \\ / // _ \\ / _` | | |\n" +
                " | |     | || (_| || |_| ||  __/| |    \\__ \\| (_| | \\ V /|  __/| (_| | |_|\n" +
                " |_|     |_| \\__,_| \\__, | \\___||_|    |___/ \\__,_|  \\_/  \\___| \\__,_| (_)\n" +
                "                     __/ |                                                \n" +
                "                    |___/     ");

        System.out.print("Press any key to continue: ");
        Scanner userInput = new Scanner((System.in));
        String pressed = "";
        if (userInput.hasNextLine())
            pressed = userInput.nextLine();
        else
            System.exit(0);
        controller.processInput(1);
    }

}