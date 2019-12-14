package util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 图片导入
 *  @Author: 侯京华
 */

public class ImageLoader {
    //游戏地图
    public static BufferedImage map1;
    public static BufferedImage map2;
    public static BufferedImage map3;
    public static BufferedImage map4;
    //游戏图标
    public static BufferedImage icon;
    //BOSS
    public static BufferedImage boss1;
    public static BufferedImage boss1Right;
    public static BufferedImage boss1Down;
    public static BufferedImage boss1Left;
    public static BufferedImage boss2;
    public static BufferedImage boss2Right;
    public static BufferedImage boss2Down;
    public static BufferedImage boss2Left;
    public static BufferedImage boss3;
    public static BufferedImage boss3Right;
    public static BufferedImage boss3Down;
    public static BufferedImage boss3Left;
    public static BufferedImage boss4;
    public static BufferedImage boss4Right;
    public static BufferedImage boss4Down;
    public static BufferedImage boss4Left;

    //玩家
    public static BufferedImage blue1;
    public static BufferedImage blue1Right;
    public static BufferedImage blue1Down;
    public static BufferedImage blue1Left;
    public static BufferedImage blue2;
    public static BufferedImage blue2Right;
    public static BufferedImage blue2Down;
    public static BufferedImage blue2Left;
    public static BufferedImage blue3;
    public static BufferedImage blue3Right;
    public static BufferedImage blue3Down;
    public static BufferedImage blue3Left;
    public static BufferedImage blue4;
    public static BufferedImage blue4Right;
    public static BufferedImage blue4Down;
    public static BufferedImage blue4Left;
    public static BufferedImage blueSuper;
    public static BufferedImage blueSuperRight;
    public static BufferedImage blueSuperDown;
    public static BufferedImage blueSuperLeft;
    public static BufferedImage blueMax;
    public static BufferedImage blueMaxRight;
    public static BufferedImage blueMaxDown;
    public static BufferedImage blueMaxLeft;
    public static BufferedImage blueDark;
    public static BufferedImage blueDarkRight;
    public static BufferedImage blueDarkDown;
    public static BufferedImage blueDarkLeft;
    public static BufferedImage blueFire;
    public static BufferedImage blueFireRight;
    public static BufferedImage blueFireDown;
    public static BufferedImage blueFireLeft;
    //敌人
    public static BufferedImage enemyRed1;
    public static BufferedImage enemyRed1Right;
    public static BufferedImage enemyRed1Down;
    public static BufferedImage enemyRed1Left;
    public static BufferedImage enemyRed2;
    public static BufferedImage enemyRed2Right;
    public static BufferedImage enemyRed2Down;
    public static BufferedImage enemyRed2Left;
    public static BufferedImage enemyYellow;
    public static BufferedImage enemyYellowRight;
    public static BufferedImage enemyYellowDown;
    public static BufferedImage enemyYellowLeft;
    //子弹
    public static BufferedImage bulletBigRed;
    public static BufferedImage bulletBigRedRight;
    public static BufferedImage bulletBigRedDown;
    public static BufferedImage bulletBigRedLeft;
    public static BufferedImage bulletBigYellow;
    public static BufferedImage bulletBigYellowRight;
    public static BufferedImage bulletBigYellowDown;
    public static BufferedImage bulletBigYellowLeft;
    public static BufferedImage bulletSmallYellow;
    public static BufferedImage bulletSmallYellowRight;
    public static BufferedImage bulletSmallYellowDown;
    public static BufferedImage bulletSmallYellowLeft;
    public static BufferedImage bulletSmallBlue;
    public static BufferedImage bulletSmallBlueRight;
    public static BufferedImage bulletSmallBlueDown;
    public static BufferedImage bulletSmallBlueLeft;
    public static BufferedImage bulletSmallRed;
    public static BufferedImage bulletSmallRedRight;
    public static BufferedImage bulletSmallRedDown;
    public static BufferedImage bulletSmallRedLeft;
    public static BufferedImage bulletMidRed;
    public static BufferedImage bulletMidRedRight;
    public static BufferedImage bulletMidRedDown;
    public static BufferedImage bulletMidRedLeft;

    public static BufferedImage bang1;
    public static BufferedImage bang2;
    public static BufferedImage bang3;
    public static BufferedImage bang4;
    public static BufferedImage bang5;
    public static BufferedImage bang6;
    public static BufferedImage bang7;
    public static BufferedImage bang8;
    public static BufferedImage bang9;
    public static BufferedImage bang10;
    //buff
    public static BufferedImage buff1;
    public static BufferedImage buff2;
    public static BufferedImage buff3;
    public static BufferedImage lv;
    //其它
    public static BufferedImage magicCircle;
    public static BufferedImage zfb;

    //障碍物
    public static BufferedImage stone1;
    public static BufferedImage volcano;
    public static BufferedImage iceberg;

    public static BufferedImage warning;

    public static BufferedImage grass1;
    public static BufferedImage grass2;
    public static BufferedImage tankHome;
    public static BufferedImage victory;
    public static BufferedImage lose;

