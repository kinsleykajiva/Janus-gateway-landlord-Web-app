package africa.jopen.configs.utils;

import africa.jopen.configs.transports.Websockets;
import org.apache.commons.text.StringSubstitutor;
import org.json.JSONObject;

import java.util.Map;
import java.util.logging.Logger;

import static africa.jopen.utils.XUtils.testIfToQoute;
import static java.util.logging.Logger.getLogger;

public class Utils {
    private final Logger logger = getLogger(Utils.class.getSimpleName());
    public static String getStringJsonFactory(Map<String, String> valuesMap, JSONObject general, String level) {
        StringBuilder layers = new StringBuilder();
        for (String target : general.keySet()) {
            var objStage = general.getJSONObject(target);
            var lineValue = objStage.getString("lineValue") + "";
            valuesMap.put(target, objStage.getBoolean("commented") ?
                    "#".concat(target).concat(" = ").concat(testIfToQoute(lineValue)) :
                    "".concat(target).concat(" = ").concat(testIfToQoute(lineValue))
            );

            layers.append(" ${").append(target).append("} \n");

        }
        String templateString = "\n\n" + level.concat(": {\n")
                .concat(layers.toString())
                .concat("\n}");
        StringSubstitutor sub = new StringSubstitutor(valuesMap);
        return sub.replace(templateString);
    }
}
