package graphic.Panels;

import graphic.GameLoop;
import graphic.MainFrame;
import models.Ball;
import models.Brick;
import models.GameState;
import models.Prize;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements KeyListener {
    private static String ADDRESS="./src/main/resources/graph/";
    private GameState gameState;
    private Image glass,prizeH,winker,wooden,woodenHit,ball,fire,paddle,slow,fast,inverting,firing,big,small,random,mult,backGround;
    private GameLoop gameLoop;
    public GamePanel(GameState gameState){
        glass=new ImageIcon(ADDRESS+"glass.png").getImage();
        prizeH=new ImageIcon(ADDRESS+"prizeH.png").getImage();
        winker=new ImageIcon(ADDRESS+"winker.png").getImage();
        wooden=new ImageIcon(ADDRESS+"wooden.png").getImage();
        woodenHit=new ImageIcon(ADDRESS+"woodenHit.png").getImage();
        ball=new ImageIcon(ADDRESS+"ball.png").getImage();
        fire=new ImageIcon(ADDRESS+"fire.png").getImage();
        paddle=new ImageIcon(ADDRESS+"paddle.png").getImage();
        slow=new ImageIcon(ADDRESS+"slow.png").getImage();
        fast=new ImageIcon(ADDRESS+"fast.png").getImage();
        inverting=new ImageIcon(ADDRESS+"inverting.png").getImage();
        firing=new ImageIcon(ADDRESS+"firing.png").getImage();
        big=new ImageIcon(ADDRESS+"big.png").getImage();
        small=new ImageIcon(ADDRESS+"small.png").getImage();
        random=new ImageIcon(ADDRESS+"random.png").getImage();
        mult=new ImageIcon(ADDRESS+"mult.png").getImage();
        backGround=new ImageIcon(ADDRESS+"backGround.png").getImage();

        this.gameState=gameState;

        this.setBounds(0,0,840,960);
        this.setBackground(new Color(00,00,00));
        this.addKeyListener(this);
        this.setVisible(true);
        this.setLayout(new BorderLayout());
        gameLoop=new GameLoop(60,MainFrame.getMainFrame().getGraphicalAgent());
        gameLoop.start();

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D gtd= (Graphics2D) g;
        gtd.drawImage(backGround,0,0,840,960,null);
        for (Brick brick: gameState.getBrickList()) {
            switch (brick.getType()){

                case "GLASS":
                    gtd.drawImage(glass,brick.getX(),brick.getY(),brick.getW(),brick.getH(),null);
                    break;
                case "INVISIBLE":
                    break;
                case "PRIZEHOLDER":
                    gtd.drawImage(prizeH,brick.getX(),brick.getY(),brick.getW(),brick.getH(),null);
                    break;
                case "WINKER":
                    if (brick.isVisible())gtd.drawImage(winker,brick.getX(),brick.getY(),brick.getW(),brick.getH(),null);
                    break;
                case "WOODEN":
                    if (brick.isHitOnce()){
                        gtd.drawImage(woodenHit,brick.getX(),brick.getY(),brick.getW(),brick.getH(),null);
                    }
                    else{
                        gtd.drawImage(wooden,brick.getX(),brick.getY(),brick.getW(),brick.getH(),null);
                    }
                    break;
            }
        }
        for (Ball ball: gameState.getBallList()) {
            if (ball.isFired()){
                gtd.drawImage(fire,ball.getX(),ball.getY(),ball.getR(),ball.getR(),null);
            }
            else {
                gtd.drawImage(this.ball,ball.getX(),ball.getY(),ball.getR(),ball.getR(),null);
            }
        }
        for (Prize prize : gameState.getPrizeReleased()) {
            switch (prize.getType()){
                case "FIREBALL":
                    gtd.drawImage(firing,prize.getX(),prize.getY(),prize.getR(),prize.getR(),null);
                    break;
                case "MULTIBALL":
                    gtd.drawImage(mult,prize.getX(),prize.getY(),prize.getR(),prize.getR(),null);
                    break;
                case "BIGPADLE":
                    gtd.drawImage(big,prize.getX(),prize.getY(),prize.getR(),prize.getR(),null);
                    break;
                case "SMALLPADLE":
                    gtd.drawImage(small,prize.getX(),prize.getY(),prize.getR(),prize.getR(),null);
                    break;
                case "INVERTEDPADLE":
                    gtd.drawImage(inverting,prize.getX(),prize.getY(),prize.getR(),prize.getR(),null);
                    break;
                case "FASTBALL":
                    gtd.drawImage(fast,prize.getX(),prize.getY(),prize.getR(),prize.getR(),null);
                    break;
                case "SLOWBALL":
                    gtd.drawImage(slow,prize.getX(),prize.getY(),prize.getR(),prize.getR(),null);
                    break;
                case "RANDOM":
                    gtd.drawImage(random,prize.getX(),prize.getY(),prize.getR(),prize.getR(),null);
                    break;
            }
        }
        gtd.drawImage(paddle,gameState.getPaddle().getX(),gameState.getPaddle().getY(),gameState.getPaddle().getW(),gameState.getPaddle().getH(),null);
        gtd.setFont(new Font("Arial",Font.PLAIN,10));
        gtd.setColor(Color.WHITE);
        gtd.drawString("hp: "+gameState.getHp()+",,,score: "+gameState.getScore(),20,900);
        gtd.drawString("press s for save,p for pause/unpause,b for back",600,900);
        this.setFocusable(true);
        this.requestFocusInWindow();
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar()=='s'){
            gameState.setPaused(true);
            gameLoop.stop();
            MainFrame.getMainFrame().doAction("Save");
            gameLoop=new GameLoop(60,MainFrame.getMainFrame().getGraphicalAgent());
            gameLoop.start();
        }
        else if (e.getKeyChar()=='p'){
            gameState.setPaused(!gameState.isPaused());
        }
        else if(e.getKeyChar()=='b'){
            gameState.setPaused(true);
            gameLoop.stop();
            MainFrame.getMainFrame().doAction("Back");
        }
        else{
            if (gameState.isPaused())return;
            if (e.getKeyCode()==39){
                gameState.getPaddle().applyRight();
            }
            if (e.getKeyCode()==37){
                gameState.getPaddle().applyleft();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) { }

    public GameLoop getGameLoop() {
        return gameLoop;
    }
}
