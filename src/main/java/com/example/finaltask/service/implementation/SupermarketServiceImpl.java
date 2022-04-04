package com.example.finaltask.service.implementation;

import com.example.finaltask.dto.SupermarketDto;
import com.example.finaltask.dto.AddItemsDto;
import com.example.finaltask.exception.NameAlreadyExistException;
import com.example.finaltask.model.Item;
import com.example.finaltask.model.Supermarket;
import com.example.finaltask.dto.SupermarketViewDto;
import com.example.finaltask.repository.ItemsRepository;
import com.example.finaltask.repository.SupermarketRepository;
import com.example.finaltask.service.SupermarketService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SupermarketServiceImpl implements SupermarketService {
    private final SupermarketRepository supermarketRepository;
    private final ItemsRepository itemsRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public SupermarketDto createSupermarket(SupermarketDto dto) {

        Optional<Supermarket> optionalSupermarket = supermarketRepository.findSupermarketByName(dto.getName());
        if (optionalSupermarket.isPresent()) {
            throw new NameAlreadyExistException("Supermarket with this name already exists");
        }
        Supermarket supermarket = modelMapper.map(dto, Supermarket.class);
        supermarketRepository.save(supermarket);
        return dto;
    }

    @Override
    public String addItemsToSupermarket(AddItemsDto dto) {
        String supermarketID = dto.getSupermarketID();
        List<String> itemsID = dto.getItemsId();
        Optional<Supermarket> optionalSupermarket = supermarketRepository.findById(UUID.fromString(supermarketID));
        if (optionalSupermarket.isEmpty()) {
            return "There is no such supermarket";
        }
        Supermarket supermarket = optionalSupermarket.get();
        List<Item> availableItems = new ArrayList<>();
        for (String itemID : itemsID) {
            Optional<Item> optionalItem = itemsRepository.findById(UUID.fromString(itemID));

            optionalItem.ifPresent(availableItems::add);

            supermarket.addItems(availableItems);
            supermarketRepository.save(supermarket);

        }
        String addedItems = supermarket.getItems().stream().map(Item::getName)
                .collect(Collectors.joining(","));
        return String.format("Supermarket:%s - Items:%s", supermarket.getName(), addedItems);
    }

    @Override
    public ResponseEntity findSupermarketById(String id) {
        Optional<Supermarket> optionalSupermarket = supermarketRepository.findById(UUID.fromString(id));
        if (optionalSupermarket.isPresent()) {
            return ResponseEntity.ok(modelMapper.map(optionalSupermarket.get(), SupermarketViewDto.class));
        } else {
            return ResponseEntity.badRequest().body("There is no such supermarket");
        }
    }
}