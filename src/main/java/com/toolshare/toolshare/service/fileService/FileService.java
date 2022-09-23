package com.toolshare.toolshare.service.fileService;

import com.toolshare.toolshare.model.ImageFile;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    ImageFile saveImageFile(MultipartFile file) throws Exception;


    ImageFile getImageFile(String fileId) throws Exception;
}
