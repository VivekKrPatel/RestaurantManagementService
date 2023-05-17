package com.geekster.resturantManagementServiceAPI.services;

import com.geekster.resturantManagementServiceAPI.dtos.FoodDto;
import com.geekster.resturantManagementServiceAPI.dtos.SignInInput;
import com.geekster.resturantManagementServiceAPI.dtos.SignInOutput;
import com.geekster.resturantManagementServiceAPI.dtos.SignUpInput;
import com.geekster.resturantManagementServiceAPI.models.Admin;
import com.geekster.resturantManagementServiceAPI.models.AdminAuthenticationToken;
import com.geekster.resturantManagementServiceAPI.models.Food;
import com.geekster.resturantManagementServiceAPI.repositories.IAdminRepository;
import jakarta.xml.bind.DatatypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class AdminService {

    @Autowired
    private IAdminRepository adminRepository;

    @Autowired
    AdminAuthenticationTokenService tokenService;

    @Autowired
    FoodService foodService;

    public String signUp(SignUpInput signUpInput) {
        boolean checkEmail = adminRepository.existsByEmail(signUpInput.getEmail());
        if(checkEmail) {
            return "Admin is present already..sign in instead";
        }

        String encryptedPassword = null;
        try {
            encryptedPassword = encryptPassword(signUpInput.getPassword());
        }catch (Exception e){
            e.printStackTrace();
        }

        Admin admin = new Admin(signUpInput.getName(),signUpInput.getEmail(),encryptedPassword,signUpInput.getAddress());
        adminRepository.save(admin);
        return "admin signed up successfully..!!";
    }

    private String encryptPassword(String password) throws NoSuchAlgorithmException {

        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(password.getBytes());
        byte[] digest = md5.digest();
        return DatatypeConverter.printHexBinary(digest).toString();
    }

    public SignInOutput signIn(SignInInput signInInput) {
        Admin admin = adminRepository.findByEmail(signInInput.getEmail());

        if(admin == null) {
            return new SignInOutput("Admin is not signed up",null);
        }

        String encryptedPassword = null;
        try {
            encryptedPassword = encryptPassword(signInInput.getPassword());
        }catch (Exception e){
            e.printStackTrace();
        }

        if(!encryptedPassword.equals(admin.getPassword())) {
            return new SignInOutput("Invalid admin details",null);
        }

        AdminAuthenticationToken authToken = new AdminAuthenticationToken(admin);
        tokenService.saveToken(authToken);

        return new SignInOutput("Admin Signed in successfully",authToken.getToken());
    }

    public String signOut(String token) {
        return tokenService.deleteToken(token);
    }

    public String addFood(FoodDto foodDto, String token) {
        if(!tokenService.isAuthenticate(token)) {
            return "cannot add food because admin is not verified";
        }

        boolean saved = foodService.addFood(foodDto);

        if(saved) {
            return "saved succesfully";
        }
        return "Did not saved food";
    }

    public void deleteByFoodId(Long id, String token) {

        if(tokenService.isAuthenticate(token)) {
            foodService.removeById(id);
        }
    }

    public Admin getAdminByToken(String token) {
        AdminAuthenticationToken authToken = tokenService.getAdminAuthToken(token);
        if(authToken != null) {
            return authToken.getAdmin();
        }
        return null;
    }
}
