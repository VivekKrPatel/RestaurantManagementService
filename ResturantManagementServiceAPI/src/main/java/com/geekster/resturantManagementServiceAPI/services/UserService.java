package com.geekster.resturantManagementServiceAPI.services;

import com.geekster.resturantManagementServiceAPI.dtos.SignInInput;
import com.geekster.resturantManagementServiceAPI.dtos.SignInOutput;
import com.geekster.resturantManagementServiceAPI.dtos.UserSignUpInput;
import com.geekster.resturantManagementServiceAPI.models.User;
import com.geekster.resturantManagementServiceAPI.models.UserAuthenticationToken;
import com.geekster.resturantManagementServiceAPI.repositories.IUserRepo;
import jakarta.xml.bind.DatatypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class UserService {

    @Autowired
    IUserRepo iUserRepo;

    @Autowired
    UserAuthenticationService authenticationService;

    public String signUp(UserSignUpInput inputUser) {
        boolean checkEmail = iUserRepo.existsByEmail(inputUser.getEmail());
        if(checkEmail) {
            return "User is present already..sign in instead";
        }

        String encryptedPassword = null;
        try {
            encryptedPassword = encryptPassword(inputUser.getPassword());
        }catch (Exception e){
            e.printStackTrace();
        }

        User user = new User(inputUser.getName(),inputUser.getEmail(),inputUser.getPassword(),inputUser.getAddress());
        iUserRepo.save(user);
        return "User signed up successfully..!!";
    }

    private String encryptPassword(String password) throws NoSuchAlgorithmException {

        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(password.getBytes());
        byte[] digest = md5.digest();
        return DatatypeConverter.printHexBinary(digest).toString();
    }

    public SignInOutput signIn(SignInInput signInInput) {
        User user = iUserRepo.findByEmail(signInInput.getEmail());

        if(user == null) {
            return new SignInOutput("user is not signed up",null);
        }

        String encryptedPassword = null;
        try {
            encryptedPassword = encryptPassword(signInInput.getPassword());
        }catch (Exception e){
            e.printStackTrace();
        }

        if(!encryptedPassword.equals(user.getPassword())) {
            return new SignInOutput("Invalid user details",null);
        }

        UserAuthenticationToken authToken = new UserAuthenticationToken(user);
//        Save user's token in table
        authenticationService.saveToken(authToken);

        return new SignInOutput("User Signed in successfully",authToken.getToken());
    }

    public String signOut(String token) {
        return authenticationService.deleteToken(token);
    }

    public User getUserByToken(String token) {
        UserAuthenticationToken authenticationToken = authenticationService.getAuthToken(token);
        if(authenticationToken != null) {
            return authenticationToken.getUser();
        }
        return null;
    }
}
