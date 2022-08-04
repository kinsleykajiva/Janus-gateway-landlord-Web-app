package africa.jopen.configs;

import africa.jopen.utils.XUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
//import org.apache.commons.text.StringSubstitutor;
import africa.jopen.json.janus.JanusObject;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
import com.fasterxml.jackson.databind.SerializationFeature;
import io.micronaut.core.annotation.ReflectiveAccess;
import org.apache.commons.text.StringSubstitutor;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static africa.jopen.utils.XUtils.testIfToQoute;


@ReflectiveAccess
public class Janus {
    private final Logger logger = LoggerFactory.getLogger(Janus.class);

    final String FileName = "janus.jcfg";
    final String FileNameJson = FileName + ".json";
    boolean isFileFound = false;
    private JanusObject janusConfigs;
    private String jsonJanus;


    public Janus() {
        try {
            // loads the default data
            jsonJanus = XUtils.readFileFromResources("configs/" + FileNameJson);
            ObjectMapper om = new ObjectMapper();
            om.enable(SerializationFeature.WRAP_ROOT_VALUE);
            janusConfigs = om.readValue(jsonJanus, new TypeReference<>() {
            });

        } catch (URISyntaxException | IOException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }

        buildGeneral();
    }



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

        logger.info(resolvedString);
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
