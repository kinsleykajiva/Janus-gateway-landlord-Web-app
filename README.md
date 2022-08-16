## Janus-gateway-landlord-Web-app 
[![Latest release](https://img.shields.io/badge/version-0.5-blue)](https://img.shields.io/badge/v0.1-tag-green)

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)   ![Linux](https://img.shields.io/badge/Linux-FCC624?style=for-the-badge&logo=linux&logoColor=black) ![Linux](https://img.shields.io/badge/Ubuntu-E95420?style=for-the-badge&logo=ubuntu&logoColor=white)   
[![Linux](https://svgshare.com/i/Zhy.svg)](https://svgshare.com/i/Zhy.svg)

# Still in development, but stable !

This is a Web app that seeks to make it very easy to use Janus Gateway.The app handles installation , setting up/ Updating configurations from just a single install of this .

Motivation: As a developer I got tired of always opening the console to see logs and make configs all the time using the console .


# Requirements
- Linux (For now Debian Based OS,please me to change this ) . 
- GraalVM JDK 17 (https://github.com/graalvm/graalvm-ce-builds/releases/tag/vm-22.2.0 ) to build/run .
- make sure you have snap installed for more review https://snapcraft.io/install/ubuntu-package-status/ubuntu.
- This so far has been built and tested on Ubuntu,please try to make changes to suite your OS.


# Installation :
You can pull the repository, make a build , navigate to target folder .
- When you build its recommended  that you build a native build to leverage on performance and app size (so far the app is smaller than 100MB).
- To build Native App : ``` sudo ./mvnw package -Dpackaging=native-image```
- To run the newly built native app ``cd Janus-gateway-landlord-Web-app/target`` and run ``` sudo ./Janus-gateway-landlord-Web-app  ``` 
- When you run the app try to run the native app or jar app as or with admin privileges to allow the app to execute admin related tasks.

## To run :
```bash  
sudo ./mvnw mn:run
```

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
- can open Demos from instance-ip-address:3200/echotest.html for example http://localhost:3200/echotest.html  or http://0.0.0.0:3200/echotest.html  or domain.com:3200/echotest.html , you can enforce http or https access also , depending on the networking on your clouding setup you can do away with the port in the address example from https://domain.com:3200/echotest.html to https://domain.com/echotest.html


Remember to always run the app as Admin!

PostMan - Endpoints - https://github.com/kinsleykajiva/Janus-gateway-landlord-Web-app/blob/master/janus-gateway-landlord.postman_collection.json




<hr>

## Remote Deployments:

Well i have to say that , this is my first project where I have taken GraalVM seriously and used it to build and deploy , 
it has not been easy running the built image/binary  file  on a remote ubuntu sever as i thought you can run the binary as a service just like jar files ,.
Tips or help is welcome .
In the meantime , I have opted to deploy the app on the ubuntu server as follows:
- Install GraalVM ```  wget https://github.com/graalvm/graalvm-ce-builds/releases/download/vm-22.2.0/graalvm-ce-java17-linux-amd64-22.2.0.tar.gz \
  && tar -xf graalvm-ce-java17-linux-amd64-22.2.0.tar.gz \
  && sudo cp -R graalvm-ce-java17-22.2.0/ usr/lib/jvm/ ```

add System env variable ```    export GRAALGU=/usr/lib/jvm/graalvm-ce-java17-22.2.0/bin ```    
add System env variable ```    export PATH=$JAVA_HOME/BIN:$GRAALGU:$PATH ```   
update or set default java installed on your system by running ``` update-alternatives --install /usr/bin/java java /usr/lib/jvm/graalvm-ce-java17-22.2.0/bin/java 2```

- Test your GraalVM version by running ```java -version ```   if your lost or is not working please make use of https://github.com/ravening/dev_setup/blob/master/Install-GraalVm-Ubuntu.md but make sure its jdk 17 not jdk 11.

- Install Maven may not be necessary, for me I had to install for it to work for some reason .
- Clone this repo on the remote machine/server/instance and copy the image/native image from the folder ```Janus-gateway-landlord-Web-app/release/```
- for example, you can use ```sudo mkdir appsFolder && cp -R  Janus-gateway-landlord-Web-app/release/ appsFolder/```
- depending on how you install GraalVM you make need to run it as root or Admin , but the application definitely needs to  be run as Admin as expressed above 
 

<hr>
## To Do
- Create a custom log file locations
- Create a websocket access to live logs to connected clients.
- To do code refactoring,remove duplicate code.
- better deployment mechanism or dev ops






