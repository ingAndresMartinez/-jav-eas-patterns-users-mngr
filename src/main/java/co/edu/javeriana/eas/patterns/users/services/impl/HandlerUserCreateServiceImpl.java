package co.edu.javeriana.eas.patterns.users.services.impl;

import co.edu.javeriana.eas.patterns.users.dtos.UserCreateDto;
import co.edu.javeriana.eas.patterns.users.enums.EProfile;
import co.edu.javeriana.eas.patterns.users.exceptions.CreateUserException;
import co.edu.javeriana.eas.patterns.users.services.IHandlerUserCreateService;
import co.edu.javeriana.eas.patterns.users.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HandlerUserCreateServiceImpl implements IHandlerUserCreateService {

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