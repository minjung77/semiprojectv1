package com.example.rd.semiprojectv1.service;

import com.example.rd.semiprojectv1.domain.*;
import com.example.rd.semiprojectv1.repository.GalleryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class GalleryServiceImpl implements GalleryService {

    private final GalleryRepository galleryMapper;
    private final GalleryUploadService galleryUploadService;

    @Override
    public List<GalleryListDTO> selectGallery() {
        return galleryMapper.selectGallery();
    }

    @Transactional
    @Override
    public GalleryImageDTO readOneGalleryImage(String gno) {
        
        galleryMapper.updateViewOne(gno);// 조회수 증가
        GalleryViewDTO gal = galleryMapper.selectOneGallery(gno);// 본문 글 가져오기
        List<GalleryImage> gi = galleryMapper.selectGalleryImages(gno);// 본문글에 포함된 이미지들 가져오기

        return new GalleryImageDTO(gal, gi);
    }

    @Transactional
    @Override
    public boolean newGalleryImage(NewGalleryDTO gal, List<MultipartFile> ginames) {
        boolean result = false;
        // 작성한 게시글을 gallerys에 저장하고, 생성된 글 번호를 알아냄
        int gno = -999;
        try {
            if(galleryMapper.insertGallery(gal)>0) gno = gal.getGgno();
        }catch (Exception e){
            throw new IllegalStateException("insertGalley 오류 발생");
        }
        // 첨부된 파일을 업로드 처리하고
        // 알아낸 글 번호로 첨부된 파일들에 대한 정보를 db에 gallery_images 에 저장
        List<NewGalleryImageDTO> gis = null;
        if(!ginames.isEmpty()) {// 첩부 파일이 존재한다면
            gis = galleryUploadService.processUpload(ginames, gno);

            log.info("{}", gis.size());
            // 업로드된 파일의 정보를 gallery_images 테이블에 저장
            // 즉, 첨부된 파일정보를 개별 행으로 저장
            try{
                for(NewGalleryImageDTO gi : gis) {
                    galleryMapper.insertGalleryImage(gi);
                }
            }catch (Exception e){
                throw new IllegalStateException("insertGalleryImg 오류 발생");
            }
            // 첨부된 파일들 중 첫번째 이미지 파일을 썸네일 처리
            try {
                galleryUploadService.makeThumbnail(gal.getSimgname(), gis.get(0).getImgname());
            }catch (Exception e){
                throw new IllegalStateException("makeThumbnail 오류 발생");
            }
        }

        return result;
    }
}
