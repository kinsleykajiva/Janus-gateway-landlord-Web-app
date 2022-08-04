package africa.jopen.controllers;

import io.micronaut.http.annotation.*;

@Controller("/home")
public class JanusGatewayLandlordWebAppController {

    @Get(uri="/", produces="text/plain")
    public String index() {
        return "Example Response";
    }
}