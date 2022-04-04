package com.example.finaltask.dto;

import com.example.finaltask.validation.PaymentType;
import com.example.finaltask.validation.validationOrder.FirstOrder;
import com.example.finaltask.validation.validationOrder.SecondOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.GroupSequence;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@GroupSequence({FirstOrder.class, SecondOrder.class, PurchaseDto.class})
public class PurchaseDto {

    @NotBlank(message = "Supermarket id is required field")
    private String superMarketId;

    @NotEmpty(message = "Items id  are required field")
    private Set<@NotBlank(message = "Item id is required field") String> itemsId;

    @NotNull(message = "Payment type   are required field", groups = FirstOrder.class)
    @PaymentType(groups = SecondOrder.class)
    private String paymentType;

    private Double cashAmount;
}