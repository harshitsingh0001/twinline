package com.example.twinlineTask.dto;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class SignUpAndLoginDto {

    @Pattern(regexp = "^[789]\\d{9}$", message = "Invalid mobile number. It must start with 7, 8, or 9 and be 10 digits long.")
    @NotBlank(message = "can not be blank")
    private String mobileNumber;

    @NotBlank(message = "cannot be blank")
    private String password;


    public SignUpAndLoginDto(String mobileNumber, String password) {
        this.mobileNumber = mobileNumber;
        this.password = password;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
