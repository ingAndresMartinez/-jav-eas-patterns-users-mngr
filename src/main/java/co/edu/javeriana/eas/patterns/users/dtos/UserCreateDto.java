package co.edu.javeriana.eas.patterns.users.dtos;

import co.edu.javeriana.eas.patterns.users.enums.EProfile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UserCreateDto {

    private int identificationType;
    private int providerId;

    @NotNull(message = "Please provide a firstName")
    @NotEmpty(message = "Please provide a firstName")
    private String firstName;
    @NotNull(message = "Please provide a lastName")
    @NotEmpty(message = "Please provide a lastName")
    private String lastName;
    @NotNull(message = "Please provide a identificationNumber")
    @NotEmpty(message = "Please provide a identificationNumber")
    private String identificationNumber;
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
    
    private EProfile profile;

    public int getIdentificationType() {
        return identificationType;
    }

    public void setIdentificationType(int identificationType) {
        this.identificationType = identificationType;
    }

    public int getProviderId() {
        return providerId;
    }

    public void setProviderId(int providerId) {
        this.providerId = providerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

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

    public EProfile getProfile() {
        return profile;
    }

    public void setProfile(EProfile profile) {
        this.profile = profile;
    }

}