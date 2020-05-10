package control;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * @author Susongfeng
 * @Title: Un_Zip
 * @Package control
 * @Description: 实现文件解压
 * @date 2020/5/10 14:57
 */
public class Un_Zip {
    @SuppressWarnings("all")
    /**
     * @Description:
     * @Param: [descDir]  目标文件夹路径
     * @return: void
     * @Date: 2020/5/10
     */
    public static void un_Zip(String descDir) throws IOException {
        File folder = new File(descDir);  // 获取当前目录下所有文件
        File[] files = folder.listFiles();  // 所有文件加成数组

        for (int i = 0; i < files.length; i++) {  // 循环
            if (files[i].isFile()) {  // 判断是否为文件
                String fileName = files[i].getName();  // 获取文件名字
                if (fileName.indexOf(".zip") != -1) {  // 判断文件是否为zip文件
                    ZipFile zip = new ZipFile(files[i], Charset.forName("GBK"));  // 转换成zipFile并解决中文文件夹乱码
                    String name = zip.getName().substring(zip.getName().lastIndexOf('\\') + 1, zip.getName().lastIndexOf('.'));  // 解压后文件名称

                    File pathFile = new File(descDir + "/" + name);  // 输出文件地址
                    if (!pathFile.exists()) {
                        pathFile.mkdirs();  // 如果当前文件夹下不含输出文件的文件夹即自动创建文件夹
                    }

                    for (Enumeration<? extends ZipEntry> entries = zip.entries(); entries.hasMoreElements(); ) {
                        ZipEntry entry = (ZipEntry) entries.nextElement();  // 创建压缩文件中的条目并将创建好的条目加入压缩文件
                        String zipEntryName = entry.getName();
                        InputStream in = zip.getInputStream(entry);  // 创建流对象加入压缩条目
                        String outPath = (descDir + "/" + name + "/" + zipEntryName).replaceAll("\\*", "/");  // 输出路径

                        // 判断路径是否存在,不存在则创建文件路径
                        File file = new File(outPath.substring(0, outPath.lastIndexOf('/')));
                        if (!file.exists()) {
                            file.mkdirs();
                        }
                        // 判断文件全路径是否为文件夹,如果是上面已经上传,不需要解压
                        if (new File(outPath).isDirectory()) {
                            continue;
                        }
                        // 创建流对象写入当前条目所对应的具体内容
                        FileOutputStream out = new FileOutputStream(outPath);
                        byte[] buf1 = new byte[1024];
                        int len;
                        while ((len = in.read(buf1)) > 0) {
                            out.write(buf1, 0, len);
                        }
                        // 关闭流对象
                        in.close();
                        out.close();
                    }


                }
            }

        }
        System.out.println("******************解压完毕********************");
        return;
    }

    public static void main(String[] args) {  // 测试使用
        try {
            un_Zip("D:/Data_Architecture_Course_Design/test");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
