package com.v01d.learn_image_upload_download.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.v01d.learn_image_upload_download.entities.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {
    Image findByName(String name);
}
