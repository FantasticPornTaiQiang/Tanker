package view;

import model.Player;
import org.xml.sax.SAXException;
import util.DataController;
import util.ImageLoader;
import util.TankUtil;
import javax.swing.*;
import javax.xml.crypto.Data;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static util.DataController.getMoney;

/**
 * 游戏窗口
 * @Author: 侯京华
 */
public class GameFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    //游戏主体
    private WarFrame wf;
    //背景
    private JLabel background;
    //菜单按钮
    private JPanel buttonsSet;

    private JPanel titlePanel;

    private PreparePanel prepareFrame;

    private JPanel warMenu;

    private StorePanel store;

    public static ArrayList<TankBox> tanksList;

    private static boolean hasStore = false;

    private String playerName = "太强";

    private Player player;

    private StatusBar statusBar;

    public static int gold;//打怪掉的游戏里的钱，可用于购买道具

    public static int sourceStone;//充值获得的源石，可用于抽奖

    private LotteryPanel lotteryPanel;

    private BookPanel bookPanel;

    private AudioClip bgmMusic;

    private GameFrame(){

        init();
        File f = new File("./music/bgm.wav");
        URL url = null;
        try {
            url = f.toURI().toURL();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        bgmMusic = Applet.newAudioClip(url);

        this.setSize(1000,800);
        this.setTitle("TANKER");
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent windowEvent) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        bgmMusic.loop();
                    }
                }).start();
            }

            @Override
            public void windowClosing(WindowEvent windowEvent) {
                try {
                    DataController.saveMoney(gold);
                    DataController.saveStone(sourceStone);
                    DataController.saveTanks(tanksList);
                    for(int i =0; i<8;i++){
                        if(tanksList.get(i).isSelect()) DataController.saveChoose(i+1);
                    }

                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (SAXException e) {
                    e.printStackTrace();
                } catch (TransformerException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void windowClosed(WindowEvent windowEvent) {

            }

            @Override
            public void windowIconified(WindowEvent windowEvent) {

            }

            @Override
            public void windowDeiconified(WindowEvent windowEvent) {

            }

            @Override
            public void windowActivated(WindowEvent windowEvent) {

            }

            @Override
            public void windowDeactivated(WindowEvent windowEvent) {

            }
        });
        this.setVisible(true);
        this.setLayout(new BorderLayout());

    }

    private void init(){

        buttonsSet = new JPanel();
        background = new JLabel();
        titlePanel = new JPanel();
        tanksList = new ArrayList();
        try {
            gold = DataController.getMoney();
            sourceStone = DataController.getStone();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }



        JLabel title = new JLabel("TANKER");
        titlePanel.setBackground(Color.BLACK);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("",Font.BOLD,48));
        titlePanel.add(title,BorderLayout.CENTER);
        this.add(titlePanel,BorderLayout.NORTH);

        //设置背景图片
        ImageIcon icon = new ImageIcon("image/frame_backg.png");
        icon.setImage(icon.getImage().getScaledInstance(1200, 667, Image.SCALE_DEFAULT));
        background.setIcon(icon);
        this.add(background,BorderLayout.CENTER);


        Box jPanelBtn = Box.createVerticalBox();

        JButton startBtn = new JButton("START");
        startBtn.setFont(new Font("",Font.BOLD,36));
        startBtn.setBorderPainted(false);
        startBtn.setBackground(Color.BLACK);
        startBtn.setForeground(Color.WHITE);
        startBtn.addActionListener(e -> prepare());

        JButton settingsBtn = new JButton("CLASS");
        settingsBtn.setFont(new Font("",Font.BOLD,36));
        settingsBtn.setBorderPainted(false);
        settingsBtn.setBackground(Color.BLACK);
        settingsBtn.setForeground(Color.WHITE);
        settingsBtn.addActionListener(e -> settings());

        JButton collectionsBtn = new JButton("BOOKS");
        collectionsBtn.setFont(new Font("",Font.BOLD,36));
        collectionsBtn.setBorderPainted(false);
        collectionsBtn.setBackground(Color.BLACK);
        collectionsBtn.setForeground(Color.WHITE);
        collectionsBtn.addActionListener(e -> {
            scanBook();
        });

        jPanelBtn.add(startBtn);
        jPanelBtn.add(settingsBtn);
        jPanelBtn.add(collectionsBtn);
        JPanel jPanel = new JPanel();
        jPanel.add(jPanelBtn,BorderLayout.CENTER);

        jPanel.setBackground(Color.BLACK);
        buttonsSet.setBackground(Color.WHITE);
        buttonsSet.setLayout(new BorderLayout());
        buttonsSet.add(jPanel,BorderLayout.CENTER);



        this.add(buttonsSet,BorderLayout.SOUTH);
        this.repaint();
    }


    //进入准备界面
    private void prepare(){
//        this.remove(titlePanel);
        titlePanel.setVisible(false);
        buttonsSet.setVisible(false);
        background.setVisible(false);

        prepareFrame = new PreparePanel(this);
        this.add(prepareFrame);
        repaint();
//        statusBar = new StatusBar();
//        this.add(statusBar,BorderLayout.NORTH);
//        wf = new WarFrame();
//        wf.setFocusable(true);
//        wf.requestFocus();
//        this.add(wf, BorderLayout.CENTER);
    }

    public void intoStore(){
        prepareFrame.setVisible(false);
        store = new StorePanel(this);
        initStore();
        this.add(store);
        this.repaint();
    }

    private void initStore(){
        if(!hasStore) {
            tanksList.add(new TankBox(TankUtil.getTankImage("小蓝车"), "小蓝车", true, true));
            tanksList.add(new TankBox(TankUtil.getTankImage("中蓝车"), "中蓝车", false, false));
            tanksList.add(new TankBox(TankUtil.getTankImage("大蓝车"), "大蓝车", false, false));
            tanksList.add(new TankBox(TankUtil.getTankImage("蓝车"), "蓝车", false, false));
            tanksList.add(new TankBox(TankUtil.getTankImage("苏婆蓝车"), "苏婆蓝车", false, false));
            tanksList.add(new TankBox(TankUtil.getTankImage("马克思蓝车"), "马克思蓝车", false, false));
            tanksList.add(new TankBox(TankUtil.getTankImage("达克蓝车"), "达克蓝车", false, false));
            tanksList.add(new TankBox(TankUtil.getTankImage("φ尔蓝车"), "φ尔蓝车", false, false));
        }

        File f = new File("Data/data.xml");
        if(f.exists()){
            if(!hasStore) {
                ArrayList<Boolean> isOwnList = new ArrayList<>();
                try {
                    DataController.TankList(isOwnList);
                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (TransformerException e) {
                    e.printStackTrace();
                } catch (SAXException e) {
                    e.printStackTrace();
                }
                for(int i =0;i<8;i++){
                    tanksList.get(i).setOwn(isOwnList.get(i));
                }

                hasStore = true;
            }
        } else {
            hasStore = true;
        }


        store.initTank();
    }


    //从准备界面返回开始界面
    public void prepareBackToMain(){
        this.remove(prepareFrame);
        buttonsSet.setVisible(true);
        background.setVisible(true);
        titlePanel.setVisible(true);
        this.repaint();
    }

    //查看图鉴
    private void scanBook(){
        bookPanel = new BookPanel(this);
        background.setVisible(false);
        titlePanel.setVisible(false);
        buttonsSet.setVisible(false);

        this.add(bookPanel);
        this.repaint();
    }

    //图鉴返回主界面
    public void bookToMain(){
        this.remove(bookPanel);
        background.setVisible(true);
        titlePanel.setVisible(true);
        buttonsSet.setVisible(true);
        this.repaint();
    }

    //仓库返回主界面
    public void storeToMain(){
        this.remove(store);
        background.setVisible(true);
        buttonsSet.setVisible(true);
        titlePanel.setVisible(true);
        repaint();
    }

    //从准备界面跳转到游戏界面
    public void prepareStartWar(){
        prepareFrame.setVisible(false);
        this.remove(prepareFrame);

//        int choose = 0;
//        try {
//            choose = DataController.getChoose() - 1;
//            System.out.println(choose +"");
//        } catch (ParserConfigurationException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (TransformerException e) {
//            e.printStackTrace();
//        } catch (SAXException e) {
//            e.printStackTrace();
//        }
//        for (int i = 0; i < 8; i++) {
//            if (i == choose) {
//                tanksList.get(i).setSelect(true);
//                System.out.println(choose +"");
//            } else {
//                tanksList.get(i).setSelect(false);
//            }
//        }
        wf = new WarFrame();
        for (int i = 0; i < tanksList.size(); i++) {
            if (tanksList.get(i).isSelect()) {
                wf.getPlayer().setTankNum(i + 1);
                wf.getPlayer().setImage(tanksList.get(i).getImage());
            }
        }
        this.add(wf);
        this.repaint();
        prepareFrame.setVisible(true);
        wf.setFocusable(true);
        wf.requestFocus();
        startWar();
        initWarMenu();
        this.add(warMenu,BorderLayout.NORTH);
    }

    public void intoLottery() {
        prepareFrame.setVisible(false);
        lotteryPanel = new LotteryPanel(this);
        if(!hasStore) {
            tanksList.add(new TankBox(TankUtil.getTankImage("小蓝车"), "小蓝车", true, true));
            tanksList.add(new TankBox(TankUtil.getTankImage("中蓝车"), "中蓝车", false, false));
            tanksList.add(new TankBox(TankUtil.getTankImage("大蓝车"), "大蓝车", false, false));
            tanksList.add(new TankBox(TankUtil.getTankImage("蓝车"), "蓝车", false, false));
            tanksList.add(new TankBox(TankUtil.getTankImage("苏婆蓝车"), "苏婆蓝车", false, false));
            tanksList.add(new TankBox(TankUtil.getTankImage("马克思蓝车"), "马克思蓝车", false, false));
            tanksList.add(new TankBox(TankUtil.getTankImage("达克蓝车"), "达克蓝车", false, false));
            tanksList.add(new TankBox(TankUtil.getTankImage("φ尔蓝车"), "φ尔蓝车", false, false));

            ArrayList<Boolean> isOwnList = new ArrayList<>();

            try {
                DataController.TankList(isOwnList);
                for(int i =0;i<8;i++){
                    tanksList.get(i).setOwn(isOwnList.get(i));
                }
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (TransformerException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            }

            hasStore = true;
        }
        this.add(lotteryPanel);
        this.repaint();
    }

    //抽奖返回主界面
    public void lotteryToMain(){
        this.remove(lotteryPanel);
        background.setVisible(true);
        buttonsSet.setVisible(true);
        titlePanel.setVisible(true);
        repaint();
    }

    public void storeStartWar(){
        wf = new WarFrame();

        for (int i = 0; i < tanksList.size(); i++) {
            if (tanksList.get(i).isSelect()) {
                wf.getPlayer().setTankNum(i + 1);
                wf.getPlayer().setImage(tanksList.get(i).getImage());
            }
        }
        store.setVisible(false);
        this.remove(store);
        store.setVisible(true);
        startWar();
        initWarMenu();
        this.add(warMenu,BorderLayout.NORTH);
        this.add(wf);
        this.repaint();
        wf.setFocusable(true);
        wf.requestFocus();

    }

    private void startWar(){

        this.player = wf.getPlayer();
        statusBar = new StatusBar(player);

    }

    private void initWarMenu() {
        warMenu = new JPanel();
        warMenu.setLayout(new BorderLayout());
        warMenu.setBackground(Color.BLACK);
        warMenu.add(statusBar,BorderLayout.WEST);

        Box box = Box.createHorizontalBox();

        JButton backToMainFrame = new JButton();
        backToMainFrame.setText("BACK");
        backToMainFrame.setFont(new Font("", Font.BOLD, 36));
        backToMainFrame.setForeground(Color.WHITE);
        backToMainFrame.setBackground(Color.BLACK);
        backToMainFrame.setBorderPainted(false);
        backToMainFrame.addActionListener(e -> warBackToMain());
//        JButton pauseBtn = new JButton();
//        pauseBtn.setIcon(new ImageIcon("image/pause.jpg"));
//        pauseBtn.addActionListener(e -> {
//            //暂停逻辑
//        });
//        box.add(pauseBtn);

        box.add(backToMainFrame);
        warMenu.add(box,BorderLayout.EAST);
    }


    //从游戏界面跳转到开始界面
    public void warBackToMain(){
        this.remove(wf);
        this.remove(warMenu);
        buttonsSet.setVisible(true);
        background.setVisible(true);
        titlePanel.setVisible(true);
        this.repaint();
    }

    //设置
    private void settings(){
        JPopupMenu menu = new JPopupMenu();
        JMenuItem easy = new JMenuItem();
        easy.setLabel("easy");
        JMenuItem normal = new JMenuItem();
        easy.setLabel("normal");
        JMenuItem hard = new JMenuItem();
        easy.setLabel("hard");

        menu.add(easy);
        menu.add(normal);
        menu.add(hard);

        //设置关卡难度
    }

    //菜单键返回
    private void back(){
        this.remove(wf);
        this.add(background);
        this.add(buttonsSet);
    }




    public static void main(String [] args){
        new GameFrame();
    }



}
