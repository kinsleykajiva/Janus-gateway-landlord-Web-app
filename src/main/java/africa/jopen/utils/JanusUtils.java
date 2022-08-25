package africa.jopen.utils;

import africa.jopen.enums.Installations;
import com.sun.net.httpserver.HttpServer;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.logging.Logger;

import static africa.jopen.utils.AdministratorChecker.IS_RUNNING_AS_ADMINISTRATOR;
import static africa.jopen.utils.XUtils.*;

public class JanusUtils {
	final static   Logger logger                 = Logger.getLogger(JanusUtils.class.getSimpleName());
	public static  String JANUS_CONFIG_FODLER    = "";
	public static  boolean IS_JANUS_ONLINE           = false;
	private static String JANUS_RECORDING_FODLER = "";
	private static String JANUS_DEMO_FODLER      = "";
	private static boolean CAN_EXECUTE_FILE_COMMANDS = Boolean.TRUE;

	private static Installations installation;

	public static void initUtils (int installType) {
		if (installType == 0) {
			installation = Installations.SNAP_INSTALLATION;
			JANUS_CONFIG_FODLER = "/var/snap/janus-gateway/common/etc";
			JANUS_DEMO_FODLER = "/snap/janus-gateway/current/opt/janus/share/janus/demos";
			JANUS_RECORDING_FODLER = "/var/snap/janus-gateway/common/share/recordings";
		} else if (installType == 1) {
			installation = Installations.NATIVE_INSTALLATION;
			JANUS_CONFIG_FODLER = "/opt/janus/etc/janus";
			JANUS_DEMO_FODLER = "/opt/janus/share/janus/demos";
			JANUS_RECORDING_FODLER = "/opt/janus/share/janus/recordings";
		}
		XUtils.setKnownIssuesSinceStartUp("installation-type", String.valueOf(installation));
		var fonderCheck = new File(JANUS_CONFIG_FODLER);
		if (!fonderCheck.exists()) {
			CAN_EXECUTE_FILE_COMMANDS = Boolean.FALSE;
		}


	}

	public static boolean isCanExecuteFileCommands () {
		return CAN_EXECUTE_FILE_COMMANDS;
	}

	public static void runDemosServers () {
		HttpServer server = null;
		try {
			server = HttpServer.create(new InetSocketAddress(3200), 0);
			server.createContext("/", new StaticFileHandler("/", JANUS_DEMO_FODLER, "echotest.html"));
			server.setExecutor(null);
			server.start();
		} catch (IOException e) {
			logger.severe(e.getMessage());
		}
	}

	/**
	 * This attempts to make sure the config folders are writable only .
	 */
	public static void makeFoldersAccessible () {
		var janusConfigs = new File(JANUS_CONFIG_FODLER);
		if (janusConfigs.exists() && !janusConfigs.canWrite()) {
			janusConfigs.setWritable(true);
			executeBashCommand("sudo chmod -R 777 " + JANUS_CONFIG_FODLER);
			logger.info("Set to write" + JANUS_CONFIG_FODLER);
		}
		var janusRecordingConfigs = new File(JANUS_RECORDING_FODLER);
		if (janusRecordingConfigs.exists() && !janusRecordingConfigs.canWrite()) {
			janusRecordingConfigs.setWritable(true);
			executeBashCommand("sudo chmod -R 777 " + JANUS_RECORDING_FODLER);
			logger.info("Set to write " + JANUS_RECORDING_FODLER);
		}
	}

	/**
	 * This is restarting the Janus instance server .
	 * This operation is blocking .
	 */
	public static synchronized boolean restartJanus () {
		if (installation == Installations.NATIVE_INSTALLATION) {
			// Restarted supervisord
			executeBashCommand("sudo supervisorctl reload");
			var result = XUtils.getLastExecuteBashCommandResponses().toString();
			if (result.equals("Restarted supervisord") || result.contains("Restarted supervisord.")) {
				return true;
			}

		}
		if (installation == Installations.SNAP_INSTALLATION) {
			executeBashCommand("sudo snap restart janus-gateway");
			var result = XUtils.getLastExecuteBashCommandResponses().toString();
			return result.equals("Restarted.") || result.contains("Restarted.");

		}


		return false;
	}

