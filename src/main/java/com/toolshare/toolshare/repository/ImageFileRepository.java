
package com.toolshare.toolshare.repository;

import com.toolshare.toolshare.model.ImageFile;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Provides methods for accessing and manipulating
 * image file data in the database.
 */
public interface ImageFileRepository extends JpaRepository<ImageFile, String> {

}
