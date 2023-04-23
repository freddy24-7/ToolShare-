
package com.toolshare.toolshare.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataObtained {

    /**
     * The name of the uploaded file.
     */
    private String fileName;

    /**
     * The download URL of the uploaded file.
     */
    private String downloadURL;

    /**
     * The file type of the uploaded file.
     */
    private String fileType;

    /**
     * The size of the uploaded file.
     */
    private long fileSize;

}
