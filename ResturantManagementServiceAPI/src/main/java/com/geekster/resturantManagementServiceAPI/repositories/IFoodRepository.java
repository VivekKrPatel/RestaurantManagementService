package com.geekster.resturantManagementServiceAPI.repositories;

import com.geekster.resturantManagementServiceAPI.models.Food;
import org.springframework.data.repository.ListCrudRepository;

public interface IFoodRepository extends ListCrudRepository<Food, Long> {
}
