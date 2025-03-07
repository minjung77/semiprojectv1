package com.example.rd.semiprojectv1.service;

import com.example.rd.semiprojectv1.domain.GalleryListDTO;
import com.example.rd.semiprojectv1.domain.GalleryImageDTO;

import java.util.List;

public interface GalleryService {

    List<GalleryListDTO> selectGallery();

    GalleryImageDTO readOneGalleryImage(String gno);
}
