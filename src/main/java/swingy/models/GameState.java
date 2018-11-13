package swingy.models;


import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.util.ArrayList;

@Getter
@Setter
public class GameState
{
    private int mapSize;
    private char map[][];
    private Player player;
    private ArrayList<Player> enemies;
    private Point previous_pos;


    public GameState(int mapSize, char[][] map, Player player, ArrayList<Player> enemies, Point previous_pos)
    {
        this.mapSize = mapSize;
        this.map = map;
        this.player = player;
        this.enemies = enemies;
        this.previous_pos = previous_pos;
    }
}