package cz.kacirekj.domain;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.*;
import org.apache.http.client.*;
import org.apache.http.cookie.*;

import java.time.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Session {
    protected LocalDateTime sessionCreationDateTime;
    protected String userDisplayName;
    
    @JsonDeserialize(as = SessionCookieStore.class)
    protected SessionCookieStore cookieStore;
    
    public Session() {
        this.cookieStore = new SessionCookieStore();
    }
    
    public Session(CookieStore cookieStore) {
        this.sessionCreationDateTime = LocalDateTime.now();
        this.cookieStore.setCookies(cookieStore.getCookies().toArray(new Cookie[0]));
    }

    public LocalDateTime getSessionCreationDateTime() {
        return sessionCreationDateTime;
    }
    
    public void setSessionCreationDateTime(LocalDateTime sessionCreationDateTime) {
        this.sessionCreationDateTime = sessionCreationDateTime;
    }
    
    public CookieStore getCookieStore() {
        return cookieStore;
    }
    
    public void setCookieStore(CookieStore cookieStore) {
        this.cookieStore = new SessionCookieStore();
        this.cookieStore.setCookies(cookieStore.getCookies().toArray(new Cookie[0]));
    }
}
