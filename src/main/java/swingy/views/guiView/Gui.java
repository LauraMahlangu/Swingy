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
                controller.initGuiGameState(createUserDetails, true, null);
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
                controller.initGuiGameState(null, false, player);
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
            errorText += "\n*  " + error + "\n";
        }

        errorLabel.setText(errorText);

        //set bounds
        errorLabel.setBounds(20,10,650,250);
     //   okButton.setBounds(330,275,40, 20);

        okButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.getErrors().clear();
                controller.processInput(0);
                frame.dispose();
            }
        });

      //  panel.add(okButton);
        panel.add(errorLabel);
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(700, 300));
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
    }

    public void displayForcedFightView(){}
    public void displayDisplayFightOrRun(){}
    public void displayMapView(){}
    public void displayGameOver(){}
    public void displaySavePlayerView(){}

}
