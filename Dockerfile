FROM openjdk:15
COPY ./build/libs/backend-0.0.1-SNAPSHOT.jar aiMasters-backend-0.0.1-SNAPSHOT.jar
EXPOSE 8089
ENTRYPOINT ["sh", "-c", "java -Dfile.encoding=UTF-8 ${JAVA_OPTS} -jar aiMasters-backend-0.0.1-SNAPSHOT.jar ${0} ${@}"]
