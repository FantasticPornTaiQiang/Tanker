package model;

import util.TankUtil;

import java.awt.image.BufferedImage;
import java.lang.ref.WeakReference;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 游戏BOSS类
 * @Author: 侯京华
 */

public class Boss extends Objects {

    private int HP;
    private int level;
    private String direction;
    private int tankNum;
    private boolean up, left, right, down, attack;
    private CopyOnWriteArrayList enemyBullets;

    public Boss(BufferedImage image) {
        super(image);
        direction = "up";
        tankNum = 1;
    }


    public int getHP() {
        return this.HP;
    }

    public boolean setHP(int HP) {
        if ((this.HP = HP) <= 0) {
            return false;
        }
        return true;
    }

    public void setTankNum(int tankNum) {
        this.tankNum = tankNum;
    }

    public void move(long time, int x, int y) {
        int durX = Math.abs(getXLocation() - x);
        int durY = Math.abs(getYLocation() - y);
        if (time % 20 == 0) {
            if (getXLocation() < x && durX > durY) {
                setDirection(false, false, false, true);
                setLocation(super.getXLocation() + getSpeed(), super.getYLocation());
                direction = "right";
            }
            if (getXLocation() > x && durX > durY) {
                setDirection(false, false, true, false);
                setLocation(super.getXLocation() - getSpeed(), super.getYLocation());
                direction = "left";
            }
            if (getYLocation() < y && durX < durY) {
                setDirection(false, true, false, false);
                setLocation(super.getXLocation(), super.getYLocation() + getSpeed());
                direction = "down";
            }
            if (getYLocation() > y && durX < durY) {
                setDirection(true, false, false, false);
                setLocation(super.getXLocation(), super.getYLocation() - getSpeed());
                direction = "up";
            }
            setImage(TankUtil.getBossImage(tankNum, direction));
        }

    }


    @Override
    public void move(long time) {

    }


    public void fire(long time) {
        if (time % 1000 == 0)
            attack = true;
        else
            attack = false;
        if (attack) {
            WeakReference<Bullet> reference = BulletPool.getBullet();
            reference.get().setDirection(direction);
            if (direction.equals("up"))
                reference.get().setLocation(super.getXLocation() + super.getWidth() / 2  - 20, super.getYLocation() + super.getHeight() - 80);
            if (direction.equals("left"))
                reference.get().setLocation(super.getXLocation() + super.getWidth() / 2  - 80, super.getYLocation() + super.getHeight()-100);
            if (direction.equals("right"))
                reference.get().setLocation(super.getXLocation() + super.getWidth() / 2  - 20, super.getYLocation() + super.getHeight()-100);
            if (direction.equals("down"))
                reference.get().setLocation(super.getXLocation() + super.getWidth() / 2  - 20, super.getYLocation() + super.getHeight());
            reference.get().setSpeed(15);
            reference.get().setImage(TankUtil.getBulletImage(tankNum+2, direction));
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
}
