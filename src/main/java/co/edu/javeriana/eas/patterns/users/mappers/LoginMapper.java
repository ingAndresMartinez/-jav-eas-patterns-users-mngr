package co.edu.javeriana.eas.patterns.users.mappers;

import co.edu.javeriana.eas.patterns.persistence.entities.UserEntity;
import co.edu.javeriana.eas.patterns.users.dtos.AuthenticationInfoDto;

public class LoginMapper {

    private LoginMapper() {

    }

    public static AuthenticationInfoDto userEntityMapperInAuthenticationInfo(UserEntity userEntity) {
        AuthenticationInfoDto authenticationInfoDto = new AuthenticationInfoDto();
        authenticationInfoDto.setUserId(userEntity.getId());
        authenticationInfoDto.setUserCode(userEntity.getUserCode());
        authenticationInfoDto.setFirstName(userEntity.getPerson().getFirstName());
        authenticationInfoDto.setLastName(userEntity.getPerson().getLastName());
        authenticationInfoDto.setProfile(userEntity.getProfile().getProfileDescription());
        return authenticationInfoDto;
    }

}