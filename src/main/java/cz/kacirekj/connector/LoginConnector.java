package cz.kacirekj.connector;

import cz.kacirekj.domain.*;
import cz.kacirekj.exception.*;
import cz.kacirekj.mapper.*;
import cz.kacirekj.util.*;
import org.apache.http.*;
import org.apache.http.client.methods.*;
import org.apache.http.entity.*;
import org.apache.http.impl.client.*;

import java.io.*;

public class LoginConnector {
    
    protected final String LOGIN_URL;
    protected final Header[] HEADRES;
    protected final GarminSession garminSession;
    protected final DefaultHttpClient httpClient;
    
    public LoginConnector() {
        this.LOGIN_URL = Constants.LOGIN_URL;
        this.HEADRES = Constants.HEADERS;
        this.garminSession = new GarminSession();
        this.httpClient = new DefaultHttpClient();
        this.httpClient.setCookieStore(this.garminSession.getSession().getCookieStore());
    }
    
    public GarminSession login(String email, String password) throws LoginGarminException, TooManyRequestsGarminException, IOException, HttpErrorGarminException {
        String loginPage = getLoginPage(email, password);
        
        String ticketUrl;
        ticketUrl = LoginMapper.mapToTicketUrl(loginPage);
        
        String loggedinPage = getLoggedinPage(ticketUrl);
        garminSession.setSocialProfile(LoginMapper.mapToSocialProfile(loggedinPage));
        garminSession.setUserPreferences(LoginMapper.mapToUserPreferences(loggedinPage));
        
        return garminSession;
    }
    
    private String getLoginPage(String email, String password) throws IOException, TooManyRequestsGarminException, HttpErrorGarminException {
        HttpEntity httpEntity = new StringEntity("username=" + email + "&password=" + password + "&embed=false");
        
        HttpPost httpPost = new HttpPost(LOGIN_URL);
        httpPost.setHeaders(HEADRES);
        httpPost.addHeader("Content-type", "application/x-www-form-urlencoded");
        httpPost.setEntity(httpEntity);
        
        HttpResponse httpResponse = httpClient.execute(httpPost);
    
        HttpExceptionHandler.handleReponseError(httpResponse);
        
        return HttpUtil.toResponseBody(httpResponse);
    }
    
    private String getLoggedinPage(String loginUrl) throws IOException, TooManyRequestsGarminException, HttpErrorGarminException {
        HttpGet httpGet = new HttpGet(loginUrl);
        httpGet.setHeaders(HEADRES);
        
        HttpResponse httpResponse = httpClient.execute(httpGet);
    
        HttpExceptionHandler.handleReponseError(httpResponse);
    
        return HttpUtil.toResponseBody(httpResponse);
    }
}
