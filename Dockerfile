FROM openjdk:8
ADD target/nsoft-zadatak.jar nsoft-zadatak.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar" , "nsoft-zadatak.jar"]