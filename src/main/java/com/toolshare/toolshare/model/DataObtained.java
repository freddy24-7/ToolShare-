package com.toolshare.toolshare.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//This class specifies data to be received when files are uploaded

@Data
@NoArgsConstructor
@AllArgsConstructor

public class DataObtained {

    //Defining the variables
    private String fileName;
    private String downloadURL;
    private String fileType;
    private long fileSize;
}
