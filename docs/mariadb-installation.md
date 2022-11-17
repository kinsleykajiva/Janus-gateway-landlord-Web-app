





```
sudo apt-get install wget software-properties-common dirmngr ca-certificates apt-transport-https -y && \
sudo apt -y install mariadb-server mariadb-client   && \
sudo mysql_secure_installation

```


```
sudo mysql_secure_installation

```

```
sudo mariadb

```
```
CREATE DATABASE landlord;

```

original  `CREATE USER 'linuxhint'@'localhost' IDENTIFIED BY 'new_password';`
```mysql
CREATE USER 'Jroot'@'localhost' IDENTIFIED BY 'Jrootuser';

GRANT ALL PRIVILEGES ON *.* to 'Jroot'@'localhost';

```

```mysql 

CREATE TABLE IF NOT EXISTS sip_events ( _id INT AUTO_INCREMENT PRIMARY KEY, ts VARCHAR(255) NOT NULL, ts_ VARCHAR(255) NOT NULL)  ENGINE=INNODB;


use landlord;
ALTER TABLE tasks ADD COLUMN IF NOT EXISTS column_a VARCHAR(255);
DESCRIBE tasks;
```

To Remove Mariadb

```
sudo apt autoremove mariadb-server mariadb-client --purge -y
```




