package view;

import javax.swing.*;
import java.awt.*;

/**
 * @author Susongfeng
 * @Title: Ui
 * @Package view
 * @Description: Software interface class
 * @date 2020/5/916:59
 */
public class Ui {
    public JFrame frame = new JFrame("拯救学委2.0");
    public JTextField text1 = new JTextField();
    public JTextField text2 = new JTextField();
    public JLabel label1 = new JLabel("目标文件夹:");
    public JLabel label2 = new JLabel("关键字:");
    public JScrollPane scrollPane_1 = new JScrollPane();
    public JTextArea area = new JTextArea(10, 40);
    public JButton button = new JButton("开始执行");
    public Ui() {
        // 设置窗口位于屏幕中间
        int sWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int sHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        int winX = (sWidth - 500) / 2;
        int winY = (sHeight - 300) / 2;
        frame.setBounds(winX + 50, winY + 50, 500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        int midX = 500 / 2 - 120 / 2;
        int midY = 300 / 2 - 30 / 2;


        text1.setBounds(midX, midY - 35, 120, 30);
        frame.add(text1);


        text2.setBounds(midX, midY - 75, 120, 30);
        frame.add(text2);


        label1.setBounds(midX - 80, midY - 35, 70, 30);
        frame.add(label1);


        label2.setBounds(midX - 80, midY - 75, 70, 30);
        frame.add(label2);


        scrollPane_1.setBounds(0, 130, 175, 120);
        scrollPane_1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        frame.getContentPane().add(scrollPane_1);

        area.setBounds(0, 130, 175, 120);
        scrollPane_1.setViewportView(area);


        button.setSize(120, 30);
        button.setBounds(midX, midY, button.getWidth(), button.getHeight());
        frame.add(button);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new Ui();
    }
}
