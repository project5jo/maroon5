//package com.spring.mood.projectmvc.mapper;
//
//import com.spring.mood.projectmvc.entity.ShopItem;
//import org.apache.ibatis.annotations.Mapper;
//
//import java.util.List;
//
//@Mapper
//public interface ShopItemMapper {
//
//    // 디테일 창 들어갈때 씀
//    ShopItem findOne(Long id);
//
//    // 관리자가 업로드 할때 씀
//    boolean save(ShopItem shopItem);
//
//    // 디테일 창 들어갈때 뷰카운트 + 1
//    void upViewCount(Long id);
//
////    Long count()
//}

package com.spring.mood.projectmvc.mapper;

import com.spring.mood.projectmvc.entity.ShopItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ShopItemMapper {

    // 디테일 창 들어갈 때 사용
    ShopItem findOne(@Param("id") Long id);

    // 모든 아이템 조회
    List<ShopItem> findAll();

    // 관리자가 업로드할 때 사용
    boolean save(ShopItem shopItem);

    // 디테일 창 들어갈 때 뷰 카운트 + 1
    void upViewCount(@Param("id") Long id);

    // 키워드로 아이템 검색
    List<ShopItem> findShopItemsByKeyword(@Param("keyword") String keyword);
}
