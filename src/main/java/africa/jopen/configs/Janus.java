package africa.jopen.configs;

import africa.jopen.json.janus.JanusObject;
import africa.jopen.utils.XUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.micronaut.core.annotation.ReflectiveAccess;
import org.apache.commons.text.StringSubstitutor;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Stream;

import static africa.jopen.utils.XUtils.CONFIG_FOLDER;
import static africa.jopen.utils.XUtils.testIfToQoute;
import static java.util.logging.Logger.getLogger;


@ReflectiveAccess
public class Janus {
    private final Logger logger = getLogger(Janus.class.getSimpleName());


    public static final String FileName = "janus.jcfg";
    final String FileNameJson = FileName + ".json";
    boolean isFileFound = false;
    private JanusObject janusConfigs;
    private String jsonJanus;
    private String CONFIG = "";


    public Janus() {
        try {
            // loads the default data
            jsonJanus = XUtils.readFileFromResources("configs/" + FileNameJson);
            ObjectMapper om = new ObjectMapper();
            om.enable(SerializationFeature.WRAP_ROOT_VALUE);
            janusConfigs = om.readValue(jsonJanus, new TypeReference<>() {
            });

        } catch (URISyntaxException | IOException e) {
            logger.severe(e.getMessage());
            e.printStackTrace();
        }

        buildGeneral();
    }

    public JanusObject loadCurrentSettings() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
        try {
            JanusObject janusObject = mapper.readValue(Paths.get(CONFIG_FOLDER + File.separator + FileNameJson).toFile(), JanusObject.class);
            return janusObject;
        } catch (IOException e) {
            logger.severe(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public String loadCurrentSettingsJCFG () {
        try {
            Path filePath = Path.of(CONFIG_FOLDER + File.separator + FileName);
            return Files.readString(filePath);
        } catch (IOException e) {
            return null;
        }
    }

    public void saveToLocalBackCopy() {
        try {
            logger.info("saveFromDefaults ->  ");
            Writer fileWriter = new FileWriter(CONFIG_FOLDER + File.separator + FileName, false);
            fileWriter.write(CONFIG);
            fileWriter.close();

            Writer fileWriterJSON = new FileWriter(CONFIG_FOLDER + File.separator + FileNameJson, false);
            fileWriterJSON.write(jsonJanus);
            fileWriterJSON.close();

        } catch (IOException e) {
            logger.severe(e.getMessage());
        }
    }

    /**To update the local configs waiting to be copied to janus folders
     * @param json the whole json to be loaded as  a object and to be saved to the json file
     * @return bool , if true configs have been saved to local , if false then the file has failed to save to local configs folder can not proceed to set as janus config .
     * */
    public boolean updateToBuild(String json){
        jsonJanus = json;

        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
            janusConfigs = mapper.readValue(jsonJanus, new TypeReference<>() {
            });
            buildGeneral();
            saveToLocalBackCopy();
            return true;
        } catch (JsonProcessingException e) {
            logger.severe(e.getMessage());
        }
        return false;
    }


/** to be the builder of the settings to make a string to be saved to the jcfg files
 * */
    private void buildGeneral() {

        Map<String, String> valuesMap = new HashMap<>();
        JSONObject obj = new JSONObject(jsonJanus);
        String resolvedString = "";
        resolvedString = getStringJsonFactory(valuesMap, obj.getJSONObject("general"), "general");
        resolvedString += getStringJsonFactory(valuesMap, obj.getJSONObject("certificates"), "certificates");
        resolvedString += getStringJsonFactory(valuesMap, obj.getJSONObject("media"), "media");
        resolvedString += getStringJsonFactory(valuesMap, obj.getJSONObject("nat"), "nat");
        resolvedString += getStringJsonFactory(valuesMap, obj.getJSONObject("loggers"), "loggers");
        resolvedString += getStringJsonFactory(valuesMap, obj.getJSONObject("plugins"), "plugins");
        resolvedString += getStringJsonFactory(valuesMap, obj.getJSONObject("transports"), "transports");
        resolvedString += getStringJsonFactory(valuesMap, obj.getJSONObject("events"), "events");
        CONFIG = resolvedString;
       // logger.info(resolvedString);
    }

    private String getStringJsonFactory(Map<String, String> valuesMap, JSONObject general, String level) {
        StringBuilder layers = new StringBuilder();
        for (String target : general.keySet()) {
            var objStage = general.getJSONObject(target);
            if (target.equals("protected_folders")) {
                var lineValue = objStage.getJSONArray("lineValue") + "";
                valuesMap.put(target, objStage.getBoolean("commented") ?
                        "#".concat(target).concat(" = ").concat(lineValue) :
                        "".concat(target).concat(" = ").concat(lineValue)
                );
            } else {
                var lineValue = objStage.getString("lineValue") + "";
                valuesMap.put(target, objStage.getBoolean("commented") ?
                        "#".concat(target).concat(" = ").concat(testIfToQoute(lineValue)) :
                        "".concat(target).concat(" = ").concat(testIfToQoute(lineValue))
                );
            }
            layers.append(" ${").append(target).append("} \n");

        }
        String templateString = "\n\n" + level.concat(": {\n")
                .concat(layers.toString())
                .concat("\n}");

        // Build StringSubstitutor
        StringSubstitutor sub = new StringSubstitutor(valuesMap);

        // Replace
        return sub.replace(templateString);
    }
}
