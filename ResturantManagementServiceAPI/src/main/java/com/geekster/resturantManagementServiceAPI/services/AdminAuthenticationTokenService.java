package com.geekster.resturantManagementServiceAPI.services;

import com.geekster.resturantManagementServiceAPI.models.Admin;
import com.geekster.resturantManagementServiceAPI.models.AdminAuthenticationToken;
import com.geekster.resturantManagementServiceAPI.repositories.IAdminAuthenticationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminAuthenticationTokenService {

    @Autowired
    IAdminAuthenticationTokenRepository authTokenRepo;

    public void saveToken(AdminAuthenticationToken authToken) {
        authTokenRepo.save(authToken);
    }

    public String deleteToken(String token) {
        AdminAuthenticationToken authToken = authTokenRepo.findByToken(token);

        if(authToken != null) {
            authTokenRepo.deleteById(authToken.getId());
            return "signed out successfully";
        }
        return "token does not match with admin details";
    }

    public boolean isAuthenticate(String token) {
        return authTokenRepo.existsByToken(token);
    }

    public AdminAuthenticationToken getAdminAuthToken(String token) {
        return authTokenRepo.findByToken(token);
    }
}
