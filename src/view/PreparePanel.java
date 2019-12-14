package view;

import javax.swing.*;
import java.awt.*;

/**
 * 游戏窗口
 * @Author: 徐紫印
 */


public class PreparePanel extends JPanel {
private JButton backBtn;
private JPanel panelBtn;
private JPanel messagePanel;
private JLabel drawing;
private GameFrame gameFrame;

    public PreparePanel(GameFrame gameFrame){

    this.setVisible(true);
    this.setSize(1000,800);
    this.setLayout(new BorderLayout());
    this.gameFrame = gameFrame;

    init();

    }

    private void init(){
        JLabel background = new JLabel();
        ImageIcon icon = new ImageIcon("image/helper.png");
        icon.setImage(icon.getImage().getScaledInstance(400, 600, Image.SCALE_DEFAULT));
        background.setIcon(icon);
        this.add(background, -1);

        panelBtn = new JPanel();
        initPanelBtn();
        this.add(panelBtn, BorderLayout.EAST);

        backBtn = new JButton("BACK");
        backBtn.addActionListener(e -> {
            gameFrame.prepareBackToMain();
            //返回主界面
        });
        backBtn.setFont(new Font("",Font.BOLD,24));
        backBtn.setForeground(Color.WHITE);
        backBtn.setBackground(Color.BLACK);
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.add(backBtn,BorderLayout.EAST);

        messagePanel = new JPanel();
        initMessagePanel();
        topPanel.add(messagePanel);
        this.add(topPanel,BorderLayout.NORTH);

    }
    private void initPanelBtn(){
        panelBtn.setLayout(new BorderLayout());
        Box box = Box.createVerticalBox();
        //出击按钮
        JButton startBtn = new JButton("FIGHT        ");
        startBtn.setFont(new Font("",Font.BOLD,48));
        startBtn.setForeground(Color.WHITE);
        startBtn.setBackground(Color.BLACK);
        startBtn.addActionListener(e -> { gameFrame.prepareStartWar(); });
        box.add(startBtn);
        //仓库按钮
        JButton storeHouseBtn = new JButton("STORE  ");
        storeHouseBtn.addActionListener(e -> {
            PreparePanel.this.setVisible(false);
            //进入仓库界面
            gameFrame.intoStore(); });
        storeHouseBtn.setFont(new Font("",Font.BOLD,48));
        storeHouseBtn.setForeground(Color.WHITE);
        storeHouseBtn.setBackground(Color.BLACK);
        box.add(storeHouseBtn);
        //抽奖按钮
        JButton lottery = new JButton("LUCKY  ");
        lottery.addActionListener(e -> {
            PreparePanel.this.setVisible(false);
            gameFrame.intoLottery();
            //进入抽奖界面
        });
        lottery.setFont(new Font("",Font.BOLD,48));
        box.add(lottery);
        lottery.setForeground(Color.WHITE);
        lottery.setBackground(Color.BLACK);
        panelBtn.add(box,BorderLayout.SOUTH);
    }

    private void initMessagePanel(){
        messagePanel.setLayout(new GridLayout(2,1));
        JLabel name = new JLabel("NAME：太强");
        name.setFont(new Font("",Font.BOLD,24));
        messagePanel.add(name);
        name.setForeground(Color.WHITE);
        JLabel level = new JLabel("LEVEL：1");
        level.setFont(new Font("",Font.BOLD,24));
        level.setForeground(Color.WHITE);
        messagePanel.add(level);
        messagePanel.setBackground(Color.BLACK);
    }
}
