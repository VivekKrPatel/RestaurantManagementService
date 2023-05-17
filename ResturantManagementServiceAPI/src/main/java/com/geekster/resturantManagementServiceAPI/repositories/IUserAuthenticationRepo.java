package com.geekster.resturantManagementServiceAPI.repositories;

import com.geekster.resturantManagementServiceAPI.models.User;
import com.geekster.resturantManagementServiceAPI.models.UserAuthenticationToken;
import org.springframework.data.repository.ListCrudRepository;

public interface IUserAuthenticationRepo extends ListCrudRepository<UserAuthenticationToken,Long> {
    UserAuthenticationToken findByToken(String token);

    boolean existsByToken(String token);
}
