Hi, here are the steps that worked for me , 2022 August . <br>
here official guide is https://github.com/meetecho/janus-gateway <br> 
This seeks to help those who have had a problem in installing based on the other guides so this may be an option.
By the way this will install the latest version of janus [Assuming branch is master] if you just do a clone to the master branch. Please do note there is need to pay attention to the tage version of the repository your pulling .
.
<br>
Common Areas to look out for errors that may stop you from install and review are :
- libwebsockets [I have attached a possible solution in the last section of this file]
<br>
Make sure you have c++ base compiler or c++ env are set globally other most builds will fail . make sure ``CMAKE_CXX_COMPILER`` is found .

run the following steps
- ``` sudo aptitude install libmicrohttpd-dev  ```
- ``` sudo aptitude install libmicrohttpd-dev   ```
- ``` sudo aptitude install  libjansson-dev   ```
- ```  sudo aptitude install 	libssl-dev  ```
- ``` sudo apt install libsrtp2-dev  ```
- ```  sudo aptitude install libsofia-sip-ua-dev   ```
- ```  sudo aptitude install libglib2.0-dev  ```
- ``` sudo aptitude install 	libopus-dev   ```
- ``` sudo aptitude install libogg-dev   ```
- ``` sudo aptitude install libcurl4-openssl-dev   ```
- ```  sudo aptitude install liblua5.3-dev  ```
- ``` sudo aptitude install 	libconfig-dev   ```
- ``` sudo aptitude install pkg-config   ```
- ``` sudo aptitude install libtool automake  ```
- ``` sudo apt install libnice-dev  ```
- ```  sudo apt-get -y install libsrtp2-dev ```
- ``` sudo apt install supervisor  ```
- ```  git clone https://github.com/sctplab/usrsctp && cd usrsctp && ./bootstrap && ./configure --prefix=/usr --disable-programs --disable-inet --disable-inet6 && make && sudo make install && cd ..  ```
- ``` git clone https://libwebsockets.org/repo/libwebsockets && cd libwebsockets && mkdir build && cd build && cmake -DLWS_MAX_SMP=1 -DLWS_WITHOUT_EXTENSIONS=0 -DCMAKE_INSTALL_PREFIX:PATH=/usr -DCMAKE_C_FLAGS="-fpic" ..    ```
- ``` make && sudo make install  ```
- ``` cd .. && cd ..  ```
- ``` git clone https://github.com/eclipse/paho.mqtt.c.git && cd paho.mqtt.c && make && sudo make install && cd ..  ```
- ```  sudo aptitude install libnanomsg-dev ```
- ``` git clone https://github.com/alanxz/rabbitmq-c  && cd rabbitmq-c  && git submodule init  && git submodule update  && mkdir build && cd build  && cmake -DCMAKE_INSTALL_PREFIX=/usr ..  && make && sudo make install && cd .. && cd ..  ```
- ``` sudo aptitude install doxygen graphviz ``` 
- ``` git clone https://github.com/meetecho/janus-gateway.git && cd janus-gateway && sh autogen.sh  ```
- ``` sudo mkdir /opt/janus && sudo mkdir /opt/janus/bin && sudo ./configure --prefix=/opt/janus && sudo  make && sudo  make install  ```
- ```  sudo make configs   ```
- ``` sudo ./configure --enable-docs  ```

At point your are done installing janus as much .Now lets run the application using supervisor that you installed on step ``sudo apt install supervisor``
to run janus as a service in the background run ``` sudo nano /etc/supervisor/conf.d/janus.conf  ```
paste this into the terminal 

```bash 
        [program:janus]
        command=/opt/janus/bin/janus
        user=root
        autostart=true
        autorestart=true
        stderr_logfile=/var/log/janus.err.log
        stdout_logfile=/var/log/janus.out.log
```
-   to save press ``` CTRL + O  ``` and press Enter or return key to save . press ```  Ctrl + X  ``` to exit and run the following command:
- ``` sudo supervisorctl reread  && sudo supervisorctl update  ```
-  to reload restart janus or service run  ```   sudo supervisorctl reload   ```
<br>
<br>
to test open http://localhost:8088/janus/info or http://ipaddress:8088/janus/info
<br>
<br>
<br>
Here is another option to run all the above commands in a single line  . This may have errors do review and give feedback to make it better for others as well .


