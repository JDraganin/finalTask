package com.example.finaltask.service.implementation;

import com.example.finaltask.dto.PurchaseDto;
import com.example.finaltask.dto.PurchaseViewDto;
import com.example.finaltask.exception.NoSuchFieldException;
import com.example.finaltask.model.Enums.PaymentType;
import com.example.finaltask.model.Item;
import com.example.finaltask.model.Purchase;
import com.example.finaltask.model.Supermarket;
import com.example.finaltask.repository.ItemsRepository;
import com.example.finaltask.repository.PurchaseRepository;
import com.example.finaltask.repository.SupermarketRepository;
import com.example.finaltask.service.PurchaseService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.lang.reflect.Field;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {
    private final SupermarketRepository supermarketRepository;
    private final ItemsRepository itemsRepository;
    private final PurchaseRepository purchaseRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public ResponseEntity buyItem(PurchaseDto dto) {
        if (dto.getPaymentType().equals("CASH") && dto.getCashAmount() == null) {
            return ResponseEntity.badRequest().body("CashAmount is required field if payment type is cash");
        }
        String superMarketId = dto.getSuperMarketId();
        Set<String> itemsId = dto.getItemsId();
        if (supermarketRepository.findById(UUID.fromString(superMarketId)).isEmpty()) {
            return ResponseEntity.badRequest().body("There is no such supermarket");
        }
        Supermarket supermarket = supermarketRepository.findById(UUID.fromString(superMarketId)).get();
        Set<Item> availableItems = new HashSet<>();
        for (String itemId : itemsId) {
            Optional<Item> optionalItem = itemsRepository.findById(UUID.fromString(itemId));
            if (optionalItem.isPresent() && supermarket.getItems().contains(optionalItem.get())) {
                availableItems.add(optionalItem.get());
            }

        }
        double totalSum = availableItems.stream().map(Item::getPrice).mapToDouble(Double::doubleValue).sum();
        if (dto.getCashAmount() < totalSum) {
            return ResponseEntity.badRequest().body("not enough money");
        }
        Purchase purchase = new Purchase();
        purchase.setItems(availableItems);
        purchase.setChangeAmount(dto.getCashAmount() - totalSum);
        purchase.setPaymentType(PaymentType.valueOf(dto.getPaymentType()));
        purchase.setTotalPrice(totalSum);
        purchase.setTime(LocalTime.now());
        return new ResponseEntity(modelMapper.map(purchase, PurchaseViewDto.class), HttpStatus.CREATED);
    }

    @Override
    public Page<PurchaseViewDto> findAll(Map<String, String> fieldKeyAndValueMap, Pageable pageable) {
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Purchase purchase;
        try {
            purchase = buildUserProperties(fieldKeyAndValueMap);
        } catch (IllegalArgumentException | IllegalAccessException e) {

            throw new NoSuchFieldException("There is no such field");
        }
        return purchaseRepository.findAll(Example.of(purchase, exampleMatcher), pageable).map((u -> modelMapper.map(u, PurchaseViewDto.class)));
    }

    private Purchase buildUserProperties(Map<String, String> fieldKeyAndValueMap) throws IllegalAccessException, IllegalArgumentException {
        Purchase purchase = new Purchase();
        if (fieldKeyAndValueMap == null) {
            return purchase;
        }
        Field[] fields = Purchase.class.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (fieldKeyAndValueMap.get(field.getName()) != null) {
                if (field.getType().isEnum()) {
                    field.set(purchase, Enum.valueOf((Class<PaymentType>) field.getType(),
                            fieldKeyAndValueMap.get(field.getName()).toUpperCase()));
                } else {
                    field.set(purchase, fieldKeyAndValueMap.get(field.getName()));
                }
            }
        }
        return purchase;
    }
}