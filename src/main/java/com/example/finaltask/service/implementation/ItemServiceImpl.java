package com.example.finaltask.service.implementation;

import com.example.finaltask.dto.ItemDto;
import com.example.finaltask.model.Item;
import com.example.finaltask.repository.ItemsRepository;
import com.example.finaltask.service.ItemService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemsRepository itemsRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public ItemDto createItem(ItemDto dto) {
        Item item = modelMapper.map(dto, Item.class);
        itemsRepository.save(item);
        return dto;
    }
}