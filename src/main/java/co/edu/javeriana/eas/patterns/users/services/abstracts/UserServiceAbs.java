package co.edu.javeriana.eas.patterns.users.services.abstracts;

import co.edu.javeriana.eas.patterns.common.enums.EExceptionCode;
import co.edu.javeriana.eas.patterns.persistence.entities.*;
import co.edu.javeriana.eas.patterns.persistence.repositories.IIdentificationTypeRepository;
import co.edu.javeriana.eas.patterns.persistence.repositories.IPersonRepository;
import co.edu.javeriana.eas.patterns.persistence.repositories.IUserRepository;
import co.edu.javeriana.eas.patterns.persistence.repositories.IUserStatusRepository;
import co.edu.javeriana.eas.patterns.users.dtos.PersonInfoDto;
import co.edu.javeriana.eas.patterns.users.dtos.UserCreateDto;
import co.edu.javeriana.eas.patterns.users.dtos.UserInfoDto;
import co.edu.javeriana.eas.patterns.users.dtos.UserUpdateDto;
import co.edu.javeriana.eas.patterns.users.enums.EProfile;
import co.edu.javeriana.eas.patterns.users.enums.EUserStatus;
import co.edu.javeriana.eas.patterns.users.exceptions.AuthenticationException;
import co.edu.javeriana.eas.patterns.users.exceptions.CreateUserException;
import co.edu.javeriana.eas.patterns.users.exceptions.UpdateUserException;
import co.edu.javeriana.eas.patterns.users.mappers.UserMapper;
import co.edu.javeriana.eas.patterns.users.services.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public abstract class UserServiceAbs implements IUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceAbs.class);

    private UserStatusEntity userActive;
    private UserStatusEntity userInactive;

    protected String milestone;
    protected ProfileEntity profileEntity;

    private IPersonRepository personRepository;
    private IIdentificationTypeRepository identificationTypeRepository;
    private IUserRepository userRepository;
    private IUserStatusRepository userStatusRepository;

    @Transactional
    @Override
    public void createUser(UserCreateDto userCreateDto) throws CreateUserException {
        LOGGER.info("INICIA CREACIÓN DE NUEVO USUARIO [{}] CON PERFIL [{}]", userCreateDto.getUserCode(), milestone);
        PersonEntity personEntity = createEntityPerson(userCreateDto);
        personRepository.save(personEntity);
        UserEntity userEntity = createUserEntity(userCreateDto, personEntity);
        userRepository.save(userEntity);
        LOGGER.info("FINALIZA CREACIÓN DE NUEVO USUARIO [{}] CON PERFIL [{}]", userCreateDto.getUserCode(), milestone);
    }

    @Transactional
    @Override
    public void updateUser(int userId, UserUpdateDto userUpdateDto) throws UpdateUserException {
        LOGGER.info("INICIA MODIFICACIÓN DE USUARIO CODIGO [{}]", userId);
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new UpdateUserException(EExceptionCode.BLOCKING, "Usuario no encontrado para modificar"));
        UserMapper.userUpdateMapperInUserEntity(userUpdateDto, userEntity);
        userRepository.save(userEntity);
        LOGGER.info("FINALIZA MODIFICACIÓN DE USUARIO CODIGO [{}]", userId);
    }

    @Transactional
    @Override
    public void updateStatusUser(int userId) throws UpdateUserException {
        LOGGER.info("INICIA MODIFICACIÓN DE ESTADO DEL USUARIO CODIGO [{}]", userId);
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new UpdateUserException(EExceptionCode.BLOCKING, "Usuario no encontrado para modificar"));
        userRepository.updateStatus(changeStatus(userEntity.getStatus()), userId);
        LOGGER.info("FINALIZA MODIFICACIÓN DE ESTADO DEL USUARIO CODIGO [{}]", userId);
    }

    @Override
    public UserInfoDto getInfoUser(int userId) throws AuthenticationException {
        LOGGER.info("INICIA CONSULTA DE USUARIO POR CODIGO CODIGO [{}]", userId);
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new AuthenticationException(EExceptionCode.BLOCKING, "Usuario no encontrado"));
        UserInfoDto userInfoDto = new UserInfoDto();
        userInfoDto.seteUserStatus(userEntity.getStatus().getId() == 0 ? EUserStatus.INACTIVE : EUserStatus.ACTIVE);
        EProfile eProfile;
        eProfile = EProfile.PROVIDER;
        if (userEntity.getProfile().getId() == 1) {
            eProfile = EProfile.ADMINISTRATOR;
        }
        if (userEntity.getProfile().getId() == 1) {
            eProfile = EProfile.CLIENT;
        }
        userInfoDto.setProfile(eProfile);
        userInfoDto.setUserCode(userEntity.getUserCode());
        userInfoDto.setUserId(userEntity.getId());
        PersonInfoDto personInfoDto = new PersonInfoDto();
        personInfoDto.setEmail(userEntity.getPerson().getEmail());
        personInfoDto.setFirstName(userEntity.getPerson().getFirstName());
        personInfoDto.setLastName(userEntity.getPerson().getLastName());
        personInfoDto.setPersonId(userEntity.getPerson().getId());
        personInfoDto.setPhoneNumber(userEntity.getPerson().getPhoneNumber());
        userInfoDto.setPerson(personInfoDto);
        LOGGER.info("FINALIZA CONSULTA DE USUARIO POR CODIGO CODIGO [{}] -> [{}]", personInfoDto);
        return userInfoDto;
    }

    protected abstract PersonEntity createEntityPerson(UserCreateDto userCreateDto) throws CreateUserException;

    protected PersonEntity personEntityBase(UserCreateDto userCreateDto) throws CreateUserException {
        IdentificationTypeEntity identificationTypeEntity = identificationTypeRepository.findById(userCreateDto.getIdentificationType())
                .orElseThrow(() -> new CreateUserException(EExceptionCode.BLOCKING, "No se encuentra el tipo de documento ingresado"));
        PersonEntity personEntity = UserMapper.userCreateMapperInPersonEntity(userCreateDto);
        personEntity.setIdentificationType(identificationTypeEntity);
        return personEntity;
    }

    private UserEntity createUserEntity(UserCreateDto userCreateDto, PersonEntity personEntity) {
        UserEntity userEntity = UserMapper.userCreateMapperInUserEntity(userCreateDto);
        userEntity.setProfile(profileEntity);
        userEntity.setPerson(personEntity);
        userEntity.setStatus(userActive);
        return userEntity;
    }

    private int changeStatus(UserStatusEntity userStatusEntity) {
        if (userStatusEntity.getId() == EUserStatus.INACTIVE.getStatus()) {
            return userActive.getId();
        }
        return userInactive.getId();
    }

    @Autowired
    public void setIdentificationTypeRepository(IIdentificationTypeRepository identificationTypeRepository) {
        this.identificationTypeRepository = identificationTypeRepository;
    }

    @Autowired
    public void setPersonRepository(IPersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Autowired
    public void setUserRepository(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setUserStatusRepository(IUserStatusRepository userStatusRepository) {
        this.userStatusRepository = userStatusRepository;
    }

    @Autowired
    public void setUserActive() {
        this.userActive = userStatusRepository.findById(EUserStatus.ACTIVE.getStatus()).orElse(new UserStatusEntity());
    }

    @Autowired
    public void setUserInactive() {
        this.userInactive = userStatusRepository.findById(EUserStatus.INACTIVE.getStatus()).orElse(new UserStatusEntity());
    }

}