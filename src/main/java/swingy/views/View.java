package swingy.views;

import swingy.models.Player;
import swingy.models.Stages;

import java.util.ArrayList;

public interface View
{
    void displayStage(Stages stage);
    ArrayList<String> getPlayerDetailsFromUser();
    Player getPlayerFromSavedPlayers(ArrayList<Player> savedPlayers);
}
