package model;

import view.GameFrame;

import java.awt.image.BufferedImage;

public class Buff extends Objects{

    private int buffNum;

    Buff(BufferedImage image) {
        super(image);
        buffNum = 1;
    }

    public void getBuff(Player player){
        switch (buffNum){
            case 1:
                player.setLevel(player.getLevel() + 1);
                break;
            case 2:
                player.setHP(player.getHP() + 20);
                break;
            case 3:
                GameFrame.gold += 1000;
            case 4:
                GameFrame.sourceStone += 100;

        }
    }


    @Override
    public void move(long time) {

    }
}
