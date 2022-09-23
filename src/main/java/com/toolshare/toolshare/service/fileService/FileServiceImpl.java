package com.toolshare.toolshare.service.fileService;

import com.toolshare.toolshare.model.ImageFile;
import com.toolshare.toolshare.repository.ImageFileRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl implements FileService {

    private ImageFileRepository imageFileRepository;

    public FileServiceImpl(ImageFileRepository imageFileRepository) {
        this.imageFileRepository = imageFileRepository;
    }


    @Override
    public ImageFile saveImageFile(MultipartFile file) throws Exception {
        String fileName = StringUtils.cleanPath((file.getOriginalFilename()));
        try {
            if(fileName.contains("..")) {
                throw  new Exception("Filename contains invalid path sequence "
                        + fileName);
            }

            ImageFile imageFile
                    = new ImageFile(fileName,
                    file.getContentType(),
                    file.getBytes());
            return imageFileRepository.save(imageFile);

        } catch (Exception e) {
            throw new Exception("Could not save File: " + fileName);
        }


    }

    @Override
    public ImageFile getImageFile(String fileId) throws Exception {
        return imageFileRepository
                .findById(fileId)
                .orElseThrow(() -> new Exception("File not found with Id: " + fileId));

    }

}
