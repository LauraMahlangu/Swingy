package swingy.models;

import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;


import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter

public class Player
{
    @NotNull
    private String name;

    @NotNull
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
    private int hitPoint;

    @NotNull
    @Min(value = 0, message = "Defence point must not be less than zero")
    private  int defencePoint;

    @NotNull
    @Min(value = 0, message = "Attack point must not be less than zero")
    private  int attackPoint;



}
