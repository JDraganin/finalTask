package com.example.finaltask.dto;
import com.example.finaltask.validation.Phone;
import com.example.finaltask.validation.validationOrder.FirstOrder;
import com.example.finaltask.validation.validationOrder.SecondOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import javax.validation.GroupSequence;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@GroupSequence({FirstOrder.class, SecondOrder.class,SupermarketDto.class})
public class SupermarketDto {
    @NotBlank(message = "Name is required field",groups = FirstOrder.class)
    @Length(max = 64,message = "name size must be between 1 and 64",groups = SecondOrder.class)
    private String name;

    @NotBlank(message = "address is required field",groups = FirstOrder.class)
    @Length(max = 64,message = "name size must be between 1 and 128",groups = SecondOrder.class)
    private String address;

    @NotBlank(message = "phone number is required field",groups = FirstOrder.class)
    @Phone(groups = SecondOrder.class)
    private String phoneNumber;

    @NotBlank(message = "work hours is required field,groups = FirstOrder.class")
    @Pattern(regexp = "([01]?[0-9]|2[0-3]):[0-5][0-9]-([01]?[0-9]|2[0-3]):[0-5][0-9]",message = "Invalid time format",groups = SecondOrder.class)
    private String workHours;
}