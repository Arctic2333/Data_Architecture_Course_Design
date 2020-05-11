package control;

import view.Ui;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

/**
 * @author Susongfeng & 林瑜捷 & 刘嵘海
 * @Title: Search
 * @Package control
 * @Description: 全盘快速搜索
 * @date 2020/5/10 23:28
 */
public class Search extends Thread {
    private File dir_path;
    private String key_root;

    public Search(File dir, String key) {  // 构造方法给多线程run传参数
        this.dir_path = dir;
        this.key_root = key;
    }

    /**
     * @Description: 多线程
     * @Param: null
     * @return: void
     * @Date: 2020/5/11
     */
    public void run() {
        try {
            search(dir_path, key_root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @Description: 获取系统中的所有的盘符，并且分开线程进行搜索
     * @Param: key
     * @return: void
     * @Date: 2020/5/10
     */
    public static void search(String key) throws IOException {

        File[] root = File.listRoots();  // 获取系统中的所有盘符
        for (File file : root) {  // 全部盘符中搜索
            Search sr1 = new Search(file, key);
            sr1.start();
        }
    }

    /**
     * @Description: 在树形存储结构下DFS
     * @Param: [dir, key]
     * @return: void
     * @Date: 2020/5/10
     */
    public static void search(File dir, String key) throws IOException {
        if (dir.exists() && dir.isDirectory()) {  // 判断路径是否正确 && 是否为文件夹
            LinkedList<File> list = new LinkedList<>();  // 队列
            list.addFirst(dir);
            int length = 0;
            File temp;
            while (!list.isEmpty()) {  // BFS
                length = list.size();
                for (int i = 0; i < length; i++) {
                    temp = list.pollFirst();  // 出队
                    if (temp.isDirectory()) {  // 判断是否为文件夹
                        File[] tempArr = temp.listFiles();  // 获取文件夹下的所有文件加入队列
                        if (tempArr != null)  // 空检查，防止空指针异常
                            for (int j = 0; j < tempArr.length; j++) {
                                if (tempArr[j].isDirectory()) {
                                    list.addLast(tempArr[j]);
                                } else {
                                    String name = tempArr[j].getName();  // 获取文件名
                                    if (name.indexOf(key) != -1) {  // 判断是否带有关键字
                                        String outPath = tempArr[j].getAbsolutePath();  // 获取绝对路径
                                        Ui.setArea(outPath);  // 输出在文本域中
                                        System.out.println(outPath);  // 终端可见路径
                                    }
                                }
                            }
                    } else {
                        String name = temp.getName();  // 获取文件名
                        if (name.indexOf(key) != -1) {  // 判断是否带有关键字
                            String outPath = temp.getAbsolutePath();  // 获取绝对路径
                            Ui.setArea(outPath);  // 输出在文本域中
                            System.out.println(outPath);  // 终端可见路径
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {  // test
        try {
            search("测试使用复杂名");
            System.out.println("**********搜索结束********");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
