package com.geekster.resturantManagementServiceAPI.controllers;

import com.geekster.resturantManagementServiceAPI.dtos.FoodDto;
import com.geekster.resturantManagementServiceAPI.services.AdminService;
import com.geekster.resturantManagementServiceAPI.services.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("food")
public class FoodController {

    @Autowired
    FoodService foodService;

    @Autowired
    AdminService adminService;

    @PostMapping("/{token}")
    public String addFood(@RequestBody FoodDto foodDto, @PathVariable String token) {
        return adminService.addFood(foodDto,token);
    }

    @DeleteMapping("byId/{id}/{token}")
    public void deleteByFoodId(@PathVariable Long id, @PathVariable String token) {
        adminService.deleteByFoodId(id,token);
    }


}
