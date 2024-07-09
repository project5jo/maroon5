package com.spring.mood.projectmvc.service;

import com.spring.mood.projectmvc.dto.responseDto.ShopItemResponseDto;
import com.spring.mood.projectmvc.entity.Member;
import com.spring.mood.projectmvc.entity.ShopItem;
import com.spring.mood.projectmvc.mapper.MemberMapper;
import com.spring.mood.projectmvc.mapper.ShopItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShopItemService {

    private final ShopItemMapper shopItemMapper;
    private final MemberMapper memberMapper;

    // 키워드 기반 검색
    public List<ShopItem> searchShopItems(String keyword) {
        return shopItemMapper.findShopItemsByKeyword(keyword);
    }

    // 모든 아이템 가져오기
    public List<ShopItem> getAllItems() {
        return shopItemMapper.findAll();
    }

    // ID 기반 검색
    public ShopItem getItemById(Long id) {
        return shopItemMapper.findOne(id);
    }

    // 사용자 역할 가져오기
    public String getUserRoleByUsername(String username) {
        Member member = memberMapper.findOne(username);
        return member != null ? member.getUserRole() : "ROLE_ANONYMOUS";
    }

    // ShopItem을 ShopItemResponseDto로 변환하는 메서드
    private List<ShopItemResponseDto> convertToDtoList(List<ShopItem> shopItems) {
        return shopItems.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private ShopItemResponseDto convertToDto(ShopItem shopItem) {
        return new ShopItemResponseDto(
                shopItem.getShopItemId(),
                shopItem.getShopItemName(),
                shopItem.getShopItemDesc(),
                shopItem.getShopItemPrice(),
                shopItem.getShopItemImg(),
                shopItem.getShopItemDate(),
                shopItem.getShopItemStock(),
                shopItem.getShopItemView()
        );
    }

    // 삭제 요청 중간처리
    public boolean deleteItem(Long shopItemId) {
        return shopItemMapper.delete(shopItemId);
    }
}
