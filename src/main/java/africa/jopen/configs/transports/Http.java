package africa.jopen.configs.transports;

import africa.jopen.configs.plugins.Sip;
import africa.jopen.json.http.JanusHttp;
import africa.jopen.json.sip.JanusSip;
import africa.jopen.utils.XUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.json.JSONObject;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import static africa.jopen.configs.utils.Utils.getStringJsonFactory;
import static africa.jopen.utils.XUtils.CONFIG_FOLDER;
import static java.util.logging.Logger.*;

public class Http {
    private final Logger logger = getLogger(Http.class.getSimpleName());

    final String FileName = "janus.transport.http.jcfg";
    final String FileNameJson = FileName + ".json";
    private JanusHttp janusConfigs;
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
            logger.severe(e.getMessage());
            e.printStackTrace();
        }
    }
    public JanusHttp loadCurrentSettings(){
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
        try {
            JanusHttp janusHttp  = mapper.readValue(Paths.get(CONFIG_FOLDER + File.separator + FileNameJson).toFile(), JanusHttp.class);
            return janusHttp;
        } catch (IOException e) {
            logger.severe(e.getMessage());
            e.printStackTrace();
        }
        return  null;
    }

    public void saveFromDefaults(){
        try {
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
