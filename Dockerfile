# Sử dụng image JDK để chạy app
FROM openjdk:17-jdk-alpine

# Tạo thư mục app trong container
WORKDIR /app

# Copy file jar vào container
COPY target/demo-0.0.1-SNAPSHOT.jar app.jar

# Lệnh chạy app
ENTRYPOINT ["java", "-jar", "app.jar"]