```bash 
sudo apt-get update && \
clear && echo "############################--Start work--############################"  && \
sudo snap install cmake --classic && \
sudo apt-get update && \
sudo apt-get -y install aptitude && \
sudo apt-get -y update && sudo apt-get -y install aptitude && \
sudo apt install -y cmake && \
sudo apt-get -y install build-essential && \
sudo apt-get -y install g++ && \
sudo apt-get -y install gengetopt && \
sudo aptitude -y install libmicrohttpd-dev && \
sudo aptitude -y install  libjansson-dev && \
sudo aptitude -y install 	libssl-dev || sudo apt-get -y install libssl-dev && \
sudo apt  -y install libsrtp2-dev && clear &&\
sudo aptitude -y install libsofia-sip-ua-dev && \
sudo aptitude -y install libglib2.0-dev && clear && \
sudo aptitude -y install 	libopus-dev || sudo apt-get install -y libopus-dev && clear && \
sudo aptitude -y install libogg-dev && clear && \
sudo aptitude -y install libcurl4-openssl-dev && \
sudo aptitude -y install liblua5.3-dev && \
sudo aptitude -y install libconfig-dev  || sudo apt-get install -y libconfig-dev && \
sudo aptitude -y install pkg-config  && clear && \
sudo aptitude -y install libtool automake && \
sudo apt -y install libnice-dev && \
sudo apt-get -y install libsrtp2-dev && \
sudo apt install supervisor && \
clear && echo "#########################################################################"  && \
echo "##################### Done.Doing Repo Packages###########################"  && \
echo "#########################################################################"  && \
sudo apt-get -y install libusrsctp-dev  && \
git clone -b v4.3.0 https://github.com/warmcat/libwebsockets.git && cd libwebsockets && mkdir build && cd build && cmake -DLWS_MAX_SMP=1 -DLWS_WITHOUT_EXTENSIONS=0 -DCMAKE_INSTALL_PREFIX:PATH=/usr -DCMAKE_C_FLAGS="-fpic" ..  && \
make && sudo make install && \
cd .. && cd .. && \
clear && echo ">> Step:INSTALLING paho.mqtt.c"  && \
git clone https://github.com/eclipse/paho.mqtt.c.git && cd paho.mqtt.c && make && sudo make install && cd .. && \
clear && echo ">> Step:INSTALLING libnanomsg-dev"  && \
sudo aptitude -y install libnanomsg-dev && \
clear && echo ">> Step:INSTALLING rabbitmq-c"  && \
git clone https://github.com/alanxz/rabbitmq-c  && cd rabbitmq-c  && git submodule init  && git submodule update  && mkdir build && cd build  && cmake -DCMAKE_INSTALL_PREFIX=/usr ..  && make && sudo make install && cd .. && cd .. && \
clear && echo ">> Step:INSTALLING doxygen graphviz "  && \
sudo aptitude -y install doxygen graphviz && \
clear && echo ">> Step:INSTALLING janus-gateway FROM GITHUB "  && \
git clone -b v1.0.4 https://github.com/meetecho/janus-gateway.git && cd janus-gateway && sudo add-apt-repository ppa:git-core/ppa -y &&\
sudo git config --global --add safe.directory /home/ubuntu/janus-gateway  && \
sudo sh autogen.sh && \
sudo mkdir -p /opt/janus && sudo mkdir -p /opt/janus/bin && sudo ./configure --prefix=/opt/janus && sudo  make && sudo  make install && \
sudo make configs && \
sudo ./configure --enable-docs && cd .. && \
clear && echo ">> Step:SYSTEM CONFIG using supervisor "  && \
sudo mkdir -p /etc/supervisor/conf.d/ && \
FILE=/etc/supervisor/conf.d/janus.conf && sudo mkdir -p "$(dirname "$FILE")" && sudo touch "$FILE" && \
sudo sh -c 'printf "[program:janus]\n command=/opt/janus/bin/janus\n user=root\n autostart=true\n autorestart=true\n stderr_logfile=/var/log/janus.err.log\n stdout_logfile=/var/log/janus.out.log\n\n" >/etc/supervisor/conf.d/janus.conf' && \
sudo supervisorctl reread  && sudo supervisorctl update && \
sudo supervisorctl reload && \
clear && echo "###################################################################################################"  && \
echo "##################### Done.To test open http://localhost:8088/janus/info ##########################"  && \
echo "###################################################################################################" 

```
## notes 


git clone -b 0.x --single-branch https://github.com/meetecho/janus-gateway.git

git clone -b v0.11.4 https://github.com/meetecho/janus-gateway.git 

git clone -b v4.3.0 https://github.com/warmcat/libwebsockets.git



Native installation configurations files path  - ``` /opt/janus/etc/janus/ ```  
- Meaning for example **janus.jcfg** file path is `` /opt/janus/etc/janus/janus.jcfg `` to edit you run `sudo nano /opt/janus/etc/janus/janus.jcfg`
Remember if you wisht to reload is `**sudo supervisorctl reload**`


## installation issues ? Try these solutions :
- https://github.com/meetecho/janus-gateway/issues/860
- https://github.com/meetecho/janus-gateway/issues/3039
- https://github.com/meetecho/janus-gateway/issues/2248