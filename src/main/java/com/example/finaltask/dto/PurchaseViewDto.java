package com.example.finaltask.dto;

import com.example.finaltask.model.Enums.PaymentType;
import com.example.finaltask.model.Item;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class PurchaseViewDto   {
    private  PaymentType paymentType;
    private  Double totalPrice;
    private  Double changeAmount;
    private  LocalTime time;
    private Set<Item> items;
}