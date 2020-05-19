package cz.kacirekj.domain;

import com.fasterxml.jackson.annotation.*;
import org.apache.http.cookie.*;
import org.apache.http.impl.cookie.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SessionCookie extends BasicClientCookie {
    
    @JsonCreator()
    public SessionCookie(@JsonProperty("name") String name, @JsonProperty("value") String value) {
        super(name, value);
    }
    
    public SessionCookie(Cookie cookie) {
        super(cookie.getName(), cookie.getValue());
        this.setComment(cookie.getComment());
        this.setExpiryDate(cookie.getExpiryDate());
        this.setDomain(cookie.getDomain());
        this.setPath(cookie.getPath());
        this.setVersion(cookie.getVersion());
    }
}
