package co.edu.javeriana.eas.patterns.users.dtos;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UserUpdateDto {

    @NotNull(message = "Please provide a email")
    @NotEmpty(message = "Please provide a email")
    private String email;
    @NotNull(message = "Please provide a phoneNumber")
    @NotEmpty(message = "Please provide a phoneNumber")
    private String phoneNumber;
    @NotNull(message = "Please provide a userCode")
    @NotEmpty(message = "Please provide a userCode")
    private String userCode;
    @NotNull(message = "Please provide a password")
    @NotEmpty(message = "Please provide a password")
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}