	/**
	 * This is remove the Janus instance server .
	 * This needs to be run from the terminal is the app was wwith out Admin previledge
	 */
	public static synchronized void unInstallJanus () {
		executeBashCommand("sudo snap remove janus-gateway");
	}

	public static synchronized String checkIfJanusIsInstalled () {
		if (IS_RUNNING_AS_ADMINISTRATOR) {
			if (installation == Installations.NATIVE_INSTALLATION) {
				executeBashCommand("ps cax | grep janus | grep -o '^[ ]*[0-9]*'");
				var result = XUtils.getLastExecuteBashCommandResponses()
						.toString()
						.trim()
						.replaceAll("\\s+", "")
						.replace(System.getProperty("line.separator"), "")
						.replace("\t", "");
				// if found we are just going to get the PID of the file

				return result.isEmpty() ? "--running--".concat(result) : "Not Found";

			}
			if (installation == Installations.SNAP_INSTALLATION) {
				executeBashCommand("sudo snap list ");
				var result = XUtils.getLastExecuteBashCommandResponses().toString();
				return Arrays.
						stream(result.split(System.lineSeparator()))
						.filter(x -> x.contains("janus-gateway"))
						.findFirst()
						.orElse("Not Found");
			}
			return "Not Found";

		}
		return "Not Found";
	}

	public static synchronized void installJanus () {
		if (IS_RUNNING_AS_ADMINISTRATOR) {
			if (installation == Installations.SNAP_INSTALLATION) {
				if (checkIfJanusIsInstalled().contains("Not Found1")) {
					executeBashCommand("sudo snap install janus-gateway ");
				} else {
					logger.info("Cant install it already exists");
				}
			}
			if (installation == Installations.NATIVE_INSTALLATION) {
				logger.info("Cant install native-installation");
			}


		}
	}

	//    sudo cp /home/variable-k/.janus-landlord/configs/janus.jcfg  /var/snap/janus-gateway/common/share/voicemail/janus.jcfg
	public static void savePlugin (String jcfgFile) {
		if (!CAN_EXECUTE_FILE_COMMANDS) {
			logger.info("Files not found");
			return;
		}
		Path sourceDirectory = Paths.get(CONFIG_FOLDER + File.separator + jcfgFile);
		Path targetDirectory = Paths.get(JANUS_CONFIG_FODLER + File.separator + jcfgFile);

		logger.info("Copying file " + CONFIG_FOLDER + File.separator + jcfgFile + " to " + JANUS_CONFIG_FODLER + File.separator + jcfgFile + "");

		try {
			Files.copy(sourceDirectory, targetDirectory, StandardCopyOption.REPLACE_EXISTING);
			Thread.sleep(1_000);

		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}

	}

	public static void copyDemoFiles () {
		if (new File(DEMOS_DESTINATION_FOLDER).exists()) {
			logger.info("Already Has Demo Files");
			return;
		}
		Path sourceDirectory = Paths.get(JANUS_DEMO_FODLER);
		Path targetDirectory = Paths.get(DEMOS_DESTINATION_FOLDER);
		copy(sourceDirectory, targetDirectory);

		//logger.info("Copying file " + JANUS_DEMO_FODLER + " to " + DEMOS_DESTINATION_FOLDER);

		try {
          /*  Path sourcepath = Paths.get(JANUS_DEMO_FODLER);
            Path destinationepath = Paths.get(DEMOS_DESTINATION_FOLDER + File.separator + "demoDocs");*/
			Files.walk(sourceDirectory)
					.forEach(source -> copy(source, targetDirectory.resolve(sourceDirectory.relativize(source))));
			Thread.sleep(1_000);

		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
	}

	static void copy (Path source, Path dest) {
		if (new File(DEMOS_DESTINATION_FOLDER).exists()) {
			logger.info("Already Has Demo Files");
			return;
		}
		try {
			Files.copy(source, dest, StandardCopyOption.REPLACE_EXISTING);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
}
