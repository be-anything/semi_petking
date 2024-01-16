FROM tomcat:9-jdk17
COPY target/semi_petking.war /usr/local/tomcat/webapps/petking.war
COPY src/main/resources/configs/Wallet_G6TQWS0RD531W8SK /Wallet_G6TQWS0RD531W8SK