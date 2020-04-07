package co.edu.javeriana.eas.patterns.users.services;

import co.edu.javeriana.eas.patterns.users.dtos.AuthenticationInfoDto;
import co.edu.javeriana.eas.patterns.users.dtos.LoginParamDto;
import co.edu.javeriana.eas.patterns.users.exceptions.AuthenticationException;

public interface IAuthenticationService {

    AuthenticationInfoDto login(LoginParamDto loginParamDto) throws AuthenticationException;

}