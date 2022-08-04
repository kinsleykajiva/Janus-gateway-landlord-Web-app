package africa.jopen.configs.plugins;

import africa.jopen.configs.transports.Http;
import africa.jopen.utils.XUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.text.StringSubstitutor;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static africa.jopen.utils.XUtils.testIfToQoute;

public class Sip {




    private final Logger logger = LoggerFactory.getLogger(Sip.class);

    final String FileName = "janus.plugin.sip.jcfg";
    final String FileNameJson = FileName + ".json";
    private africa.jopen.json.sip.Root janusConfigs;
    private String jsonJanus;

    public Sip() {
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
        StringBuilder layers = new StringBuilder();
        var stages = new String[]{"general"};
        final String[] level = new String[1];
        Arrays.stream(stages).toList()
                .forEach(stageTarget->{
                    level[0] = stageTarget;
                    JSONObject general = obj.getJSONObject(stageTarget);
                    for (String target : general.keySet()) {
                        var objStage = general.getJSONObject(target);
                        var lineValue = objStage.getString("lineValue") + "";
                        valuesMap.put(target, objStage.getBoolean("commented") ?
                                "#".concat(target).concat(" = ").concat(testIfToQoute(lineValue)) :
                                "".concat(target).concat(" = ").concat(testIfToQoute(lineValue))
                        );
                        layers.append(" ${").append(target).append("} \n");
                    }
                });
        String templateString = "\n\n" + level[0].concat(": {\n")
                .concat(layers.toString())
                .concat("\n}");

        StringSubstitutor sub = new StringSubstitutor(valuesMap);

        // Replace
        var bul = sub.replace(templateString);
        logger.info(bul);

    }

}
