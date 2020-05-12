package control;

import com.github.junrar.Junrar;
import com.github.junrar.exception.RarException;

import java.io.File;
import java.io.IOException;

/**
 * @author 林瑜捷
 * @version V1.0
 * @Package cn.Demo
 * @Description: 解压RAR
 * @date 2020/5/9
 */
public class Un_Rar {
    public static void main(String[] args) {
        try {
            System.out.println(unAllRar("D:/Data_Architecture_Course_Design/test"));
        } catch (IOException | RarException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查找文件目录下所有的RAR文件
     *
     * @param descDir 文件夹的绝对路径
     * @return 文件是否存在 && 是文件夹 && 解压成功
     */
    public static boolean unAllRar(String descDir) throws IOException, RarException {
        File DirectoryPath = new File(descDir);
        if (DirectoryPath.exists() && DirectoryPath.isDirectory()) {
            //获取路径下的文件
            File[] files = DirectoryPath.listFiles();
            assert files != null;
            for (File file : files) {
                if (file.getAbsolutePath().toLowerCase().endsWith(".rar")) {
                    try {
                        un_Rar(descDir, file);
                    } catch (IOException | RarException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            return false;
        }
        return true;
    }

    /**
     * 解压RAR文件至当前的文件目录
     *
     * @param descDir 文件目录绝对路径
     * @param rarFile RAR文件
     * @return void
     */


    public static void un_Rar(String descDir, File rarFile) throws IOException, RarException {
        //创建解压后的文件夹
        File Directory = new File(descDir + File.separator + rarFile.getName().substring(0, rarFile.getName().lastIndexOf(".")));
        if (!Directory.exists()) {
            Directory.mkdirs();
        }
        Junrar.extract(rarFile, Directory);
        return;
    }
}
