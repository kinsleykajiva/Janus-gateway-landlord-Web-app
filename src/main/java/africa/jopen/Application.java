package africa.jopen;

import africa.jopen.configs.Janus;
import africa.jopen.configs.eventhandlers.SampleEventHandler;
import africa.jopen.configs.plugins.Sip;
import africa.jopen.configs.transports.Http;
import africa.jopen.configs.transports.Websockets;
import africa.jopen.configs.utils.JanusOverFilesWrites;
import africa.jopen.database.mongodb.LazyMongoDB;
import africa.jopen.security.AuthenticationProviderUserPassword;
import africa.jopen.utils.JanusUtils;
import africa.jopen.utils.XUtils;
import com.github.wnameless.json.flattener.JsonFlattener;
import io.micronaut.runtime.Micronaut;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.json.JSONObject;

import java.io.File;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.TimeZone;
import java.util.function.UnaryOperator;
import java.util.logging.Logger;

import static africa.jopen.utils.AdministratorChecker.IS_RUNNING_AS_ADMINISTRATOR;
import static africa.jopen.utils.JanusUtils.copyDemoFiles;
import static africa.jopen.utils.JanusUtils.runDemosServers;
import static africa.jopen.utils.XUtils.CONFIG_FOLDER;
import static africa.jopen.utils.XUtils.DEMOS_DESTINATION_FOLDER;


@OpenAPIDefinition (info = @Info (title = "Janus-gateway-landlord-Web-app", version = "0.1"))
public class Application {

	static final Logger logger = Logger.getLogger(Application.class.getSimpleName());

	public static void main (String[] args) {
		logger.info("System args passed   " + Arrays.toString(args));
		if (!Arrays.toString(args).contains("janusInstall=")) {
			JanusUtils.initUtils(0);
		}

		Arrays.stream(args)
				.forEach(x -> {
					if (x.contains("basicWebAuthUserName") && x.split("=").length > 1) {
						AuthenticationProviderUserPassword.INIT_USERNAME = x.split("=")[1];
					}
					if (x.contains("basicWebAuthPassword") && x.split("=").length > 1) {
						AuthenticationProviderUserPassword.INIT_PASSWORD = x.split("=")[1];
					}
					if (x.contains("mongoPORT") && x.split("=").length > 1) {
						LazyMongoDB.DB_PORT = Integer.parseInt(x.split("=")[1]);
					}
					if (x.contains("mongoHOST") && x.split("=").length > 1) {
						LazyMongoDB.DB_HOST = x.split("=")[1];
					}
					if (x.contains("mongoPASSWORD") && x.split("=").length > 1) {
						LazyMongoDB.DB_PASSWORD = x.split("=")[1];
					}
					if (x.contains("mongoUSERNAME") && x.split("=").length > 1) {
						LazyMongoDB.DB_USERNAME = x.split("=")[1];
					}
					if (x.contains("mongoNAME") && x.split("=").length > 1) {
						LazyMongoDB.DB_NAME = x.split("=")[1];
					}
					if (x.contains("janusInstall") && x.split("=").length > 1) {
						JanusUtils.initUtils(Integer.parseInt(x.split("=")[1]));
					}
				});

		homeKeeping();
		Micronaut.run(Application.class, args);
		runDemosServers();
		copyDemoFiles();
		JanusOverFilesWrites.saveOverWrite(JanusOverFilesWrites.getSettingsJs(), DEMOS_DESTINATION_FOLDER + "/settings.js");

	}


	private static void homeKeeping () {

		XUtils.setKnownIssuesSinceStartUp("app-version", "0.7");
		XUtils.setKnownIssuesSinceStartUp("startup-time", new Timestamp(System.currentTimeMillis()).toString());
		XUtils.setKnownIssuesSinceStartUp("time-zone-name", String.valueOf(TimeZone.getDefault().getDisplayName()));
		XUtils.setKnownIssuesSinceStartUp("time-zone-id", String.valueOf(TimeZone.getDefault().getID()));

		if (!IS_RUNNING_AS_ADMINISTRATOR) {
			logger.severe("***************App is not running AS Admin ,Exiting By***************");
			System.exit(1);
		}

		if (!JanusUtils.isCanExecuteFileCommands()) {
			logger.severe("***************Janus Not Found App is exiting***************");
			System.exit(1);
		}

		try {
			String getHostAddress = String.valueOf(InetAddress.getLocalHost().getHostAddress());
			XUtils.MACHINE_PUBLIC_IP = String.valueOf(InetAddress.getLocalHost().getHostAddress());
			logger.info("Ip Address : " + getHostAddress);
			XUtils.setKnownIssuesSinceStartUp("ip-address", getHostAddress);
		} catch (UnknownHostException e) {
			e.printStackTrace();
			logger.severe(e.getMessage());
			XUtils.MACHINE_PUBLIC_IP = null;
			XUtils.setKnownIssuesSinceStartUp("ip-address-Exception", e.getMessage());
		}
		XUtils.createSystemFolders();
		JanusUtils.makeFoldersAccessible();
		LazyMongoDB.getInstance();

		// we are testing if the file is empty , if so its becuase the file just got created by the server
		if (new File(CONFIG_FOLDER + File.separator + Janus.FileName).length() == 0) {
			new Janus().saveToLocalBackCopy();
		}

		if (new File(CONFIG_FOLDER + File.separator + Http.FileName).length() == 0) {
			new Http().saveFromDefaults();
		}
		if (new File(CONFIG_FOLDER + File.separator + Websockets.FileName).length() == 0) {
			new Websockets().saveFromDefaults();
		}

		if (new File(CONFIG_FOLDER + File.separator + Sip.FileName).length() == 0) {
			new Sip().saveFromDefaults();
		}
		if (new File(CONFIG_FOLDER + File.separator + SampleEventHandler.FileName).length() == 0) {
			new SampleEventHandler().saveFromDefaults();
		}


	}


}
