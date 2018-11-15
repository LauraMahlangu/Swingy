package swingy.factories;

import swingy.controllers.GameController;
import swingy.models.Player;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.Set;

public class PlayerFactory
{
    public static Player createPlayer(ArrayList<String> details)
    {
        String name = details.get(0);
        String type = details.get(1);
        int level = 0, exp = 0, attackPoints = 0, defencePoints = 0, hitPoints = 0, x = 0, y = 0;
        switch (type)
        {
            case "SuperGirl":
                level = 2;
                exp = 2450;
                attackPoints = 100;
                defencePoints = 80;
                hitPoints = 300;
                break;

            case "SuperMan":
                level = 3;
                exp = 4800;
                attackPoints = 120;
                defencePoints = 60;
                hitPoints = 400;
                break;

            case "Flash":
                level = 4;
                exp = 8050;
                attackPoints = 600;
                defencePoints = 60;
                hitPoints = 500;
                break;

        }
        int mapSize = ((level - 1) * 5) + 10 - (level % 2);
        x = mapSize / 2;
        y = mapSize / 2;
        Player hero = new Player(name, type, level, exp, defencePoints, attackPoints, hitPoints, x, y, mapSize);
        if (isValid(hero))
            return (hero);
        else
            return (null);
    }

    public static Player createSavedPlayer(ArrayList<String> details)
    {
        String name = details.get(0);
        String type = details.get(1);
        int level = Integer.parseInt(details.get(2));
        int exp = Integer.parseInt(details.get(3));
        int defencePoints = Integer.parseInt(details.get(4));
        int attackPoints = Integer.parseInt(details.get(5));
        int hitPoints = Integer.parseInt(details.get(6));
        int mapSize = Integer.parseInt(details.get(7));
        int x = Integer.parseInt(details.get(8));
        int y = Integer.parseInt(details.get(9));
    }

    public static boolean isValid(Player hero)
    {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<Player>> constraintViolations = validator.validate(hero);
        if (constraintViolations.size() > 0 )
        {
            for (ConstraintViolation<Player> constraints : constraintViolations)
               GameController.errors.add("Error :" + constraints.getMessage());
            return (false);
        }
        return (true);
    }
}
