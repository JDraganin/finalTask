package com.example.finaltask.controller;

import com.example.finaltask.dto.PurchaseDto;
import com.example.finaltask.dto.PurchaseViewDto;
import com.example.finaltask.service.PurchaseService;
import com.example.finaltask.util.SearchUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/purchase")
public class PurchaseController {
    private final PurchaseService purchaseService;

    @PostMapping
    public ResponseEntity buyItemsFromSupermarket(@Valid @RequestBody PurchaseDto dto) {
        return purchaseService.buyItem(dto);
    }

    @GetMapping
    public ResponseEntity<Page<PurchaseViewDto>> getAll(Pageable pageable, @RequestParam String keyword) {
        return ResponseEntity.ok(purchaseService.findAll(SearchUtil.buildEntityProperties(keyword), pageable));
    }
}