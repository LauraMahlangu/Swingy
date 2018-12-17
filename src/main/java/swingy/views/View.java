package swingy.views;

import swingy.models.Player;
import swingy.models.Stages;

import java.util.ArrayList;

public interface View
{
    void displayStage(Stages stage);
    void displayWelcomeView();
    void displayCreatePlayerView();
    void displayErrorView();
    void displaySelectPlayerView();
    void displayForcedFightView();
    void displayDisplayFightOrRun();
    void displayMapView();
    void displayGameOver();
    void displaySavePlayerView();
}
