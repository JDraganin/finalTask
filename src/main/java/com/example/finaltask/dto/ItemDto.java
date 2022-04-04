package com.example.finaltask.dto;

import com.example.finaltask.validation.ItemType;
import com.example.finaltask.validation.validationOrder.FirstOrder;
import com.example.finaltask.validation.validationOrder.SecondOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import javax.validation.GroupSequence;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@GroupSequence({FirstOrder.class, SecondOrder.class, ItemDto.class})
public class ItemDto {

    @NotBlank(message = "Name is required field", groups = FirstOrder.class)
    @Length(max = 64, message = "name size must be between 1 and 64", groups = SecondOrder.class)
    private String name;

    @NotNull(message = "price is required field", groups = FirstOrder.class)
    @DecimalMin(value = "0.01", message = "min price is 0.01", groups = SecondOrder.class)
    @DecimalMax(value = "9999.99", message = "max price is 9999.99", groups = SecondOrder.class)
    private Double price;

    @NotNull(message = "type is required field", groups = FirstOrder.class)
    @ItemType(groups = SecondOrder.class)
    private String type;
}