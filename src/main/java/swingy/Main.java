package swingy;

import swingy.controllers.DataController;

public class Main
{
    public static void main(String args[])
    {
        DataController dataController = new DataController();
        dataController.createDatabase();
        dataController.initDatabase();

    }
}
