package co.edu.javeriana.eas.patterns.users.controllers;

import co.edu.javeriana.eas.patterns.common.enums.EExceptionCode;
import co.edu.javeriana.eas.patterns.users.dtos.AuthenticationInfoDto;
import co.edu.javeriana.eas.patterns.users.dtos.LoginParamDto;
import co.edu.javeriana.eas.patterns.users.exceptions.AuthenticationException;
import co.edu.javeriana.eas.patterns.users.services.impl.AuthenticatorServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private AuthenticatorServiceImpl authenticatorService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationInfoDto> loginUser(@RequestBody LoginParamDto loginParamDto) {
        LOGGER.info("INICIA PROCESO DE LOGIN PARA EL USUARIO [{}]", loginParamDto.getUserCode());
        AuthenticationInfoDto response;
        try {
            response = authenticatorService.login(loginParamDto);
        } catch (AuthenticationException e) {
            LOGGER.error("ERROR EN AUTENTICACIÓN", e);
            return handleAuthenticationException(e);
        }
        LOGGER.info("FINALIZA PROCESO DE LOGIN PARA EL USUARIO [{}] CON RESULTADO [{}]", loginParamDto.getUserCode(), response);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    private ResponseEntity<AuthenticationInfoDto> handleAuthenticationException(AuthenticationException e) {
        ResponseEntity<AuthenticationInfoDto> errorResponse = null;
        if (e.getExceptionCode() == EExceptionCode.USER_OR_PASSWORD_INVALID) {
            errorResponse = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (e.getExceptionCode() == EExceptionCode.USER_BLOCKED) {
            errorResponse = new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return errorResponse;
    }

    @Autowired
    public void setAuthenticatorService(AuthenticatorServiceImpl authenticatorService) {
        this.authenticatorService = authenticatorService;
    }
}