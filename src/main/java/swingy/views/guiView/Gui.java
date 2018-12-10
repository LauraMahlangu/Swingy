package swingy.views.guiView;

import swingy.controllers.DataController;
import swingy.controllers.GameController;
import swingy.factories.PlayerFactory;
import swingy.models.Player;
import swingy.models.Stages;
import swingy.views.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;

public class Gui implements View
{
    private boolean mapInitialied = false;
    JFrame mapframe;
    private GameController controller;
    private ArrayList<String> createUserDetails;

    public Gui(GameController controller)
    {
        this.controller = controller;
        createUserDetails = new ArrayList<>();
    }

    public void displayStage(Stages stages)
    {

        if (stages == Stages.WELCOME)
            displayWelcomeView();
        else if (stages == Stages.CREATEPLAYER)
            displayCreatePlayerView();
        else if (stages == Stages.DISPLAYERRORS)
            displayErrorView();
        else if (stages == Stages.SELECTPLAYER)
            displaySelectPlayerView();
        else if (stages == Stages.DISPLAYFORCEDFIGHT)
            displayForcedFightView();
        else if (stages == Stages.DISPLAYFIGHTORRUN)
            displayDisplayFightOrRun();
        else if (stages == Stages.DISPLAYMAP)
            displayMapView();
        else if (stages == Stages.GAMEOVER)
            displayGameOver();
        else
            displaySavePlayerView();
    }

