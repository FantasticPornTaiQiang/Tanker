package view;

import model.Player;

import javax.swing.*;
import java.awt.*;


/**
 * 游戏状态栏
 * @Author: 侯京华
 */

public class StatusBar extends JPanel {

    //玩家姓名
    private JLabel playerName;
    //玩家HP
    public static JLabel playerHP;
    //玩家MP
    private JLabel playerMP;
    //玩家等级
    private JLabel playerLevel;
    //玩家状态
    private JPanel playerStatus;
    //金币
    public static JLabel gold;
    public static JLabel source;

    //玩家
    private Player player;

    public StatusBar(Player player){
        playerName = new JLabel();
        playerHP = new JLabel();
        playerMP = new JLabel();
        playerLevel = new JLabel();
        playerStatus = new JPanel();
        gold = new JLabel();
        source = new JLabel();

        this.player = player;

        init();
        StatusBar.this.setBackground(Color.BLACK);
        playerStatus.setBackground(Color.BLACK);

    }

    private void init(){
        this.setLayout(new GridLayout(2, 2));
        this.add(playerName);
        this.add(playerLevel);
        this.add(playerStatus);
        this.add(gold);
        this.add(source);

        initStatus();
    }

    private void initStatus(){
        initPlayer();
        playerStatus.setLayout(new GridLayout(2, 1));
        playerStatus.add(playerHP);
        playerStatus.add(playerMP);
        playerStatus.add(gold);
        playerStatus.add(source);
    }

    private void initPlayer(){
        //设置字体颜色
        playerName.setForeground(Color.BLUE);
        playerHP.setForeground(Color.BLUE);
        playerMP.setForeground(Color.BLUE);
        playerLevel.setForeground(Color.BLUE);

        //设置字体大小
        playerName.setFont(new Font("",Font.BOLD,21));
        playerHP.setFont(new Font("",Font.BOLD,21));
        playerMP.setFont(new Font("",Font.BOLD,21));
        playerLevel.setFont(new Font("",Font.BOLD,21));
        source.setFont(new Font("",Font.BOLD,21));
        gold.setFont(new Font("",Font.BOLD,21));

        playerName.setText("ID: "+player.getName()+"           ");
        playerName.setForeground(Color.WHITE);
        playerName.setFont(new Font("", Font.BOLD, 36));
        playerHP.setText("HP："+player.getHP());
        playerHP.setForeground(Color.WHITE);
        playerMP.setText("MP："+player.getMP());
        playerMP.setForeground(Color.WHITE);
        playerLevel.setText("LEVEL："+player.getLevel());
        playerLevel.setForeground(Color.WHITE);
        source.setText("源石: "+GameFrame.sourceStone);
        source.setForeground(Color.WHITE);
        gold.setText("金钱: "+GameFrame.gold);
        gold.setForeground(Color.WHITE);
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
