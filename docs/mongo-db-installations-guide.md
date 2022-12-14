
One run:

```bash
 wget -qO - https://www.mongodb.org/static/pgp/server-6.0.asc | sudo apt-key add - && \
 sudo apt-get -y install gnupg  && sudo apt-get -y install gnupg && \
 wget -qO - https://www.mongodb.org/static/pgp/server-6.0.asc | sudo apt-key add - && \
 echo "deb [ arch=amd64,arm64 ] https://repo.mongodb.org/apt/ubuntu focal/mongodb-org/6.0 multiverse" | sudo tee /etc/apt/sources.list.d/mongodb-org-6.0.list && \
 sudo apt-get -y update  && \
 wget http://archive.ubuntu.com/ubuntu/pool/main/o/openssl/libssl1.1_1.1.1f-1ubuntu2_amd64.deb && sudo dpkg -i libssl1.1_1.1.1f-1ubuntu2_amd64.deb && sudo rm -f libssl1.1_1.1.1f-1ubuntu2_amd64.deb && \
echo "deb http://security.ubuntu.com/ubuntu impish-security main" | sudo aptitude -y install libssl-dev| sudo tee /etc/apt/sources.list.d/impish-security.list && sudo apt-get update && sudo apt-get install libssl1.1 && \
sudo apt-get install -y mongodb-org && \ echo "mongodb-org hold" | sudo dpkg --set-selections && echo "mongodb-org-database hold" | sudo dpkg --set-selections && \
echo "mongodb-org-server hold" | sudo dpkg --set-selections && echo "mongodb-mongosh hold" | sudo dpkg --set-selections && \
echo "mongodb-org-mongos hold" | sudo dpkg --set-selections && \
echo "mongodb-org-tools hold" | sudo dpkg --set-selections && \
ps --no-headers -o comm 1 && \
sudo systemctl start mongod && \
sudo systemctl daemon-reload 
 
```





Official Documentation: https://www.mongodb.com/docs/manual/tutorial/install-mongodb-on-ubuntu/#install-mongodb-community-edition
```bash
 wget -qO - https://www.mongodb.org/static/pgp/server-6.0.asc | sudo apt-key add -
```

```bash
sudo apt-get -y install gnupg
```

```bash
wget -qO - https://www.mongodb.org/static/pgp/server-6.0.asc | sudo apt-key add -
```

```bash
echo "deb [ arch=amd64,arm64 ] https://repo.mongodb.org/apt/ubuntu focal/mongodb-org/6.0 multiverse" | sudo tee /etc/apt/sources.list.d/mongodb-org-6.0.list
```

```bash
sudo apt-get -y update

```

```bash
wget http://archive.ubuntu.com/ubuntu/pool/main/o/openssl/libssl1.1_1.1.1f-1ubuntu2_amd64.deb && sudo dpkg -i libssl1.1_1.1.1f-1ubuntu2_amd64.deb && sudo rm -f libssl1.1_1.1.1f-1ubuntu2_amd64.deb && \
echo "deb http://security.ubuntu.com/ubuntu impish-security main" | sudo aptitude -y install libssl-dev | sudo tee /etc/apt/sources.list.d/impish-security.list && sudo apt-get update && sudo apt-get install libssl1.1 

```
or


```bash
echo "deb http://security.ubuntu.com/ubuntu impish-security main" | sudo tee /etc/apt/sources.list.d/impish-security.list && \

sudo apt-get update  && \
sudo apt-get install libssl1.1

```




```bash
sudo apt-get install -y mongodb-org

```

```bash
echo "mongodb-org hold" | sudo dpkg --set-selections
echo "mongodb-org-database hold" | sudo dpkg --set-selections
echo "mongodb-org-server hold" | sudo dpkg --set-selections
echo "mongodb-mongosh hold" | sudo dpkg --set-selections
echo "mongodb-org-mongos hold" | sudo dpkg --set-selections
echo "mongodb-org-tools hold" | sudo dpkg --set-selections

```

```bash
ps --no-headers -o comm 1

```

```bash
sudo systemctl start mongod
```

```bash
sudo systemctl daemon-reload
```
Verify that MongoDB has started successfully.

```bash
sudo systemctl status mongod
```
You can optionally ensure that MongoDB will start following a system reboot by issuing the following command:
```bash
sudo systemctl enable mongod
```
Restart MongoDB.

```bash
sudo systemctl stop mongod
```

Restart MongoDB.
```bash
sudo systemctl restart mongod
```
Begin using MongoDB.

```bash
mongosh
```
To set up MongoDB user example - https://www.guru99.com/mongodb-create-user.html

for example to set up user `root` and password `rootuser` , remember this is run in mongo termianl , run `mongosh` then run :
```bash 
use landlord
db.createUser({	user: "root",pwd: "rootuser",roles:[{role: "userAdminAnyDatabase" , db:"admin"}]})
	
	

mongosh --port 27017 -u root -p 'rootuser' --authenticationDatabase 'landlord'


```

To creat database for example creating a database called **landlord** just run : `use landlord`

To create table or collection in the selected database you need to run `db.createCollection("sip_events");`


from here the rest

db.getUser("root")



solutions - https://askubuntu.com/questions/842592/apt-get-fails-on-16-04-or-18-04-installing-mongodb
