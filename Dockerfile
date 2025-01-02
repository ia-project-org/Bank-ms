# Builder stage
FROM bellsoft/liberica-runtime-container:jdk-21-stream-musl as builder
WORKDIR /app
ADD . /app
WORKDIR /app
RUN chmod +x mvnw
RUN ./mvnw clean package -Dmaven.test.skip=true

# Runtime stage
FROM bellsoft/liberica-runtime-container:jre-21-musl
WORKDIR /app

# Expose the necessary port
EXPOSE 9001

# Copy the built JAR file from the builder stage
COPY --from=builder /app/target/*.jar app.jar

# Copy the resources/static folder from the builder stage
COPY --from=builder /app/target/classes/static/*.csv /app/resources/static/clientsDetails.csv

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
