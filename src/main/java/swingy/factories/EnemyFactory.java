package swingy.factories;

import swingy.models.Enemy;
import swingy.models.Player;

import java.util.Random;

public class EnemyFactory
{

    public static Enemy createEnemy(int x, int y)
    {
        Random randomGenerator = new Random();
        int  level, exp, defence, attack, hit, mapSize, enemy_x, enemy_y;

        enemy_x = x;
        enemy_y = y;
        mapSize = 0;

        String name, type;

        String enemyNames[] = {"Lerapo", "Scorcher", "Slash", "Mezainle"};
        String enemyClasses[] = {"Goblin", "Demon Hunter", "Wizard", "Alchemist"};

        name = enemyNames[randomGenerator.nextInt(4)];
        type = enemyClasses[randomGenerator.nextInt(4)];
        level = randomGenerator.nextInt(5) + 1;
        exp = (level * 1000) + (level - 1) * (level - 1) * 450;
        defence = randomGenerator.nextInt(300);
        attack = randomGenerator.nextInt(350);
        hit = randomGenerator.nextInt(200);

        Enemy enemy = new Enemy(name, type, level, exp, defence, attack, hit, enemy_x, enemy_y, mapSize);
        return (enemy);
    }
}