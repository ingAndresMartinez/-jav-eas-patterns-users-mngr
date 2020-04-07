package co.edu.javeriana.eas.patterns.users.services;

import co.edu.javeriana.eas.patterns.users.dtos.UserCreateDto;
import co.edu.javeriana.eas.patterns.users.exceptions.CreateUserException;

public interface IHandlerUserCreateService {

    void defineAndCreateUserFromInputProfile(UserCreateDto userCreateDto) throws CreateUserException;

}