
package com.toolshare.toolshare.service;

import com.toolshare.toolshare.model.ImageFile;
import org.springframework.web.multipart.MultipartFile;

/**
 * Interface for managing image files.
 */
public interface FileService {

    /**
     * Saves an image file to the database.
     *
     * @param file the image file to save
     * @return the saved image file
     * @throws Exception if the file cannot be saved
     */
    ImageFile saveImageFile(MultipartFile file) throws Exception;

    /**
     * Gets an image file from the database by ID.
     *
     * @param id the ID of the image file to get
     * @return the image file with the specified ID
     * @throws Exception if the image file with the given ID is not found
     */
    ImageFile getImageFile(String id) throws Exception;
}
