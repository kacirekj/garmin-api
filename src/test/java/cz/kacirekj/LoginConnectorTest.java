package cz.kacirekj;

import com.fasterxml.jackson.databind.*;
import cz.kacirekj.connector.*;
import cz.kacirekj.domain.*;
import cz.kacirekj.exception.*;
import cz.kacirekj.util.*;
import org.junit.*;

import java.io.*;
import java.time.*;
import java.util.*;
import java.util.concurrent.*;

@Ignore
public class LoginConnectorTest {
    String garminUserJson;
    ObjectMapper objectMapper = JsonUtil.getMapper();
    
    String email;
    String password;
    
    @Before
    public void initialize() throws IOException, LoginGarminException, TooManyRequestsGarminException, HttpErrorGarminException {
        String filePath = "recentGarminDomain.json";
        String json = FileUtil.getFileContent(filePath);
        
        try {
            GarminSession garminSession = objectMapper.readValue(json, GarminSession.class);
            DataConnector dataConnector = new DataConnector(garminSession);
            Map<String,Object> userSummary = dataConnector.getUserSummary(LocalDate.now().minusDays(1));
            if(userSummary.get("totalSteps") == null) {
                System.out.println("Recent " + filePath +" session is no longer valid. Create new one.");
                throw new Exception();
            }
            garminUserJson = json;
            System.out.println("Recent " + filePath +" session is still valid. Will be reused.");
        } catch (Exception e) {
            LoginConnector loginConnector1 = new LoginConnector();
            GarminSession garminSession = loginConnector1.login(email, password);
            garminUserJson = garminSession.toJson();
            FileUtil.setFileContent(filePath, garminUserJson);
            
        }
    }
    
    @Test
    public void shallReturnStatsWithLongTimeDeserializedCookieStore() throws IOException, InterruptedException {
        GarminSession garminSession = GarminSession.createFromJson(garminUserJson);
        DataConnector dataConnector = new DataConnector(garminSession);
        
        System.out.println(garminSession);
        
        int count = 0;
        while(true) {
            try {
                Map<String,Object> stats = dataConnector.getHeartRates(LocalDate.now().minusDays(1));
                System.out.println(stats);
                stats = dataConnector.getUserSummary(LocalDate.now().minusDays(1));
                System.out.println(stats);
    
            } catch(Exception e) {
                System.out.println(e.getMessage());
                break;
            }
            
            count++;
            System.out.println(count);
            TimeUnit.MILLISECONDS.sleep(100);
            
            if(count == 10) {
                break;
            }
        }
    }
    
}
