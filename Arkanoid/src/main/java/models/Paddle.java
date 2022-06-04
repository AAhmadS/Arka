package models;

public class Paddle {
    private int x, y,w,h,xVel=30;
    private boolean inverted;
    private int invertedTime,sizeTime;
    private Size size;

    public Paddle(){
        x=320;
        y=870;
        size=Size.NORMAL;
        w=200;
        h=20;
        inverted=false;
        invertedTime=0;
        sizeTime=0;
    }

    public Paddle(int x,int y, int w, int h,boolean inverted, int invertedTime, int sizeTime, String size) {
        this.w = w;
        this.h = h;
        this.x = x;
        this.y = y;
        this.inverted = inverted;
        this.invertedTime = invertedTime;
        this.sizeTime = sizeTime;
        this.size = Size.valueOf(size);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public boolean isInverted() {
        return inverted;
    }

    public void setInverted(boolean inverted) {
        this.inverted = inverted;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        if (this.size.equals(Size.NORMAL)){
            if (size.equals(Size.NORMAL))return;
            this.size=size;
            w=size.getW();
            if (size.equals(Size.SMALL)){
                x+=size.getW();
                x=Math.min(x,838-w);
            }
            else{
                x-=Size.NORMAL.getW();
                x=Math.max(0,x);
            }
        }
        else if (this.size.equals(Size.BIG)){
            if (size.equals(Size.BIG)){
                sizeTime=120;
                return;
            }
            this.size=Size.NORMAL;
            w=Size.NORMAL.getW();
            this.x+=this.size.getW();
            this.x=Math.min(x,838-w);
        }
        else if (this.size.equals(Size.SMALL)){
            if (size.equals(Size.SMALL)){
                sizeTime=120;
                return;
            }
            this.size=Size.NORMAL;
            w=Size.NORMAL.getW();
            this.x-=Size.SMALL.getW();
            this.x=Math.max(0,x);
        }
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getxVel() {
        return xVel;
    }

    public void setxVel(int xVel) {
        this.xVel = xVel;
    }

    public int getInvertedTime() {
        return invertedTime;
    }

    public void setInvertedTime(int invertedTime) {
        this.invertedTime = invertedTime;
    }

    public int getSizeTime() {
        return sizeTime;
    }

    public void setSizeTime(int sizeTime) {
        this.sizeTime = sizeTime;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public void update() {
        if (inverted){
            invertedTime--;
            if (invertedTime==0)inverted=false;
        }
        if (!size.equals(Size.NORMAL)){
            sizeTime--;
            if (sizeTime==0)setSize(Size.NORMAL);
        }
    }

    public void applyRight(){
        if (inverted){
            this.setX(Math.max(0,this.getX()-this.getxVel()));
        }
       else{
           this.setX(Math.min(840-this.w,this.getX()+this.getxVel()));
        }
    }
    public void applyleft(){
        if (inverted){
            this.setX(Math.min(820-this.w,this.getX()+this.getxVel()));
        }
        else{
            this.setX(Math.max(0,this.getX()-this.getxVel()));
        }
    }
}
enum Size{
    BIG(400),
    NORMAL(200),
    SMALL(100);

    private int w;

    Size(int w){
        this.w=w;
    }

    int getW(){
        return this.w;
    }
}