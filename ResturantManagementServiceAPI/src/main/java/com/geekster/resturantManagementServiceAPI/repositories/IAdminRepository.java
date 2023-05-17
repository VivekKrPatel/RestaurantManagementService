package com.geekster.resturantManagementServiceAPI.repositories;

import com.geekster.resturantManagementServiceAPI.models.Admin;
import org.springframework.data.repository.ListCrudRepository;

public interface IAdminRepository extends ListCrudRepository<Admin, Long> {
    boolean existsByEmail(String email);

    Admin findByEmail(String email);

}
