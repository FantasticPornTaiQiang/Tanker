package util;

import java.awt.image.BufferedImage;

public class TankUtil {


    public static BufferedImage getTankImage(String name){
        switch (name){
            case "小蓝车":
                return ImageLoader.blue1;
            case "中蓝车":
                return ImageLoader.blue2;
            case "大蓝车":
                return ImageLoader.blue3;
            case "蓝车":
                return ImageLoader.blue4;
            case "苏婆蓝车":
                return ImageLoader.blueSuper;
            case "马克思蓝车":
                return ImageLoader.blueMax;
            case "达克蓝车":
                return ImageLoader.blueDark;
            case "φ尔蓝车":
                return ImageLoader.blueFire;
        }
        return ImageLoader.blue1;
    }

    public static BufferedImage getTankImage(int tankNum, String dir){
        switch (tankNum){
            case 1:
                if (dir.equals("up")){
                    return ImageLoader.blue1;
                }else if (dir.equals("right")){
                    return ImageLoader.blue1Right;
                }else if (dir.equals("down")){
                    return ImageLoader.blue1Down;
                }else if (dir.equals("left")){
                    return ImageLoader.blue1Left;
                }else {
                    return ImageLoader.blue1;
                }
            case 2:
                if (dir.equals("up")){
                    return ImageLoader.blue2;
                }else if (dir.equals("right")){
                    return ImageLoader.blue2Right;
                }else if (dir.equals("down")){
                    return ImageLoader.blue2Down;
                }else if (dir.equals("left")){
                    return ImageLoader.blue2Left;
                }else {
                    return ImageLoader.blue2;
                }
            case 3:
                if (dir.equals("up")){
                    return ImageLoader.blue3;
                }else if (dir.equals("right")){
                    return ImageLoader.blue3Right;
                }else if (dir.equals("down")){
                    return ImageLoader.blue3Down;
                }else if (dir.equals("left")){
                    return ImageLoader.blue3Left;
                }else {
                    return ImageLoader.blue3;
                }
            case 4:
                if (dir.equals("up")){
                    return ImageLoader.blue4;
                }else if (dir.equals("right")){
                    return ImageLoader.blue4Right;
                }else if (dir.equals("down")){
                    return ImageLoader.blue4Down;
                }else if (dir.equals("left")){
                    return ImageLoader.blue4Left;
                }else {
                    return ImageLoader.blue4;
                }
            case 5:
                if (dir.equals("up")){
                    return ImageLoader.blueSuper;
                }else if (dir.equals("right")){
                    return ImageLoader.blueSuperRight;
                }else if (dir.equals("down")){
                    return ImageLoader.blueSuperDown;
                }else if (dir.equals("left")){
                    return ImageLoader.blueSuperLeft;
                }else {
                    return ImageLoader.blueSuper;
                }
            case 6:
                if (dir.equals("up")){
                    return ImageLoader.blueMax;
                }else if (dir.equals("right")){
                    return ImageLoader.blueMaxRight;
                }else if (dir.equals("down")){
                    return ImageLoader.blueMaxDown;
                }else if (dir.equals("left")){
                    return ImageLoader.blueMaxLeft;
                }else {
                    return ImageLoader.blueMax;
                }
            case 7:
                if (dir.equals("up")){
                    return ImageLoader.blueDark;
                }else if (dir.equals("right")){
                    return ImageLoader.blueDarkRight;
                }else if (dir.equals("down")){
                    return ImageLoader.blueDarkDown;
                }else if (dir.equals("left")){
                    return ImageLoader.blueDarkLeft;
                }else {
                    return ImageLoader.blueDark;
                }
            case 8:
                if (dir.equals("up")){
                    return ImageLoader.blueFire;
                }else if (dir.equals("right")){
                    return ImageLoader.blueFireRight;
                }else if (dir.equals("down")){
                    return ImageLoader.blueFireDown;
                }else if (dir.equals("left")){
                    return ImageLoader.blueFireLeft;
                }else {
                    return ImageLoader.blueFire;
                }
        }
        return ImageLoader.blue1;
    }

