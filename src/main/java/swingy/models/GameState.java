package swingy.models;


import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.util.ArrayList;

@Getter
@Setter
public class GameState
{
    private char map[][];
    private Player player;
    private ArrayList<Enemy> enemies;
    private Point previousPosition;


    public GameState(Player player, ArrayList<Enemy> enemies)
    {
        this.map = new char[player.getMapSize()][player.getMapSize()];
        this.player = player;
        this.enemies = enemies;
        this.previousPosition = new Point(player.getMapSize() / 2,player.getMapSize()/ 2);
    }
}