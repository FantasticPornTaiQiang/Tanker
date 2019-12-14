package model;

import util.TankUtil;

import java.awt.image.BufferedImage;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 游戏玩家类
 * @Author: 侯京华
 */
public class Player extends Objects {


    private int MP;
    private int level;
    private int tankNum = 1;
    private boolean up, left, right, down, attack;
    private String direction;
    private boolean upCrash,downCrash,leftCrash,rightCrash;
    private int beforeCrashDirection;

    private String name;
    private CopyOnWriteArrayList<WeakReference<Bullet>> playerBullets;

    public Player(BufferedImage image) {
        super(image);
        this.name = "太强";
        direction = "up";
    }


    @Override
    public void move(long time) {
        if (up && !isUpOut()) {
            direction = "up";
            this.setLocation(super.getXLocation(), super.getYLocation() - this.getSpeed());
            super.setImage(TankUtil.getTankImage(tankNum, "up"));
        }
        if (down && !isDownOut()) {
            direction = "down";
            this.setLocation(super.getXLocation(), super.getYLocation() + this.getSpeed());
            super.setImage(TankUtil.getTankImage(tankNum, "down"));
        }
        if (left && !isLeftOut()) {
            direction = "left";
            this.setLocation(super.getXLocation() - this.getSpeed(), super.getYLocation());
            super.setImage(TankUtil.getTankImage(tankNum, "left"));
        }
        if (right && !isRightOut()) {
            direction = "right";
            this.setLocation(super.getXLocation() + this.getSpeed(), super.getYLocation());
            super.setImage(TankUtil.getTankImage(tankNum, "right"));
        }
    }

    public void setPlayerBullets(CopyOnWriteArrayList<WeakReference<Bullet>> playerBullets) {
        this.playerBullets = playerBullets;
    }

    public void fire(){
        if (attack){
            WeakReference<Bullet> reference = BulletPool.getBullet();
            reference.get().setDirection(direction);
            reference.get().setLocation(super.getXLocation() + super.getWidth()/2 - 20, super.getYLocation()+super.getHeight()/2);
            reference.get().setSpeed(15);
            reference.get().setDamage(level * 5);
            if (tankNum <= 4) {
                reference.get().setImage(TankUtil.getBulletImage(1, direction));
            } else {
                reference.get().setImage(TankUtil.getBulletImage(4, direction));
            }
            playerBullets.add(reference);
        }
    }
    public void setName(String name){this.name = name;}

    public String getName(){return this.name;}

    public void setAttack(boolean attack) {
        this.attack = attack;
    }

    public void setMP(int MP) {
        this.MP = MP;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setTankNum(int tankNum) {
        this.tankNum = tankNum;
    }

    public int getMP() {
        return MP;
    }

    public int getLevel() {
        return level;
    }


    public void setDirection(boolean up, boolean down, boolean left, boolean right) {
        this.up = up;
        this.down = down;
        this.right = right;
        this.left = left;
    }

    public int getBeforeCrashDirection() {
        return beforeCrashDirection;
    }

    public void setBeforeCrashDirection(int beforeCrashDirection) {
        this.beforeCrashDirection = beforeCrashDirection;
    }
    public void setCrashDirection(boolean leftCrash,boolean rightCrash,boolean upCrash,boolean downCrash){
        this.leftCrash = leftCrash;
        this.rightCrash = rightCrash;
        this.upCrash = upCrash;
        this.downCrash =  downCrash;
    }
    public boolean getUpCrash(){
        return upCrash;
    }
    public boolean getDownCrash(){
        return downCrash;
    }
    public boolean getRightCrash(){
        return rightCrash;
    }
    public boolean getLeftCrash(){
        return leftCrash;
    }


    public boolean isLeftOut(){
        return getXLocation() <= 10;
    }

    public boolean isUpOut(){
        return getYLocation() <= 0;
    }

    public boolean isDownOut(){
        return getYLocation() + getHeight() >= 650;
    }

    public boolean isRightOut(){
        return getXLocation() + getWidth() >= 950;
    }

    public void setLeftCrash(boolean leftCrash) {
        this.leftCrash = leftCrash;
    }

    public void setRightCrash(boolean rightCrash) {
        this.rightCrash = rightCrash;
    }

    public void setDownCrash(boolean downCrash) {
        this.downCrash = downCrash;
    }

    public void setUpCrash(boolean upCrash) {
        this.upCrash = upCrash;
    }
}
