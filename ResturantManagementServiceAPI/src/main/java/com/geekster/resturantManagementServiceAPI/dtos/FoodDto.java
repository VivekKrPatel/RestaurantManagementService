package com.geekster.resturantManagementServiceAPI.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodDto {

    @NotBlank
    private String title;
    private String description;
    @Min(10)
    private Double price;
    private String dummyImage;
}
