package africa.jopen;

import africa.jopen.configs.utils.JanusOverFilesWrites;
import africa.jopen.security.AuthenticationProviderUserPassword;
import africa.jopen.utils.JanusUtils;
import africa.jopen.utils.XUtils;
import io.micronaut.context.ApplicationContext;
import io.micronaut.context.annotation.Parameter;
import io.micronaut.context.annotation.Value;
import io.micronaut.runtime.Micronaut;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.logging.Logger;

import static africa.jopen.utils.AdministratorChecker.IS_RUNNING_AS_ADMINISTRATOR;
import static africa.jopen.utils.JanusUtils.copyDemoFiles;
import static africa.jopen.utils.JanusUtils.runDemosServers;
import static africa.jopen.utils.XUtils.DEMOS_DESTINATION_FOLDER;
import static africa.jopen.utils.XUtils.logInfo;


@OpenAPIDefinition(info = @Info(title = "Janus-gateway-landlord-Web-app",version = "0.1"))
public class Application {
    final static Logger       logger = Logger.getLogger(Application.class.getSimpleName());

    public static void main(String[] args) {

        logger.info("Started application ");
        logger.info("System args passed   " + args);
        Arrays.stream(args)
                .forEach (x -> {
           if(x.contains("basicWebAuthUserName")){
               AuthenticationProviderUserPassword.INIT_USERNAME = x.split("=")[1];
           }
             if(x.contains("basicWebAuthPassword")){
                 AuthenticationProviderUserPassword.INIT_PASSWORD = x.split("=")[1];
           }
        });

        logger.info("Passed 1-> " +  AuthenticationProviderUserPassword.INIT_USERNAME);
        logger.info("Passed 2-> " +  AuthenticationProviderUserPassword.INIT_PASSWORD);

        XUtils.setKnownIssuesSinceStartUp("startup-time",new Timestamp(System.currentTimeMillis()).toString());
        XUtils.setKnownIssuesSinceStartUp("time-zone-name",String.valueOf(TimeZone.getDefault().getDisplayName()) );
        XUtils.setKnownIssuesSinceStartUp("time-zone-id",String.valueOf(TimeZone.getDefault().getID()) );


        logger.info("Running as Admin " + IS_RUNNING_AS_ADMINISTRATOR);
        if(!IS_RUNNING_AS_ADMINISTRATOR) {
            logger.severe("App is not running AS Admin");
            logger.severe("App is exiting");
            System.exit(1);
        }
        logInfo("Running as Admin " + IS_RUNNING_AS_ADMINISTRATOR);
        logger.info("Started application ");
        try {
            String getHostAddress = String.valueOf(InetAddress.getLocalHost().getHostAddress());
            XUtils.MACHINE_PUBLIC_IP = String.valueOf(InetAddress.getLocalHost().getHostAddress());
            logger.info("Ip Address : " +getHostAddress);
            XUtils.setKnownIssuesSinceStartUp("ip-address",getHostAddress );
        } catch (UnknownHostException e) {
            e.printStackTrace();
            logger.severe(e.getMessage());
            XUtils.MACHINE_PUBLIC_IP=null;
            XUtils.setKnownIssuesSinceStartUp("ip-address-Exception",e.getMessage() );
            //throw new RuntimeException(e);
        }
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



       // new Thread(() -> XUtils.executeBashCommand("sudo snap logs janus-gateway -f | stdbuf -o0 grep abc", null)).start();
         Micronaut.run(Application.class, args);

        runDemosServers();
        copyDemoFiles();
        JanusOverFilesWrites.saveOverWrite(JanusOverFilesWrites.getSettingsJs() , DEMOS_DESTINATION_FOLDER+"/settings.js");




    }
}
