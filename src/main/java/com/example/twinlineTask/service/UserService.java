package com.example.twinlineTask.service;


import com.example.twinlineTask.dto.ResponseDto;
import com.example.twinlineTask.entities.Users;
import com.example.twinlineTask.repos.UserRepository;
import com.example.twinlineTask.utilities.messageutility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public ResponseDto signup(String mobileNumber, String password) {
        Optional<Users> existingUser = userRepository.findByMobileNumber(mobileNumber);

        if (existingUser.isPresent()) {
            return Utility.createSuccessResponse("User already exists please login!",null);
        }
        Users user = new Users();
        user.setMobileNumber(mobileNumber);
        user.setPassword(password);
        user=userRepository.save(user);
        return Utility.createSuccessResponse("User signed up successfully!",user);

    }

    public ResponseDto login(String mobileNumber, String password) {
        Optional<Users> userOptional = userRepository.findByMobileNumber(mobileNumber);
        if (userOptional.isEmpty()) {
            return Utility.createFailureResponse("User not found, please sign up.",null);

        }
        Users user = userOptional.get();
         if (!user.getPassword().equals(password)) {
             return Utility.createFailureResponse("Invalid password.",null);

         } else {
             return Utility.createSuccessResponse("login Successful",user);
         }
    }
}
