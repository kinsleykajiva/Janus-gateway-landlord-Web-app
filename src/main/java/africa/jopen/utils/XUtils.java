package africa.jopen.utils;

import africa.jopen.Application;
import africa.jopen.websockets.JanusLogsWebsockets;
import io.micronaut.websocket.WebSocketBroadcaster;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.graalvm.polyglot.*;
import org.graalvm.polyglot.proxy.*;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormatSymbols;
import java.util.Arrays;

public class XUtils {
    private static final Logger logger = LoggerFactory.getLogger(XUtils.class);
    public static String ROOT_FOLDER = "";
    public static String CONFIG_FOLDER = "";
    public static String DEMOS_DESTINATION_FOLDER = "";
    public static String MACHINE_PUBLIC_IP = "";
    private static JSONObject KNOWN_ISSUES_SINCE_START_UP = new JSONObject();
    private final static String[] configFileNames = new String[]{
            "janus.jcfg",
            "janus.plugin.sip.jcfg",
            "janus.transport.http.jcfg",
            "janus.transport.websockets.jcfg",
/*the following is just load or make a reference but this can be done better or saved elsewhere*/
            "janus.jcfg.json",
            "janus.plugin.sip.jcfg.json",
            "janus.transport.http.jcfg.json",
            "janus.transport.websockets.jcfg.json",


    };

    public static void setKnownIssuesSinceStartUp(String name,String knownIssuesSinceStartUp) {
        KNOWN_ISSUES_SINCE_START_UP .put(name,knownIssuesSinceStartUp);
    }

    public static JSONObject getKnownIssuesSinceStartUp() {
        return KNOWN_ISSUES_SINCE_START_UP;
    }

    //sudo ./mvnw mn:run

    public static void createSystemFolders() {

        String path = "/opt";
        path += File.separator + ".janus-landlord";
        DEMOS_DESTINATION_FOLDER = path;
        File customDir = new File(path);
        File configs = new File(path + File.separator + "configs");

        if (customDir.exists()) {
            logger.info(customDir + " already exists" );
            logInfo(customDir + " already exists");
        } else if (customDir.mkdirs()) {
            logger.info(customDir + " was created");
            logInfo(customDir + " was created");
        } else {
            logger.info(customDir + " was not created");
            logInfo(customDir + " was not created");
        }


        if (configs.exists()) {
            logger.info(configs + " already exists");
        } else if (configs.mkdirs()) {
            logger.info(configs + " was created");
        } else {
            logger.info(configs + " was not created");
        }
        ROOT_FOLDER = customDir.getAbsolutePath();
        CONFIG_FOLDER = configs.getAbsolutePath();
        logger.info(ROOT_FOLDER + " :: ROOT_FOLDER");
        DEMOS_DESTINATION_FOLDER= DEMOS_DESTINATION_FOLDER + File.separator + "demoDocs";

        Arrays.stream(configFileNames).toList().forEach(file -> {
            try {
                if (!new File((CONFIG_FOLDER + File.separator + file)).exists()) {
                    Writer fileWriter = new FileWriter(CONFIG_FOLDER + File.separator + file, false);
                    fileWriter.close();
                }

            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        });


    }

    public static void logInfo(String message) {
        System.out.println(message);
    }
    public static void logError(String message) {
        System.err.println(message);
    }

    public static String readFileFromResources(String filename) throws URISyntaxException, IOException {

        try {
            URL resource = Application.class.getClassLoader().getResource(filename);
            byte[] bytes = Files.readAllBytes(Paths.get(resource.toURI()));
            return new String(bytes);
        } catch (IOException | URISyntaxException | java.nio.file.FileSystemNotFoundException e) {
            e.printStackTrace();
            logger.error("Access Error Occurred,its alright access from native image module perspective !");
            String moduleName = "java.base";
            String resourcePath = "/" + filename;
            Module resource1 = ModuleLayer.boot().findModule(moduleName).get();
            InputStream ins = resource1.getResourceAsStream(resourcePath);
            if (ins == null) {
                logger.info(" now trying to load from Class");
                ins = Application.class.getResourceAsStream(resourcePath);
            }
            if (ins != null) {
                StringBuilder sb = new StringBuilder();
                for (int ch; (ch = ins.read()) != -1; ) {
                    sb.append((char) ch);
                }
                return sb.toString();
            }
        }
        return null;
    }

    public static String testIfToQoute(String value) {
        return value.equals("true") || value.equals("false") || XUtils.isNumeric(value) ?
                (value) :
                "".concat("\"").concat(value).concat("\"");
    }

    private static String readFileFromResourcesLargeFiles(String fileName) {
        URL resource = Application.class.getClassLoader().getResource(fileName);

        if (resource == null)
            throw new IllegalArgumentException("file is not found!");

        StringBuilder fileContent = new StringBuilder();

        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(resource.getFile()));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                fileContent.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return fileContent.toString();
    }

