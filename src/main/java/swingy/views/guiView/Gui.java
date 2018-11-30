package swingy.views.guiView;

import swingy.controllers.GameController;
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

    public Gui(GameController controller)
    {
        this.controller = controller;
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

    public ArrayList<String> getPlayerDetailsFromUser()
    {
        return (null);
    }

    public Player getPlayerFromSavedPlayers(ArrayList<Player> savedPlayers)
    {
        return (null);
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

        //adding items to janel

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
        final JFrame frame;
        final JButton btnContinue;
        final JButton btnBack;
        JPanel panel;
        JComboBox typeComboBox;
        JLabel headingLabel, nameLabel, typeLabel, levelLabel, atkLabel, defLabel, hpLabel, errorLabel;
        JTextField nameText, levelText, atkText, defText, hpText;



        //adding items to  panel
        panel.add(btnBack);
        panel.add(btnContinue);
        panel.add(headingLabel);
        panel.add(nameLabel); panel.add(nameText);
        panel.add(typeLabel); panel.add(typeComboBox);
        panel.add(levelLabel); panel.add(levelText);
        panel.add(atkLabel); panel.add(atkText);
        panel.add(defLabel); panel.add(defText);
        panel.add(hpLabel); panel.add(hpText);
        panel.add(errorLabel);


        //setup listeners

        btnContinue.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (customPlayer.isSelected())
                {
                    if (!errors(levelText.getText(), atkText.getText(), defText.getText(), hpText.getText()))
                    {
                        controller.receiveUserInput(
                                typeComboBox.getSelectedItem() + ","
                                        + nameText.getText() + ","
                                        + levelText.getText() + ","
                                            + atkText.getText() + ","
                                            + defText.getText() + ","
                                            + hpText.getText());
                            if(controller.getHero() == null)
                                ((GuiDisplay)controller.getDisplay()).setErrorFrame(frame);
                            else
                                frame.dispose();
                            controller.displayStage();
                        }

                    }
                    else
                    {
                        frame.dispose();
                        controller.receiveUserInput(typeComboBox.getSelectedItem() + "," + nameText.getText());
                        controller.displayStage();
                    }

                }
            });

            btnBack.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    frame.dispose();
                    controller.receiveUserInput("b");
                    controller.displayStage();
                }
            });

            customPlayer.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    setCustomPlayerBounds();
                }
            });

            defaultPlayer.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    setDefaultPlayerBounds();
                }
            });
        }


        //set boundaries

        headingLabel.setBounds(140, 10 ,150,20);
        btnBack.setBounds(50, 220 ,130,20);
        btnContinue.setBounds(200, 220 ,130,20);
        errorLabel.setBounds(50, 180,300,40);
        nameText.setBounds(180, 60, 150, 20);
        nameLabel.setBounds(40, 60, 100, 20);
        typeLabel.setBounds(40, 100, 100, 20);
        typeComboBox.setBounds(180, 100, 150, 20);
        defaultPlayer.setBounds(40, 140, 140,20);
        customPlayer.setBounds(180, 140, 150,20);
        levelText.setVisible(false); levelLabel.setVisible(false);
        atkText.setVisible(false); atkLabel.setVisible(false);
        defText.setVisible(false); defLabel.setVisible(false);
        hpText.setVisible(false); hpLabel.setVisible(false);
        errorLabel.setVisible(false);
        frame.setPreferredSize(new Dimension(400, 500));
        frame.setResizable(false);



        frame = new JFrame("Swingy");
        btnContinue = new JButton("Start Game");
        btnBack = new JButton("Back");
        panel = new JPanel();
        String[] options  = {"Swordsman","Gunman", "KungFuMaster"};
        typeComboBox = new JComboBox(options);
        headingLabel = new JLabel("Create a Hero");
        typeLabel = new JLabel("Hero class:"); errorLabel = new JLabel("");
        nameLabel = new JLabel("Hero name:"); levelLabel = new JLabel("level:");
        atkLabel = new JLabel("attack:");
        defLabel = new JLabel("defense:"); hpLabel = new JLabel("hp");
        nameText = new JTextField("..."); levelText = new JTextField("...");
        atkText = new JTextField("..."); defText = new JTextField("..."); hpText = new JTextField("...");

    }



    public void displaySelectPlayerView()
    {

        JFrame frame;
        JButton btnContinue;
        JButton btnBack;
        final JPanel panel;
        ArrayList<String> types;
        JComboBox cbOptions;
        JLabel headingLabel;
        JTextArea info;
        JScrollPane jScrollPane;
        String selectedHero = "-1";

    public GuiPlayerSelectionView(GameController controller)
        {
            this.controller = controller;
        }

        private ArrayList<String> getShortHeroDetails(ArrayList<Player> heroes)
        {
            ArrayList<String> details = new ArrayList<>();
            int index = 0;
            for(Player player : heroes)
            {
                index++;
                String detail = Integer.toString(index) + ". " + player.getType() + " " + player.getName();
                details.add(detail);
            }
            return (details);
        }

        private ArrayList<String> getAllHeroDetails(ArrayList<Player> heroes)
        {
            ArrayList<String> details = new ArrayList<>();
            for(Player player : heroes)
            {
                String detail = "\n\tName: "+ player.getName() +"\n\n";
                detail += "\tClass: "+ player.getType() +"\n\n";
                detail += "\tLevel: "+ player.getLevel() +"\n\n";
                detail += "\tExp: "+ player.getExp() +"\n\n";
                detail += "\tAtk: "+ player.getAtk() +"\n\n";
                detail += "\tDef: "+ player.getDef() +"\n\n";
                detail += "\tHp: "+ player.getHp() +"\n\n";
                details.add(detail);
            }
            return (details);
        }

        private   void initSelectionView(ArrayList<Player> heroes)
        {
            frame = new JFrame("Swingy");
            btnContinue = new JButton("Start Game");
            if (heroes.size() == 0)
                btnContinue.setEnabled(false);
            else
                btnContinue.setEnabled(true);
            btnBack = new JButton("Back");
            panel = new JPanel();
            options = getShortHeroDetails(heroes);
            cbOptions = new JComboBox(options.toArray());
            lHeading = new JLabel("Hero Details");
            info = new JTextArea();
            jScrollPane = new JScrollPane(info);
            info.setEditable(false);
            setColors();
            setBounds();
            setListeners(heroes);
            addToPanel();
        }

        private  void addToPanel()
        {
            panel.add(btnBack);
            panel.add(btnContinue);
            panel.add(cbOptions);
            panel.add(lHeading);
            panel.add(jScrollPane);
        }

        private  void setBounds()
        {
            lHeading.setBounds(180, 10 ,150,20);
            jScrollPane.setBounds(50, 70, 350,300);
            cbOptions.setBounds(50, 380, 350, 20);
            btnBack.setBounds(100, 420 ,130,20);
            btnContinue.setBounds(230, 420 ,130,20);
        }

        private  void setListeners(final ArrayList<Player> heroes)
        {
            btnContinue.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    controller.receiveUserInput(selectedHero);
                    if(controller.getHero() == null)
                        ((GuiDisplay)controller.getDisplay()).setErrorFrame(frame);
                    else
                        frame.dispose();
                    controller.displayStage();
                }
            });

            btnBack.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    frame.dispose();
                    controller.receiveUserInput("b");
                    controller.displayStage();
                }
            });

            cbOptions.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    ArrayList<String> details = getAllHeroDetails(heroes);
                    info.setText(details.get(cbOptions.getSelectedIndex()));
                    selectedHero = Integer.toString(cbOptions.getSelectedIndex() + 1);
                }
            });
        }

        public  void displaySelectionView(ArrayList<Player> heroes)
        {
            initSelectionView(heroes);
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


    }


    public void displayErrorView(){}

    public void displayForcedFightView(){}
    public void displayDisplayFightOrRun(){}
    public void displayMapView(){}
    public void displayGameOver(){}
    public void displaySavePlayerView(){}

}
