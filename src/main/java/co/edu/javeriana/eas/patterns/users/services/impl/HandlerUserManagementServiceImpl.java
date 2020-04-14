package co.edu.javeriana.eas.patterns.users.services.impl;

import co.edu.javeriana.eas.patterns.users.dtos.UserCreateDto;
import co.edu.javeriana.eas.patterns.users.dtos.UserInfoDto;
import co.edu.javeriana.eas.patterns.users.dtos.UserUpdateDto;
import co.edu.javeriana.eas.patterns.users.enums.EProfile;
import co.edu.javeriana.eas.patterns.users.exceptions.AuthenticationException;
import co.edu.javeriana.eas.patterns.users.exceptions.CreateUserException;
import co.edu.javeriana.eas.patterns.users.exceptions.UpdateUserException;
import co.edu.javeriana.eas.patterns.users.services.IHandlerUserManagementService;
import co.edu.javeriana.eas.patterns.users.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HandlerUserManagementServiceImpl implements IHandlerUserManagementService {

    private IUserService administratorServiceImpl;
    private IUserService clientServiceImpl;
    private IUserService providerServiceImpl;

    @Override
    public void defineAndCreateUserFromInputProfile(UserCreateDto userCreateDto) throws CreateUserException {
        EProfile profile = userCreateDto.getProfile();
        switch (profile) {
            case ADMINISTRATOR:
                administratorServiceImpl.createUser(userCreateDto);
                break;
            case CLIENT:
                clientServiceImpl.createUser(userCreateDto);
                break;
            default:
                providerServiceImpl.createUser(userCreateDto);
        }
    }

    @Override
    public void updateUser(int userId, UserUpdateDto userUpdateDto) throws UpdateUserException {
        clientServiceImpl.updateUser(userId, userUpdateDto);
    }

    @Override
    public void updateStatusUser(int userId) throws UpdateUserException {
        clientServiceImpl.updateStatusUser(userId);
    }

    @Override
    public UserInfoDto getInfoUser(int userId) throws AuthenticationException {
        return clientServiceImpl.getInfoUser(userId);
    }

    @Autowired
    public void setAdministratorServiceImpl(IUserService administratorServiceImpl) {
        this.administratorServiceImpl = administratorServiceImpl;
    }

    @Autowired
    public void setClientServiceImpl(IUserService clientServiceImpl) {
        this.clientServiceImpl = clientServiceImpl;
    }

    @Autowired
    public void setProviderServiceImpl(IUserService providerServiceImpl) {
        this.providerServiceImpl = providerServiceImpl;
    }
}