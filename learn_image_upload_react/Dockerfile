# Use an official Node.js image as the base image
FROM node:21-alpine

# Set the working directory inside the container
WORKDIR /app

# # Copy package.json and package-lock.json (if available)
COPY package.json ./

# # Install dependencies
RUN npm install

# Copy the entire project to the working directory
COPY . .

# # Build the React app
# RUN npm run build

# Expose the port on which the app will run (default for React is 3000)
EXPOSE 3000

# Define the command to run your app
CMD ["npm", "start"]
