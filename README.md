# Image Gallery Using Spring React and Docker
---
This project represents a personal project of an Image Gallery made with Spring (Java) as the backend, and React as the front end. Finishing by containerizing the project using 
Docker

## Backend Spring : 
In the backend, I used Spring to create my entities ( Image ),controller and service ( THe service also contains a method to save the image in the file system, to fasten the data
retrieval of the images from the database ( MySQL ) )

## FrontEnd React :
For the frontend, I thought of using an SPA, so the choice was put on React, where I've created one component that fetches the data asynchronously from the Spring server and get 
the bytes of the images that was converted in the server and was not stored to show the list of the images. The images can be added by batch (More than one image at once), and show and deleted

---
# Image demo : 
The following is a quick demo for the application ran using Docker
Make sure that all of the ports you're using in the app are free before using them

https://github.com/MohamedOuhami/ImageUpload_Using_Spring_React_Docker/assets/60238938/496b7878-d117-4ce6-a463-a883406c69d8


