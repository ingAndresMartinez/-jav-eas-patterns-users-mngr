package co.edu.javeriana.eas.patterns.users.dtos;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class LoginParamDto {

    @NotNull(message = "Please provide a userCode")
    @NotEmpty(message = "Please provide a userCode")
    private String userCode;
    @NotNull(message = "Please provide a password")
    @NotEmpty(message = "Please provide a password")
    private String password;

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