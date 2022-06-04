package models;


import java.util.Random;

public class Prize {
    private static Random random = new Random();
    private int x, y, r = 20, velocityY = 5;
    private PrizeLevel type;
    private boolean removable=false;

    public Prize(int x, int y) {
        this.x = x;
        this.y = y;
        type = PrizeLevel.getType(random.nextInt(8));
    }

    public Prize(int x, int y, String type) {
        this.x = x;
        this.y = y;
        this.type = PrizeLevel.valueOf(type);
    }

    public Prize(int x, int y, PrizeLevel type) {
        this.x = x;
        this.y = y;
        this.type = type;
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

    public int getVelocityY() {
        return velocityY;
    }

    public String getType() {
        return type.name();
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

    public void setVelocityY(int velocityY) {
        this.velocityY = velocityY;
    }

    public void setType(PrizeLevel type) {
        this.type = type;
    }

    public int down() {
        return y + r;
    }

    public int up() {
        return y;
    }

    public int left() {
        return x;
    }

    public int right() {
        return x + r;
    }

    public void update(Paddle paddle, GameState gameState) {
        y += velocityY;
        if (this.down() >= 870 && this.down() < 890) {
            if (this.right() > paddle.getX() && this.left() < paddle.getX() + paddle.getW()) {
                gameState.applyPrize(this);
                this.setRemovable(true);
            }
        }
    }

    public boolean isRemovable() {
        return removable;
    }

    public void setRemovable(boolean removable) {
        this.removable = removable;
    }
}
enum PrizeLevel {
    FIREBALL,
    MULTIBALL,
    BIGPADLE,
    SMALLPADLE,
    INVERTEDPADLE,
    FASTBALL,
    SLOWBALL,
    RANDOM;

    public static PrizeLevel getType(int i){
        switch (i){
            case 0:
                return FIREBALL;
            case 1:
                return MULTIBALL;
            case 2:
                return BIGPADLE;
            case 3:
                return SMALLPADLE;
            case 4:
                return INVERTEDPADLE;
            case 5:
                return FASTBALL;
            case 6:
                return SLOWBALL;
            case 7:
                return RANDOM;
        }
        return null;
    }

}
