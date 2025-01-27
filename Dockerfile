FROM openjdk:17

COPY target/civilink-gateway-server.jar .

EXPOSE 9090

ENTRYPOINT ["java","-jar","civilink-gateway-server.jar"]