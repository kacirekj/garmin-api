package cz.kacirekj.util;

import java.io.*;

public class FileUtil {
    public static String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
    
    public static String getResource(String resourceName) {
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(resourceName);
        return convertStreamToString(inputStream);
    }
    
    public static String getFileContent(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        try {
            InputStream inputStream = new FileInputStream(f);
            return convertStreamToString(inputStream);
        } catch (Exception e) {
            return "";
        }
    }
    
    public static void setFileContent(String filePath, String content) throws FileNotFoundException {
        try(PrintWriter writer = new PrintWriter(new File(filePath))) {
            writer.print(content);
        }
    }
}
