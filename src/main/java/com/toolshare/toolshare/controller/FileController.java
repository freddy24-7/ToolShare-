package com.toolshare.toolshare.controller;

//This controller class sets up upload and download APIs that can be accessed
//from the front-end port 3000 (as per the CORSConfig class)

import com.toolshare.toolshare.model.DataObtained;
import com.toolshare.toolshare.model.ImageFile;
import com.toolshare.toolshare.service.fileService.FileService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("")
public class FileController {

    private FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("api/imagefile/upload")
    public DataObtained uploadFile(@RequestParam("file") MultipartFile file) throws Exception {
        ImageFile imageFile = null;
        String downloadUrl = "";
        imageFile = fileService.saveImageFile(file);
        downloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("download/")
                .path(imageFile.getId())
                .toUriString();
        return new DataObtained(imageFile.getFileName(),
                downloadUrl,
                file.getContentType(),
                file.getSize()
        );

    }

    @GetMapping("download/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) throws Exception {
        ImageFile imageFile = null;
        imageFile = fileService.getImageFile(fileId);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(imageFile.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + imageFile.getFileName()
                                + "\"")
                .body(new ByteArrayResource(imageFile.getData()));

    }

}
