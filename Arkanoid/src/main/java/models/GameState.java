package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameState {
    private static Random random=new Random();
    private final int SHIFT_Y=100,SHIFT_X=100,DefaultX=65,DefaultY=70;
    private String player;
    private boolean paused;
    private Paddle paddle;
    private int score;
    private List<Brick> brickList;
    private List<Ball> ballList;
    private List<Prize> prizeReleased;
    private int hp;
    private boolean gameOver;
    private int gen;

    public GameState(String name) {
        player=name;
        brickList=new ArrayList<>();
        ballList=new ArrayList<>();
        prizeReleased=new ArrayList<>();
        hp=3;
        score=0;
        gameOver=false;
        paused=true;
        paddle=new Paddle();
        gen=1350;
        generate();
        ballList.add(new Ball(410,849));
    }

    public GameState(String player, Paddle paddle, int score, List<Brick> brickList, List<Ball> ballList, List<Prize> prizeReleased, int hp, boolean gameOver, int gen) {
        this.player = player;
        this.paddle = paddle;
        this.score = score;
        this.brickList = brickList;
        this.ballList = ballList;
        this.prizeReleased = prizeReleased;
        this.hp = hp;
        this.gameOver = gameOver;
        this.gen=gen;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    public Paddle getBoard() {
        return paddle;
    }

    public void setBoard(Paddle paddle) {
        this.paddle = paddle;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getDefaultX() {
        return DefaultX;
    }

    public int getDefaultY() {
        return DefaultY;
    }

    public List<Brick> getBrickList() {
        return brickList;
    }

    public void setBrickList(List<Brick> brickList) {
        this.brickList = brickList;
    }

    public List<Ball> getBallList() {
        return ballList;
    }

    public void setBallList(List<Ball> ballList) {
        this.ballList = ballList;
    }

    public List<Prize> getPrizeReleased() {
        return prizeReleased;
    }

    public void setPrizeReleased(List<Prize> prizeReleased) {
        this.prizeReleased = prizeReleased;
    }

    public Paddle getPaddle() {
        return paddle;
    }

    public void setPaddle(Paddle paddle) {
        this.paddle = paddle;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public int getGen() {
        return gen;
    }

    public void setGen(int gen) {
        this.gen = gen;
    }

    public void update() {
        if (paused){
            return;
        }
        for (Ball ball: ballList) {
            ball.update(this);
            checkIntersection(ball);
        }
        ballList.removeIf(Ball::isRemovable);

        if (ballList.isEmpty()){
            hp--;
            score-=5;
            if (hp==0){
                gameOver=true;
                paused=true;
                return;
            }
            ballList.add(new Ball(paddle.getX()+ paddle.getW()/2-10,849));
        }

        for (Brick br: brickList) {
            br.update();
        }
        for (Prize rel: prizeReleased) {
            rel.update(paddle,this);
        }
        prizeReleased.removeIf(Prize::isRemovable);

        paddle.update();

        if (gen==0){
            generate();
            gen=900;
        }
        else {
            gen--;
        }
    }

    public void generate() {
        doShift();
        for (int i=0;i<7;i++){
            brickList.add(new Brick(DefaultX+i*SHIFT_X,DefaultY));
        }
    }

    public void doShift() {
        for (Brick brick : brickList) {
            brick.setY(brick.getY()+SHIFT_Y);
        }
    }

    public void checkIntersection(Ball ball){
        if (ball.down()>=870&&ball.down()<890){
            if (ball.right()>paddle.getX()&&ball.left()<paddle.getX()+ paddle.getW()){
                ball.setVelocityY(-ball.getVelocityY());
            }
            return;
        }

        for (Brick brick: brickList){
            if (brick.getType().equals("WINKER")&&!brick.isVisible())continue;
            if (ball.down()>=brick.up()&&ball.up()<=brick.down()&&ball.right()>=brick.left()&&ball.left()<=brick.right()){
                if (!ball.isFired()){
                    int overlapLeft = ball.right() - brick.left();
                    int overlapRight = brick.right() - ball.left();
                    int overlapup = ball.down() - brick.up();
                    int overlapdown = brick.down() - ball.up();

                    int minOverlapX =overlapLeft;
                    if (overlapLeft > overlapRight)minOverlapX=overlapRight;

                    int minOverlapY = overlapdown;
                    if (overlapup< overlapdown)minOverlapY=overlapup;

                    if (minOverlapX < minOverlapY) ball.setVelocityX(-ball.getVelocityX()) ;
                    else ball.setVelocityY (-ball.getVelocityY());
                }
                if (brick.isHitOnce())brickList.remove(brick);
                else {
                    brick.setHitOnce(true);
                    if (!brick.getType().equals("WOODEN")){
                        brickList.remove(brick);
                        if (brick.getType().equals("PRIZEHOLDER")){
                            this.prizeReleased.add(new Prize(brick.getX(),brick.getY()));
                        }
                    }
                }
                score+=4;
                return;
            }
        }
    }

    public void applyPrize(Prize prize){
        score+=4;
        switch (prize.getType()) {
            case "FIREBALL":
                for (Ball ball : this.ballList) {
                    ball.setFired(true);
                    ball.setFireTime(240);
                }
                break;
            case "MULTIBALL":
                int a = ballList.size();
                for (int i = 0; i < a; i++) {
                    Ball ball = ballList.get(i);
                    ballList.add(new Ball(ball.getX(), ball.getY(), -ball.getVelocityX(), ball.getVelocityY(), ball.isFired(), ball.isVeloEd(), ball.getPrizeTime(), ball.getFireTime()));
                    ballList.add(new Ball(ball.getX(), ball.getY(), ball.getVelocityX(), -ball.getVelocityY(), ball.isFired(), ball.isVeloEd(), ball.getPrizeTime(), ball.getFireTime()));
                }
                break;
            case "BIGPADLE":
                paddle.setSize(Size.BIG);
                break;
            case "SMALLPADLE":
                paddle.setSize(Size.SMALL);
                break;
            case "INVERTEDPADLE":
                paddle.setInverted(true);
                paddle.setInvertedTime(240);
                break;
            case "FASTBALL":
                for (Ball ball : this.ballList) {
                    if (ball.isVeloEd()) {
                        if (ball.getVelocity() > 20) {
                            ball.setPrizeTime(240);
                        } else {
                            ball.setVeloEd(false);
                            ball.setPrizeTime(0);
                            ball.setVelocityX(10);
                            ball.setVelocityY(10);
                            ball.setVelocity(ball.getVelocity() * 2);
                        }
                    } else {
                        ball.setVeloEd(true);
                        ball.setVelocityX(20);
                        ball.setVelocityY(20);
                        ball.setVelocity(ball.getVelocity() * 2);
                        ball.setPrizeTime(240);
                    }
                }
                break;
            case "SLOWBALL":
                for (Ball ball : this.ballList) {
                    if (ball.isVeloEd()) {
                        if (ball.getVelocity() < 15) {
                            ball.setPrizeTime(240);
                        } else {
                            ball.setVeloEd(false);
                            ball.setPrizeTime(0);
                            ball.setVelocityX(10);
                            ball.setVelocityY(10);
                            ball.setVelocity(ball.getVelocity() / 2);
                        }
                    } else {
                        ball.setVeloEd(true);
                        ball.setVelocityX(5);
                        ball.setVelocityY(5);
                        ball.setVelocity(ball.getVelocity() /2);
                        ball.setPrizeTime(240);
                    }
                }
                break;
            case "RANDOM":
                applyPrize(new Prize(0, 0, PrizeLevel.getType(new Random().nextInt(7))));
                break;
        }
    }
}