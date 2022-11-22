package com.toolshare.toolshare.model;


//Using lombok to avoid boilerplate code for getters, setters, constructors, ToString
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity(name = "ImageFile")
@Table(name = "imagefile"
)
public class ImageFile {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;

    //Defining the variables of the image-upload class
    private String fileName;
    private String fileType;

    //Defining Lob - large object type - for the data that is the image file
    @Lob
    private byte[] data;


    //Defining one extra constructor - with all variables except the id
    public ImageFile(String fileName, String fileType, byte[] data) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
    }


}
