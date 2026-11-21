FROM eclipse-temurin:17-jre-jammy AS runtime

RUN groupadd -r appgroup && useradd -r -g appgroup appuser
WORKDIR /app

COPY target/task-manager-1.0.0.jar app.jar
RUN chown -R appuser:appgroup /app

USER appuser
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
