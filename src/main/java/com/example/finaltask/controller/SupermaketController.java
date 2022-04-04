package com.example.finaltask.controller;

import com.example.finaltask.dto.AddItemsDto;
import com.example.finaltask.dto.SupermarketDto;
import com.example.finaltask.service.SupermarketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/supermarket")
public class SupermaketController {
    private final SupermarketService supermarketService;

    @PostMapping("/create")
    public ResponseEntity<SupermarketDto> createSupermarket(@Valid @RequestBody SupermarketDto dto) {
        return new ResponseEntity<>(supermarketService.createSupermarket(dto), HttpStatus.CREATED);
    }

    @PostMapping("/addItems")
    public ResponseEntity<String> addItems(@Valid @RequestBody AddItemsDto dto) {
        return ResponseEntity.ok(supermarketService.addItemsToSupermarket(dto));

    }

    @GetMapping("/{id}")
    public ResponseEntity getSupermarketById(@PathVariable("id") String id) {
        return supermarketService.findSupermarketById(id);
    }
}