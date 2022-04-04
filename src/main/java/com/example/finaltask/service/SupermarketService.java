package com.example.finaltask.service;

import com.example.finaltask.dto.SupermarketDto;
import com.example.finaltask.dto.AddItemsDto;
import org.springframework.http.ResponseEntity;

public interface SupermarketService {
    SupermarketDto createSupermarket(SupermarketDto dto);

    String addItemsToSupermarket(AddItemsDto dto);

    ResponseEntity findSupermarketById(String id);
}