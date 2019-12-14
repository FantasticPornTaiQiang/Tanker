package model;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * 游戏物体超类
 * @Author: 侯京华
 */
public abstract class Objects {
    //基础属性
    private int x;
    private int y;
    private int width;
    private int height;
    private int speed;
    private int HP;
    private BufferedImage image;

    Objects(BufferedImage image){
        this.width = image.getWidth();
        this.height = image.getHeight();
        this.image = image;
    }

    //移动
    public abstract void move(long time);

    //碰撞检测
    public static boolean collision (Objects o1, Objects o2) {
        int o1x = o1.x + o1.width/2;
        int o1y = o1.y + o1.height/2;
        int o2x,o2y;
        if (o2.width > 200 && o2.height > 350 ) {
            o2.width -= 150;
            o2.height -= 150;
        }
        o2x = o2.x + o2.width / 2;
        o2y = o2.y + o2.height / 2;
        boolean H = Math.abs(o1x - o2x) < (o1.width + o2.width)/2;
        boolean V = Math.abs(o1y - o2y) < (o1.height + o2.height)/2;
        return H & V;
    }

    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
        this.width = image.getWidth();
        this.height = image.getHeight();
    }


    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean setHP(int HP) {
        if((this.HP = HP) <= 0){
            this.HP = 0;
            return false;
        }
        return true;
    }


    public int getXLocation(){
        return this.x;
    }

    public int getYLocation(){
        return this.y;
    }

    public int getWidth() { return this.width; }

    public int getHeight() { return this.height; };

    public int getSpeed() {
        return speed;
    }

    public int getHP() {
        return HP;
    }

    //绘制
    public void draw(Graphics g){
        g.drawImage(image, x, y, width, height,null);

    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
