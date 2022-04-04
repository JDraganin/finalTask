package com.example.finaltask.dto;

import com.example.finaltask.model.Item;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
public class SupermarketViewDto {
    private String name;
    private String address;
    private String phoneNumber;
    private String workHours;
    private Set<Item> items;
}