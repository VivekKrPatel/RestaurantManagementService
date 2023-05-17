package com.geekster.resturantManagementServiceAPI.controllers;

import com.geekster.resturantManagementServiceAPI.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/{foodId}/{token}/{count}")
    public String createOrder(@PathVariable Long foodId,@PathVariable String token,@PathVariable Integer count) {
        return orderService.createOrder(foodId,token,count);
    }

}
