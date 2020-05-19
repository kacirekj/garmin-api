package cz.kacirekj.domain;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.core.*;
import cz.kacirekj.util.*;

import java.io.*;
import java.util.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GarminSession implements Serializable {
    protected Session session;
    protected Map<String,Object> userPreferences;
    protected Map<String,Object> socialProfile;
    
    public GarminSession() {
        session = new Session();
    }
    
    public static GarminSession createFromJson(String json) throws IOException {
        return JsonUtil.getMapper().readValue(json, GarminSession.class);
    }
    
    public String toJson() throws JsonProcessingException {
        return JsonUtil.getMapper().writeValueAsString(this);
    }
    
    @JsonIgnore
    public String getDisplayName() {
        return (String) socialProfile.get("displayName");
    }
    
    public Session getSession() {
        return session;
    }
    
    public void setSession(Session session) {
        this.session = session;
    }
    
    public Map<String, Object> getUserPreferences() {
        return userPreferences;
    }
    
    public void setUserPreferences(Map<String, Object> userPreferences) {
        this.userPreferences = userPreferences;
    }
    
    public Map<String, Object> getSocialProfile() {
        return socialProfile;
    }
    
    public void setSocialProfile(Map<String, Object> socialProfile) {
        this.socialProfile = socialProfile;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GarminSession that = (GarminSession) o;
        return Objects.equals(session, that.session) &&
                Objects.equals(userPreferences, that.userPreferences) &&
                Objects.equals(socialProfile, that.socialProfile);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(session, userPreferences, socialProfile);
    }
    
    @Override
    public String toString() {
        return "GarminSession{" +
                "session=" + session +
                ", userPreferences=" + userPreferences +
                ", socialProfile=" + socialProfile +
                '}';
    }
}
