package africa.jopen.utils;

import static africa.jopen.utils.AdministratorChecker.IS_RUNNING_AS_ADMINISTRATOR;
import static africa.jopen.utils.XUtils.executeBashCommand;

public class JanusUtils {

    private static  String JANUS_CONFIG_FODLER = "/var/snap/janus-gateway/common/etc";
    private static  String JANUS_RECORDING_FODLER = "/var/snap/janus-gateway/common/share/recordings";


    /**
     * This is restarting the Janus instance server .
     * This operation is blocking .
     */
    public static synchronized void restartJanus() {
        executeBashCommand("sudo snap restart janus-gateway");
    }

    /**
     * This is remove the Janus instance server .
     * This needs to be run from the terminal is the app was wwith out Admin previledge
     */
    public static synchronized void unInstallJanus() {
        executeBashCommand("sudo snap remove janus-gateway");
    }

    public static synchronized void installJanus() {
        if (IS_RUNNING_AS_ADMINISTRATOR) {
            executeBashCommand("sudo snap install janus-gateway ");

        }
    }




}
