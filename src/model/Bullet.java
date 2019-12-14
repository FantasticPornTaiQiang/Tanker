package model;

import model.Objects;
import util.ImageLoader;

import java.awt.image.BufferedImage;
import java.util.List;

/**
 * 子弹类
 * @Author: 谢宇弢
 */
public class Bullet extends Objects{

    private int damage;
    private String direction;

    Bullet(BufferedImage image) {
        super(image);
    }

    @Override
    public void move(long time) {
        switch (direction){
            case "up":
                this.setLocation(super.getXLocation(), super.getYLocation() - this.getSpeed());
                break;
            case "right":
                this.setLocation(super.getXLocation() + this.getSpeed(), super.getYLocation());
                break;
            case "down":
                this.setLocation(super.getXLocation(), super.getYLocation() + this.getSpeed());
                break;
            case "left":
                this.setLocation(super.getXLocation() - this.getSpeed(), super.getYLocation());
                break;
        }
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }


    public boolean isOutBoundary(){
        if (getXLocation() > 1000 || getXLocation() < 0 || getYLocation() < 0 || getYLocation() > 1000)
            return true;
        return false;
    }

    public boolean isHit(Objects o){

        if(super.collision(this, o)){
            o.setHP(o.getHP() - damage);
            return true;
        }
        return false;
    }

}
