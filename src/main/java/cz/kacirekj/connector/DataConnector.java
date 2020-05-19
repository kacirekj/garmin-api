package cz.kacirekj.connector;

import com.fasterxml.jackson.databind.*;
import cz.kacirekj.domain.*;
import cz.kacirekj.util.*;
import org.apache.http.*;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.*;

import java.io.*;
import java.time.*;
import java.util.*;

public class DataConnector {
    
    protected final String BASE_URL = Constants.BASE_URL;
    protected final String USERSUMMARY_URL = BASE_URL + "proxy/usersummary-service/usersummary/daily/";
    protected final String DAILY_HEART_RATE_URL = BASE_URL + "proxy/wellness-service/wellness/dailyHeartRate/";
    protected final Header[] headers = Constants.HEADERS;
    protected final GarminSession garminSession;
    protected final String displayName;
    protected final DefaultHttpClient httpClient;
    protected final ObjectMapper objectMapper = JsonUtil.getMapper();
    
    public DataConnector(GarminSession garminSession) {
        this.httpClient = new DefaultHttpClient();
        this.httpClient.setCookieStore(garminSession.getSession().getCookieStore());
        this.garminSession = garminSession;
        this.displayName = garminSession.getDisplayName();
    }
    
    public Map<String,Object> getUserSummary(LocalDate localDate) throws IOException {
        HttpGet httpGet = new HttpGet(USERSUMMARY_URL
                + displayName + "?"
                + "calendarDate=" + localDate.toString()
        );
        httpGet.setHeaders(headers);
        HttpResponse httpResponse = httpClient.execute(httpGet);
        
        String body = HttpUtil.toResponseBody(httpResponse);
        
        return objectMapper.readValue(body, Map.class);
    }
    
    public Map<String,Object> getHeartRates(LocalDate localDate) throws IOException {
        HttpGet httpGet = new HttpGet(DAILY_HEART_RATE_URL
                + displayName + "?"
                + "date=" + localDate.toString()
        );
        httpGet.setHeaders(headers);
        HttpResponse httpResponse = httpClient.execute(httpGet);
        
        String body = HttpUtil.toResponseBody(httpResponse);
        
        return objectMapper.readValue(body, Map.class);
    }
    
}
