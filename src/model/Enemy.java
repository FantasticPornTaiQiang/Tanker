package model;

import util.TankUtil;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.lang.ref.WeakReference;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 游戏敌人类
 * @Author: 侯京华
 */
public class Enemy extends Objects {

    private int HP;
    private int level;
    private String direction;
    private int tankNum;
    private boolean up, left, right, down, attack;
    private CopyOnWriteArrayList enemyBullets;

    public Enemy(BufferedImage image) {
        super(image);
        tankNum = 1;
        direction = "up";
    }

    public int getHP(){
        return this.HP;
    }

    public boolean setHP(int HP){
        if((this.HP = HP) <= 0 ){
            return false;
        }
        return true;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setTankNum(int tankNum) {
        this.tankNum = tankNum;
    }

    public void findPath(long time, int x, int y) {
        int durX = Math.abs(getXLocation() - x);
        int durY = Math.abs(getYLocation() - y);
        if ((time) % 20 == 0){
            if (getXLocation() < x && durX > durY) {
                setDirection(false, false, false, true);
                direction = "right";
            }
            if (getXLocation() > x && durX > durY) {
                setDirection(false, false, true,false);
                direction = "left";
            }
            if (getYLocation() < y && durX < durY) {
                setDirection(false, true, false,false);
                direction = "down";
            }
            if (getYLocation() > y && durX < durY) {
                setDirection(true, false, false,false);
                direction = "up";
            }
            setImage(TankUtil.getEnemyImage(tankNum, direction));
        }

    }

    public void move() {
        if(right==true){
            setLocation(super.getXLocation() + getSpeed(), super.getYLocation());
            setImage(TankUtil.getEnemyImage(tankNum, direction));
        }
        else if(left==true){
            setLocation(super.getXLocation() - getSpeed(), super.getYLocation());
            setImage(TankUtil.getEnemyImage(tankNum, direction));
        }
        else if(down==true){
            setLocation(super.getXLocation(), super.getYLocation() + getSpeed());
            setImage(TankUtil.getEnemyImage(tankNum, direction));
        }
        else {
            setLocation(super.getXLocation(), super.getYLocation() - getSpeed());
            setImage(TankUtil.getEnemyImage(tankNum, direction));
        }
    }

    public void fire(long time){
        if (time % 1000 == 0)
            attack = true;
        else
            attack = false;
        if (attack){
            WeakReference<Bullet> reference = BulletPool.getBullet();
            reference.get().setDirection(direction);
            reference.get().setDamage(level);
            reference.get().setLocation(super.getXLocation() + super.getWidth()/2, super.getYLocation()+super.getHeight()/2);
            reference.get().setSpeed(15);
            reference.get().setImage(TankUtil.getBulletImage(1, direction));
            enemyBullets.add(reference);
        }
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public void setDirection(boolean up, boolean down, boolean left, boolean right) {
        this.up = up;
        this.down = down;
        this.right = right;
        this.left = left;
    }

    public void setEnemyBullets(CopyOnWriteArrayList enemyBullets) {
        this.enemyBullets = enemyBullets;
    }

    @Override
    public void move(long time) {

    }

    public boolean isUp() {
        return up;
    }

    public boolean isDown() {
        return down;
    }

    public boolean isLeft() {
        return left;
    }



    public boolean isRight() {
        return right;
    }



    public void setRightCrash(boolean right) {
        this.right = right;
    }



    public String getDirection() {
        return direction;
    }
}
