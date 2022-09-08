package africa.jopen.configs.eventhandlers;

import africa.jopen.json.simpleevents.Root;
import africa.jopen.utils.XUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.text.StringSubstitutor;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import static africa.jopen.utils.XUtils.CONFIG_FOLDER;
import static africa.jopen.utils.XUtils.testIfToQoute;

public class SampleEventHandler {
	public static final String FileName     = "janus.eventhandler.sampleevh.jcfg";
	final               String FileNameJson = FileName + ".json";
	private final Logger logger = Logger.getLogger(SampleEventHandler.class.getSimpleName());
	private             Root   janusConfigs;
	private             String jsonJanus;
	private             String CONFIG       = "";

	public SampleEventHandler () {
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

	/**
	 * To update the local configs waiting to be copied to janus folders
	 *
	 * @param json the whole json to be loaded as  a object and to be saved to the json file
	 * @return bool , if true configs have been saved to local , if false then the file has failed to save to local configs folder can not proceed to set as janus config .
	 */
	public boolean updateToBuild (String json) {
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

	public void saveToLocalBackCopy () {
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

	public Root loadCurrentSettings () {
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
		try {
			return mapper.readValue(Paths.get(CONFIG_FOLDER + File.separator + FileNameJson).toFile(), Root.class);

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
			return "";
		}
	}

	public void saveFromDefaults () {
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

	private void buildGeneral () {
		Map<String, String> valuesMap = new HashMap<>();
		JSONObject          obj       = new JSONObject(jsonJanus);
		StringBuilder       layers    = new StringBuilder();
		var                 stages    = new String[]{"general"};
		final String[]      level     = new String[1];
		Arrays.stream(stages).toList()
				.forEach(stageTarget -> {
					level[0] = stageTarget;
					JSONObject general = obj.getJSONObject(stageTarget);
					for (String target : general.keySet()) {
						var objStage  = general.getJSONObject(target);
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
		CONFIG = bul;
		// logger.info(bul);

	}

}