    static {

        buff1 = loadImage("buff1");
        buff2 = loadImage("buff2");
        buff3 = loadImage("buff3");
        lv = loadImage("lv");
        bang1 = loadImage("bang1");
        bang2 = loadImage("bang2");
        bang3 = loadImage("bang3");
        bang4 = loadImage("bang4");
        bang5 = loadImage("bang5");
        bang6 = loadImage("bang6");
        bang7 = loadImage("bang7");
        bang8 = loadImage("bang8");
        bang9 = loadImage("bang9");
        bang10 = loadImage("bang10");

        boss1 = loadImage("boss1");
        boss1Right = loadImage("boss1-90");
        boss1Down = loadImage("boss1-180");
        boss1Left = loadImage("boss1-270");
        boss2 = loadImage("boss2");
        boss2Right = loadImage("boss2-90");
        boss2Down = loadImage("boss2-180");
        boss2Left = loadImage("boss2-270");
        boss3 = loadImage("boss3");
        boss3Right = loadImage("boss3-90");
        boss3Down = loadImage("boss3-180");
        boss3Left = loadImage("boss3-270");
        boss4 = loadImage("boss4");
        boss4Right = loadImage("boss4-90");
        boss4Down = loadImage("boss4-180");
        boss4Left = loadImage("boss4-270");

        map1 = loadImage("map1");
        map2 = loadImage("map2");
        map3 = loadImage("map3");
        map4 = loadImage("map4");

        blue1 = loadImage("tank_blue_1");
        blue1Right = loadImage("tank_blue_1-90");
        blue1Down = loadImage("tank_blue_1-180");
        blue1Left = loadImage("tank_blue_1-270");
        blue2 = loadImage("tank_blue_2");
        blue2Right = loadImage("tank_blue_2-90");
        blue2Down = loadImage("tank_blue_2-180");
        blue2Left = loadImage("tank_blue_2-270");
        blue3 = loadImage("tank_blue_3");
        blue3Right = loadImage("tank_blue_3-90");
        blue3Down = loadImage("tank_blue_3-180");
        blue3Left = loadImage("tank_blue_3-270");
        blue4 = loadImage("tank_blue_4");
        blue4Right = loadImage("tank_blue_4-90");
        blue4Down = loadImage("tank_blue_4-180");
        blue4Left = loadImage("tank_blue_4-270");
        blueSuper = loadImage("tank_blue_4_super");
        blueSuperRight = loadImage("tank_blue_4_super-90");
        blueSuperDown = loadImage("tank_blue_4_super-180");
        blueSuperLeft = loadImage("tank_blue_4_super-270");
        blueMax = loadImage("tank_blue_4_max");
        blueMaxRight = loadImage("tank_blue_4_max-90");
        blueMaxDown = loadImage("tank_blue_4_max-180");
        blueMaxLeft = loadImage("tank_blue_4_max-270");
        blueDark = loadImage("tank_blue_4_dark");
        blueDarkRight = loadImage("tank_blue_4_dark-90");
        blueDarkDown = loadImage("tank_blue_4_dark-180");
        blueDarkLeft = loadImage("tank_blue_4_dark-270");
        blueFire = loadImage("tank_blue_4_fire");
        blueFireRight = loadImage("tank_blue_4_fire-90");
        blueFireDown = loadImage("tank_blue_4_fire-180");
        blueFireLeft = loadImage("tank_blue_4_fire-270");
        enemyRed1 = loadImage("tank_enemy_red_1");
        enemyRed1Right = loadImage("tank_enemy_red_1-90");
        enemyRed1Down = loadImage("tank_enemy_red_1-180");
        enemyRed1Left = loadImage("tank_enemy_red_1-270");
        enemyRed2 = loadImage("tank_enemy_red_2");
        enemyRed2Right = loadImage("tank_enemy_red_2-90");
        enemyRed2Down = loadImage("tank_enemy_red_2-180");
        enemyRed2Left = loadImage("tank_enemy_red_2-270");
        enemyYellow = loadImage("tank_enemy_yellow_1");
        enemyYellowRight = loadImage("tank_enemy_yellow_1-90");
        enemyYellowDown = loadImage("tank_enemy_yellow_1-180");
        enemyYellowLeft = loadImage("tank_enemy_yellow_1-270");

        bulletBigRed = loadImage("bullet_big_red");
        bulletBigRedRight = loadImage("bullet_big_red-90");
        bulletBigRedDown = loadImage("bullet_big_red-180");
        bulletBigRedLeft = loadImage("bullet_big_red-270");
        bulletBigYellow = loadImage("bullet_big_yellow");
        bulletBigYellowRight = loadImage("bullet_big_yellow-90");
        bulletBigYellowDown = loadImage("bullet_big_yellow-180");
        bulletBigYellowLeft = loadImage("bullet_big_yellow-270");
        bulletMidRed = loadImage("bullet_mid_red");
        bulletMidRedRight = loadImage("bullet_mid_red-90");
        bulletMidRedDown = loadImage("bullet_mid_red-180");
        bulletMidRedLeft = loadImage("bullet_mid_red-270");
        bulletSmallRed = loadImage("bullet_small_red");
        bulletSmallRedRight = loadImage("bullet_small_red-90");
        bulletSmallRedDown = loadImage("bullet_small_red-180");
        bulletSmallRedLeft = loadImage("bullet_small_red-270");
        bulletSmallBlue = loadImage("bullet_small_blue");
        bulletSmallBlueRight = loadImage("bullet_small_blue-90");
        bulletSmallBlueDown = loadImage("bullet_small_blue-180");
        bulletSmallBlueLeft = loadImage("bullet_small_blue-270");
        bulletSmallYellow = loadImage("bullet_small_yellow");
        bulletSmallYellowRight = loadImage("bullet_small_yellow-90");
        bulletSmallYellowDown = loadImage("bullet_small_yellow-180");
        bulletSmallYellowLeft = loadImage("bullet_small_yellow-270");

        magicCircle = loadImage("10lian");
        zfb = loadImage("zfb");
        stone1 = loadImage("stone1");
        volcano = loadImage("volcano");
        iceberg = loadImage("iceberg");

        grass1 = loadImage("grass1");
        grass2 = loadImage("grass2");
        tankHome = loadImage("tank_home");

        victory = loadImage("victory");
        lose = loadImage("lose");
    }

    private static BufferedImage loadImage(String name){
        try {
            return ImageIO.read(new FileInputStream("./image/"+name+".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
