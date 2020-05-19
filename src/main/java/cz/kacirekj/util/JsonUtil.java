package cz.kacirekj.util;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.*;

public class JsonUtil {
    private static ObjectMapper objectMapper;
    
    public static ObjectMapper getMapper() {
        if(objectMapper == null) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            mapper.findAndRegisterModules();
            objectMapper = mapper;
        }
        
        return objectMapper;
    }
}
