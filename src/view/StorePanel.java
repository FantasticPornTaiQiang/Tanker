package view;

import util.ImageLoader;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static view.GameFrame.tanksList;

/**
 * 仓库界面
 * @Author: 彭泰强
 */
public class StorePanel extends JPanel {

    private JPanel boxPanel;
    private GameFrame gameFrame;
    private int tankNum;

    StorePanel(GameFrame gameFrame){
        init();

        this.gameFrame = gameFrame;
    }

    private void init(){
        setLayout(new BorderLayout());
        JLabel title = new JLabel("仓库");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("", Font.BOLD, 32));
        JPanel jPanel = new JPanel();
        jPanel.setBackground(Color.black);
        jPanel.add(title, BorderLayout.CENTER);
        add(jPanel, BorderLayout.NORTH);

        boxPanel = new JPanel();
        boxPanel.setLayout(new GridLayout(1,8));
        add(boxPanel, BorderLayout.CENTER);

        //初始
        for(TankBox tank : tanksList) {
            if(tank.isSelect()) {
                tank.getTankBoxButton().addActionListener(e -> choose(tank.getTankName()));
                tank.setBorder(new TitledBorder("已选择"));
            }
        }

        JPanel jPanel1 = new JPanel();
        JButton backBtn = new JButton("返回");
        backBtn.addActionListener(e -> {
            gameFrame.storeToMain();
        });
        JButton confirmBtn = new JButton("出击");
        confirmBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameFrame.storeStartWar();
            }
        });
        backBtn.setForeground(Color.white);
        confirmBtn.setForeground(Color.white);
        backBtn.setOpaque(false);
        backBtn.setContentAreaFilled(false);
        confirmBtn.setOpaque(false);
        confirmBtn.setContentAreaFilled(false);
        backBtn.setFont(new Font("", Font.BOLD, 28));
        confirmBtn.setFont(new Font("", Font.BOLD, 28));
        jPanel1.setLayout(new BorderLayout());
        jPanel1.add(backBtn, BorderLayout.WEST);
        jPanel1.add(confirmBtn, BorderLayout.EAST);
        jPanel1.setBackground(Color.black);
        this.add(jPanel1, BorderLayout.SOUTH);
    }

    public void initTank(){
        for(TankBox tank : tanksList){
            tank.getTankBoxButton().addActionListener(e -> choose(tank.getTankName()));
            if(!tank.isOwn()) {
                tank.getTankBoxButton().setEnabled(false);
            } else {
                tank.getTankBoxButton().setEnabled(true);
                tank.getIsOwnTankLabel().setText("已拥有");
            }
            boxPanel.add(tank);
        }

    }

    public void choose(String name){
        for (TankBox tank : tanksList){
            if (tank.getTankName().equals(name)) {
                tank.getTankBoxButton().setBorderPainted(true);
                tank.setBorder(new TitledBorder(""));
                tank.setSelect(true);
            } else {
                tank.getTankBoxButton().setBorderPainted(false);
                tank.setBorder(null);
                tank.setSelect(false);
            }
        }
    }

}