    public static BufferedImage getEnemyImage(int tankNum, String dir){
        switch (tankNum){
            case 1:
                if (dir.equals("up")){
                    return ImageLoader.enemyRed1;
                }else if (dir.equals("right")){
                    return ImageLoader.enemyRed1Right;
                }else if (dir.equals("down")){
                    return ImageLoader.enemyRed1Down;
                }else if (dir.equals("left")){
                    return ImageLoader.enemyRed1Left;
                }
            case 2:
                if (dir.equals("up")){
                    return ImageLoader.enemyRed2;
                }else if (dir.equals("right")){
                    return ImageLoader.enemyRed2Right;
                }else if (dir.equals("down")){
                    return ImageLoader.enemyRed2Down;
                }else if (dir.equals("left")){
                    return ImageLoader.enemyRed2Left;
                }
            case 3:
                if (dir.equals("up")){
                    return ImageLoader.enemyYellow;
                }else if (dir.equals("right")){
                    return ImageLoader.enemyYellowRight;
                }else if (dir.equals("down")){
                    return ImageLoader.enemyYellowDown;
                }else if (dir.equals("left")){
                    return ImageLoader.enemyYellowLeft;
                }
        }
        return ImageLoader.enemyRed1;
    }

    public static BufferedImage getBossImage(int tankNum, String dir){
        switch (tankNum){
            case 1:
                if (dir.equals("up")){
                    return ImageLoader.boss1;
                }else if (dir.equals("right")){
                    return ImageLoader.boss1Right;
                }else if (dir.equals("down")){
                    return ImageLoader.boss1Down;
                }else if (dir.equals("left")){
                    return ImageLoader.boss1Left;
                }
            case 2:
                if (dir.equals("up")){
                    return ImageLoader.boss2;
                }else if (dir.equals("right")){
                    return ImageLoader.boss2Right;
                }else if (dir.equals("down")){
                    return ImageLoader.boss2Down;
                }else if (dir.equals("left")){
                    return ImageLoader.boss2Left;
                }
            case 3:
                if (dir.equals("up")){
                    return ImageLoader.boss3;
                }else if (dir.equals("right")){
                    return ImageLoader.boss3Right;
                }else if (dir.equals("down")){
                    return ImageLoader.boss3Down;
                }else if (dir.equals("left")){
                    return ImageLoader.boss3Left;
                }
            case 4:
                if (dir.equals("up")){
                    return ImageLoader.boss4;
                }else if (dir.equals("right")){
                    return ImageLoader.boss4Right;
                }else if (dir.equals("down")){
                    return ImageLoader.boss4Down;
                }else if (dir.equals("left")){
                    return ImageLoader.boss4Left;
                }
        }
        return ImageLoader.boss1;
    }

    public static BufferedImage getBulletImage(int bulletNum, String dir){

        switch (bulletNum){

            case 1:
                if (dir.equals("up")) {
                    return ImageLoader.bulletSmallBlue;
                }else if (dir.equals("right")){
                    return ImageLoader.bulletSmallBlueRight;
                }else if (dir.equals("down")){
                    return ImageLoader.bulletSmallBlueDown;
                }else if (dir.equals("left")){
                    return ImageLoader.bulletSmallBlueLeft;
                }
            case 2:
                if (dir.equals("up")) {
                    return ImageLoader.bulletSmallRed;
                }else if (dir.equals("right")){
                    return ImageLoader.bulletSmallRedRight;
                }else if (dir.equals("down")){
                    return ImageLoader.bulletSmallRedDown;
                }else if (dir.equals("left")){
                    return ImageLoader.bulletSmallRedLeft;
                }
            case 3:
                if (dir.equals("up")) {
                    return ImageLoader.bulletSmallYellow;
                }else if (dir.equals("right")){
                    return ImageLoader.bulletSmallYellowRight;
                }else if (dir.equals("down")){
                    return ImageLoader.bulletSmallYellowDown;
                }else if (dir.equals("left")){
                    return ImageLoader.bulletSmallYellowLeft;
                }
            case 4:
                if (dir.equals("up")) {
                    return ImageLoader.bulletMidRed;
                }else if (dir.equals("right")){
                    return ImageLoader.bulletMidRedRight;
                }else if (dir.equals("down")){
                    return ImageLoader.bulletMidRedDown;
                }else if (dir.equals("left")){
                    return ImageLoader.bulletMidRedLeft;
                }
            case 5:
                if (dir.equals("up")) {
                    return ImageLoader.bulletBigYellow;
                }else if (dir.equals("right")){
                    return ImageLoader.bulletBigYellowRight;
                }else if (dir.equals("down")){
                    return ImageLoader.bulletBigYellowDown;
                }else if (dir.equals("left")){
                    return ImageLoader.bulletBigYellowLeft;
                }
            case 6:
                if (dir.equals("up")) {
                    return ImageLoader.bulletBigRed;
                }else if (dir.equals("right")){
                    return ImageLoader.bulletBigRedRight;
                }else if (dir.equals("down")){
                    return ImageLoader.bulletBigRedDown;
                }else if (dir.equals("left")){
                    return ImageLoader.bulletBigRedLeft;
                }
        }
        return ImageLoader.bulletSmallBlue;

    }


}
