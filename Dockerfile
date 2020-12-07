FROM openjdk:8
ADD target/nsoftZadatak-0.0.1-SNAPSHOT.jar nsoft-zadatak.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar" , "nsoft-zadatak.jar"]