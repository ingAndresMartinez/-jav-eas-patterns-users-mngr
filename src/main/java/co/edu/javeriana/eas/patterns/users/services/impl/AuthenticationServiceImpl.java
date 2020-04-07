package co.edu.javeriana.eas.patterns.users.services.impl;

import co.edu.javeriana.eas.patterns.common.enums.EExceptionCode;
import co.edu.javeriana.eas.patterns.persistence.entities.UserEntity;
import co.edu.javeriana.eas.patterns.persistence.entities.UserStatusEntity;
import co.edu.javeriana.eas.patterns.persistence.repositories.IUserRepository;
import co.edu.javeriana.eas.patterns.users.dtos.AuthenticationInfoDto;
import co.edu.javeriana.eas.patterns.users.dtos.LoginParamDto;
import co.edu.javeriana.eas.patterns.users.enums.EUserStatus;
import co.edu.javeriana.eas.patterns.users.exceptions.AuthenticationException;
import co.edu.javeriana.eas.patterns.users.mappers.LoginMapper;
import co.edu.javeriana.eas.patterns.users.services.IAuthenticationService;
import co.edu.javeriana.eas.patterns.users.utilities.Base64Utility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements IAuthenticationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

    private IUserRepository userRepository;

    @Override
    public AuthenticationInfoDto login(LoginParamDto loginParamDto) throws AuthenticationException {
        String userCode = loginParamDto.getUserCode();
        LOGGER.info("inicia consulta de usuario en bases [{}]", userCode);
        String password = Base64Utility.decode(loginParamDto.getPassword());
        UserEntity userEntity = userRepository.findByUserCodeAndPassword(userCode, password).orElseThrow(() -> new AuthenticationException(EExceptionCode.USER_OR_PASSWORD_INVALID, "usuario o clave invalida."));
        validateStatus(userEntity.getStatus());
        AuthenticationInfoDto authenticationInfoDto = LoginMapper.userEntityMapperInAuthenticationInfo(userEntity);
        LOGGER.info("finaliza consulta de usuario en bases [{}]", userCode);
        return authenticationInfoDto;
    }

    private void validateStatus(UserStatusEntity userStatusEntity) throws AuthenticationException {
        if (userStatusEntity.getId() == EUserStatus.INACTIVE.getStatus()) {
            throw new AuthenticationException(EExceptionCode.USER_BLOCKED, "usuario inactivo.");
        }
    }

    @Autowired
    public void setUserRepository(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
