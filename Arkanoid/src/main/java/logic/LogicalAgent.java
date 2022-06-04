package logic;

import graphic.GraphicalAgent;
import graphic.Panels.LeaderBoard;
import models.GameState;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class LogicalAgent {
    private ModelLoader modelLoader;
    private GraphicalAgent graphicalAgent;
    private GameState gameState;
    private String gameName;

    public LogicalAgent(){
        modelLoader=new ModelLoader(this);
        graphicalAgent=new GraphicalAgent(this);
        gameName="";
    }

    public void start(){
        String name=graphicalAgent.start();
        initialize(name);
    }

    public ModelLoader getModelLoader() {
        return modelLoader;
    }

    public void setModelLoader(ModelLoader modelLoader) {
        this.modelLoader = modelLoader;
    }

    public GraphicalAgent getGraphicalAgent() {
        return graphicalAgent;
    }

    public void setGraphicalAgent(GraphicalAgent graphicalAgent) {
        this.graphicalAgent = graphicalAgent;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public void initialize(String name){
        File file=new File("./src/main/resources/Users/"+name);
        if (!file.exists())file.mkdirs();
        graphicalAgent.initialize();
        gameState=new GameState(name);
        LeaderBoard.leads= modelLoader.initialize();
    }

    public void loadGame(String name) {
        gameName=name;
        this.gameState=modelLoader.loadGame(name);
    }

    public void saveGame(String name){
        try {
            modelLoader.saveGame(name,this.getGameState());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        gameState.update();
    }

    public void gameOver() {
        if (!gameName.equals("")){
            modelLoader.remove(gameName);
        }
        if (LeaderBoard.leads.containsKey(1-gameState.getScore())){
            LeaderBoard.leads.get(1-gameState.getScore()).add(gameState.getPlayer());
        }
        else{
            ArrayList<String> s=new ArrayList<>();
            s.add(gameState.getPlayer());
            LeaderBoard.leads.put(1-gameState.getScore(),s);
        }
        modelLoader.init();
    }
}