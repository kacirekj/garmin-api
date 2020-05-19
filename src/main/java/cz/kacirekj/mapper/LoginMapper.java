package cz.kacirekj.mapper;

import com.fasterxml.jackson.databind.*;
import cz.kacirekj.exception.*;

import java.util.*;
import java.util.regex.*;

public class LoginMapper {
    public static Map<String,Object> mapToSocialProfile(String body) throws LoginGarminException {
        try {
            Pattern p = Pattern.compile("(VIEWER_SOCIAL_PROFILE.*?JSON.parse..)(\\{.*?\\})(\\\"\\))");
            Matcher m = p.matcher(body);
            m.find();
            String json = m.group(2);
            json = json.replace("\\\"", "\"");
            
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(json, Map.class);
        } catch (Exception e) {
            throw new LoginGarminException("parsing social profile");
        }
    }
    
    public static Map<String,Object> mapToUserPreferences(String body) throws LoginGarminException {
        try {
            Pattern p = Pattern.compile("(VIEWER_USERPREFERENCES.*?JSON.parse..)(\\{.*?\\})(\\\"\\))");
            Matcher m = p.matcher(body);
            m.find();
            String json = m.group(2);
            json = json.replace("\\\"", "\"");
            
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(json, Map.class);
        } catch (Exception e) {
            throw new LoginGarminException("parsing user preferences");
        }
    }
    
    public static String mapToTicketUrl(String body) throws LoginGarminException {
        try {
            Pattern p = Pattern.compile("(response_url.*\")(https.*?ticket=.*?)(\")");
            Matcher m = p.matcher(body);
            m.find();
            
            String urlRaw = m.group(2);
            return urlRaw.replace("\\", "");
        } catch (Exception e) {
            throw new LoginGarminException("parsing ticketUrl from login web page");
        }
    }
}
