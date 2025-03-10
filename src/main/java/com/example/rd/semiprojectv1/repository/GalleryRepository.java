package com.example.rd.semiprojectv1.repository;

import com.example.rd.semiprojectv1.domain.GalleryImage;
import com.example.rd.semiprojectv1.domain.GalleryListDTO;
import com.example.rd.semiprojectv1.domain.GalleryViewDTO;
import com.example.rd.semiprojectv1.domain.NewGalleryDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface GalleryRepository {

    @Select("select gno, title, userid, regdate, thumbs, views, simgname from gallerys order by gno desc")
    List<GalleryListDTO> selectGallery();

    @Update("update gallerys set views = views + 1 where gno = #{gno}")
    void updateViewOne(String gno);

    @Select("select gno, title, userid, regdate, thumbs, views, contents from gallerys where gno = #{gno}")
    GalleryViewDTO selectOneGallery(String gno);

    @Select("select imgname, imgsize from gallery_images where gno = #{gno}")
    List<GalleryImage> selectGalleryImages(String gno);

    int insertGallery(NewGalleryDTO gal);
}
