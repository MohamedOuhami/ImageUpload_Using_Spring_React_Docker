package com.v01d.learn_image_upload_download.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.print.attribute.standard.Media;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.v01d.learn_image_upload_download.entities.Image;
import com.v01d.learn_image_upload_download.services.ImageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/images")
@CrossOrigin(origins = "*")
public class ImageController {
    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    // * End point to upload Multiple Images coming from a client by looping through
    // them and calling the uploadImage service method
    @PostMapping
    public ResponseEntity<?> uploadImage(@RequestParam("images") List<MultipartFile> images) throws IOException {
        System.out.println(images);
        for (MultipartFile image : images) {
            imageService.uploadImage(image);
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body("Uploaded Images");
    }

    // * Endpoint to download a specific image content byte from the server based on
    // the image name

    @GetMapping("/{imageName}")
    public ResponseEntity<?> downloadImage(@PathVariable String imageName)
            throws IOException {

        Image image = imageService.imageDetails(imageName);
        return ResponseEntity.status(HttpStatus.OK).body(image);

    }

    @GetMapping
    public ResponseEntity<?> getAllImagesData() throws IOException {

        List<Image> images = imageService.getAllImages().getBody();

        for (Image image : images) {
            image.setImage_data(imageService.imageContent(image.getName()));
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(images);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        return imageService.deleteImageById(id);
    }
}