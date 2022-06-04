package graphic;

import graphic.Panels.GamePanel;
import graphic.Panels.LeaderBoard;
import graphic.Panels.MainPanel;
import models.Ball;
import models.Brick;
import models.GameState;
import models.Prize;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MainFrame extends JFrame {
    private MainPanel mainPanel;
    private GamePanel gamePanel;
    private static MainFrame mainFrame=null;
    private GraphicalAgent graphicalAgent;

    private MainFrame(){
        mainPanel=new MainPanel();

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setContentPane(mainPanel);
        this.setResizable(false);
        this.setSize(840,960);
        this.setVisible(true);
        mainFrame=this;
    }

    public MainPanel getMainPanel() {
        return mainPanel;
    }

    public void setMainPanel(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public GraphicalAgent getGraphicalAgent() {
        return graphicalAgent;
    }

    public void setGraphicalAgent(GraphicalAgent graphicalAgent) {
        this.graphicalAgent = graphicalAgent;
    }

    public void doAction(String string){
        switch (string){
            case "Start" :
                gamePanel= new GamePanel(graphicalAgent.getLogicalAgent().getGameState());
                this.setContentPane(gamePanel);
                break;
            case "Resume":
                resume();
                break;
            case "Exit":
                dispose();
                break;
            case"Leaderboard":
                this.remove(this.getContentPane());
                this.setContentPane(new LeaderBoard());
                break;
            case "Back":
                this.setContentPane(mainPanel);
                break;
            case"Save":
                save();
                break;

        }
    }

    public static MainFrame getMainFrame(){
        if (mainFrame==null){
            return new MainFrame();
        }
        else{
            return mainFrame;
        }
    }

    public void resume(){
        JFileChooser fileChooser=new JFileChooser("./src/main/resources/Users/"+graphicalAgent.getLogicalAgent().getGameState().getPlayer());
        int response=fileChooser.showOpenDialog(null);
        if (response==JFileChooser.APPROVE_OPTION){
            graphicalAgent.loadGame(fileChooser.getSelectedFile().getName());
            this.doAction("Start");
        }
        else{
            JOptionPane.showMessageDialog(null,"no file chosen!");
        }
    }

    public void save(){
        JFileChooser fileChooser=new JFileChooser("./src/main/resources/Users/"+graphicalAgent.getLogicalAgent().getGameState().getPlayer());
        int response=fileChooser.showSaveDialog(null);
        if (response==JFileChooser.APPROVE_OPTION){
            graphicalAgent.saveGame(fileChooser.getSelectedFile().getName());
            JOptionPane.showMessageDialog(null,"saved successfully");
        }
    }

    public void update() {
        if (graphicalAgent.getLogicalAgent().getGameState().isGameOver()){
            graphicalAgent.getLogicalAgent().gameOver();
            JOptionPane.showMessageDialog(null,"Game over  :/");
            this.gamePanel.getGameLoop().stop();
            this.setContentPane(mainPanel);
            graphicalAgent.getLogicalAgent().setGameState(new GameState(graphicalAgent.getLogicalAgent().getGameState().getPlayer()));
        }
        else{
            getContentPane().repaint();
            getContentPane().revalidate();
        }
    }
}
