## Janus-gateway-landlord-Web-app 
[![Latest release](https://img.shields.io/badge/version-0.8-blue)](https://img.shields.io/badge/v0.1-tag-green)

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)   ![Linux](https://img.shields.io/badge/Linux-FCC624?style=for-the-badge&logo=linux&logoColor=black) ![Linux](https://img.shields.io/badge/Ubuntu-E95420?style=for-the-badge&logo=ubuntu&logoColor=white)   
[![Linux](https://svgshare.com/i/Zhy.svg)](https://svgshare.com/i/Zhy.svg)

# Still in development, but stable !

This is a Web app that seeks to make it very easy to use Janus Gateway.The app handles installation , setting up/ Updating configurations from just a single install of this .

Motivation: As a developer I got tired of always opening the console to see logs and make configs all the time using the console .


# Requirements
- Linux (For now Debian Based OS,please help me to change this ) . 
- GraalVM JDK 17 (https://github.com/graalvm/graalvm-ce-builds/releases/tag/vm-22.2.0 ) to build/run .
- make sure you have snap installed for more review https://snapcraft.io/install/ubuntu-package-status/ubuntu.
- This so far has been built and tested on Ubuntu,please try to make changes to suite your OS.


```bash
git clone https://github.com/kinsleykajiva/Janus-gateway-landlord-Web-app.git
```

# Installation :
You can pull the repository, make a build , navigate to target folder .
- When you build its recommended  that you build a native build to leverage on performance and app size (so far the app is smaller than 100MB).
- To build Native App : 
```shell 
sudo ./mvnw package -Dpackaging=native-image

```
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
- can Janus store events to this app as a server, so far can save to MongoDB . Pass MongoDB arguments `` mongoPORT=27017 mongoHOST=localhost mongoPASSWORD=rootuser mongoUSERNAME=root mongoNAME=landlord ``  . mongoPORT - is the database port , mongoHOST - host adddress , mongoPASSWORD- database password , mongoUSERNAME - username , mongoNAME - database name .
- 

Remember to always run the app as Admin!

PostMan - Endpoints - https://github.com/kinsleykajiva/Janus-gateway-landlord-Web-app/blob/master/janus-gateway-landlord.postman_collection.json




<hr>

## Remote Deployments:

Well I have to say that , this is my first project where I have taken GraalVM seriously and used it to build and deploy , 
it has not been easy running the built image/binary  file  on a remote ubuntu sever as i thought you can run the binary as a service just like jar files ,.
Tips or help is welcome .
In the meantime , I have opted to deploy the app on the ubuntu server as follows:
Install GraalVM :
```bash
sudo apt install -y openjdk-17-jre-headless && \
wget https://github.com/graalvm/graalvm-ce-builds/releases/download/vm-22.2.0/graalvm-ce-java17-linux-amd64-22.2.0.tar.gz &&  \
tar -xf graalvm-ce-java17-linux-amd64-22.2.0.tar.gz &&  \
sudo mv graalvm-ce-java17-22.2.0 /usr/lib/jvm/ &&  \
cd /usr/lib/jvm &&  \
sudo ln -s graalvm-ce-java17-linux-amd64-22.2.0 graalvm && sudo update-alternatives --install /usr/bin/java java /usr/lib/jvm/graalvm-ce-java17-22.2.0/bin/java 2  &&  \
sudo update-alternatives --config java
```
To test run `java -version` , it shouw say something like this :

```shell
openjdk version "17.0.4" 2022-07-19
OpenJDK Runtime Environment GraalVM CE 22.2.0 (build 17.0.4+8-jvmci-22.2-b06)
OpenJDK 64-Bit Server VM GraalVM CE 22.2.0 (build 17.0.4+8-jvmci-22.2-b06, mixed mode, sharing)
```


to export `sudo nano ~/.profile`



add System env variable ```    export GRAALGU=/usr/lib/jvm/graalvm-ce-java17-22.2.0/bin ```    
add System env variable ```    export PATH=$JAVA_HOME/BIN:$GRAALGU:$PATH ```   
run `source ~/.profile`

run `gu install native-image`  ,  it should run the gu installation

- Test your GraalVM version by running ```java -version ```   if your lost or is not working please make use of https://github.com/ravening/dev_setup/blob/master/Install-GraalVm-Ubuntu.md but make sure its jdk 17 not jdk 11.

- Install Maven may not be necessary, for me I had to install for it to work for some reason .
- Clone this repo on the remote machine/server/instance and copy the image/native image from the folder ```Janus-gateway-landlord-Web-app/release/```
- You just runt the image in the release folder .
- Auth Test: please run in release folder to test and see the logged pass is the same as you pass in the arguments ``` ./Janus-gateway-landlord-Web-app  basicWebAuthUserName=no_money basicWebAuthPassword=SkillS@Home#ArePretty!``` 
- in local development to run or test using console run : ``` sudo ./mvnw mn:run -Dmn.appArgs="basicWebAuthUserName=no_money basicWebAuthPassword=SkillS@Home#ArePretty!"```
- if your have MongoDB installed as a database pass as : ``` ./mvnw mn:run -Dmn.appArgs="basicWebAuthUserName=no_money basicWebAuthPassword=SkillS@Home#ArePretty! mongoPORT=27017 mongoHOST=localhost mongoPASSWORD=rootuser mongoUSERNAME=root mongoNAME=landlord" ```

## Passed Parameters meaning

- basicWebAuthUserName - bais auth user 
- basicWebAuthPassword - bais auth password 
- mongoPORT - mongo database port where the server is running
- mongoHOST - mongo database host address where the server is running
- mongoPASSWORD - mongo database user password
- mongoUSERNAME - mongo database user name
- mongoNAME - mongo database target database name
- janusInstall - this is a constant variable that describes the installation type . 0-means its janus was installed using snap on this machine , 1 mean its janus was installed natively or directly by the user or Admin via SSH.



Yes Please replace the username and password with your own !
To run the native image application as a service you can consider to use  ``nohup`` for example navigate to the release folder and run ``` nohup  ./Janus-gateway-landlord-Web-app & ``` .Remember to run as root or admin user .

<hr>

## Docs

- Janus installation guide  - https://github.com/kinsleykajiva/Janus-gateway-landlord-Web-app/blob/master/docs/janus-installation-guide.md 
- MongoDb installation - https://github.com/kinsleykajiva/Janus-gateway-landlord-Web-app/blob/master/docs/mongo-db-installations-guide.md

<hr>

## Janus configs json files and App folder structure .

When the app first runs or when ever its started it creates a base folder for caching `/opt/.janus-landlord/` .
This folder always has what's currently configured in tha janus jcfg files .Since the app does not parse the jcfg files directly but overwrites them ,
if you need to enable some config lines per file config just edit in that folder `/opt/.janus-landlord/` .

In the event you also delete the files in the `/opt/.janus-landlord/`  or even if the files are found to be empty as well the files themselves even if they exist, the app will reset the folder files when the app is restarted  
meaning all those configs you had will be lost to be reset to default to match what's in the folder of the project `src/main/resources/configs` . So be care full in that area as well .




## None Goals of the project

- This project is not directly a janus installation management app , janus installation is directly your responsibility . Install janus however you want but for now ,this only handles snap based installation .

Yes there are plans to have this web app also handle native installation , also there is some considerations for handling docker installations .


<hr>

## Known Issues

- Websockets are failing to work properly . The client fails to connect if the app is run as an image/binary.This is a problem because we need the app to run as a native image application than to run as a jar .


<hr>

## Simple install.

Make sure that you have Node installed .
This will create the parent folder and a subfolder for node js application.
```bash

sudo echo 1 && \
FILE_APP=Janus-landlord-app && \
NODE_APP=node-app && \
mkdir -p "$FILE_APP"  && mkdir -p "$FILE_APP"/"$NODE_APP"  && \
cd "$FILE_APP"  && \
wget https://github.com/kinsleykajiva/Janus-gateway-landlord-Web-app/blob/master/target/Janus-landlord-app.jar?raw=true  && \
mv "$FILE_APP".jar?raw=true "$FILE_APP".jar &&  \
echo "Getting Node Modules ......"  &&  \
cd "$NODE_APP"  && \
wget https://raw.githubusercontent.com/kinsleykajiva/Janus-gateway-landlord-Web-app/master/release/ecosystem.config.js  && \
wget https://raw.githubusercontent.com/kinsleykajiva/Janus-gateway-landlord-Web-app/master/release/node-app-server.js && \
wget https://raw.githubusercontent.com/kinsleykajiva/Janus-gateway-landlord-Web-app/master/release/package-lock.json  && \
wget https://raw.githubusercontent.com/kinsleykajiva/Janus-gateway-landlord-Web-app/master/release/package.json  && \
sudo npm install  && \
sudo npm install pm2 -g  && \
sudo pm2 start ecosystem.config.js  && \
cd .. && \
echo "###### App is ready !!! ###### " 

```

## Run the app as a Service on ubuntu Server

```bash

FILE=/etc/systemd/system/Janus-landlord-app.service && sudo mkdir -p "$(dirname "$FILE")" && sudo touch "$FILE" && \
sudo sh -c 'printf "[Unit]\n Description=Janus-landlord-app jar \n [Service]\n User="$USER"\n WorkingDirectory="$PWD"\n\n ExecStart="$PWD"/Janus-landlord-app-bash\n\n SuccessExitStatus=143\n TimeoutStopSec=10\n Restart=on-failure\n RestartSec=5\n\n [Install]\n WantedBy=multi-user.target " > /etc/systemd/system/Janus-landlord-app.service' && \
sudo /usr/bin/java -jar Janus-landlord-app.jar basicWebAuthUserName=no_money basicWebAuthPassword=SkillS@Home#ArePretty! mSQLport=3306 mSQLhost=localhost mSQLpassword=Q5b7Uio9yaKq_fhscJsUeCujUfZhuO mSQLusername=jaMaican_admin mSQLname=landlord janusInstall=1 server config.yml && \
sudo chmod u+x "$PWD"  && \
sudo systemctl daemon-reload  && \
sudo systemctl enable Janus-landlord-app.service  && \
sudo systemctl start Janus-landlord-app  && \
sudo journalctl -f -n 1000 -u Janus-landlord-app  && \
sudo systemctl status Janus-landlord-app 




```
<hr>
 
## To Do
- to handle native direct janus installations other than using/depending on snap to handle the instance .
- Custom sessions recording ,save to file 
- Create a websocket access to live logs to connected clients.
- To do code refactoring,remove duplicate code.
- better deployment mechanism or dev ops
- Add support for native installations 






