package model;

import util.ImageLoader;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Wall extends Objects {

    public Wall(BufferedImage image) {
        super(image);
    }

    @Override
    public void move(long time) {
        //do nothing
    }
    public void collision(Objects o){
        if(Objects.collision(this,o)){
            if(o instanceof Player) {
                ((Player) o).setDirection(false,false,false,false);
            }
            if(o instanceof Enemy){
                ((Enemy) o).setDirection(false,false,false,false);
            }
        }
    }

}