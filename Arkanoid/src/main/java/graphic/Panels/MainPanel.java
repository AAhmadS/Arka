package graphic.Panels;

import graphic.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainPanel extends JPanel implements ActionListener{
    private JButton startNew,resumeOldGames,leaderboard,exit;
    
    public MainPanel(){
        startNew=new JButton("Start");
        startNew.setHorizontalTextPosition(JButton.CENTER);
        startNew.setVerticalTextPosition(JButton.CENTER);
        startNew.setForeground(Color.BLACK);
        startNew.setBackground(Color.WHITE);
        startNew.setFont(new Font("Arial", Font.PLAIN,14));
        startNew.addActionListener(this);
        startNew.setVisible(true);
        startNew.setBounds(310,120,220,90);

        resumeOldGames=new JButton("Resume");
        resumeOldGames.setHorizontalTextPosition(JButton.CENTER);
        resumeOldGames.setVerticalTextPosition(JButton.CENTER);
        resumeOldGames.setForeground(Color.BLACK);
        resumeOldGames.setBackground(Color.WHITE);
        resumeOldGames.setFont(new Font("Arial", Font.PLAIN,14));
        resumeOldGames.addActionListener(this);
        resumeOldGames.setVisible(true);
        resumeOldGames.setBounds(310,330,220,90);

        leaderboard=new JButton("Leaderboard");
        leaderboard.setHorizontalTextPosition(JButton.CENTER);
        leaderboard.setVerticalTextPosition(JButton.CENTER);
        leaderboard.setForeground(Color.BLACK);
        leaderboard.setBackground(Color.WHITE);
        leaderboard.setFont(new Font("Arial", Font.PLAIN,14));
        leaderboard.addActionListener(this);
        leaderboard.setVisible(true);
        leaderboard.setBounds(310,540,220,90);

        exit=new JButton("Exit");
        exit.setHorizontalTextPosition(JButton.CENTER);
        exit.setVerticalTextPosition(JButton.CENTER);
        exit.setForeground(Color.BLACK);
        exit.setBackground(Color.WHITE);
        exit.setFont(new Font("Arial", Font.PLAIN,14));
        exit.addActionListener(this);
        exit.setVisible(true);
        exit.setBounds(310,750,220,90);

        this.setBackground(new Color(168,60,40));

        this.setBounds(0,0,840,960);
        this.setVisible(true);
        this.setLayout(null);
        this.add(startNew);
        this.add(resumeOldGames);
        this.add(leaderboard);
        this.add(exit);
    }

    public JButton getStartNew() {
        return startNew;
    }

    public void setStartNew(JButton startNew) {
        this.startNew = startNew;
    }

    public JButton getResumeOldGames() {
        return resumeOldGames;
    }

    public void setResumeOldGames(JButton resumeOldGames) {
        this.resumeOldGames = resumeOldGames;
    }

    public JButton getLeaderboard() {
        return leaderboard;
    }

    public void setLeaderboard(JButton leaderboard) {
        this.leaderboard = leaderboard;
    }

    public JButton getExit() {
        return exit;
    }

    public void setExit(JButton exit) {
        this.exit = exit;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action=e.getActionCommand();
        MainFrame.getMainFrame().doAction(action);
    }
}
