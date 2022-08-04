package africa.jopen;

import africa.jopen.configs.Janus;
import africa.jopen.configs.plugins.Sip;
import africa.jopen.configs.transports.Http;
import africa.jopen.configs.transports.Websockets;
import africa.jopen.utils.AdministratorChecker;
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

import static africa.jopen.utils.AdministratorChecker.IS_RUNNING_AS_ADMINISTRATOR;


@OpenAPIDefinition(
        info = @Info(
                title = "Janus-gateway-landlord-Web-app",
                version = "0.1"
        )
)
public class Application {
    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        logger.info("Started application ");

        logger.info("Running as Admin " + IS_RUNNING_AS_ADMINISTRATOR);

        XUtils.createSystemFolders();


        new Janus().saveFromDefaults();
        new Http();
        new Websockets();
        new Sip();
        new Websockets();


        // Micronaut.run(Application.class, args);
    }
}
