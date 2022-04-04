package com.example.finaltask.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor

public class AddItemsDto {
    @NotBlank(message = "Supermarket id is required field")
    private String supermarketID;
    @NotEmpty(message = "items id is required field")
    private List<String> itemsId;

}