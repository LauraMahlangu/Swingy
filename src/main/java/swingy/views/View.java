package swingy.views;

import swingy.models.Player;
import swingy.models.Stages;

import java.util.ArrayList;

public interface View
{
    void displayStage(Stages stage);
    ArrayList<String> getPlayerDetailsFromUser();
    Player getPlayerFromSavedPlayers(ArrayList<Player> savedPlayers);
    void displayWelcomeView();//todo done
    void displayCreatePlayerView();//todo saturday
    void displayErrorView();//todo saturday
    void displaySelectPlayerView();//todo saturday
    void displayForcedFightView();
    void displayDisplayFightOrRun();
    void displayMapView();//todo saturday
    void displayGameOver();
    void displaySavePlayerView();
}
