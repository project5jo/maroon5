package com.spring.mood.projectmvc.mapper;

import com.spring.mood.projectmvc.entity.ShopItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShopItemMapper {

    // 디테일 창 들어갈때 씀
    ShopItem findOne(Long id);

    // 관리자가 업로드 할때 씀
    boolean save(ShopItem shopItem);

    // 디테일 창 들어갈때 뷰카운트 + 1
    void upViewCount(Long id);

//    Long count()
}
