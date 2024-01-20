# Use a smaller base image for the runtime stage
FROM openjdk:17-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the entire source code to the container
COPY target/Image_Gallery.jar target/

# Expose the port your application will run on
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "target/Image_Gallery.jar"]
