package com.lucien.utils;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import static java.lang.System.out;

/**
 * <pre>
 *     author : Lucien Z
 *     e-mail : 825038797@qq.com
 *     time   : 2020/1/8
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class FileUtils {
    /**
     * replace file name with specific string recurcively
     * @param path the dir including the target files
     * @param str2Replacement the str to be replace
     * @param substitute the str to substitute str2Replacement
     * @throws ReplacementException
     */
    public static void replaceFileName(String path,String str2Replacement,String substitute) throws ReplacementException {
        File dir = new File(path);
        if(!dir.isDirectory()) throw new ReplacementException("np this dir");
        File[] files = dir.listFiles((dir1, name) -> {
            return name.contains(str2Replacement);
        });
        for (File f:files) {
            if(f == null) continue;
            if(f.isDirectory()){
                replaceFileName(f.getAbsolutePath(),str2Replacement,substitute);
            }else {
                String newFilename = f.getName().replace(str2Replacement, substitute);
                File desFile = new File(dir, newFilename);
                f.renameTo(desFile);
            }
        }
    }

    public static void replaceStrInTextFile(String path,String str2Replacement,String substitute) throws ReplacementException {
        File file = new File(path);
        if(file.isDirectory()) throw new ReplacementException("not a file");
        try {
            String textContent = readFile(path);
            out.println("replaceStrInTextFile---"+textContent);
            writeFile(path,
                    textContent.replaceAll(str2Replacement,
                    substitute));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String readFile(String filename) throws IOException {
        File file = new File(filename);
        int len = (int) file.length();
        byte[] bytes = new byte[len];
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            fis.read(bytes);
        } catch (IOException e) {
            close(fis);
            throw e;
        }
        return new String(bytes, "UTF-8");
    }

    public static void writeFile(String filename, String text) throws IOException {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(filename);
            fos.write(text.getBytes("UTF-8"));
        } catch (IOException e) {
            close(fos);
            throw e;
        }
    }

    public static void close(Closeable closeable) {
        try {
            closeable.close();
        } catch(IOException ignored) {
        }
    }

    public static void main(String[] args) {
/*        try {
            FileUtils.replaceFileName("D:\\bzl_code\\mygithub\\testdir","8","12");
        } catch (ReplacementException e) {
            e.printStackTrace();
        }*/

        try {
            FileUtils.replaceStrInTextFile("D:\\bzl_code\\mygithub\\testdir\\replace.txt","RT",
                    "KKKKKK");
        } catch (ReplacementException e) {
            e.printStackTrace();
        }
    }
}
