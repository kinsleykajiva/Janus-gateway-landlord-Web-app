package africa.jopen.configs.transports;

import africa.jopen.configs.Janus;
import africa.jopen.json.janus.JanusObject;
import africa.jopen.utils.XUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.text.StringSubstitutor;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static africa.jopen.configs.utils.Utils.getStringJsonFactory;
import static africa.jopen.utils.XUtils.CONFIG_FOLDER;
import static africa.jopen.utils.XUtils.testIfToQoute;

public class Http {
    private final Logger logger = LoggerFactory.getLogger(Http.class);

    final String FileName = "janus.transport.http.jcfg";
    final String FileNameJson = FileName + ".json";
    private africa.jopen.json.http.Root janusConfigs;
    private String jsonJanus;
    private String CONFIG="";

    public Http() {
        try {
            // loads the default data
            jsonJanus = XUtils.readFileFromResources("configs/" + FileNameJson);
            ObjectMapper om = new ObjectMapper();
            om.enable(SerializationFeature.WRAP_ROOT_VALUE);
            janusConfigs = om.readValue(jsonJanus, new TypeReference<>() {
            });
            buildGeneral();
        } catch (URISyntaxException | IOException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }
    public void saveFromDefaults(){
        try {
            Writer fileWriter = new FileWriter(CONFIG_FOLDER + File.separator + FileName, false);
            fileWriter.write(CONFIG);
            fileWriter.close();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
    private void buildGeneral() {
        Map<String, String> valuesMap = new HashMap<>();
        JSONObject obj = new JSONObject(jsonJanus);
        String resolvedString = "";
        resolvedString = getStringJsonFactory(valuesMap, obj.getJSONObject("general"), "general");
        resolvedString += getStringJsonFactory(valuesMap, obj.getJSONObject("admin"), "admin");
        resolvedString += getStringJsonFactory(valuesMap, obj.getJSONObject("cors"), "cors");
        resolvedString += getStringJsonFactory(valuesMap, obj.getJSONObject("certificates"), "certificates");

        CONFIG = resolvedString;
        logger.info(resolvedString);

    }

}
