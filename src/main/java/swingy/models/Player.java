package swingy.models;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.Set;


import javax.validation.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter

public class Player
{
    @NotNull
    @Size(min = 4, message = "Player name length should be equals to 4 or greater")
    private String name;

    @NotNull
    @Size(min = 5, message = "Player class length should be equals to 5 or greater")
    private String type;

    @NotNull
    @Min(value = 1, message = "Level must be greater or equals to 1")
    private int level;

    @NotNull
    @Min(value = 1000, message = "Player experience cannot be lower than 1000")
    private int exp;

    @NotNull
    @Valid
    private ArrayList<Artifact> artifacts;

    @NotNull
    @Min(value = 0, message = " Hit point must be not less than zero")
    private int hitPoints;

    @NotNull
    @Min(value = 0, message = "Defence point must not be less than zero")
    private  int defencePoints;

    @NotNull
    @Min(value = 0, message = "Attack point must not be less than zero")
    private  int attackPoints;

    @NotNull
    @Min(value = 0, message = "x-coordinate can't be less than zero")
    private int x;

    @NotNull
    @Min(value = 0, message = "y-coordinate can't be less than zero")
    private int y;

    @NotNull
    @Min(value = 0, message = "map cant be smaller than 0")
    private int mapSize;


    public  Player(){}
    public Player(String name, String type, int level, int exp, int defencePoint, int attackPoint, int hitPoint, int x, int y, int mapSize)
    {
        this.name = name;
        this.type = type;
        this.level = level;
        this.exp = exp;
        this.hitPoints = hitPoint;
        this.defencePoints = defencePoint;
        this.attackPoints = attackPoint;
        this.x = x;
        this.y = y;
        this.mapSize = mapSize;
        artifacts = new ArrayList<>();
    }
}
