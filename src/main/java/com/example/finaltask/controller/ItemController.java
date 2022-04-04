package com.example.finaltask.controller;

import com.example.finaltask.dto.ItemDto;
import com.example.finaltask.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/items")
public class ItemController {
    private final ItemService itemService;


    @PostMapping
    public ResponseEntity<ItemDto> createItem(@Valid @RequestBody ItemDto dto) {
        return new ResponseEntity<>(itemService.createItem(dto), HttpStatus.CREATED);
    }
}