    public static boolean isNumeric(final String input) {
        //Check for null or blank string
        if (input == null || input.isBlank()) return false;

        //Retrieve the minus sign and decimal separator characters from the current Locale
        final var localeMinusSign = DecimalFormatSymbols.getInstance().getMinusSign();
        final var localeDecimalSeparator = DecimalFormatSymbols.getInstance().getDecimalSeparator();

        //Check if first character is a minus sign
        final var isNegative = input.charAt(0) == localeMinusSign;
        //Check if string is not just a minus sign
        if (isNegative && input.length() == 1) return false;

        var isDecimalSeparatorFound = false;

        //If the string has a minus sign ignore the first character
        final var startCharIndex = isNegative ? 1 : 0;

        //Check if each character is a number or a decimal separator
        //and make sure string only has a maximum of one decimal separator
        for (var i = startCharIndex; i < input.length(); i++) {
            if (!Character.isDigit(input.charAt(i))) {
                if (input.charAt(i) == localeDecimalSeparator && !isDecimalSeparatorFound) {
                    isDecimalSeparatorFound = true;
                } else return false;
            }
        }
        return true;
    }

    private static StringBuilder lastExecuteBashCommandResponses = new StringBuilder();

    public static StringBuilder getLastExecuteBashCommandResponses() {
        return lastExecuteBashCommandResponses;
    }

    /**
     * Execute a bash command. We can handle complex bash commands including
     * multiple executions (; | && ||), quotes, expansions ($), escapes (\), e.g.:
     * "cd /abc/def; mv ghi 'older ghi '$(whoami)"
     *
     * @param command
     * @return true if bash got started, but your command may have failed.
     */
    public static boolean executeBashCommand(String command) {
        lastExecuteBashCommandResponses.delete(0 , lastExecuteBashCommandResponses.length());
        boolean success = false;
        logger.info("Executing BASH command:\n   " + command);
        Runtime r = Runtime.getRuntime();
        // Use bash -c so we can handle things like multi commands separated by ; and
        // things like quotes, $, |, and \. My tests show that command comes as
        // one argument to bash, so we do not need to quote it to make it one thing.
        // Also, exec may object if it does not have an executable file as the first thing,
        // so having bash here makes it happy provided bash is installed and in path.
        String[] commands = {"bash", "-c", command};
        try {
            Process p = r.exec(commands);

            p.waitFor();
            BufferedReader b = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = "";

            while ((line = b.readLine()) != null) {
                lastExecuteBashCommandResponses.append(line).append(System.lineSeparator());
                logger.info(line);
                System.out.println(line);
            }

            b.close();
            success = true;
        } catch (Exception e) {
        //    logger.error("Failed to execute bash with command: " + command);
            e.printStackTrace();
        }
        return success;
    }

    public static boolean executeBashCommand(String command, WebSocketBroadcaster broadcaster){

        boolean success = false;
        logger.info("Executing BASH command:\n   " + command);
        Runtime r = Runtime.getRuntime();
        // Use bash -c so we can handle things like multi commands separated by ; and
        // things like quotes, $, |, and \. My tests show that command comes as
        // one argument to bash, so we do not need to quote it to make it one thing.
        // Also, exec may object if it does not have an executable file as the first thing,
        // so having bash here makes it happy provided bash is installed and in path.
        String[] commands = {"bash", "-c", command};

        try {
            Process p = r.exec(commands);

            p.waitFor();
            BufferedReader b = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = "";
            logInfo("xxxxxx");
            while ((line = b.readLine()) != null) {
                if(broadcaster != null){
                    broadcaster.broadcast(String.format("%s", line));
                }

                logger.info(line);
                System.out.println(line);
            }

            b.close();
            success = true;
        } catch (Exception e) {
            //    logger.error("Failed to execute bash with command: " + command);
            e.printStackTrace();
        }
        return success;
    }
}
