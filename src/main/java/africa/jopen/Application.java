package africa.jopen;

import africa.jopen.configs.Janus;
import africa.jopen.configs.plugins.Sip;
import africa.jopen.configs.transports.Http;
import africa.jopen.configs.transports.Websockets;
import africa.jopen.utils.XUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.micronaut.logging.LoggingSystem;
import io.micronaut.runtime.Micronaut;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.info.*;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@OpenAPIDefinition(
    info = @Info(
            title = "Janus-gateway-landlord-Web-app",
            version = "0.1"
    )
)
public class Application {
    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    // snap remove janus-gateway
    public static void main(String[] args) {


       /* String[] cmd = {"/bin/bash","-c","sudo snap list"};
        Process pb = Runtime.getRuntime().exec(cmd);

        String line;
        BufferedReader input = new BufferedReader(new InputStreamReader(pb.getInputStream()));
        while ((line = input.readLine()) != null) {
            System.out.println(line);
        }
        input.close();
*/

        logger.info("Started applicationX");

        //  XUtils.executeBashCommand("sudo snap install janus-gateway ");
//        XUtils.executeBashCommand("ls");
          new Janus();
          new Http();
          new Websockets();
        new Sip();
            new Websockets();


        // Micronaut.run(Application.class, args);
    }
}
