package view;

import util.TankUtil;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static view.GameFrame.tanksList;

/**
 * 图鉴游戏窗口
 * @Author: 徐紫印、彭泰强
 */

public class BookPanel extends JPanel {

    private JPanel boxPanel;
    private GameFrame gameFrame;
    private List<TankBox> tanks;

    public BookPanel(GameFrame gameFrame){
        this.gameFrame = gameFrame;
        init();
        this.repaint();
    }

    private void init(){
        tanks = new ArrayList<>();
        tanks.add(new TankBox(TankUtil.getTankImage("小蓝车"), "小蓝车"));
        tanks.add(new TankBox(TankUtil.getTankImage("中蓝车"), "中蓝车"));
        tanks.add(new TankBox(TankUtil.getTankImage("大蓝车"), "大蓝车"));
        tanks.add(new TankBox(TankUtil.getTankImage("蓝车"), "蓝车"));
        tanks.add(new TankBox(TankUtil.getTankImage("苏婆蓝车"), "苏婆蓝车"));
        tanks.add(new TankBox(TankUtil.getTankImage("马克思蓝车"), "马克思蓝车"));
        tanks.add(new TankBox(TankUtil.getTankImage("达克蓝车"), "达克蓝车"));
        tanks.add(new TankBox(TankUtil.getTankImage("φ尔蓝车"), "φ尔蓝车"));


        setLayout(new BorderLayout());
        JLabel title = new JLabel("图  鉴");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("", Font.BOLD, 32));
        JPanel jPanel = new JPanel();
        jPanel.setBackground(Color.black);
        jPanel.add(title, BorderLayout.CENTER);
        add(jPanel, BorderLayout.NORTH);

        boxPanel = new JPanel();
        boxPanel.setLayout(new GridLayout(1,8));
        for(TankBox tank : tanks){
            boxPanel.add(tank);
        }
        add(boxPanel, BorderLayout.CENTER);

        JPanel jPanel1 = new JPanel();
        JButton backBtn = new JButton("返回");
        backBtn.addActionListener(e -> {
            gameFrame.bookToMain();
        });

        backBtn.setForeground(Color.white);
        backBtn.setOpaque(false);
        backBtn.setContentAreaFilled(false);
        backBtn.setFont(new Font("", Font.BOLD, 28));
        jPanel1.setLayout(new BorderLayout());
        jPanel1.add(backBtn, BorderLayout.WEST);
        jPanel1.setBackground(Color.black);
        this.add(jPanel1, BorderLayout.SOUTH);
    }
}
