package graphic;

import logic.LogicalAgent;
import models.GameState;

import javax.swing.*;
import java.io.File;

public class GraphicalAgent implements Runnable{
    private LogicalAgent logicalAgent;
    private MainFrame mainFrame;

    public GraphicalAgent(LogicalAgent logicalAgent) {
        this.logicalAgent = logicalAgent;

    }

    public String start(){
        return JOptionPane.showInputDialog("please enter your name : ");
    }

    public void initialize() {
        mainFrame=MainFrame.getMainFrame();
        mainFrame.setGraphicalAgent(this);
    }

    public LogicalAgent getLogicalAgent() {
        return logicalAgent;
    }

    public void setLogicalAgent(LogicalAgent logicalAgent) {
        this.logicalAgent = logicalAgent;
    }

    public MainFrame getMainFrame() {
        return mainFrame;
    }

    public void setMainFrame(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public void loadGame(String name){
        logicalAgent.loadGame(name);
    }

    public void update(){
        logicalAgent.update();
    }

    @Override
    public void run() {
        logicalAgent.update();
        mainFrame.update();
    }

    public void saveGame(String name) {
        logicalAgent.saveGame(name);
    }
}
