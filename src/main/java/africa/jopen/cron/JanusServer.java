package africa.jopen.cron;

import africa.jopen.json.serverinformation.JanusSeverInfo;
import africa.jopen.utils.HttpClientUtils;
import africa.jopen.utils.JanusUtils;
import africa.jopen.utils.XUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.micronaut.scheduling.annotation.Scheduled;
import jakarta.inject.Singleton;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
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
              //  logger.info("janus" + severInfo.getJanus());
            } else {
                JanusUtils.IS_JANUS_ONLINE = false;
            }


            return result != null;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            //logger.severe(e.getMessage());
        }
        return false;
    }

    @Scheduled(fixedDelay = "150s")
    void updateServerUpTime()  {

        try {
            String ts = XUtils.getKnownIssuesSinceStartUp().getString("startup-time") ;
            Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(ts);
            JSONObject jsonLife = new JSONObject();
            Timestamp timestamp1 = new Timestamp(date.getTime());

            var timestamp2 =    new Timestamp(System.currentTimeMillis());

            long milliseconds = timestamp2.getTime() - timestamp1.getTime();
            int seconds = (int) milliseconds / 1000;

            int hours = seconds / 3600;
            int minutes = (seconds % 3600) / 60;
            seconds = (seconds % 3600) % 60;
            jsonLife.put("Hours",hours);
            jsonLife.put("Minutes",minutes);
            jsonLife.put("Seconds",seconds);
            jsonLife.put("last-timestamp",timestamp2.toString());

            XUtils.setKnownIssuesSinceStartUp("startup-life",jsonLife);
        } catch (ParseException e){
            // handleException
            XUtils.setKnownIssuesSinceStartUp("startup-life", "-");
            XUtils.setKnownIssuesSinceStartUp("startup-life-Exception", e.getMessage());
        }

    }

    @Scheduled(fixedDelay = "40s", initialDelay = "10s")
    void janusHealthCheck()  {

        isServerUp();


    }

}
