package co.edu.javeriana.eas.patterns.users.services.impl;

import co.edu.javeriana.eas.patterns.persistence.entities.PersonEntity;
import co.edu.javeriana.eas.patterns.persistence.entities.ProfileEntity;
import co.edu.javeriana.eas.patterns.users.dtos.UserCreateDto;
import co.edu.javeriana.eas.patterns.users.enums.EProfile;
import co.edu.javeriana.eas.patterns.users.services.abstracts.UserServiceAbs;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl extends UserServiceAbs {

    public ClientServiceImpl() {
        this.milestone = "CLIENT_PROFILE";
        ProfileEntity profileEntity = new ProfileEntity();
        profileEntity.setId(EProfile.CLIENT.getProfile());
        this.profileEntity = profileEntity;
    }

    @Override
    protected PersonEntity createEntityPerson(UserCreateDto userCreateDto) {
        return personEntityBase(userCreateDto);
    }

}