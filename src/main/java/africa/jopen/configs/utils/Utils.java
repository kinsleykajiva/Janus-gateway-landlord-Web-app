package africa.jopen.configs.utils;

import org.apache.commons.text.StringSubstitutor;
import org.json.JSONObject;

import java.util.Map;

import static africa.jopen.utils.XUtils.testIfToQoute;

public class Utils {

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
