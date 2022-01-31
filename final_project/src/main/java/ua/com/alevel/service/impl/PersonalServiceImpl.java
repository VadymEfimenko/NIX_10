package ua.com.alevel.service.impl;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.exception.EntityExistException;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.user.Personal;
import ua.com.alevel.persistence.repository.user.PersonalRepository;
import ua.com.alevel.service.PersonalService;

import java.util.Optional;

@Service
public class PersonalServiceImpl implements PersonalService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final PersonalRepository personalRepository;
    private final CrudRepositoryHelper<Personal, PersonalRepository> crudRepositoryHelper;

    public PersonalServiceImpl(
            BCryptPasswordEncoder bCryptPasswordEncoder,
            PersonalRepository personalRepository, CrudRepositoryHelper<Personal, PersonalRepository> crudRepositoryHelper) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.personalRepository = personalRepository;
        this.crudRepositoryHelper = crudRepositoryHelper;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
    public void create(Personal personal) {
        if (personalRepository.existsByEmail(personal.getEmail())) {
            throw new EntityExistException("this personal is exist");
        }
        personal.setPassword(bCryptPasswordEncoder.encode(personal.getPassword()));
        crudRepositoryHelper.create(personalRepository, personal);
    }

    @Override
    public void update(Personal entity) {
        crudRepositoryHelper.update(personalRepository,entity);
    }

    @Override
    public void delete(Long id) {
        crudRepositoryHelper.delete(personalRepository,id);
    }

    @Override
    public Optional<Personal> findById(Long id) {
        return crudRepositoryHelper.findById(personalRepository,id);
    }

    @Override
    public DataTableResponse<Personal> findAll(DataTableRequest request) {
        return crudRepositoryHelper.findAll(personalRepository,request);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
