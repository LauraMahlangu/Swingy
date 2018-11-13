package swingy;

import swingy.controllers.DataController;
import swingy.controllers.GameController;

public class Main
{
    public static void main(String args[])
    {
        DataController dataController = new DataController();
        dataController.createDatabase();
        dataController.initDatabase();
        boolean isConsole = true;
        if (args[0].equals("console"))
            isConsole = true;
        else if (args[0].equals("gui"))
            isConsole = false;
        else
        {
            System.out.println("Invalid view supplied.");
            System.exit(0);
        }
        GameController gameController = new GameController(isConsole);
        gameController.playGame();
    }
}