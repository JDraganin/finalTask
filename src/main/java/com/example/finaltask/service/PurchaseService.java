package com.example.finaltask.service;

import com.example.finaltask.dto.PurchaseDto;
import com.example.finaltask.dto.PurchaseViewDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import java.util.Map;

public interface PurchaseService {
    ResponseEntity buyItem(PurchaseDto dto);

    Page<PurchaseViewDto> findAll(Map<String, String> fieldKeyAndValueMap, Pageable pageable);
}