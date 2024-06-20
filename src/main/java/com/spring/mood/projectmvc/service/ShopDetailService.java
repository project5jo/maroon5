package com.spring.mood.projectmvc.service;

import com.spring.mood.projectmvc.mapper.ShopDetailMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShopDetailService {

    private final ShopDetailMapper shopDetailMapper;

}
