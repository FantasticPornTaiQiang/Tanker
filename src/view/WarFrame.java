package view;

import model.*;
import presenter.GamePresenter;
import util.GameStage;
import util.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 游戏主体
 * @Author: 彭少青
 */
public class WarFrame extends JPanel {

    //游戏逻辑表示器
    private GamePresenter presenter;
    //游戏时间
    private int time = 0;
    private boolean isBoss;
    //游戏状态
    private GameStage stage;
    private Player player;
    private Boss boss;
    private CopyOnWriteArrayList<Enemy> enemyList;
    private CopyOnWriteArrayList<WeakReference<Bullet>> playerBullets;
    private CopyOnWriteArrayList<WeakReference<Bullet>> enemyBullets;
    private CopyOnWriteArrayList<Boss> bossList;
    private CopyOnWriteArrayList<Wall> wallList;
    private KeyListener listener;

    WarFrame(){

        init();
        //游戏运行线程
        Thread game = new Thread(() -> {

            while(true){

                //根据具体关卡进行具体游戏逻辑
                if (stage.equals(GameStage.START)){

                    //通过表示器调用具体游戏逻辑方法...如： presenter.collision(Objects, Objects);

                    //线程睡眠30毫秒，降低内存消耗，游戏帧
                    time += 30;
                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                if (stage.equals(GameStage.ONE)){

                    player.move(time);
                    presenter.enemyCtrl(enemyList, time, player);
                    presenter.playerBulletCtrl(playerBullets, time, enemyList, boss);
                    presenter.enemyBulletCtrl(enemyBullets, time, player);
                    presenter.pwCollision(player, wallList);
                    presenter.ewCollision(enemyList, wallList);
                    presenter.enemyMove(enemyList);

                    if (enemyList.isEmpty()) {
                        if (!isBoss)
                            initBoss(stage);
                        presenter.bossCtrl(bossList, time, player);
                        if (bossList.get(0).getHP() <= 0){
                            stage = GameStage.TWO;
                            initEnemy(stage);
                            isBoss = false;
                            GameFrame.sourceStone += 1000;
                            StatusBar.source.setText("源石："+GameFrame.sourceStone);
                        }
                    }

                    if (player.getHP() <= 0)
                        stage = GameStage.OVER;

                    time += 30;
                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                if (stage.equals(GameStage.TWO)){

                    player.move(time);
                    presenter.enemyCtrl(enemyList, time, player);
                    presenter.playerBulletCtrl(playerBullets, time, enemyList, boss);
                    presenter.enemyBulletCtrl(enemyBullets, time, player);
                    presenter.pwCollision(player, wallList);
                    presenter.ewCollision(enemyList, wallList);
                    presenter.enemyMove(enemyList);

                    if (enemyList.isEmpty()) {
                        if (!isBoss)
                            initBoss(stage);
                        presenter.bossCtrl(bossList, time, player);

                        if (bossList.get(0).getHP() <= 0){
                            stage = GameStage.THREE;
                            initEnemy(stage);
                            isBoss = false;
                            GameFrame.sourceStone += 1000;
                            StatusBar.source.setText("源石："+GameFrame.sourceStone);
                        }
                    }

                    if (player.getHP() <= 0)
                        stage = GameStage.OVER;

                    time += 30;
                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                if (stage.equals(GameStage.THREE)){

                    player.move(time);
                    presenter.enemyCtrl(enemyList, time, player);
                    presenter.playerBulletCtrl(playerBullets, time, enemyList, boss);
                    presenter.enemyBulletCtrl(enemyBullets, time, player);
                    presenter.pwCollision(player, wallList);
                    presenter.ewCollision(enemyList, wallList);
                    presenter.enemyMove(enemyList);
                    if (enemyList.isEmpty()) {
                        if (!isBoss)
                            initBoss(stage);
                        presenter.bossCtrl(bossList, time, player);

                        if (bossList.get(0).getHP() <= 0){
                            stage = GameStage.FOUR;
                            initEnemy(stage);
                            isBoss = false;
                            GameFrame.sourceStone += 1000;
                            StatusBar.source.setText("源石："+GameFrame.sourceStone);
                        }
                    }
                    if (player.getHP() <= 0)
                        stage = GameStage.OVER;
                    time += 30;
                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                if (stage.equals(GameStage.FOUR)){

                    player.move(time);
                    presenter.enemyCtrl(enemyList, time, player);
                    presenter.playerBulletCtrl(playerBullets, time, enemyList, boss);
                    presenter.enemyBulletCtrl(enemyBullets, time, player);
                    presenter.pwCollision(player, wallList);
                    presenter.ewCollision(enemyList, wallList);
                    presenter.enemyMove(enemyList);
                    if (enemyList.isEmpty()) {
                        if (!isBoss)
                            initBoss(stage);
                        presenter.bossCtrl(bossList, time, player);

                        if (bossList.get(0).getHP() <= 0){
                            stage = GameStage.SUCCESS;
                            GameFrame.sourceStone += 1000;
                            StatusBar.source.setText("源石："+GameFrame.sourceStone);
                        }
                    }
                    if (player.getHP() <= 0)
                        stage = GameStage.OVER;
                    time += 30;
                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                if (stage.equals(GameStage.SUCCESS)){
                    time += 30;
                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                if (stage.equals(GameStage.OVER)){
                    time += 30;
                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                //重复绘制
                repaint();

            }

        });
        game.start();
    }

    private void init(){
        presenter = new GamePresenter();
        stage = GameStage.ONE;
        playerBullets = new CopyOnWriteArrayList<>();
        enemyBullets = new CopyOnWriteArrayList<>();
        bossList = new CopyOnWriteArrayList<>();
        wallList = new CopyOnWriteArrayList<>();
        BulletPool.init();
        initPlayer();
        initEnemy(stage);
        initWall();
        isBoss = false;
    }

    public Player getPlayer(){return this.player; }


    private void initPlayer(){
        player = new Player(ImageLoader.blue1);
        player.setLocation(300,500);
        player.setHP(1000000);
        player.setMP(100);
        player.setLevel(10);
        player.setSpeed(2*player.getLevel());
        presenter.playerCtrl(player);
        listener = presenter.playerKeyListener();
        addKeyListener(listener);
        player.setPlayerBullets(playerBullets);
    }

    private void initEnemy(GameStage stage){
        enemyList = new CopyOnWriteArrayList();
        switch (stage){
            case ONE:
                for (int i = 0; i < 5; i++){
                    Enemy enemy = new Enemy(ImageLoader.enemyRed1);
                    enemy.setTankNum(1);
                    enemy.setLocation(100 + i*20, 100);
                    enemy.setHP(20);
                    enemy.setLevel(1);
                    enemy.setSpeed(3);
                    enemy.setEnemyBullets(enemyBullets);
                    enemyList.add(enemy);
                    break;
                }
            case TWO:
                for (int i = 0; i < 3; i++){
                    Enemy enemy = new Enemy(ImageLoader.enemyRed1);
                    enemy.setTankNum(1);
                    enemy.setLocation(100 + i*20, 100);
                    enemy.setHP(100);
                    enemy.setLevel(2);
                    enemy.setSpeed(3);
                    enemy.setEnemyBullets(enemyBullets);
                    enemyList.add(enemy);

                }
                for (int i = 3; i < 8; i++){
                    Enemy enemy = new Enemy(ImageLoader.enemyRed2);
                    enemy.setTankNum(2);
                    enemy.setLocation(100 + i*20, 100);
                    enemy.setHP(100);
                    enemy.setSpeed(4);
                    enemy.setLevel(3);
                    enemy.setEnemyBullets(enemyBullets);
                    enemyList.add(enemy);

                }
                break;
            case THREE:
                for (int i = 0; i < 10; i++){
                    Enemy enemy = new Enemy(ImageLoader.enemyRed2);
                    enemy.setTankNum(2);
                    enemy.setLocation(100 + i*20, 100);
                    enemy.setHP(100);
                    enemy.setLevel(3);
                    enemy.setSpeed(3);
                    enemy.setEnemyBullets(enemyBullets);
                    enemyList.add(enemy);

                }
                break;
            case FOUR:
                for (int i = 0; i < 5; i++){
                    Enemy enemy = new Enemy(ImageLoader.enemyYellow);
                    enemy.setTankNum(3);
                    enemy.setLocation(100 + i*20, 100);
                    enemy.setHP(100);
                    enemy.setSpeed(5);
                    enemy.setEnemyBullets(enemyBullets);
                    enemyList.add(enemy);
                }
                for (int i = 5; i < 10; i++){
                    Enemy enemy = new Enemy(ImageLoader.enemyRed1);
                    enemy.setTankNum(1);
                    enemy.setLocation(100 + i*20, 100);
                    enemy.setHP(100);
                    enemy.setLevel(2);
                    enemy.setSpeed(3);
                    enemy.setEnemyBullets(enemyBullets);
                    enemyList.add(enemy);
                }
                for (int i = 10; i < 15; i++){
                    Enemy enemy = new Enemy(ImageLoader.enemyRed2);
                    enemy.setTankNum(2);
                    enemy.setLocation(100 + i*20, 100);
                    enemy.setHP(100);
                    enemy.setLevel(4);
                    enemy.setSpeed(4);
                    enemy.setEnemyBullets(enemyBullets);
                    enemyList.add(enemy);
                }
                break;
        }
    }

    private void initWall(){
        Wall home = new Wall(ImageLoader.tankHome);
        home.setLocation(800,0);
        Wall wall1 = new Wall(ImageLoader.stone1);
        wall1.setLocation(100,100);
        Wall wall2 = new Wall(ImageLoader.stone1);
        wall2.setLocation(100,280);
        Wall wall3 = new Wall(ImageLoader.stone1);
        wall3.setLocation(100,460);
        Wall wall4 = new Wall(ImageLoader.stone1);
        wall4.setLocation(300,50);
        Wall wall5 = new Wall(ImageLoader.stone1);
        wall5.setLocation(370,200);
        Wall wall6 = new Wall(ImageLoader.stone1);
        wall6.setLocation(300,590);
        Wall wall7 = new Wall(ImageLoader.volcano);
        wall7.setLocation(420,250);
        Wall wall8 = new Wall(ImageLoader.grass1);
        wall8.setLocation(200, 320);
        Wall wall9 = new Wall(ImageLoader.grass1);
        wall9.setLocation(490, 570);
        Wall wall10 = new Wall(ImageLoader.grass2);
        wall10.setLocation(550, 420);
        Wall wall11 = new Wall(ImageLoader.grass2);
        wall11.setLocation(840, 260);
        Wall wall12 = new Wall(ImageLoader.iceberg);
        wall12.setLocation(770, 520);
        Wall wall13 = new Wall(ImageLoader.stone1);
        wall13.setLocation(600, 220);
        Wall wall14 = new Wall(ImageLoader.stone1);
        wall14.setLocation(740, 0);
        Wall wall15 = new Wall(ImageLoader.stone1);
        wall15.setLocation(935, 160);
        Wall wall16 = new Wall(ImageLoader.grass2);
        wall16.setLocation(530, 0);
        Wall wall17 = new Wall(ImageLoader.stone1);
        wall17.setLocation(760, 460);
        Wall wall18 = new Wall(ImageLoader.grass1);
        wall18.setLocation(850, 420);
        Wall wall19 = new Wall(ImageLoader.grass1);
        wall19.setLocation(490, 570);
        Wall wall20 = new Wall(ImageLoader.stone1);
        wall20.setLocation(740, 160);
        Wall wall21 = new Wall(ImageLoader.grass1);
        wall21.setLocation(670, 80);

        wallList.add(home);
        wallList.add(wall1);
        wallList.add(wall2);
        wallList.add(wall3);
        wallList.add(wall4);
        wallList.add(wall5);
        wallList.add(wall6);
        wallList.add(wall7);
        wallList.add(wall8);
        wallList.add(wall9);
        wallList.add(wall10);
        wallList.add(wall11);
        wallList.add(wall12);
        wallList.add(wall13);
        wallList.add(wall14);
        wallList.add(wall15);
        wallList.add(wall16);
        wallList.add(wall17);
        wallList.add(wall18);
        wallList.add(wall19);
        wallList.add(wall20);
        wallList.add(wall21);


    }

    private void initBoss(GameStage stage){
        bossList = new CopyOnWriteArrayList<>();
        switch (stage){
            case ONE:
                boss = new Boss(ImageLoader.boss1);
                boss.setTankNum(1);
                boss.setLocation(300, 500);
                boss.setHP(1000);
                boss.setSpeed(4);
                boss.setEnemyBullets(enemyBullets);
                bossList.add(boss);
                break;
            case TWO:
                boss = new Boss(ImageLoader.boss2);
                boss.setTankNum(2);
                boss.setLocation(300, 500);
                boss.setHP(1600);
                boss.setSpeed(3);
                boss.setEnemyBullets(enemyBullets);
                bossList.add(boss);
                break;
            case THREE:
                boss = new Boss(ImageLoader.boss3);
                boss.setTankNum(3);
                boss.setLocation(300, 500);
                boss.setHP(2000);
                boss.setSpeed(4);
                boss.setEnemyBullets(enemyBullets);
                bossList.add(boss);
                break;
            case FOUR:
                boss = new Boss(ImageLoader.boss4);
                boss.setTankNum(4);
                boss.setLocation(300, 500);
                boss.setHP(4000);
                boss.setSpeed(4);
                boss.setEnemyBullets(enemyBullets);
                bossList.add(boss);
                break;
        }
        isBoss = true;
    }


    //绘制总方法 根据不同游戏状态 调用不同具体绘制方法
    public void paint(Graphics g){
        super.paint(g);

        switch (stage){

            case START:
                drawMap(0, g);
                break;
            case ONE:
                drawMap(0, g);
                player.draw(g);
                paintEnemy(g);
                drawBullet(g);
                paintBoss(g);
                break;
            case TWO:
                drawMap(1, g);
                player.draw(g);
                paintEnemy(g);
                drawBullet(g);
                paintBoss(g);
                break;
            case THREE:
                drawMap(2, g);
                player.draw(g);
                paintEnemy(g);
                drawBullet(g);
                paintBoss(g);
                break;
            case FOUR:
                drawMap(3, g);
                player.draw(g);
                paintEnemy(g);
                drawBullet(g);
                paintBoss(g);
                break;
            case SUCCESS:
                drawMap(4, g);
                break;
            case OVER:
                drawMap(5, g);
                break;
        }
    }

    private void paintEnemy(Graphics g){
        if (!enemyList.isEmpty()) {
            for (Enemy enemy : enemyList) {
                enemy.draw(g);
            }
        }
    }

    private void paintBoss(Graphics g){
        if (!bossList.isEmpty()) {
            for (Boss boss : bossList) {
                if (isBoss)
                    boss.draw(g);
            }
        }
    }

    private void drawMap(int i, Graphics g){

        switch (i){
            case 0:
                g.drawImage(ImageLoader.map1, 0, 0, 1000, 800,null);
                drawWall(g);
                break;
            case 1:
                g.drawImage(ImageLoader.map2, 0, 0, 1000, 800,null);
                drawWall(g);
                break;
            case 2:
                g.drawImage(ImageLoader.map3, 0, 0, 1000, 800,null);
                drawWall(g);
                break;
            case 3:
                g.drawImage(ImageLoader.map4, 0, 0, 1000, 800,null);
                drawWall(g);
                break;
            case 4:
                g.drawImage(ImageLoader.victory, 0, 0, 1000, 800,null);
                break;
            case 5:
                g.drawImage(ImageLoader.lose, 0, 0, 1000, 800,null);
                break;
            default:
                break;
        }
    }

    private void drawBullet(Graphics g){
        for (WeakReference<Bullet> bullet : playerBullets){
            bullet.get().draw(g);
        }
        for (WeakReference<Bullet> bullet : enemyBullets){
            bullet.get().draw(g);
        }
    }

    public void drawWall(Graphics g){
        for(int i=0;i<wallList.size();i++){
            wallList.get(i).draw(g);
        }
    }

}

