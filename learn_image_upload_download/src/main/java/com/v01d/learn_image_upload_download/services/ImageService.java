package com.v01d.learn_image_upload_download.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.v01d.learn_image_upload_download.entities.Image;
import com.v01d.learn_image_upload_download.repositories.ImageRepository;

import org.springframework.http.ResponseEntity;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    private final String IMAGE_PATH = "/app/images/";


    // * Method to upload a given image to the database and store the image in the
    // file system

    public String uploadImage(MultipartFile uploaded_image) throws IOException {


        String file_path = IMAGE_PATH + uploaded_image.getOriginalFilename();

        Image new_image = Image.builder()
                .name(uploaded_image.getOriginalFilename())
                .path(file_path)
                .build();

        if (new_image != null) {

            Image image = imageRepository.save(new_image);
            // Store the image in the folder system
            uploaded_image.transferTo(new File(file_path));
            if (image != null) {

                System.out.println("THe service uploaded here" + file_path);
                return "Image uploaded successfully";
            }
        }

        return "Image Upload failed";

    }

    // * A method to get the data and content of the image based on the file name

    public byte[] imageContent(String filename) throws IOException {

        Image dbImage = imageDetails(filename);
        String filePath = dbImage.getPath();

        System.out.println("The service is looking here" + filePath);

        byte[] image = Files.readAllBytes(new File(filePath).toPath());

        return image;
    }

    // * Get the info ( Image model ) of the image based on the image name

    public Image imageDetails(String filename) throws IOException {
        Image image = imageRepository.findByName(filename);

        return image;
    }

    // * Fetching all of the images from the database (Image model)

    public ResponseEntity<List<Image>> getAllImages() {
        List<Image> entities = imageRepository.findAll();
        return ResponseEntity.ok(entities);
    }

    // * Method to delete an image from the database based on the id
    public ResponseEntity<Void> deleteImageById(Integer id) {

        Image image_to_delete = imageRepository.findById(id).get();

        if (image_to_delete != null) {
            imageRepository.delete(image_to_delete);

            try {
                System.out.println("Image Deleted");
                Path path = Paths.get(image_to_delete.getPath());
                Files.deleteIfExists(path);
            } catch (IOException err) {
                err.printStackTrace();
            }
        }

        return ResponseEntity.noContent().build();
    }

}