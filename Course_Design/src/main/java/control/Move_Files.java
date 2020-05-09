package control;

import view.Ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * @author Susongfeng
 * @Title: Move_Files
 * @Package: control
 * @Description: Move files with keywords from the current directory to the specified folder
 * @date 2020/5/917:13
 */
public class Move_Files {
    @SuppressWarnings("all")
    public static void move_Files(String str1, String str2) {
        int total = 0;
        File folder = new File(System.getProperty("user.dir"));  // 获取当期路径下的文件和文件夹
        File[] files = folder.listFiles();  // 筛选出文件成数组
        for (int i = 0; i < files.length; i++) {  // 遍历文件通过特定字进行分类  采用renameto  永久重命名移动
            if (files[i].isFile()) {
                String name = files[i].getName();
                if (name.indexOf(str2) != -1) {
                    files[i].renameTo(new File(System.getProperty("user.dir") + "/" + str1 + "/" + files[i].getName()));
                    Ui.setArea(name);
                    total++;
                }
            }
        }
        Ui.setArea(total);
        System.out.println("移动成功");
    }

    public static void main(String[] args) {
        move_Files("test", "test");
    }
}



