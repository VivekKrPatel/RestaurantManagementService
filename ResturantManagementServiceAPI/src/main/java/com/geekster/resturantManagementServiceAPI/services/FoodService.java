package com.geekster.resturantManagementServiceAPI.services;

import com.geekster.resturantManagementServiceAPI.dtos.FoodDto;
import com.geekster.resturantManagementServiceAPI.repositories.IFoodRepository;
import com.geekster.resturantManagementServiceAPI.models.Food;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodService {

    @Autowired
    private IFoodRepository foodRepository;

    public List<Food> getFoods() {
        return foodRepository.findAll();
    }

    public boolean addFood(FoodDto foodDto) {
        Food food = new Food(foodDto.getTitle(),foodDto.getDescription(),foodDto.getPrice(),foodDto.getDummyImage());

        Food isAdded = foodRepository.save(food);
        if(isAdded == null){
            return false;
        }
        return true;
    }

    public void removeById(Long id) {
        foodRepository.deleteById(id);
    }

    public boolean existsById(Long foodId) {
        return foodRepository.existsById(foodId);
    }
}
