package view;


import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class TankBox extends JPanel {

    private BufferedImage image;
    private String name;
    private boolean isOwn;
    private boolean isSelect;
    private JButton tankBoxButton;
    private JLabel isOwnTankLabel;

    TankBox(BufferedImage image, String name){
        this.image = image;
        this.name = name;
        init();
    }

    TankBox(BufferedImage image, String name, boolean isOwn){
        this.image = image;
        this.name = name;
        this.isOwn = isOwn;
        init();
    }

    TankBox(BufferedImage image, String name, boolean isOwn, boolean isSelect){
        this.image = image;
        this.name = name;
        this.isOwn = isOwn;
        this.isSelect = isSelect;
        init();
    }

    private void init(){
        JPanel jPanel1 = new JPanel();

        this.setLayout(new BorderLayout());
        JLabel tankBoxLabel = new JLabel(name);
        tankBoxLabel.setForeground(Color.white);
        tankBoxLabel.setFont(new Font("", Font.PLAIN, 20));
        JPanel jPanel = new JPanel();
        jPanel.setBackground(Color.black);
        jPanel.add(tankBoxLabel, BorderLayout.CENTER);
        jPanel1.add(jPanel);

        tankBoxButton = new JButton(new ImageIcon(image));
        tankBoxButton.setContentAreaFilled(false);
        tankBoxButton.setBorderPainted(false);
        jPanel1.add(tankBoxButton, BorderLayout.SOUTH);

        this.add(jPanel1, BorderLayout.CENTER);

        isOwnTankLabel = new JLabel("");
        isOwnTankLabel.setForeground(Color.white);
        isOwnTankLabel.setFont(new Font("", Font.PLAIN, 20));
        JPanel jPanel2 = new JPanel();
        jPanel2.setBackground(Color.black);
        jPanel2.add(isOwnTankLabel, BorderLayout.CENTER);
        this.add(jPanel2, BorderLayout.SOUTH);

        this.setBackground(Color.black);
    }

    public String getTankName() {
        return name;
    }

    public BufferedImage getImage() {
        return image;
    }

    public JButton getTankBoxButton() { return tankBoxButton; }

    public boolean isOwn() {
        return isOwn;
    }

    public void setOwn(boolean own) {
        isOwn = own;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public JLabel getIsOwnTankLabel() {
        return isOwnTankLabel;
    }
}
