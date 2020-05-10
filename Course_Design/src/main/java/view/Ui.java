package view;

import control.Move_Files;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * @author Susongfeng
 * @Title: Ui
 * @Package view
 * @Description: Software interface class
 * @date 2020/5/916:59
 */
public class Ui {
    private JFrame frame = new JFrame("拯救学委2.0");  // 窗口
    private JTextField text1 = new JTextField();  // 输入的是目标文件夹
    private JTextField text2 = new JTextField();  // 输入的是关键字
    private JLabel label1 = new JLabel("移动或解压目标文件夹:");  // 标签1
    private JLabel label2 = new JLabel("查询或移动关键字:");  // 标签2
    private JScrollPane scrollPane_1 = new JScrollPane();  // 滚动条
    private static JTextArea area = new JTextArea(10, 40);  // 文本域
    private JButton button = new JButton("开始执行");  // 按钮
    ActionHandle handle = new ActionHandle();  // 事件监听

    public Ui() {
        //添加监听事件
        button.addActionListener(handle);
        // 设置窗口位于屏幕中间
        int sWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int sHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        int winX = (sWidth - 500) / 2;
        int winY = (sHeight - 300) / 2;
        frame.setBounds(winX + 50, winY + 50, 700, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // 退出、最小化、关闭
        frame.setLayout(null);  // 空白布局

        int midX = 700 / 2 - 120 / 2;  // 窗口中间位置
        int midY = 400 / 2 - 30 / 2;


        text1.setBounds(midX + 50, midY - 35, 200, 25);
        frame.add(text1);  // 向窗口添加text1


        text2.setBounds(midX + 50, midY - 75, 200, 25);
        frame.add(text2);


        label1.setBounds(midX - 100, midY - 35, 140, 30);
        frame.add(label1);


        label2.setBounds(midX - 80, midY - 75, 110, 30);
        frame.add(label2);


        scrollPane_1.setBounds(0, 130, 175, 120);
        scrollPane_1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);  // 默认显示滚动条
        frame.getContentPane().add(scrollPane_1);

        area.setBounds(0, 130, 175, 120);
        scrollPane_1.setViewportView(area);  // 向文件域中添加滚动条


        button.setSize(120, 30);
        button.setBounds(midX, midY, button.getWidth(), button.getHeight());
        frame.add(button);

        frame.setVisible(true);
    }

    /**
     * @author Susongfeng
     * @Title: ActionHandle
     * @Package view
     * @Description: ActionListener Class
     * @date 2020/5/10 00:58
     */
    class ActionHandle implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == button) {
                try {
                    Move_Files.move_Files(text1.getText(), text2.getText());  // 将两个文本框中内容当作参数传入
                    System.out.println("GUI.out:移动成功");  // 终端测试可观，与control层输出区分开
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                    System.out.println("GUI.out:移动失败");  // 终端测试可观，与control层输出区分开
                }
            }
        }
    }

    public static void setArea(String text) {  // 输出内容到文本域
        area.append(text + '\n');
    }

    public static void setArea(int n) {  // 输出内容到文本域
        area.append("成功移动文件： " + n + " 个\n");
    }

    public static void main(String[] args) {  // GUI测试
        new Ui();
    }
}
