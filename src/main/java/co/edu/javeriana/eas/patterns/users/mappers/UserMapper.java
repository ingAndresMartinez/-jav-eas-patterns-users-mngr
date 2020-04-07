package co.edu.javeriana.eas.patterns.users.mappers;

import co.edu.javeriana.eas.patterns.persistence.entities.PersonEntity;
import co.edu.javeriana.eas.patterns.persistence.entities.UserEntity;
import co.edu.javeriana.eas.patterns.users.dtos.UserCreateDto;
import co.edu.javeriana.eas.patterns.users.utilities.Base64Utility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserMapper {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserMapper.class);

    private UserMapper() {
    }

    public static PersonEntity userCreateMapperInPersonEntity(UserCreateDto userCreateDto) {
        LOGGER.info("inicia mapeo para creación de entidad persona. [{}]", userCreateDto.getUserCode());
        PersonEntity personEntity = new PersonEntity();
        personEntity.setFirstName(userCreateDto.getFirstName());
        personEntity.setLastName(userCreateDto.getLastName());
        personEntity.setIdentificationType(userCreateDto.getIdentificationType());
        personEntity.setIdentificationNumber(userCreateDto.getIdentificationNumber());
        personEntity.setEmail(userCreateDto.getEmail());
        personEntity.setPhoneNumber(userCreateDto.getPhoneNumber());
        LOGGER.info("finaliza mapeo para creación de entidad persona. [{}]", userCreateDto.getUserCode());
        return personEntity;
    }

    public static UserEntity userCreateMapperInUserEntity(UserCreateDto userCreateDto) {
        LOGGER.info("inicia mapeo para creación de entidad usuario. [{}]", userCreateDto.getUserCode());
        UserEntity userEntity = new UserEntity();
        userEntity.setUserCode(userCreateDto.getUserCode());
        userEntity.setPassword(Base64Utility.decode(userCreateDto.getPassword()));
        LOGGER.info("finaliza mapeo para creación de entidad usuario. [{}]", userCreateDto.getUserCode());
        return userEntity;
    }

}