    public void displayWelcomeView()
    {
        final JFrame frame = new JFrame("Laura's Swingy");
        ButtonGroup btnGroup = new ButtonGroup();
        JButton btnContinue = new JButton("Continue");
        JPanel welcomePanel= new JPanel();
        JLabel welcomeLabel = new JLabel("Welcome To Laura's Swingy");
        final JRadioButton rbtnNewHero = new JRadioButton("New Hero");
        final JRadioButton rbtnSavedHero = new JRadioButton("Saved Hero.");

        //adding items to jpanel

        welcomePanel.add(btnContinue);
        welcomePanel.add(rbtnNewHero);
        welcomePanel.add(rbtnSavedHero);
        welcomePanel.add(welcomeLabel);
        rbtnNewHero.setSelected(true);
        btnGroup.add(rbtnNewHero);
        btnGroup.add(rbtnSavedHero);


        //setting up listeners
        btnContinue.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                frame.dispose();
                if (rbtnSavedHero.isSelected())
                    controller.processInput(2);
                else
                    controller.processInput(1);
            }
        });


        //setting up boundaries
        welcomeLabel.setBounds(180, 10 ,200,20);
        btnContinue.setBounds(200, 120 ,100,20);
        rbtnNewHero.setBounds(100,  60,200,40);
        rbtnSavedHero.setBounds(300, 60 ,200,40);


        //setting up frame for display
        frame.setContentPane(welcomePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(500, 200));
        frame.setResizable(false);
        frame.setLayout(null);
        frame.pack();
        frame.setVisible(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
    }

    public void displayCreatePlayerView()
    {
        final JFrame frame = new JFrame("Laura's Swingy");
        final JButton btnContinue = new JButton("Continue");
        final JButton btnBack = new JButton("Back");
        JPanel panel = new JPanel();
        final String[] playerTypes = {"SuperGirl", "SuperMan", "Flash"};
        final JComboBox typeComboBox = new JComboBox(playerTypes);


        JLabel headingLabel = new JLabel("Create Player"),
                nameLabel = new JLabel("Player Name: "),
                typeLabel = new JLabel("Player Class: "),
                levelLabel = new JLabel("Player Level: "),
                expLabel  = new JLabel("Player Exp: "),
                atkLabel = new JLabel("Player Attack Point: "),
                defLabel = new JLabel("Player Defence Points: "),
                hpLabel = new JLabel("Player Hit Points: ");

        final JTextField nameText = new JTextField(),
                levelText  = new JTextField(),
                expText = new JTextField(),
                atkText = new JTextField(),
                defText = new JTextField(),
                hpText = new JTextField();


//sets the initial values in the text boxes to display on loading
        typeComboBox.setSelectedIndex(0);
        levelText.setText("2");
        expText.setText("2450");
        atkText.setText("100");
        defText.setText("80");
        hpText.setText("300");


        //adding items to  panel
        panel.add(btnBack);
        panel.add(btnContinue);
        panel.add(headingLabel);
        panel.add(nameLabel); panel.add(nameText);
        panel.add(typeLabel); panel.add(typeComboBox);
        panel.add(levelLabel); panel.add(levelText);
        panel.add(expLabel); panel.add(expText);
        panel.add(atkLabel); panel.add(atkText);
        panel.add(defLabel); panel.add(defText);
        panel.add(hpLabel); panel.add(hpText);

        //disabling textfields
        levelText.setEditable(false);
        expText.setEditable(false);
        atkText.setEditable(false);
        defText.setEditable(false);
        hpText.setEditable(false);

        //setup listeners
        btnContinue.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                frame.dispose();
                createUserDetails.clear();
                createUserDetails.add(nameText.getText());
                createUserDetails.add(playerTypes[typeComboBox.getSelectedIndex()]);
                if (controller.initGuiGameState(createUserDetails, true, null))//if no errors exists during player creation
                    controller.processInput(1);
            }
        });

        btnBack.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                frame.dispose();
                controller.processInput(-1);
            }
        });

        typeComboBox.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (typeComboBox.getSelectedIndex() == 0)
                {
                    levelText.setText("2");
                    expText.setText("2450");
                    atkText.setText("100");
                    defText.setText("80");
                    hpText.setText("300");
                }

                else if (typeComboBox.getSelectedIndex() == 1)
                {
                    levelText.setText("3");
                    expText.setText("4800");
                    atkText.setText("120");
                    defText.setText("60");
                    hpText.setText("400");
                }

                else
                {
                    levelText.setText("4");
                    expText.setText("8050");
                    atkText.setText("600");
                    defText.setText("60");
                    hpText.setText("500");
                }
            }
        });



        //set boundaries

        headingLabel.setBounds(140, 10 ,150,20);

        nameLabel.setBounds(40, 60, 100, 20);
        nameText.setBounds(180, 60, 150, 20);

        typeLabel.setBounds(40, 100, 100, 20);
        typeComboBox.setBounds(180, 100, 150, 20);

        levelLabel.setBounds(40, 140, 100, 20);
        levelText.setBounds(180, 140, 60, 20);

        expLabel.setBounds(40, 180, 100, 20);
        expText.setBounds(180, 180, 60, 20);

        atkLabel.setBounds(40, 220, 160, 20);
        atkText.setBounds(180, 220, 60, 20);

        defLabel.setBounds(40, 260, 160, 20);
        defText.setBounds(180, 260, 60, 20);

        hpLabel.setBounds(40, 300, 160, 20);
        hpText.setBounds(180, 300, 60, 20);

        btnBack.setBounds(100, 340 ,80,20);
        btnContinue.setBounds(200, 340 ,80,20);


        //setup frame for display
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(360, 400));
        frame.setResizable(false);
        frame.setLayout(null);
        frame.pack();
        frame.setVisible(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
    }


    private ArrayList<String> getNames(ArrayList<Player> players)
    {
        ArrayList<String> justNames = new ArrayList<>();
        for( Player player : players)
        {
            justNames.add(player.getName());
        }
        return (justNames);
    }

    private String selectedPlayerDetails(Player player)
    {
        String details = "";
        details += "\n\tName: \t" + player.getName() + "\n";
        details += "\n\tClass: \t" + player.getType() + "\n";
        details += "\n\tLevel: \t" + player.getLevel() + "\n";
        details += "\n\tExperience: \t" + player.getExp() + "\n";
        details += "\n\tAttack Points: \t" + player.getAttackPoints() + "\n";
        details += "\n\tDefence Points: " + player.getDefencePoints() + "\n";
        details += "\n\tHit Points: \t" + player.getHitPoints() + "\n";
        details += "\n\tMap Size: \t" + player.getMapSize() + "\n";
        details += "\n\tX-coordinate: \t" + player.getX() + "\n";
        details += "\n\tY-coordinate: \t" + player.getY() + "\n";
        return (details);
    }

    public void displaySelectPlayerView()
    {

        final JFrame frame = new JFrame("Swingy");
        JButton btnContinue = new JButton("Play");
        JButton btnBack =  new JButton("Back");
        final JPanel panel = new JPanel();
        final JComboBox optionsDisplay;
        JLabel headingLabel = new JLabel("Player Data");
        final JTextArea dataDisplay = new JTextArea();
        JScrollPane jScrollPane;

        final ArrayList<Player> savedPlayers = DataController.getAllPlayers();

        if (savedPlayers.size() == 0)
            btnContinue.setEnabled(false);
        else
            btnContinue.setEnabled(true);
        ArrayList<String> options = getNames(savedPlayers);
        optionsDisplay = new JComboBox(options.toArray());
        jScrollPane = new  JScrollPane(dataDisplay);
        dataDisplay.setEditable(false);

//      add to panel

        panel.add(btnBack);
        panel.add(btnContinue);
        panel.add(optionsDisplay);
        panel.add(headingLabel);
        panel.add(jScrollPane);


//      set boundaries
        headingLabel.setBounds(180, 10 ,150,20);
        jScrollPane.setBounds(50, 40, 350,340);
        optionsDisplay.setBounds(50, 400, 350, 20);
        btnBack.setBounds(100, 430 ,130,20);
        btnContinue.setBounds(230, 430 ,130,20);


//      set listeners
        btnContinue.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                frame.dispose();
                Player player = savedPlayers.get(optionsDisplay.getSelectedIndex());
                if (controller.initGuiGameState(null, false, player))
                    controller.processInput(1);
            }
        });


        btnBack.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                frame.dispose();
                controller.processInput(-1);
            }
        });

        optionsDisplay.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Player player = savedPlayers.get(optionsDisplay.getSelectedIndex());
                String details = selectedPlayerDetails(player);
                dataDisplay.setText(details);
            }
        });


        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(450, 500));
        frame.setResizable(false);
        frame.setLayout(null);
        frame.pack();
        frame.setVisible(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
    }


    public void displayErrorView()
    {
        final JFrame frame = new JFrame("Errors");
        JPanel panel = new JPanel();
        JLabel errorLabel = new JLabel();
        JButton okButton  = new JButton("OK");
        String errorText = "";

        for (String error : controller.getErrors())
        {
            errorText += "\n\n*  " + error + "\n";
        }

        errorLabel.setText(errorText);


        // adding to panel
        panel.add(okButton);
        panel.add(errorLabel);

        //set bounds
        errorLabel.setBounds(20,30,400,100);
        okButton.setBounds(10,180,40, 20);

        //set text colour
        errorLabel.setForeground(Color.RED);


        okButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                controller.getErrors().clear();
                controller.processInput(0);
            }
        });


        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(500, 200));
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
    }


    private void drawMap(char[][] map, int mapSize, JPanel panel)
    {
        panel.removeAll();
        panel.setLayout(new GridLayout(mapSize, mapSize));
        for (int i = 0; i < mapSize; i++)
        {
            for (int j = 0; j < mapSize; j++)
            {
                JButton button;
                if (map[i][j] == 'H')
                {
                    button = new JButton();
                    button.setBackground(Color.GREEN);
                }
                else if (map[i][j] == 'E')
                {
                    button = new JButton();
                    button.setBackground(Color.BLACK);
                }
                else
                {
                    button = new JButton();
                    button.setBackground(Color.LIGHT_GRAY);
                }
                button.setOpaque(true);
                button.setEnabled(false);
                panel.add(button);
            }
        }
        panel.revalidate();
        panel.repaint();
    }

    public void displayMapView()
    {
        if (!mapInitialied)
           mapframe = new JFrame("Swingy");
        JButton btnNorth =  new JButton("^");
        JButton btnSouth=  new JButton("v");
        JButton btnEast =  new JButton(">");
        JButton btnWest =  new JButton("<");
        final JPanel panel = new JPanel();
        final JPanel gridPanel =  new JPanel();
        final JComboBox optionsDisplay;
        JLabel playerDataLabel = new JLabel();


        String[] options = {"Options", "Save Game", "Quit"};
        optionsDisplay = new JComboBox(options);
        optionsDisplay.setSelectedIndex(0);

//      add to panel

        panel.add(gridPanel);
        panel.add(btnNorth);
        panel.add(btnSouth);
        panel.add(btnEast);
        panel.add(btnWest);
        panel.add(optionsDisplay);
        panel.add(playerDataLabel);

        //player datails
        playerDataLabel.setText("Name: " + controller.getGameState().getPlayer().getName()
                + ", Class: " + controller.getGameState().getPlayer().getType()
                + ", Level: " + controller.getGameState().getPlayer().getLevel()
                +  ", EPR: " + controller.getGameState().getPlayer().getExp()
                +  ", Defence: " + controller.getGameState().getPlayer().getDefencePoints()
                +  ", Attack: " + controller.getGameState().getPlayer().getAttackPoints()
                +  ", HitPoints: " + controller.getGameState().getPlayer().getHitPoints()
                +  ", X : " + controller.getGameState().getPlayer().getX()
                +  ", Y : " + controller.getGameState().getPlayer().getY()
                +  ", MapSize: " + controller.getGameState().getPlayer().getMapSize());

//      set boundaries
        playerDataLabel.setBounds(20, 10 ,860,20);
        optionsDisplay.setBounds(390, 630, 120, 20);
        btnNorth.setBounds(440, 560 ,20,20);
        btnSouth.setBounds(440, 600 ,20,20);
        btnEast.setBounds(460, 580 ,20,20);
        btnWest.setBounds(420, 580 ,20,20);
        gridPanel.setBounds(20, 60, 860, 480);

        //set listeners

        btnNorth.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                controller.processInput(1);
                drawMap(controller.getGameState().getMap(), controller.getGameState().getPlayer().getMapSize(), gridPanel);
            }
        });
        btnWest.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                controller.processInput(2);

                //drawMap(controller.getGameState().getMap(), controller.getGameState().getPlayer().getMapSize(), gridPanel);
            }
        });
        btnSouth.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                controller.processInput(3);
                controller.renderStage();
                //drawMap(controller.getGameState().getMap(), controller.getGameState().getPlayer().getMapSize(), gridPanel);
            }
        });
        btnEast.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                controller.processInput(4);
                controller.renderStage();//drawMap(controller.getGameState().getMap(), controller.getGameState().getPlayer().getMapSize(), gridPanel);
            }
        });

        mapframe.setContentPane(panel);
        mapframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mapframe.setLocationRelativeTo(null);
        mapframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mapframe.setPreferredSize(new Dimension(900, 700));
        mapframe.setResizable(false);
        mapframe.setLayout(null);
        mapframe.pack();
        mapframe.setVisible(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        mapframe.setLocation(dim.width/2-mapframe.getSize().width/2, dim.height/2-mapframe.getSize().height/2);
        drawMap(controller.getGameState().getMap(), controller.getGameState().getPlayer().getMapSize(), gridPanel);
    }

    public void displayForcedFightView(){}
    public void displayDisplayFightOrRun(){}
    public void displayGameOver(){}
    public void displaySavePlayerView(){}

}
