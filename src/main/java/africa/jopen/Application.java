package africa.jopen;

import africa.jopen.configs.Janus;
import africa.jopen.configs.plugins.Sip;
import africa.jopen.configs.transports.Http;
import africa.jopen.configs.transports.Websockets;
import africa.jopen.json.janus.JanusObject;
import africa.jopen.utils.AdministratorChecker;
import africa.jopen.utils.JanusUtils;
import africa.jopen.utils.XUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micronaut.logging.LoggingSystem;
import io.micronaut.runtime.Micronaut;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.info.*;
import jakarta.inject.Inject;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.Map;
import java.util.logging.Logger;

import static africa.jopen.utils.AdministratorChecker.IS_RUNNING_AS_ADMINISTRATOR;
import static africa.jopen.utils.XUtils.logInfo;


@OpenAPIDefinition(
        info = @Info(
                title = "Janus-gateway-landlord-Web-app",
                version = "0.1"
        )
)
public class Application {
    final static Logger logger = Logger.getLogger(Application.class.getSimpleName());



    public static void main(String[] args) {
        logger.info("Started application ");
        logger.info("Running as Admin " + IS_RUNNING_AS_ADMINISTRATOR);
        logInfo("Running as Admin " + IS_RUNNING_AS_ADMINISTRATOR);
        logger.info("Started application ");
        XUtils.createSystemFolders();
        JanusUtils.makeFoldersAccessible();




       /* try {
            // create object mapper instance
            ObjectMapper mapper = new ObjectMapper();

            // convert JSON string to Book object
            JanusObject janusConfigs  = mapper.readValue(Paths.get("/home/variable-k/.janus-landlord/configs/janus.jcfg.json").toFile(), JanusObject.class);
            String jsonStr = mapper.writeValueAsString(janusConfigs);

            // Displaying JSON String on console
            System.out.println("xxx->>"+jsonStr);
            // print book
            System.out.println("====xxx="+janusConfigs);

        } catch (Exception ex) {
            ex.printStackTrace();
        }*/



//       new Janus().saveFromDefaults();
       /* new Http().saveFromDefaults();
        new Websockets().saveFromDefaults();
        new Sip().saveFromDefaults();
        new Websockets().saveFromDefaults();
*/
         Micronaut.run(Application.class, args);
    }
}
