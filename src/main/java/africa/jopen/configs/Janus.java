package africa.jopen.configs;

import africa.jopen.utils.XUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
//import org.apache.commons.text.StringSubstitutor;
import africa.jopen.json.janus.JanusObject;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.micronaut.core.annotation.ReflectiveAccess;
import io.micronaut.logging.LogLevel;
import io.micronaut.logging.LoggingSystem;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.json.JSONObject;
//import org.jboss.logging.Logger;

//import javax.inject.Inject;

@ReflectiveAccess
public class Janus {
    private  final Logger logger = LoggerFactory.getLogger(Janus.class);
  //  @Inject
   // Logger log;
    final String FileName = "janus.jcfg";
    final String FileNameJson = FileName+".json";
    boolean isFileFound = false;
    private JanusObject janusConfigs ;


    public Janus() throws JsonProcessingException {
        try {
            logger.info("hey");
            logger.debug("hey");
            logger.error("hey");
            // loads the default data
            String jsonJanus = XUtils. readFileFromResources("configs/"+FileNameJson);

           /* Gson gson = new Gson();
            JanusObject pojo = gson.fromJson(jsonJanus, new TypeToken<JanusObject>() {}.getType());
            System.out.println("getAdmin_secret: " + pojo.getGeneral().getTransports_folder().getLineValue());*/

            ObjectMapper om = new ObjectMapper();

          //  System.out.println("vvvvv"+jsonJanus);
//            janusConfigs = om.readValue(jsonJanus, JanusObject.class);
            janusConfigs = om.readValue(jsonJanus,  new TypeReference<>(){});
            System.out.println("getAdmin_secret: " + janusConfigs.getGeneral().getTransports_folder().getLineValue());
            logger.info("getAdmin_secret: " + janusConfigs.getGeneral().getTransports_folder().getLineValue());
         //   log.info(janusConfigs.getGeneral().getAdmin_secret().getLineValue());

        } catch (URISyntaxException | IOException e) {
            // throw new RuntimeException(e);
          //  log.error(e.getMessage());
            e.printStackTrace();
        }


    }


    private void buildGeneral(){
        Map<String, String> valuesMap = new HashMap<>();



//        StringSubstitutor sub = new StringSubstitutor(valuesMap);
//        String resolvedString = sub.replace(general);
    }
}
