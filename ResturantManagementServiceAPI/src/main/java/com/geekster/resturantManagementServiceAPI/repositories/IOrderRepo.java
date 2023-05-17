package com.geekster.resturantManagementServiceAPI.repositories;

import com.geekster.resturantManagementServiceAPI.models.Order;
import org.springframework.data.repository.ListCrudRepository;

public interface IOrderRepo extends ListCrudRepository<Order,Long> {

}
