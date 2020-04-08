package co.edu.javeriana.eas.patterns.users.services;

import co.edu.javeriana.eas.patterns.users.dtos.UserCreateDto;
import co.edu.javeriana.eas.patterns.users.dtos.UserUpdateDto;
import co.edu.javeriana.eas.patterns.users.exceptions.CreateUserException;
import co.edu.javeriana.eas.patterns.users.exceptions.UpdateUserException;

public interface IHandlerUserManagementService {

    void defineAndCreateUserFromInputProfile(UserCreateDto userCreateDto) throws CreateUserException;

    void updateUser(int userId, UserUpdateDto userUpdateDto) throws UpdateUserException;

    void updateStatusUser(int userId) throws UpdateUserException;

}