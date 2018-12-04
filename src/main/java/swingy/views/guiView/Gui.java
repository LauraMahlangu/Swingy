package swingy.views.guiView;

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
    private ArrayList<String> savedUserDetails;

    public Gui(GameController controller)
    {
        this.controller = controller;
        createUserDetails = new ArrayList<>();
        savedUserDetails = new ArrayList<>();
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
        typeComboBox.setSelectedIndex(0);

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
                createUserDetails.clear();
                createUserDetails.add(nameText.getText());
                createUserDetails.add(playerTypes[typeComboBox.getSelectedIndex()]);
                controller.initGuiGameState(createUserDetails, true);
                frame.dispose();
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



    public void displaySelectPlayerView()
    {
//
//        JFrame frame;
//        JButton btnContinue;
//        JButton btnBack;
//        final JPanel panel;
//        ArrayList<String> types;
//        JComboBox cbOptions;
//        JLabel headingLabel;
//        JTextArea info;
//        JScrollPane jScrollPane;
//        String selectedHero = "-1";
//
//    public GuiPlayerSelectionView(GameController controller)
//        {
//            this.controller = controller;
//        }
//
//        private ArrayList<String> getShortHeroDetails(ArrayList<Player> heroes)
//        {
//            ArrayList<String> details = new ArrayList<>();
//            int index = 0;
//            for(Player player : heroes)
//            {
//                index++;
//                String detail = Integer.toString(index) + ". " + player.getType() + " " + player.getName();
//                details.add(detail);
//            }
//            return (details);
//        }
//
//        private ArrayList<String> getAllHeroDetails(ArrayList<Player> heroes)
//        {
//            ArrayList<String> details = new ArrayList<>();
//            for(Player player : heroes)
//            {
//                String detail = "\n\tName: "+ player.getName() +"\n\n";
//                detail += "\tClass: "+ player.getType() +"\n\n";
//                detail += "\tLevel: "+ player.getLevel() +"\n\n";
//                detail += "\tExp: "+ player.getExp() +"\n\n";
//                detail += "\tAtk: "+ player.getAtk() +"\n\n";
//                detail += "\tDef: "+ player.getDef() +"\n\n";
//                detail += "\tHp: "+ player.getHp() +"\n\n";
//                details.add(detail);
//            }
//            return (details);
//        }
//
//        private   void initSelectionView(ArrayList<Player> heroes)
//        {
//            frame = new JFrame("Swingy");
//            btnContinue = new JButton("Start Game");
//            if (heroes.size() == 0)
//                btnContinue.setEnabled(false);
//            else
//                btnContinue.setEnabled(true);
//            btnBack = new JButton("Back");
//            panel = new JPanel();
//            options = getShortHeroDetails(heroes);
//            cbOptions = new JComboBox(options.toArray());
//            lHeading = new JLabel("Hero Details");
//            info = new JTextArea();
//            jScrollPane = new JScrollPane(info);
//            info.setEditable(false);
//            setColors();
//            setBounds();
//            setListeners(heroes);
//            addToPanel();
//        }
//
//        private  void addToPanel()
//        {
//            panel.add(btnBack);
//            panel.add(btnContinue);
//            panel.add(cbOptions);
//            panel.add(lHeading);
//            panel.add(jScrollPane);
//        }
//
//        private  void setBounds()
//        {
//            lHeading.setBounds(180, 10 ,150,20);
//            jScrollPane.setBounds(50, 70, 350,300);
//            cbOptions.setBounds(50, 380, 350, 20);
//            btnBack.setBounds(100, 420 ,130,20);
//            btnContinue.setBounds(230, 420 ,130,20);
//        }
//
//        private  void setListeners(final ArrayList<Player> heroes)
//        {
//            btnContinue.addActionListener(new ActionListener()
//            {
//                @Override
//                public void actionPerformed(ActionEvent e)
//                {
//                    controller.receiveUserInput(selectedHero);
//                    if(controller.getHero() == null)
//                        ((GuiDisplay)controller.getDisplay()).setErrorFrame(frame);
//                    else
//                        frame.dispose();
//                    controller.displayStage();
//                }
//            });
//
//            btnBack.addActionListener(new ActionListener()
//            {
//                @Override
//                public void actionPerformed(ActionEvent e)
//                {
//                    frame.dispose();
//                    controller.receiveUserInput("b");
//                    controller.displayStage();
//                }
//            });
//
//            cbOptions.addActionListener(new ActionListener()
//            {
//                @Override
//                public void actionPerformed(ActionEvent e)
//                {
//                    ArrayList<String> details = getAllHeroDetails(heroes);
//                    info.setText(details.get(cbOptions.getSelectedIndex()));
//                    selectedHero = Integer.toString(cbOptions.getSelectedIndex() + 1);
//                }
//            });
//        }
//
//        public  void displaySelectionView(ArrayList<Player> heroes)
//        {
//            initSelectionView(heroes);
//            frame.setContentPane(panel);
//            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            frame.setLocationRelativeTo(null);
//            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            frame.setPreferredSize(new Dimension(450, 500));
//            frame.setResizable(false);
//            frame.setLayout(null);
//            frame.pack();
//            frame.setVisible(true);
//            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
//            frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
//        }
//

    }


    public void displayErrorView(){}

    public void displayForcedFightView(){}
    public void displayDisplayFightOrRun(){}
    public void displayMapView(){}
    public void displayGameOver(){}
    public void displaySavePlayerView(){}

}
