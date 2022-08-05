package africa.jopen.utils;

import africa.jopen.Application;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.logging.Logger;

import static africa.jopen.utils.AdministratorChecker.IS_RUNNING_AS_ADMINISTRATOR;
import static africa.jopen.utils.XUtils.*;

public class JanusUtils {
    final static Logger logger = Logger.getLogger(JanusUtils.class.getSimpleName());
    private static String JANUS_CONFIG_FODLER = "/var/snap/janus-gateway/common/etc";
   private static String JANUS_RECORDING_FODLER = "/var/snap/janus-gateway/common/share/recordings";


    public static void makeFoldersAccessible(){
        if(!new File(JANUS_CONFIG_FODLER).canWrite()){
            new File(JANUS_CONFIG_FODLER).setWritable(true);
            executeBashCommand("sudo chmod -R 777 "+JANUS_CONFIG_FODLER);
            logger.info("Set to write" + JANUS_CONFIG_FODLER);
        }
         if(!new File(JANUS_RECORDING_FODLER).canWrite()){
             new File(JANUS_RECORDING_FODLER).setWritable(true);
             executeBashCommand("sudo chmod -R 777 "+JANUS_RECORDING_FODLER);
             logger.info("Set to write " + JANUS_RECORDING_FODLER);
        }


    }

    /**
     * This is restarting the Janus instance server .
     * This operation is blocking .
     */
    public static synchronized boolean restartJanus() {
        executeBashCommand("sudo snap restart janus-gateway");
        var result = XUtils.getLastExecuteBashCommandResponses().toString();
        if(result.equals("Restarted.") || result.contains("Restarted.")){
            return true;
        }

        return false;
    }

    /**
     * This is remove the Janus instance server .
     * This needs to be run from the terminal is the app was wwith out Admin previledge
     */
    public static synchronized void unInstallJanus() {
        executeBashCommand("sudo snap remove janus-gateway");
    }

    public static synchronized String checkIfJanusIsInstalled() {
        if (IS_RUNNING_AS_ADMINISTRATOR) {
            executeBashCommand("sudo snap list ");
            var result = XUtils.getLastExecuteBashCommandResponses().toString();
           return Arrays.
                   stream(result.split(System.lineSeparator()))
                   .filter(x->x.contains("janus-gateway"))
                   .findFirst()
                   .orElse("Not Found");



        }
        return "Not Found";
    }
    public static synchronized void installJanus() {
        if (IS_RUNNING_AS_ADMINISTRATOR) {
            if(checkIfJanusIsInstalled().contains("Not Found1")){
                executeBashCommand("sudo snap install janus-gateway ");
            }else{
                logger.info("Cant install it already exists");
            }


        }
    }
//    sudo cp /home/variable-k/.janus-landlord/configs/janus.jcfg  /var/snap/janus-gateway/common/share/voicemail/janus.jcfg
    public static void savePlugin(String jcfgFile) {
        Path sourceDirectory = Paths.get(CONFIG_FOLDER + File.separator + jcfgFile);
        Path targetDirectory = Paths.get(JANUS_CONFIG_FODLER + File.separator + jcfgFile);

        logger.info("Copying file " + CONFIG_FOLDER + File.separator + jcfgFile + " to " + JANUS_CONFIG_FODLER + File.separator + jcfgFile+"" );

        try {
            Files.copy(sourceDirectory, targetDirectory, StandardCopyOption.REPLACE_EXISTING);
            Thread.sleep(2_000);
            // restartJanus();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }

    }


}
