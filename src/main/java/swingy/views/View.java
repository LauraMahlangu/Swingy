package swingy.views;

import swingy.models.Player;
import swingy.models.Stages;

import java.util.ArrayList;

public interface View
{
    void displayStage(Stages stage);
    void displayWelcomeView();//todo done
    void displayCreatePlayerView();//todo done
    void displayErrorView();//todo friday
    void displaySelectPlayerView();//todo wednesday
    void displayForcedFightView();//todo friday
    void displayDisplayFightOrRun();//todo friday
    void displayMapView();//todo thursday
    void displayGameOver();
    void displaySavePlayerView();
}
