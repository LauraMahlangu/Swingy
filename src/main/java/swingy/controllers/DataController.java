package swingy.controllers;

import java.sql.*;


import swingy.models.Player;
import java.util.ArrayList;

public class DataController
{


    private Connection getConnection()
    {
        Connection connection = null;
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "NKibSf67");
        }
        catch (ClassNotFoundException ex)
        {
            ex.printStackTrace();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return (connection);
    }

    public void createDatabase()
    {
        Connection connection = null;
        try
        {
            connection = getConnection();
            Statement  statement = connection.createStatement();
            String create = "create database if not exists swingy";
            statement.executeUpdate(create);
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            try
            {
                if (connection != null)
                    connection.close();
            }
            catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void initDatabase()
    {
        Connection connection = null;
        try
        {
            connection = getConnection();
            Statement statement = connection.createStatement();

            String createTable =
                    "CREATE TABLE if not exists `swingy`.`players` (" +
                            "`name` TEXT NOT NULL , " +
                            "`type` TEXT NOT NULL , " +
                            "`level` INT NOT NULL , " +
                            "`exp` INT NOT NULL , " +
                            "`attackPoints` INT NOT NULL , " +
                            "`defensePoints` INT NOT NULL , " +
                            "`hitPoints` INT NOT NULL);";
            statement.executeUpdate(createTable);
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            try
            {
                if (connection != null)
                    connection.close();
            }
            catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        }
    }

    public ArrayList<Player> getAllPlayers()
    {

        ArrayList<Player>  allPlayers = new ArrayList<>();

        Connection connection = null;
        try
        {
            connection = getConnection();
            Statement statement = connection.createStatement();
            String  getPlayers = "SELECT * FROM `swingy`.`players`";

            ResultSet results = statement.executeQuery(getPlayers);
            while(results.next())
            {
                String name = results.getString("name");
                String type = results.getString("type");
                int level = results.getInt("level");
                int exp = results.getInt("exp");
                int attackPoints = results.getInt("attackPoints");
                int defensePoints = results.getInt("defensePoints");
                int hitPoints = results.getInt("hitPoints");
                Player player = new Player(name, type, level, exp, defensePoints, attackPoints, hitPoints);
                if (player.isValid() != false)
                    allPlayers.add(player);
            }
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            try
            {
                if (connection != null)
                    connection.close();
            }
            catch (SQLException ex)
            {
                ex.printStackTrace();
            }

        }
        return (allPlayers);
    }

    public void savePlayer(Player player)
    {
        Connection connection = null;
        try
        {
            connection = getConnection();
            Statement statement = connection.createStatement();
            String save = String.format("INSERT INTO `swingy`.`players` (`name`, `type`, `level`, `exp`," +
                            " `attackPoints`, `defencePoint`, `hitPints`) " +
                            " VALUES ('%s', '%s', %d, %d, %d, %d, %d)",
                    player.getName(),
                    player.getType(),
                    player.getLevel(),
                    player.getExp(),
                    player.getAttackPoints(),
                    player.getDefencePoints(),
                    player.getHitPoints()
            );
            statement.executeUpdate(save);
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            try
            {
                if (connection != null)
                    connection.close();
            }
            catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        }
    }
}
