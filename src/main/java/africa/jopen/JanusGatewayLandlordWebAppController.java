package africa.jopen;

import io.micronaut.http.annotation.*;

@Controller("/janusGatewayLandlordWebApp")
public class JanusGatewayLandlordWebAppController {

    @Get(uri="/", produces="text/plain")
    public String index() {
        return "Example Response";
    }
}