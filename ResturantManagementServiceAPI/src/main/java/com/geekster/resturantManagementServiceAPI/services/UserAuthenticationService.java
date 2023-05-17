package com.geekster.resturantManagementServiceAPI.services;

import com.geekster.resturantManagementServiceAPI.models.UserAuthenticationToken;
import com.geekster.resturantManagementServiceAPI.repositories.IUserAuthenticationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAuthenticationService {

    @Autowired
    IUserAuthenticationRepo iUserAuthenticationRepo;

    public void saveToken(UserAuthenticationToken authToken) {
        iUserAuthenticationRepo.save(authToken);
    }

    public String deleteToken(String token) {
        UserAuthenticationToken authenticationToken = iUserAuthenticationRepo.findByToken(token);

        if(authenticationToken != null) {
            iUserAuthenticationRepo.deleteById(authenticationToken.getId());
            return "Signed out successfully";
        }
        return "token does not match with user details";
    }

    public boolean isAuthenticate(String token) {
        return iUserAuthenticationRepo.existsByToken(token);
    }

    public UserAuthenticationToken getAuthToken(String token) {
        return iUserAuthenticationRepo.findByToken(token);
    }
}
