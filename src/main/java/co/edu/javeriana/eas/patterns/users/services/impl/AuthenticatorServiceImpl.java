package co.edu.javeriana.eas.patterns.users.services.impl;

import co.edu.javeriana.eas.patterns.common.enums.EExceptionCode;
import co.edu.javeriana.eas.patterns.persistence.entities.UserEntity;
import co.edu.javeriana.eas.patterns.persistence.entities.UserStatusEntity;
import co.edu.javeriana.eas.patterns.persistence.repositories.IUserRepository;
import co.edu.javeriana.eas.patterns.users.dtos.AuthenticationInfoDto;
import co.edu.javeriana.eas.patterns.users.dtos.LoginParamDto;
import co.edu.javeriana.eas.patterns.users.exceptions.AuthenticationException;
import co.edu.javeriana.eas.patterns.users.mappers.LoginMapper;
import co.edu.javeriana.eas.patterns.users.services.IAuthenticationService;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticatorServiceImpl implements IAuthenticationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticatorServiceImpl.class);

    private static final int INACTIVE_STATUS = 0;

    private IUserRepository userRepository;

    @Override
    public AuthenticationInfoDto login(LoginParamDto loginParamDto) throws AuthenticationException {
        String userCode = loginParamDto.getUserCode();
        LOGGER.info("inicia consulta de usuario en bases [{}]", userCode);
        Base64 base64 = new Base64();
        String password = new String(base64.decode(loginParamDto.getPassword().getBytes()));
        UserEntity userEntity = userRepository.findByUserCodeAndPassword(userCode, password).orElseThrow(() -> new AuthenticationException(EExceptionCode.USER_OR_PASSWORD_INVALID, "usuario o clave invalida."));
        validateStatus(userEntity.getStatus());
        AuthenticationInfoDto authenticationInfoDto = LoginMapper.userEntityMapperInAuthenticationInfo(userEntity);
        LOGGER.info("finaliza consulta de usuario en bases [{}]", userCode);
        return authenticationInfoDto;
    }

    private void validateStatus(UserStatusEntity userStatusEntity) throws AuthenticationException {
        if (userStatusEntity.getId() == INACTIVE_STATUS) {
            throw new AuthenticationException(EExceptionCode.USER_BLOCKED, "usuario inactivo.");
        }
    }

    @Autowired
    public void setUserRepository(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
