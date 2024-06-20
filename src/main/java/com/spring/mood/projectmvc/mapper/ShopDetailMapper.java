package com.spring.mood.projectmvc.mapper;

import com.spring.mood.projectmvc.dto.responseDto.ShopDetailResponseDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ShopDetailMapper {

    // 주문 완료 버튼 누르면 카트에 누른 아이템 정보와 유저 정보 저장
    boolean save(ShopDetailResponseDTO dto); // user_account , shop_item_id 포함된 DTO


}
