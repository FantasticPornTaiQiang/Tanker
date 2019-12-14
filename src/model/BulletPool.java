package model;

import util.ImageLoader;

import java.lang.ref.WeakReference;
import java.util.LinkedList;
import java.util.List;

/**
 * 子弹列类
 * @Author: 谢宇弢
 */
public class BulletPool {

    private static LinkedList<Bullet> bulletList;
    private static int count;

    public static void init(){
        bulletList = new LinkedList<>();
        count = 0;
        for (int i = 0; i < 100; i++){
            Bullet bullet = new Bullet(ImageLoader.blue1);
            bulletList.add(bullet);
        }
    }

    private static void bigger(){
        for (int i = 0; i < 100; i++){
            Bullet bullet = new Bullet(ImageLoader.blue1);
            bulletList.add(bullet);
        }
    }

    public static void remove(WeakReference<Bullet> reference){
        reference = null;
        if (count > 0)
            count--;
    }

    public static WeakReference<Bullet> getBullet(){
        if (count < bulletList.size()-1) {
            WeakReference<Bullet> reference = new WeakReference<>(bulletList.get(count));
            count++;
            return reference;
        }else {
            bigger();
            WeakReference<Bullet> reference = new WeakReference<>(bulletList.get(count));
            count++;
            return reference;
        }
    }

}