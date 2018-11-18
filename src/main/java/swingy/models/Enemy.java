package swingy.models;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class Enemy extends Player
{
    @NotNull
    private boolean alive;

    public Enemy(String name, String type, int level, int exp, int defencePoint, int attackPoint, int hitPoint, int x, int y, int mapSize)
    {
        super(name, type, level, exp, defencePoint, attackPoint, hitPoint, x, y, mapSize);
        this.alive = true;
    }

}