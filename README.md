## Janus-gateway-landlord-Web-app
# version 0.1

This is a Web app that seeks to make it very easy to use Janus Gateway.The app handles installation , setting up/ Updating configurations from just a single install of this .

Motivation: As a developer I got tired of always opening the console to see logs and make configs all the time using the console .


# Requirements
- Linux (For now Debian Based OS,please me to change this ) . 
- JDK 17+ to run . 
- Better with GraalVM
- make sure you have snap installed for more review https://snapcraft.io/install/ubuntu-package-status/ubuntu.
- This so far has been built and tested on Ubuntu,please try to make changes to suite your OS.


# Installation :
You can pull the repository, make a build , navigate to target folder .
- When you build its recommended  that you build a native build to leverage on performance and app size (so far the app is smaller than 100MB).
- To build Native App : ```  ./mvnw package -Dpackaging=native-image```
- To run the newly built native app ``cd Janus-gateway-landlord-Web-app/target`` and run ``` sudo ./Janus-gateway-landlord-Web-app  ``` 
- When you run the app try to run the native app or jar app as or with admin privileges to allow the app to execute admin related tasks.



# Janus Configurations
- Has support for Janus
- Added support for Sip plugin
- Added support for Http transport
- Added support for WebSockets transport





This service is heavy dependent on this https://github.com/RSATom/janus-gateway-snap/tree/stable-0.11 as an installation mechanism 



## Contributions are very welcome! Make a pull Request


## So what you do with this web app ?
- can install/uninstall Janus Gateway Server
- can update Sip details/Configurations
- can update janus Core configs
- can update HTTP configs
- can update websockets configs
- can restart Janus Gateway Server
- can open Demos from instance-ip-address:7000/echotest.html for example http://localhost:7000/echotest.html  or http://0.0.0.0:7000/echotest.html  or domain.com:7000/echotest.html , you can enforce http or https access also , depending on the networking on your clouding setup you can do away with the port in the address example from https://domain.com:7000/echotest.html to https://domain.com/echotest.html


Remember to always run the app as Admin!

PostMan - Endpoints - https://github.com/kinsleykajiva/Janus-gateway-landlord-Web-app/blob/master/janus-gateway-landlord.postman_collection.json

