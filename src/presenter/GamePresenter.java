package presenter;

import model.*;
import view.GameFrame;
import view.StatusBar;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.ref.WeakReference;
import java.util.List;

/**
 * 游戏逻辑表示器 游戏逻辑在这里实现
 * @Author: 侯京华
 */
public class GamePresenter implements  GameContract.GamePresenter {


    private KeyListener listener;

    @Override
    public void playerCtrl(Objects player) {

        listener = new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

                switch (e.getKeyCode()){
                    //W A S D 上下左右 J K L 攻击什么的
                    case KeyEvent.VK_W:
                        if (!((Player)player).getUpCrash()) {
                            ((Player) player).setDirection(true, false, false, false);
                            ((Player)player).setBeforeCrashDirection(KeyEvent.VK_W);
                        }
                        ((Player)player).setUpCrash(false);

                        break;
                    case KeyEvent.VK_A:
                        if(!((Player)player).getLeftCrash()) {
                            ((Player) player).setBeforeCrashDirection(KeyEvent.VK_A);
                            ((Player) player).setDirection(false, false, true, false);
                        }
                        ((Player)player).setLeftCrash(false);

                        break;
                    case KeyEvent.VK_D:
                        if(!((Player)player).getRightCrash()) {
                            ((Player) player).setBeforeCrashDirection(KeyEvent.VK_D);
                            ((Player) player).setDirection(false, false, false, true);
                        }
                        ((Player)player).setRightCrash(false);

                        break;
                    case KeyEvent.VK_S:
                        if(!((Player)player).getDownCrash()) {
                            ((Player) player).setBeforeCrashDirection(KeyEvent.VK_S);
                            ((Player) player).setDirection(false, true, false, false);
                        }
                        ((Player)player).setDownCrash(false);

                        break;
                    case KeyEvent.VK_SPACE:
                        ((Player)player).setAttack(true);
                        ((Player)player).fire();
                        break;
                    case KeyEvent.VK_K:
                        break;
                    case KeyEvent.VK_L:
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()){
                    //W A S D 上下左右 J K L 攻击什么的
                    case KeyEvent.VK_W:
                        ((Player)player).setDirection(false, false, false, false);
                        break;
                    case KeyEvent.VK_A:
                        ((Player)player).setDirection(false, false, false, false);
                        break;
                    case KeyEvent.VK_D:
                        ((Player)player).setDirection(false, false, false, false);
                        break;
                    case KeyEvent.VK_S:
                        ((Player)player).setDirection(false, false, false, false);
                        break;
                    case KeyEvent.VK_SPACE:
                        ((Player)player).setAttack(false);
                        break;
                    case KeyEvent.VK_K:
                        break;
                    case KeyEvent.VK_L:
                        break;
                    default:
                        break;
                }
            }
        };
    }

    @Override
    public void collision(Objects player, List<Objects> enemies) {

    }

    public void pwCollision(Player player,List<Wall> walls){
        for(int i=0;i<walls.size();i++){
            if(Objects.collision(player, walls.get(i))) {
                player.setDirection(false, false, false, false);
                switch(player.getBeforeCrashDirection()){
                    case KeyEvent.VK_W:
                        player.setCrashDirection(false,false,true,false);
                        break;
                    case KeyEvent.VK_A:
                        player.setCrashDirection(true,false,false,false);
                        break;
                    case KeyEvent.VK_S:
                        player.setCrashDirection(false,false,false,true);
                        break;
                    case KeyEvent.VK_D:
                        player.setCrashDirection(false,true,false,false);
                        break;
                }
            }
        }
    }

    public void ewCollision(List<Enemy> enemies,List<Wall> walls){
        for(int i=0;i<enemies.size();i++){
            for(int j=0;j<walls.size();j++){
                if(Objects.collision(enemies.get(i),walls.get(j))){
                    if(enemies.get(i).isUp()){
                        enemies.get(i).setDirection(false, false, false, true);
                        enemies.get(i).setLocation(enemies.get(i).getXLocation() + enemies.get(i).getSpeed(), enemies.get(i).getYLocation());
                        enemies.get(i).setY(walls.get(j).getYLocation() - 1 + walls.get(j).getHeight());

                    }
                    if(enemies.get(i).isDown()){
                        enemies.get(i).setX(walls.get(j).getXLocation()+1);
                        enemies.get(i).setDirection(false,false,false,true);
                        enemies.get(i).setY(walls.get(j).getYLocation() + 1 + enemies.get(i).getHeight());
                    }
                    if(enemies.get(i).isRight()){
                        enemies.get(i).setDirection(false, true, false, false);
                        enemies.get(i).setLocation(enemies.get(i).getXLocation() + enemies.get(i).getSpeed(), enemies.get(i).getYLocation());
                        enemies.get(i).setX(walls.get(j).getXLocation() - 1 - enemies.get(i).getWidth());

                    }
                    if(enemies.get(i).isLeft()){
                        enemies.get(i).setY(walls.get(j).getYLocation()+1);
                        enemies.get(i).setDirection(false,true,false,false);
                        enemies.get(i).setX(walls.get(j).getXLocation() + 1 + enemies.get(i).getWidth());
                    }
                }
            }
        }
    }

    public void enemyMove(List<Enemy> enemies){
        for (Enemy enemy : enemies){
            enemy.move();
        }
    }

    @Override
    public void enemyCtrl(List<Enemy> enemies, long time, Objects player) {
        for (Enemy enemy : enemies){
            enemy.findPath(time, player.getXLocation(), player.getYLocation());
            enemy.fire(time);
        }
    }

    @Override
    public KeyListener playerKeyListener() {
        return listener;
    }

    @Override
    public void enemyBulletCtrl(List<WeakReference<Bullet>> bullets, long time, Player player) {
        for (WeakReference<Bullet> bullet : bullets){
            bullet.get().move(time);
            if (bullet.get().isHit(player)){
                StatusBar.playerHP.setText("HP："+player.getHP());
                BulletPool.remove(bullet);
                bullets.remove(bullet);
            }else if (bullet.get().isOutBoundary()){
                BulletPool.remove(bullet);
                bullets.remove(bullet);
            }
        }
    }

    @Override
    public void playerBulletCtrl(List<WeakReference<Bullet>> bullets, long time, List<Enemy> enemyList, Boss boss) {
        for (WeakReference<Bullet> bullet : bullets){
            bullet.get().move(time);
            for (Objects o : enemyList) {
                if (bullet.get().isHit(o)) {
                    BulletPool.remove(bullet);
                    bullets.remove(bullet);
                    if (o.getHP() <= 0) {
                        enemyList.remove(o);
                        GameFrame.gold += 100;
                        StatusBar.gold.setText("金钱："+GameFrame.gold);
                    }
                } else if (bullet.get().isOutBoundary()) {
                    BulletPool.remove(bullet);
                    bullets.remove(bullet);
                }
            }
            if (boss != null) {
                if (bullet.get().isHit(boss)) {
                    BulletPool.remove(bullet);
                    bullets.remove(bullet);
                } else if (bullet.get().isOutBoundary()) {
                    BulletPool.remove(bullet);
                    bullets.remove(bullet);
                }
            }
        }
    }

    @Override
    public void bossCtrl(List<Boss> bosses, long time, Objects player) {
        for (Boss boss : bosses){
            boss.move(time, player.getXLocation(), player.getYLocation());
            boss.fire(time);
        }
    }
}
