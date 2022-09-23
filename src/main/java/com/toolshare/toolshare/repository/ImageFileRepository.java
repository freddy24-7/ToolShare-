package com.toolshare.toolshare.repository;

import com.toolshare.toolshare.model.ImageFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageFileRepository extends JpaRepository <ImageFile, String> {

}
