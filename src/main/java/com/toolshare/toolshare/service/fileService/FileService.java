package com.toolshare.toolshare.service.fileService;

import com.toolshare.toolshare.model.ImageFile;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    //Interface for uploading and downloading an image file
    ImageFile saveImageFile(MultipartFile file) throws Exception;

    ImageFile getImageFile(String id) throws Exception;


}
