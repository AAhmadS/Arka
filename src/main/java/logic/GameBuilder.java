package logic;

import models.*;

import java.util.ArrayList;
import java.util.Scanner;

public class GameBuilder {
    private Scanner scanner;
    private String player;
    public GameBuilder(Scanner scan,String player){
        scanner=scan;
        this.player=player;
    }

    public GameState build(){
        scanner.next();
        Paddle paddle=this.loadPaddle(scanner);

        ArrayList<Ball> balls=loadBalls(scanner);

        ArrayList<Brick> bricks=loadBricks(scanner);

        ArrayList<Prize> prizes=loadRel(scanner);


        scanner.next();
        int gen= scanner.nextInt();
        scanner.next();
        int score= scanner.nextInt();
        scanner.next();
        int hp= scanner.nextInt();
        scanner.close();

        return new GameState(player,paddle,score,bricks,balls,prizes,hp,false,gen);
    }

    private Paddle loadPaddle(Scanner scanner) {
        int x=scanner.nextInt();
        int y= scanner.nextInt();
        int w=scanner.nextInt();
        int h=scanner.nextInt();
        boolean inverted=scanner.nextBoolean();
        int invertedTime=scanner.nextInt();
        int sizeTime=scanner.nextInt();
        String size=scanner.next();
        return new Paddle(x,y,w,h,inverted,invertedTime,sizeTime,size);
    }
    
    private ArrayList<Ball> loadBalls(Scanner scanner){
        scanner.next();
        int index=scanner.nextInt();
        ArrayList<Ball> balls=new ArrayList<>();
        for (int i = 0; i < index; i++) {
            int x=scanner.nextInt();
            int y= scanner.nextInt();
            int Xvel= scanner.nextInt();
            int Yvel=scanner.nextInt();
            boolean fired=scanner.nextBoolean();
            boolean vel=scanner.nextBoolean();
            int prizeTime=scanner.nextInt();
            int fireTime=scanner.nextInt();
            balls.add(new Ball(x,y,Xvel,Yvel,fired,vel,prizeTime,fireTime));
        }
        return balls;
    }
    
    private ArrayList<Brick> loadBricks(Scanner scanner){
        scanner.next();
        int index=scanner.nextInt();
        ArrayList<Brick> bricks=new ArrayList<>();
        for (int i = 0; i < index; i++) {
            int x=scanner.nextInt();
            int y= scanner.nextInt();
            boolean visible=scanner.nextBoolean();
            boolean broken=scanner.nextBoolean();
            boolean hit=scanner.nextBoolean();
            int time=scanner.nextInt();
            String type=scanner.next();
            bricks.add(new Brick(x,y,visible,broken,hit, time, type));
        }
        return bricks;
    }
    
    private ArrayList<Prize> loadRel(Scanner scanner){
        scanner.next();
        int index=scanner.nextInt();
        ArrayList<Prize> prizes=new ArrayList<>();
        for (int i = 0; i < index; i++) {
            int x=scanner.nextInt();
            int y= scanner.nextInt();
            String level=scanner.next();
            prizes.add(new Prize(x,y,level));
        }
        return prizes;
    }
}
