
package com.toolshare.toolshare.service;

import com.toolshare.toolshare.model.ImageFile;
import com.toolshare.toolshare.repository.ImageFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * Service implementation for managing image files.
 */
@Service
public class FileServiceImpl implements FileService {

    /**
     * The repository for managing image files.
     */
    @Autowired
    private ImageFileRepository imageFileRepository;

    /**
     * Saves an image file to the database.
     *
     * @param file the image file to save
     * @return the saved image file
     * @throws Exception if the file cannot be saved
     */
    @Override
    public ImageFile saveImageFile(final MultipartFile file) throws Exception {
        String fileName = StringUtils.cleanPath((file.getOriginalFilename()));
        try {
            if (fileName.contains("..")) {
                throw  new Exception("Bestandsnaam bevat ongeldige padvolgorde "
                        + fileName);
            }
            ImageFile imageFile
                    = new ImageFile(fileName,
                    file.getContentType(),
                    file.getBytes());
            return imageFileRepository.save(imageFile);
        } catch (Exception e) {
            throw new Exception("Kon bestand niet opslaan: " + fileName);
        }
    }

    /**
     * Gets an image file from the database by ID.
     *
     * @param fileId the ID of the image file to get
     * @return the image file with the specified ID
     * @throws Exception if the image file with the given ID is not found
     */
    @Override
    public ImageFile getImageFile(final String fileId) throws Exception {
        return imageFileRepository
                .findById(fileId)
                .orElseThrow(() -> new Exception(
                        "Bestand niet gevonden met id: " + fileId));
    }
}
