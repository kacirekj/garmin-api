package cz.kacirekj.domain;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.*;
import org.apache.http.cookie.*;
import org.apache.http.impl.client.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SessionCookieStore extends BasicCookieStore {
    
    @JsonDeserialize(as = SessionCookie[].class)
    public void setCookies(Cookie[] cookies) {
        for(Cookie cookie : cookies) {
            addCookie(cookie);
        }
    }
    
    @Override
    public void addCookie(Cookie cookie) {
        super.addCookie(new SessionCookie(cookie));
    }
}
