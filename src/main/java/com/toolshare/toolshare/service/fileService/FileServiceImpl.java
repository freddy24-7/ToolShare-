package com.toolshare.toolshare.service.fileService;

import com.toolshare.toolshare.model.ImageFile;
import com.toolshare.toolshare.repository.ImageFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl implements FileService {

    //importing repository and instantiating
    @Autowired
    private ImageFileRepository imageFileRepository;

    //checking for correct format and saving image-file
    @Override
    public ImageFile saveImageFile(MultipartFile file) throws Exception {
        String fileName = StringUtils.cleanPath((file.getOriginalFilename()));
        try {
            if(fileName.contains("..")) {
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

    //Obtaining image-file (downloading)
    @Override
    public ImageFile getImageFile(String fileId) throws Exception {
        return imageFileRepository
                .findById(fileId)
                .orElseThrow(() -> new Exception("Bestand niet gevonden met id: " + fileId));
    }
}
