
package com.toolshare.toolshare.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@Entity(name = "ImageFile")
@Table(name = "imagefile")
public class ImageFile {

    /**
     * The unique identifier for the ImageFile object.
     */
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id")
    private String id;

    /**
     * The name of the uploaded image file.
     */
    @Column(name = "file_name")
    private String fileName;

    /**
     * The file type of the uploaded image file.
     */
    @Column(name = "file_type")
    private String fileType;

    /**
     * The byte array of the uploaded image file.
     */
    @Lob
    private byte[] data;

    /**
     * Creates a new ImageFile object with the given
     * file name, file type, and data.
     *
     * @param imageFileName The name of the uploaded image file.
     * @param imageFileType The file type of the uploaded image file.
     * @param imageData The byte array of the uploaded image file.
     */
    public ImageFile(
            final String imageFileName,
            final String imageFileType,
            final byte[] imageData) {
        this.fileName = imageFileName;
        this.fileType = imageFileType;
        this.data = imageData;
    }
}
