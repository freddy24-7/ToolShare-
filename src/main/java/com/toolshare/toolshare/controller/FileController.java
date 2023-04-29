
package com.toolshare.toolshare.controller;

import com.toolshare.toolshare.model.DataObtained;
import com.toolshare.toolshare.model.ImageFile;
import com.toolshare.toolshare.service.FileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("")
public class FileController {

    /**
     * The file service where the business logic for
     * file uploads and downloads takes place.
     */
    @Autowired
    private FileService fileService;

    /**
     * Handles the upload of an image file.
     *
     * @param file the file to upload
     * @return a data object containing information about the uploaded file
     * @throws Exception if there is an error uploading the file
     */
    @PostMapping("api/imagefile/upload")
    @Operation(summary = "This API allows a participant to post an image.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "image uploaded successfully",
                    content = {@Content(mediaType = "multipart/form-data",
                            schema = @Schema(
                                    implementation = DataObtained.class))}),
            @ApiResponse(responseCode = "400",
                    description =
                            "Bad Request"),
            @ApiResponse(responseCode = "500",
                    description = "Internal server error")
    })
    public DataObtained uploadFile(
            @RequestParam("file") @RequestPart("file")
            final MultipartFile file) throws Exception {
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

    /**
     * Handles the download of an image file.
     *
     * @param fileId the ID of the file to download
     * @return a response entity containing the file and its metadata
     * @throws Exception if there is an error downloading the file
     */
    @GetMapping("download/{fileId}")
    @Operation(summary = "This API allows a participant to download an image.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "image downloaded successfully",
                    content = {@Content(mediaType = "multipart/form-data",
                            schema = @Schema(
                                    implementation = DataObtained.class))}),
            @ApiResponse(responseCode = "400",
                    description =
                            "Bad Request"),
            @ApiResponse(responseCode = "500",
                    description = "Internal server error")
    })
    public ResponseEntity<Resource> downloadFile(
            @PathVariable final String fileId) throws Exception {
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
