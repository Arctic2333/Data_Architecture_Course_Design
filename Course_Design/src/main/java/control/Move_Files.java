package control;

import com.sun.org.glassfish.gmbal.Description;
import view.Ui;

import java.io.File;
import java.io.IOException;

/**
 * @author Susongfeng
 * @Title: Move_Files
 * @Package: control
 * @Description: Move files with keywords from the current directory to the specified folder
 * @date 2020/5/9 17:13
 */
public class Move_Files {
    @SuppressWarnings("all")
    /**
     * @Description: 指定到指定目录和移动文件名中带有关键字的文件
     * @Param: [str1, str2]
     * @return: void
     * @Date: 2020/5/9
     */
    public static void move_Files(String str1, String str2) throws IOException {  // str1：目标文件夹  str2：文件名关键字
        int total = 0;
        File folder = new File(System.getProperty("user.dir"));  // 获取当期路径下的文件和文件夹
        File[] files = folder.listFiles();  // 将获取的文件及文件夹列表导入数组
        for (int i = 0; i < files.length; i++) {  // 遍历文件
            if (files[i].isFile()) {  // 判断是否为文件
                String name = files[i].getName();  // 获取文件名字
                if (name.indexOf(str2) != -1) {  // 判断是否带有关键字
                    // 采用方法renameto()永久重命名移动
                    files[i].renameTo(new File(System.getProperty("user.dir") + "/" + str1 + "/" + files[i].getName()));
                    Ui.setArea(name);  // 输出移动成功的文件名到文件域
                    total++;  // 移动成功文件数加一
                }
            }
        }
        Ui.setArea(total);  // 输出移动成功的文件数
        System.out.println("*********************移动成功**********************");  // 终端测试可观
        System.out.println("成功移动：" + total + "个");  // 终端测试可观
        System.out.println("当前工作路径："+System.getProperty("user.dir"));  // 终端测试可观 当前路径
        return;
    }

    public static void main(String[] args) {  // 功能1，逻辑功能测试
        try {
            move_Files("test", "test");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



