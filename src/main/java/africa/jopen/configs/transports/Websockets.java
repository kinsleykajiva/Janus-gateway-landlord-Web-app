package africa.jopen.configs.transports;

import africa.jopen.utils.XUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import static africa.jopen.configs.utils.Utils.getStringJsonFactory;

public class Websockets {


    private final Logger logger = LoggerFactory.getLogger(Websockets.class);

    final String FileName = "janus.transport.websockets.jcfg";
    final String FileNameJson = FileName + ".json";


    private africa.jopen.json.websockets.Root janusConfigs;
    private String jsonJanus;

    public Websockets() {
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
    private void buildGeneral() {
        Map<String, String> valuesMap = new HashMap<>();
        JSONObject obj = new JSONObject(jsonJanus);
        String resolvedString = "";
        resolvedString = getStringJsonFactory(valuesMap, obj.getJSONObject("general"), "general");
        resolvedString += getStringJsonFactory(valuesMap, obj.getJSONObject("admin"), "admin");
        resolvedString += getStringJsonFactory(valuesMap, obj.getJSONObject("cors"), "cors");
        resolvedString += getStringJsonFactory(valuesMap, obj.getJSONObject("certificates"), "certificates");


        logger.info(resolvedString);

    }


}
