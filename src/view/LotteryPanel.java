package view;

import util.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

import static view.GameFrame.*;
import static view.GameFrame.sourceStone;


/**
 * @Author: 彭泰强
 * 抽奖
 */
public class LotteryPanel extends JPanel {

    private GameFrame gameFrame;
    private int count = 0;
    private String awardMsg = "";
    private JLabel goldLabel = new JLabel();
    private JLabel sourceStoneLabel = new JLabel();

    LotteryPanel (GameFrame gameFrame) {
        this.gameFrame = gameFrame;

        init();
    }

    private void init() {

        this.setLayout(new BorderLayout());
        JPanel topPanel = new JPanel();
        JLabel title = new JLabel("抽  奖");
        title.setForeground(Color.white);
        title.setFont(new Font("", Font.BOLD, 42));
        topPanel.add(title, BorderLayout.CENTER);
        topPanel.setOpaque(false);
        this.add(topPanel, BorderLayout.NORTH);

        JPanel moneyPanel = new JPanel();
        goldLabel.setText("金币：" + gold + "              ");
        sourceStoneLabel.setText("源石：" + sourceStone);
        goldLabel.setFont(new Font("", Font.PLAIN, 24));
        sourceStoneLabel.setFont(new Font("", Font.PLAIN, 24));
        goldLabel.setForeground(Color.yellow);
        sourceStoneLabel.setForeground(Color.orange);
        moneyPanel.add(goldLabel);
        moneyPanel.add(sourceStoneLabel, BorderLayout.EAST);
        moneyPanel.setOpaque(false);
        this.add(moneyPanel);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        JPanel payPanel = new JPanel();
        JButton oneChouBtn = new JButton("单抽");
        oneChouBtn.addActionListener(e -> {
            if(sourceStone < 100) {
                JOptionPane.showMessageDialog(null, "源石不足，请充值", "抽奖错误", JOptionPane.INFORMATION_MESSAGE);
            } else {
                awardMsg = "";
                sourceStone -= 100;
                sourceStoneLabel.setText("源石：" + sourceStone);
                doAward(getAward());
                JOptionPane.showMessageDialog(null, awardMsg, "抽奖结果", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        JButton tenLianBtn = new JButton("十连");
        tenLianBtn.addActionListener(e -> {
            if(sourceStone < 998) {
                JOptionPane.showMessageDialog(null, "源石不足，请充值", "抽奖错误", JOptionPane.INFORMATION_MESSAGE);
            } else {
                awardMsg = "";
                sourceStone -= 998;
                sourceStoneLabel.setText("源石：" + sourceStone);
                for(int i = 0; i < 10; i++){
                    doAward(getAward());
                }
                JOptionPane.showMessageDialog(null, awardMsg, "抽奖结果", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        JButton payBtn = new JButton("充值");
        payBtn.addActionListener(e->{
            if(count%5 == 0) {
                JOptionPane.showMessageDialog(null, "", "打赏", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(ImageLoader.zfb));
            }
            sourceStone += 648;
            sourceStoneLabel.setText("源石：" + sourceStone);
            count++;
        });
        oneChouBtn.setContentAreaFilled(false);
        tenLianBtn.setContentAreaFilled(false);
        payBtn.setContentAreaFilled(false);
        oneChouBtn.setForeground(Color.white);
        tenLianBtn.setForeground(Color.white);
        payBtn.setForeground(Color.white);
        JLabel emptyLabel = new JLabel("             ");
        oneChouBtn.setFont(new Font("", Font.PLAIN, 26));
        tenLianBtn.setFont(new Font("", Font.PLAIN, 26));
        payBtn.setFont(new Font("", Font.PLAIN, 26));
        payPanel.add(oneChouBtn);
        payPanel.add(tenLianBtn);
        payPanel.add(emptyLabel);
        payPanel.add(payBtn);
        payPanel.setOpaque(false);
        bottomPanel.add(payPanel);

        JButton backBtn = new JButton("返  回");
        backBtn.addActionListener(actionEvent -> {
            //返回
            gameFrame.lotteryToMain();
        });
        backBtn.setContentAreaFilled(false);
        backBtn.setFont(new Font("", Font.PLAIN, 28));
        backBtn.setForeground(Color.white);
        bottomPanel.add(backBtn, BorderLayout.SOUTH);
        bottomPanel.setOpaque(false);
        this.add(bottomPanel, BorderLayout.SOUTH);
    }

    private int getAward() {
        double p = Math.random();

        if(p < 0.02) {
            if(!tanksList.get(4).isOwn())
                return 1;//苏婆蓝车
            return 5;//金币
        } else if (p < 0.04 && p >= 0.02) {
            if(!tanksList.get(5).isOwn())
                return 2;//马克思蓝车
            return 5;//金币
        } else if (p < 0.06 && p >= 0.04) {
            if(!tanksList.get(6).isOwn())
                return 3;//达克蓝车
            return 5;//金币
        } else if (p < 0.08 && p >= 0.06) {
            if(!tanksList.get(7).isOwn())
                return 4;//达克蓝车
            return 5;//金币
        } else if (p < 0.4 && p >= 0.08) {
            return 5;//金币
        } else if (p < 0.5 && p >= 0.4) {
            return 6;//血量回复
        } else if (p < 0.6 && p >= 0.5) {
            return 7;//MP回复
        } else if (p < 0.7 && p >= 0.6) {
            return 8;//等级提升
        } else if (p < 0.8 && p >= 0.7) {
            return 9;//源石
        } else {
            return 0;//无
        }
    }

    private void doAward(int what) {
        switch (what) {
            case 0:
                awardMsg += "哦豁，你什么也没有抽到\n";
                return;
            case 1:
                awardMsg += "恭喜你获得强力“苏婆蓝车”\n";
                tanksList.get(4).setOwn(true);
                return;
            case 2:
                awardMsg += "恭喜你获得强力“马克思蓝车”\n";
                tanksList.get(5).setOwn(true);
                return;
            case 3:
                awardMsg += "恭喜你获得强力“达克蓝车”\n";
                tanksList.get(6).setOwn(true);
                return;
            case 4:
                awardMsg += "恭喜你获得强力“φ尔蓝车”\n";
                tanksList.get(7).setOwn(true);
                return;
            case 5:
                int money = (int)(Math.random() * 1000);
                awardMsg += "恭喜你获得" + money + "金币\n";
                gold += money;
                goldLabel.setText("金币：" + gold + "              ");
                return;
            case 6:
                //TODO:血量回复
                awardMsg += "恭喜你获得血量回复\n";
                return;
            case 7:
                //TODO:MP回复
                awardMsg += "恭喜你获得MP回复\n";
                return;
            case 8:
                //TODO:等级提升
                awardMsg += "恭喜你获得等级提升\n";
                return;
            case 9:
                int stone = (int)(Math.random() * 120);
                awardMsg += "恭喜你获得" + stone + "源石\n";
                sourceStone += stone;
                sourceStoneLabel.setText("源石：" + sourceStone);
                return;
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(ImageLoader.magicCircle, 0, 0, 1000, 800, this);
        this.repaint();
    }
}
