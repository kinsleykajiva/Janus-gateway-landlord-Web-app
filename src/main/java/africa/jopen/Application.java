package africa.jopen;

import africa.jopen.configs.utils.JanusOverFilesWrites;
import africa.jopen.database.mongodb.LazyMongoDB;
import africa.jopen.security.AuthenticationProviderUserPassword;
import africa.jopen.utils.JanusUtils;
import africa.jopen.utils.XUtils;
import com.github.wnameless.json.flattener.JsonFlattener;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import io.micronaut.runtime.Micronaut;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.bson.Document;


import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.util.Arrays;
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

    public static void main(String[] args)  {

        logger.info("Started application ");
        logger.info("System args passed   " + Arrays.toString(args));
        Arrays.stream(args)
                .forEach (x -> {

           if(x.contains("basicWebAuthUserName")&& x.split("=").length > 1){
               AuthenticationProviderUserPassword.INIT_USERNAME = x.split("=")[1];
           }
            if(x.contains("basicWebAuthPassword")&& x.split("=").length > 1){
                AuthenticationProviderUserPassword.INIT_PASSWORD = x.split("=")[1];
            }

           if(x.contains("mongoPORT") && x.split("=").length > 1 ){
               LazyMongoDB.DB_PORT = Integer.parseInt(x.split("=")[1]);
           }
           if(x.contains("mongoHOST") && x.split("=").length > 1 ){
               LazyMongoDB.DB_HOST = x.split("=")[1];
           }
           if(x.contains("mongoPASSWORD") && x.split("=").length > 1 ){
               LazyMongoDB.DB_PASSWORD = x.split("=")[1];
           }
           if(x.contains("mongoUSERNAME") && x.split("=").length > 1 ){
               LazyMongoDB.DB_USERNAME = x.split("=")[1];
           }
           if(x.contains("mongoNAME") && x.split("=").length > 1 ){
               LazyMongoDB.DB_NAME = x.split("=")[1];
           }


        });



        logger.info("Passed 1-> " +  AuthenticationProviderUserPassword.INIT_USERNAME);
        logger.info("Passed 2-> " +  AuthenticationProviderUserPassword.INIT_PASSWORD);
        logger.info("Passed 3-> " +   LazyMongoDB.DB_HOST);

        XUtils.setKnownIssuesSinceStartUp("app-version","0.6");
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

//       new Janus().saveFromDefaults();
       /* new Http().saveFromDefaults();
        new Websockets().saveFromDefaults();
        new Sip().saveFromDefaults();
        new Websockets().saveFromDefaults();
*/

        LazyMongoDB.getInstance();

       // new Thread(() -> XUtils.executeBashCommand("sudo snap logs janus-gateway -f | stdbuf -o0 grep abc", null)).start();
        System.out.println("YYYYYY=>||||");
         Micronaut.run(Application.class, args);
        System.out.println("XXXXX=>||||");
        runDemosServers();
        copyDemoFiles();
        JanusOverFilesWrites.saveOverWrite(JanusOverFilesWrites.getSettingsJs() , DEMOS_DESTINATION_FOLDER+"/settings.js");
     //   logger.info("********************************");


    }


}
