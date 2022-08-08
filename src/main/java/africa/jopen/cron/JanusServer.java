package africa.jopen.cron;

import africa.jopen.json.serverinformation.JanusSeverInfo;
import africa.jopen.utils.HttpClientUtils;
import africa.jopen.utils.JanusUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.micronaut.scheduling.annotation.Scheduled;
import jakarta.inject.Singleton;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

@Singleton
public class JanusServer {

    final static Logger logger = Logger.getLogger(JanusServer.class.getSimpleName());


    public static boolean isServerUp() {
        try {
            String result = HttpClientUtils.getSimpleGetReqst("http://localhost:8088/janus/info");

            if (result != null) {

                ObjectMapper om = new ObjectMapper();
                om.enable(SerializationFeature.WRAP_ROOT_VALUE);
                JanusSeverInfo severInfo = om.readValue(result, JanusSeverInfo.class);
                JanusUtils.IS_JANUS_ONLINE = true;
                logger.info("janus" + severInfo.getJanus());
            } else {
                JanusUtils.IS_JANUS_ONLINE = false;
            }


            return result != null;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            logger.severe(e.getMessage());
        }
        return false;
    }

    @Scheduled(fixedDelay = "45s", initialDelay = "10s")
    void janusHealthCheck() throws IOException, InterruptedException {
        logger.info("Simple Job every 45 seconds: {}" + new SimpleDateFormat("dd/M/yyyy hh:mm:ss").format(new Date()));
        isServerUp();


    }

}
