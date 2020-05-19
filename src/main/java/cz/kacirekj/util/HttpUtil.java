package cz.kacirekj.util;

import org.apache.http.*;

import java.io.*;
import java.util.*;

public class HttpUtil {
    
    public static String toResponseBody(HttpResponse response) throws IOException {
        StringBuilder result = new StringBuilder();
        Scanner sc = new Scanner(response.getEntity().getContent());
        while (sc.hasNext()) {
            String s = sc.nextLine();
            result.append(s).append("\n");
        }
        
        return result.toString();
    }
}
