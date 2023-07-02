FROM openjdk:20
EXPOSE 8080
ADD target/crud1exampledemo.jar crud1exampledemo.jar
ENTRYPOINT ["java","-jar","/crud1exampledemo.jar"]