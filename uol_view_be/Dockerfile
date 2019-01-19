FROM openjdk:8
MAINTAINER "rafael.sjp17@gmail.com"
ADD target/uol_cadastro_be.jar uol_cadastro_be.jar
#EXPOSE 8080
#CMD ["./wait-for-it.sh", "db:3306", "--", "java", "-jar", "app.jar"]
ENTRYPOINT ["java", "-jar", "uol_cadastro_be.jar"]
