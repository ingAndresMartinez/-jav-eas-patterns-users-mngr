package co.edu.javeriana.eas.patterns.users.services.impl;

import co.edu.javeriana.eas.patterns.persistence.entities.PersonEntity;
import co.edu.javeriana.eas.patterns.persistence.entities.ProfileEntity;
import co.edu.javeriana.eas.patterns.users.dtos.UserCreateDto;
import co.edu.javeriana.eas.patterns.users.enums.EProfile;
import co.edu.javeriana.eas.patterns.users.services.abstracts.UserServiceAbs;
import org.springframework.stereotype.Service;

@Service
public class AdministratorServiceImpl extends UserServiceAbs {

    public AdministratorServiceImpl() {
        this.milestone = "ADMINISTRATOR_PROFILE";
        ProfileEntity profileEntity = new ProfileEntity();
        profileEntity.setId(EProfile.ADMINISTRATOR.getProfile());
        this.profileEntity = profileEntity;
    }

    @Override
    protected PersonEntity createEntityPerson(UserCreateDto userCreateDto) {
        return personEntityBase(userCreateDto);
    }

}