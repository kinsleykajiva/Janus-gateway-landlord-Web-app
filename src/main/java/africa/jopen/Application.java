package africa.jopen;

import africa.jopen.configs.utils.JanusOverFilesWrites;
import africa.jopen.utils.JanusUtils;
import africa.jopen.utils.XUtils;
import io.micronaut.runtime.Micronaut;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Logger;

import static africa.jopen.utils.AdministratorChecker.IS_RUNNING_AS_ADMINISTRATOR;
import static africa.jopen.utils.JanusUtils.copyDemoFiles;
import static africa.jopen.utils.JanusUtils.runDemosServers;
import static africa.jopen.utils.XUtils.DEMOS_DESTINATION_FOLDER;
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
        try {
            String getHostAddress = String.valueOf(InetAddress.getLocalHost().getHostAddress());
            XUtils.MACHINE_PUBLIC_IP = String.valueOf(InetAddress.getLocalHost().getHostAddress());
            logger.info("Ip Address : " +getHostAddress);
        } catch (UnknownHostException e) {
            e.printStackTrace();
            logger.severe(e.getMessage());
            XUtils.MACHINE_PUBLIC_IP=null;
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
