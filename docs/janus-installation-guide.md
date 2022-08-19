Hi, here are the steps that worked for me , 2022 August . <br>
here official guide is https://github.com/meetecho/janus-gateway <br> 
This seeks to help those who have had a problem in installing based on the other guides so this may be an option.
By the way this will install the latest version of janus [Assuming branch is master] .
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
- ``` ./configure --enable-docs  ```

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
sudo apt-get -y update && sudo apt-get -y install aptitude && \
sudo apt install -y cmake && \
sudo apt-get -y install build-essential && \
sudo apt-get -y install g++ && \
sudo aptitude -y install libmicrohttpd-dev && \
sudo aptitude -y install  libjansson-dev && \
sudo aptitude -y install 	libssl-dev && \
sudo apt  -y install libsrtp2-dev && \
sudo aptitude -y install libsofia-sip-ua-dev && \
sudo aptitude -y install libglib2.0-dev && \
sudo aptitude -y install 	libopus-dev && \
sudo aptitude -y install libogg-dev && \
sudo aptitude -y install libcurl4-openssl-dev && \
sudo aptitude -y install liblua5.3-dev && \
sudo aptitude -y install 	libconfig-dev && \
sudo aptitude -y install pkg-config && \
sudo aptitude -y install libtool automake && \
sudo apt -y install libnice-dev && \
sudo apt-get -y install libsrtp2-dev && \
sudo apt install supervisor && \
clear && echo "#########################################################################"  && \
echo "##################### Done.Doing Repo Packages###########################"  && \
echo "#########################################################################"  && \
sudo apt-get -y install libusrsctp-dev  && \
git clone https://libwebsockets.org/repo/libwebsockets && cd libwebsockets && mkdir build && cd build && cmake -DLWS_MAX_SMP=1 -DLWS_WITHOUT_EXTENSIONS=0 -DCMAKE_INSTALL_PREFIX:PATH=/usr -DCMAKE_C_FLAGS="-fpic" ..  && \
make && sudo make install && \
cd .. && cd .. && \
git clone https://github.com/eclipse/paho.mqtt.c.git && cd paho.mqtt.c && make && sudo make install && cd .. && \
sudo aptitude -y install libnanomsg-dev && \
git clone https://github.com/alanxz/rabbitmq-c  && cd rabbitmq-c  && git submodule init  && git submodule update  && mkdir build && cd build  && cmake -DCMAKE_INSTALL_PREFIX=/usr ..  && make && sudo make install && cd .. && cd .. && \
sudo aptitude -y install doxygen graphviz && \
git clone https://github.com/meetecho/janus-gateway.git && cd janus-gateway && sh autogen.sh && \
sudo mkdir /opt/janus && sudo mkdir /opt/janus/bin && sudo ./configure --prefix=/opt/janus && sudo  make && sudo  make install && \
sudo make configs && \
./configure --enable-docs && \
sudo mkdir -p /etc/supervisor/conf.d/ && \
FILE=/etc/supervisor/conf.d/janus2.conf && sudo mkdir -p "$(dirname "$FILE")" && sudo touch "$FILE" && \
sudo sh -c 'printf "[program:janus]\n command=/opt/janus/bin/janus\n user=root\n autostart=true\n autorestart=true\n stderr_logfile=/var/log/janus.err.log\n stdout_logfile=/var/log/janus.out.log\n\n" >/etc/supervisor/conf.d/janus.conf' && \
sudo supervisorctl reread  && sudo supervisorctl update && \
sudo supervisorctl reload && \
clear && echo "###################################################################################################"  && \
echo "##################### Done.To test open http://localhost:8088/janus/info ##########################"  && \
echo "###################################################################################################" 
```

