package presenter;

import model.*;

import java.awt.event.KeyListener;
import java.lang.ref.WeakReference;
import java.util.List;

/**
 * 游戏契约类 定义需要的游戏逻辑接口，再到Presenter中实现， WarFrame中调用
 * @Author: 侯京华
 */
public interface GameContract {

    interface GamePresenter{

        //玩家控制
        void playerCtrl(Objects player);
        //碰撞
        void collision(Objects player, List<Objects> enemies);
        //敌人控制
        void enemyCtrl(List<Enemy> enemies, long time, Objects player);
        //移动监听
        KeyListener playerKeyListener();
        //子弹控制
        void enemyBulletCtrl(List<WeakReference<Bullet>> bullets, long time, Player player);
        void playerBulletCtrl(List<WeakReference<Bullet>> bullets, long time, List<Enemy> objectsList, Boss boss);

        void bossCtrl(List<Boss> bosses, long time, Objects player);

        void pwCollision(Player player,List<Wall> walls);

        void ewCollision(List<Enemy> enemies,List<Wall> walls);
    };
}
