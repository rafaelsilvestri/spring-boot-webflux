FROM amazoncorretto:8

RUN mkdir /opt/apps

WORKDIR /opt/apps

COPY build/libs/spring-boot-webflux-0.0.1-SNAPSHOT.jar ./

EXPOSE 8081
EXPOSE 9090

CMD java \
-Dconfig.file=dbconfig-prod.properties \
-Dcom.sun.management.jmxremote.rmi.port=9090 \
-Dcom.sun.management.jmxremote=true \
-Dcom.sun.management.jmxremote.port=9090 \ 
-Dcom.sun.management.jmxremote.ssl=false \
-Dcom.sun.management.jmxremote.authenticate=false \
-Dcom.sun.management.jmxremote.local.only=false \
-Djava.rmi.server.hostname=${HOST} \
-Xms512m -Xmx512m \
-jar spring-boot-webflux-0.0.1-SNAPSHOT.jar
