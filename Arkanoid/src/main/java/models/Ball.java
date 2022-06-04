package models;

public class Ball {
    private int x,y,r,velocityX,velocityY,velocity;
    private int prizeTime,fireTime;
    private boolean fired,veloEd;
    private boolean removable=false;

    public Ball(int x,int y){
        this.x=x;
        this.y=y;
        r=20;
        velocityX=10;
        velocityY=10;
        velocity= (int) Math.sqrt(200);
        fired=false;
        prizeTime=0;
        fireTime=0;
        veloEd=false;
    }

    public Ball(int x, int y, int velocityX, int velocityY, boolean fired,boolean veloEd, int prizeTime,int fireTime) {
        this.x = x;
        this.y = y;
        this.r=20;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.velocity =(int) Math.sqrt(velocityX*velocityX+velocityY*velocityY);
        this.fired = fired;
        this.fireTime=fireTime;
        this.veloEd=veloEd;
        this.prizeTime=prizeTime;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getR() {
        return r;
    }
    public int getVelocityX() {
        return velocityX;
    }
    public int getVelocityY() {
        return velocityY;
    }
    public int getVelocity() {
        return velocity;
    }
    public boolean isFired() {
        return fired;
    }
    public int getPrizeTime() {
        return prizeTime;
    }
    public int getFireTime() {
        return fireTime;
    }
    public boolean isVeloEd() {
        return veloEd;
    }
    public boolean isRemovable() {
        return removable;
    }

    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void setR(int r) {
        this.r = r;
    }
    public void setVelocityX(int velocityX) {
        this.velocityX = velocityX;
    }
    public void setVelocityY(int velocityY) {
        this.velocityY = velocityY;
    }
    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }
    public void setFired(boolean fired) {
        this.fired = fired;
    }
    public void setPrizeTime(int prizeTime) {
        this.prizeTime = prizeTime;
    }
    public void setFireTime(int fireTime) {
        this.fireTime = fireTime;
    }
    public void setVeloEd(boolean veloEd) {
        this.veloEd = veloEd;
    }
    public void setRemovable(boolean removable) {
        this.removable = removable;
    }

    public void update(GameState gameState) {
        x += velocityX;
        y += velocityY;

        if (veloEd){
            prizeTime--;
            if (prizeTime==0){
                veloEd=false;
                if (Math.abs(velocityX)>10){
                    velocity/=2;
                    velocityY/=2;
                    velocityX/=2;
                }
                else {
                    velocityX*=2;
                    velocityY*=2;
                    velocity*=2;
                }
            }
        }
        if (fired){
            fireTime--;
            if (fireTime==0){
                fired=false;
            }
        }

        if (x <= 0||x+r >= 840) velocityX =-velocityX ;
        if (y <= 0) velocityY = -velocityY;
        else if (y+r >= 960) {
            velocityY = -velocityY;
            this.removable=true;
        }
    }

    public int down(){
        return y+r;
    }
    public int up(){
        return y;
    }
    public int left(){
        return x;
    }
    public int right(){
        return x+r;
    }
}
