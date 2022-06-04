package logic;

import graphic.Panels.LeaderBoard;
import models.Ball;
import models.Brick;
import models.GameState;
import models.Prize;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;

public class ModelLoader {
    private LogicalAgent logicalAgent;

    public ModelLoader(LogicalAgent logicalAgent) {
        this.logicalAgent = logicalAgent;
    }

    public GameState loadGame(String name){
        File file=new File("./src/main/resources/Users/"+logicalAgent.getGameState().getPlayer()+"/"+name);
        try {
            Scanner scanner=new Scanner(file);
            return new GameBuilder(scanner,logicalAgent.getGameState().getPlayer()).build();
        }
        catch (FileNotFoundException e) {
            return logicalAgent.getGameState();
        }
    }

    public void saveGame(String name,GameState gameState) throws IOException {
        File file=new File("./src/main/resources/Users/"+logicalAgent.getGameState().getPlayer()+"/"+name+".txt");
        if (!file.exists()){
            file.createNewFile();
        }
        PrintStream printStream=new PrintStream(new FileOutputStream(file,false));

        savePaddle(printStream,gameState);
        printStream.println();

        saveBalls(printStream,gameState);
        printStream.println();

        saveBricks(printStream,gameState);
        printStream.println();

        savePrizes(printStream,gameState);
        printStream.println();

        printStream.println("gen: "+gameState.getGen()+"\n" +
                "score: "+gameState.getScore()+"\n" +
                "hp: "+gameState.getHp());
        
        printStream.println();

        printStream.flush();
        printStream.close();
    }

    public void savePaddle(PrintStream printStream,GameState gameState){
        printStream.print("paddle: ");
        printStream.println(gameState.getPaddle().getX()+" "+gameState.getPaddle().getY()+" "+gameState.getPaddle().getW()+" "+gameState.getPaddle().getH()+" "+gameState.getPaddle().isInverted()+" "+gameState.getPaddle().getInvertedTime()+" "+gameState.getPaddle().getSizeTime()+" "+gameState.getPaddle().getSize());
    }

    public void saveBalls(PrintStream printStream, GameState gameState){
        printStream.println("Balls");
        printStream.println(gameState.getBallList().size());
        for (Ball ball: gameState.getBallList()) {
            printStream.println(ball.getX()+" "+ball.getY()+" "+ball.getVelocityX()+" "+ball.getVelocityY()+" "+ball.isFired()+" "+ball.isVeloEd()+" "+ball.getPrizeTime()+" "+ball.getFireTime());
        }
    }

    public void saveBricks(PrintStream printStream, GameState gameState){
        printStream.println("Bricks");printStream.println(gameState.getBrickList().size());
        for (Brick brick: gameState.getBrickList()) {
            printStream.println(brick.getX()+" "+brick.getY()+" "+brick.isVisible()+" "+brick.isBroken()+" "+brick.isHitOnce()+" "+brick.getTimeLeft()+" "+brick.getType());
        }
    }
    
    public void savePrizes(PrintStream printStream, GameState gameState){
        printStream.println("Prize");printStream.println(gameState.getPrizeReleased().size());
        for (Prize prize: gameState.getPrizeReleased()) {
            printStream.println(prize.getX()+" "+prize.getY()+" "+prize.getType());
        }
    }

    public void remove(String name) {
        File file=new File("./src/main/resources/"+logicalAgent.getGameState().getPlayer()+"/"+name);
        file.delete();
    }

    public void init() {
        File file=new File("./src/main/resources/INIT.txt");
        try {
            TreeMap<Integer, ArrayList<String>> leads=LeaderBoard.leads;
            PrintStream p=new PrintStream(new FileOutputStream(file,false));
            p.println(leads.size());

            for (Integer i: leads.keySet()) {
                p.println();
                p.println(i+" "+leads.get(i).size());
                for (String s: leads.get(i)) {
                    p.println(s);
                }
            }
            p.flush();
            p.close();
        } catch (FileNotFoundException e) {
            return;
        }
    }

    public TreeMap<Integer, ArrayList<String>> initialize(){
        File file=new File("./src/main/resources/INIT.txt");
        TreeMap<Integer, ArrayList<String>> leads=new TreeMap<>();
        try {
            Scanner scanner=new Scanner(file);
            int index=scanner.nextInt();
            for (int i=0;i<index;i++){
                int I= scanner.nextInt();
                int si=scanner.nextInt();
                ArrayList<String> names=new ArrayList<>();
                for (int j = 0; j < si; j++) {
                    names.add(scanner.next());
                }
                leads.put(I,names);
            }
            scanner.close();
            return leads;
        } catch (FileNotFoundException e) {
            return leads;
        }
    }
}
