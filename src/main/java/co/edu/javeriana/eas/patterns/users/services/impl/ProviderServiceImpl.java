package co.edu.javeriana.eas.patterns.users.services.impl;

import co.edu.javeriana.eas.patterns.common.enums.EExceptionCode;
import co.edu.javeriana.eas.patterns.persistence.entities.PersonEntity;
import co.edu.javeriana.eas.patterns.persistence.entities.ProfileEntity;
import co.edu.javeriana.eas.patterns.persistence.entities.ProviderEntity;
import co.edu.javeriana.eas.patterns.persistence.repositories.IProviderRepository;
import co.edu.javeriana.eas.patterns.users.dtos.UserCreateDto;
import co.edu.javeriana.eas.patterns.users.enums.EProfile;
import co.edu.javeriana.eas.patterns.users.exceptions.CreateUserException;
import co.edu.javeriana.eas.patterns.users.services.abstracts.UserServiceAbs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProviderServiceImpl extends UserServiceAbs {

    private IProviderRepository providerRepository;

    public ProviderServiceImpl() {
        this.milestone = "PROVIDER_PROFILE";
        ProfileEntity profileEntity = new ProfileEntity();
        profileEntity.setId(EProfile.PROVIDER.getProfile());
        this.profileEntity = profileEntity;
    }

    @Override
    protected PersonEntity createEntityPerson(UserCreateDto userCreateDto) throws CreateUserException {
        PersonEntity personEntity = personEntityBase(userCreateDto);
        int providerId = userCreateDto.getProviderId();
        ProviderEntity providerEntity = providerRepository.findById(providerId).
                orElseThrow(() -> new CreateUserException(EExceptionCode.BLOCKING, "No existe el proveedor ingresado"));
        personEntity.setProviderEntity(providerEntity);
        return personEntity;
    }

    @Autowired
    public void setProviderRepository(IProviderRepository providerRepository) {
        this.providerRepository = providerRepository;
    }
}
