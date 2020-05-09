package control;

import view.Ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * @author Susongfeng
 * @Title: move_Files
 * @Package: control
 * @Description: Move files with keywords from the current directory to the specified folder
 * @date 2020/5/917:13
 */
public class move_Files {
    move_Files() {
        final Ui ui = new Ui();
        ui.button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String str1 = ui.text1.getText();
                String str2 = ui.text2.getText();
                int total = 0;
                File folder = new File(System.getProperty("user.dir"));  // 获取当期路径下的文件和文件夹
                File[] files = folder.listFiles();  // 筛选出文件成数组

                for (int i = 0; i < files.length; i++) {  // 遍历文件通过特定字进行分类  采用renameto  永久重命名移动
                    if (files[i].isFile()) {
                        String name = files[i].getName();
                        if (name.indexOf(str2) != -1) {
                            files[i].renameTo(new File(System.getProperty("user.dir") + "/" + str1 + "/" + files[i].getName()));
                            ui.area.append(name + "\n");
                            total++;
                        }
                    }
                }
                ui.area.append("成功移动文件： " + total + " 个");
            }
        });
    }
}
