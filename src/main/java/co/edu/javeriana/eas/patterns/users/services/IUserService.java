package co.edu.javeriana.eas.patterns.users.services;

import co.edu.javeriana.eas.patterns.users.dtos.UserCreateDto;
import co.edu.javeriana.eas.patterns.users.exceptions.CreateUserException;

public interface IUserService {

    void createUser(UserCreateDto userCreateDto) throws CreateUserException;

    void updateUser();

}