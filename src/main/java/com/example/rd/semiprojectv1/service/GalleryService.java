package com.example.rd.semiprojectv1.service;

import com.example.rd.semiprojectv1.domain.GalleryListDTO;
import com.example.rd.semiprojectv1.domain.GalleryImageDTO;
import com.example.rd.semiprojectv1.domain.NewGalleryDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface GalleryService {

    List<GalleryListDTO> selectGallery();

    GalleryImageDTO readOneGalleryImage(String gno);

    boolean newGalleryImage(NewGalleryDTO gal, java.util.List<org.springframework.web.multipart.MultipartFile> ginames);
}
