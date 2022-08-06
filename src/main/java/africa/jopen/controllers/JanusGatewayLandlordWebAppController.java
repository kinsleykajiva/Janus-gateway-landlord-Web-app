package africa.jopen.controllers;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

@Controller("/home")
public class JanusGatewayLandlordWebAppController {

    @Get(uri="/", produces="text/plain")
    public String index() {
        return "Example Response";
    }









}