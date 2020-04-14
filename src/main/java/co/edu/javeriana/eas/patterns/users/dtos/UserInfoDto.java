package co.edu.javeriana.eas.patterns.users.dtos;

import co.edu.javeriana.eas.patterns.users.enums.EProfile;
import co.edu.javeriana.eas.patterns.users.enums.EUserStatus;

public class UserInfoDto {

    private int userId;
    private PersonInfoDto person;
    private EProfile profile;
    private String userCode;
    private EUserStatus eUserStatus;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public PersonInfoDto getPerson() {
        return person;
    }

    public void setPerson(PersonInfoDto person) {
        this.person = person;
    }

    public EProfile getProfile() {
        return profile;
    }

    public void setProfile(EProfile profile) {
        this.profile = profile;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public EUserStatus geteUserStatus() {
        return eUserStatus;
    }

    public void seteUserStatus(EUserStatus eUserStatus) {
        this.eUserStatus = eUserStatus;
    }

    @Override
    public String toString() {
        return "UserInfoDto{" +
                "userId=" + userId +
                ", person=" + person +
                ", profile='" + profile + '\'' +
                ", userCode='" + userCode + '\'' +
                ", eUserStatus=" + eUserStatus +
                '}';
    }

}