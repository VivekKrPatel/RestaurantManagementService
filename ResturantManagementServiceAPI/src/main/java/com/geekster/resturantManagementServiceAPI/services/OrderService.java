package com.geekster.resturantManagementServiceAPI.services;

import com.geekster.resturantManagementServiceAPI.models.Admin;
import com.geekster.resturantManagementServiceAPI.models.Food;
import com.geekster.resturantManagementServiceAPI.models.Order;
import com.geekster.resturantManagementServiceAPI.models.User;
import com.geekster.resturantManagementServiceAPI.repositories.IOrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    IOrderRepo iOrderRepo;

    @Autowired
    UserService userService;

    @Autowired
    AdminService adminService;

    @Autowired
    FoodService foodService;

    public String createOrder(Long foodId, String token, Integer count) {
        Admin admin = adminService.getAdminByToken(token);
        User user = userService.getUserByToken(token);

        boolean isPresentFood = foodService.existsById(foodId);

        if(!isPresentFood){
            return "food does not exists";
        }

        if(admin != null) {
            Order order = new Order(count,new Food(foodId),admin);
            iOrderRepo.save(order);
        }else if(user != null){
            Order order = new Order(count,new Food(foodId),user);
            iOrderRepo.save(order);
        }else{
            return "Invalid token";
        }
        return "Order placed successfully";
    }
}
