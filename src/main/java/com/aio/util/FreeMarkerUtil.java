package com.aio.util;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.*;

/**
 * @author:hahaha
 * @creattime:2021-12-02 10:33
 */

public class FreeMarkerUtil {


    /**
     * 复制path目录下所有文件
     * @param path  文件目录 不能以/开头
     * @param newpath 新文件目录
     */
    public static void BatCopyFileFromJar(String path,String newpath) {
        if (!new File(newpath).exists()){
            new File(newpath).mkdir();
        }
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            //获取所有匹配的文件
            Resource[] resources = resolver.getResources(path+"/*");
            //打印有多少文件
            for(int i=0;i<resources.length;i++) {
                Resource resource=resources[i];
                try {
                    //以jar运行时，resource.getFile().isFile() 无法获取文件类型，会报异常，抓取异常后直接生成新的文件即可；以非jar运行时，需要判断文件类型，避免如果是目录会复制错误，将目录写成文件。
                    if(resource.getFile().isFile()) {
                        makeFile(newpath+"/"+resource.getFilename());
                        InputStream stream = resource.getInputStream();
                        write2File(stream, newpath+"/"+resource.getFilename());
                    }
                }catch (Exception e) {
                    makeFile(newpath+"/"+resource.getFilename());
                    InputStream stream = resource.getInputStream();
                    write2File(stream, newpath+"/"+resource.getFilename());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 创建文件
     * @param path  全路径 指向文件
     * @return
     */
    public static boolean makeFile(String path) {
        File file = new File(path);
        if(file.exists()) {
            return false;
        }
        if (path.endsWith(File.separator)) {
            return false;
        }
        if(!file.getParentFile().exists()) {
            if(!file.getParentFile().mkdirs()) {
                return false;
            }
        }
        try {
            if (file.createNewFile()) {
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * 输入流写入文件
     *
     * @param is
     *            输入流
     * @param filePath
     *            文件保存目录路径
     * @throws IOException
     */
    public static void write2File(InputStream is, String filePath) throws IOException {
        OutputStream os = new FileOutputStream(filePath);
        int len = 8192;
        byte[] buffer = new byte[len];
        while ((len = is.read(buffer, 0, len)) != -1) {
            os.write(buffer, 0, len);
        }
        os.close();
        is.close();
    }
    /**
     *处理异常报错（springboot读取classpath里的文件，解决打jar包java.io.FileNotFoundException: class path resource cannot be opened）
     **/
    public static File getFileFromClassPath(String path){
        File targetFile = new File(path);
        if(!targetFile.exists()){
            if(targetFile.getParent()!=null){
                File parent=new File(targetFile.getParent());
                if(!parent.exists()){
                    parent.mkdirs();
                }
            }
            InputStream initialStream=null;
            OutputStream outStream =null;
            try {
                Resource resource=new ClassPathResource(path);
                //注意通过getInputStream，不能用getFile
                initialStream=resource.getInputStream();
                byte[] buffer = new byte[initialStream.available()];
                initialStream.read(buffer);
                outStream = new FileOutputStream(targetFile);
                outStream.write(buffer);
            } catch (IOException e) {
            } finally {
                if (initialStream != null) {
                    try {
                        initialStream.close(); // 关闭流
                    } catch (IOException e) {
                    }
                }
                if (outStream != null) {
                    try {
                        outStream.close(); // 关闭流
                    } catch (IOException e) {
                    }
                }
            }
        }
        return targetFile;
    }
}

