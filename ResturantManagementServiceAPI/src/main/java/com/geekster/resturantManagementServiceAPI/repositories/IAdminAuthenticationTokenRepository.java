package com.geekster.resturantManagementServiceAPI.repositories;

import com.geekster.resturantManagementServiceAPI.models.AdminAuthenticationToken;
import org.springframework.data.repository.ListCrudRepository;

public interface IAdminAuthenticationTokenRepository extends ListCrudRepository<AdminAuthenticationToken, Long> {
    AdminAuthenticationToken findByToken(String token);

    boolean existsByToken(String token);
}
