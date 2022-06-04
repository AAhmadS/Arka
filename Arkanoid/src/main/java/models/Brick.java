package models;

import java.util.Random;

public class Brick {
    private static Random random=new Random();
    private int x,y,w=90,h=45;
    private boolean visible=true;
    private boolean broken;
    private BrickType type;
    private boolean hitOnce;
    private int timeLeft;

    public Brick(int x,int y){
        this.x = x;
        this.y = y;
        type=BrickType.get(random.nextInt(5));
        if (type.equals(BrickType.INVISIBLE))visible=false;
        broken=false;
        hitOnce=false;
        if (type.equals(BrickType.WINKER))timeLeft=60;
    }
    public Brick(int x, int y, boolean visible, boolean broken, boolean hitOnce, int timeLeft,String type) {
        this.x = x;
        this.y = y;
        this.visible = visible;
        this.broken = broken;
        this.hitOnce=hitOnce;
        this.type=BrickType.valueOf(type);
        this.timeLeft=timeLeft;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isBroken() {
        return broken;
    }
    
    public void setBroken(boolean broken) {
        this.broken = broken;
    }

    public String getType() {
        return type.name();
    }

    public void setType(BrickType type) {
        this.type = type;
    }

    public boolean isHitOnce() {
        return hitOnce;
    }

    public void setHitOnce(boolean hitOnce) {
        this.hitOnce = hitOnce;
    }

    public int getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(int timeLeft) {
        this.timeLeft = timeLeft;
    }

    public int down(){
        return y+h;
    }
    public int up(){
        return y;
    }
    public int left(){
        return x;
    }
    public int right(){
        return x+w;
    }

    public void update(){
        if (type.equals(BrickType.WINKER)){
            if (timeLeft==0){
                visible=!visible;
                timeLeft=300;
            }
            else{
                timeLeft--;
            }
        }
    }

}
enum BrickType {
    GLASS,
    INVISIBLE,
    PRIZEHOLDER,
    WINKER,
    WOODEN;

    public static BrickType get(int i){
        switch (i){
            case 0:
                return GLASS;
            case 1:
                return PRIZEHOLDER;
            case 2:
                return INVISIBLE;
            case 3:
                return WINKER;
            case 4:
                return WOODEN;
        }
        return null;
    }
}
