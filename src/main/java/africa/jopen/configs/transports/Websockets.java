package africa.jopen.configs.transports;

import africa.jopen.json.websockets.JanusWebSockets;
import africa.jopen.utils.XUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import static africa.jopen.configs.utils.Utils.getStringJsonFactory;
import static africa.jopen.utils.XUtils.CONFIG_FOLDER;
import static java.util.logging.Logger.getLogger;

public class Websockets {


	public static final String FileName     = "janus.transport.websockets.jcfg";
	final               String FileNameJson = FileName + ".json";
	private final Logger logger = getLogger(Websockets.class.getSimpleName());
	private JanusWebSockets janusConfigs;
	private String          jsonJanus;
	private String          CONFIG = "";

	public Websockets () {
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

	public JanusWebSockets loadCurrentSettings () {
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
		try {
			JanusWebSockets janusWebSockets = mapper.readValue(Paths.get(CONFIG_FOLDER + File.separator + FileNameJson).toFile(), JanusWebSockets.class);
			return janusWebSockets;
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

	public synchronized void saveFromDefaults () {
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
		Map<String, String> valuesMap      = new HashMap<>();
		JSONObject          obj            = new JSONObject(jsonJanus);
		String              resolvedString = "";
		resolvedString = getStringJsonFactory(valuesMap, obj.getJSONObject("general"), "general");
		resolvedString += getStringJsonFactory(valuesMap, obj.getJSONObject("admin"), "admin");
		resolvedString += getStringJsonFactory(valuesMap, obj.getJSONObject("cors"), "cors");
		resolvedString += getStringJsonFactory(valuesMap, obj.getJSONObject("certificates"), "certificates");
		CONFIG = resolvedString;

		//  logger.info(resolvedString);

	}


}
