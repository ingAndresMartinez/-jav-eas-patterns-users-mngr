package co.edu.javeriana.eas.patterns.users.dtos;

import co.edu.javeriana.eas.patterns.users.enums.EProfile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UserCreateDto extends UserUpdateDto {

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

    public EProfile getProfile() {
        return profile;
    }

    public void setProfile(EProfile profile) {
        this.profile = profile;
    }

}