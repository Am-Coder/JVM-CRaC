## Spring Boot, JVM and CRaC

This is a sample project to demonstrate Spring Boot 3.2+ CRaC support by creating a checkpoint and then restoring your application using it.

### Step 1: Prerequisites
* Download JDK 17 with CRaC support provided by Azul From [here](https://www.azul.com/downloads/?version=java-17-lts&os=ubuntu&architecture=x86-64-bit&package=jdk-crac#zulu
) and install it. This one is for Debian based OS like Ubuntu.
```
sudo dpkg -i zulu17.58.25-ca-crac-jdk17.0.15-linux_amd64.deb
```
* Grant appropriate permissions for CRIU to be functional. Note that you need to be present in your java home directory of the JDK you downloaded in previous step.
```
sudo chown root:root ./lib/criu

sudo chmod u+s ./lib/criu
```
### Step 2: Build
```
mvn clean package
```

### Step 3: Deploy
```
/usr/lib/jvm/zulu-17-crac-amd64/bin/java -XX:CRaCCheckpointTo=./tmp_chkpoint -jar ./target/crac-demo-0.0.1-SNAPSHOT.jar
```

### Step 4: Checkpoint
Find the PID of the running instance and use it to create an application checkpoint:
```
jcmd 189254 JDK.checkpoint
```

### Step 5: Restore
Restore and bring your instance back up:
```
/usr/lib/jvm/zulu-17-crac-amd64/bin/java -XX:CRaCRestoreFrom=./tmp_chkpoint
```