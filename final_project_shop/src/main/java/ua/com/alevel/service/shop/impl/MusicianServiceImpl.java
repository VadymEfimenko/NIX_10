package ua.com.alevel.service.shop.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.shop.Musician;
import ua.com.alevel.persistence.entity.shop.Release;
import ua.com.alevel.persistence.repository.MusicianRepository;
import ua.com.alevel.service.shop.MusicianService;
import ua.com.alevel.service.shop.ReleaseService;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class MusicianServiceImpl implements MusicianService {

    private final CrudRepositoryHelper<Musician, MusicianRepository> crudRepositoryHelper;
    private final MusicianRepository musicianRepository;
    private final ReleaseService releaseService;

    public MusicianServiceImpl(CrudRepositoryHelper<Musician, MusicianRepository> crudRepositoryHelper,
                               MusicianRepository musicianRepository,
                               ReleaseService releaseService) {
        this.crudRepositoryHelper = crudRepositoryHelper;
        this.musicianRepository = musicianRepository;
        this.releaseService = releaseService;
    }

    @Override
    public void create(Musician entity) {
        crudRepositoryHelper.create(musicianRepository, entity);
    }

    @Override
    public void update(Musician entity) {
        crudRepositoryHelper.update(musicianRepository, entity);
    }

    @Override
    public void delete(Long id) {
        Set<Release> releases = findById(id).get().getReleases();
        releases.stream().forEach(release -> releaseService.delete(release.getId()));
        crudRepositoryHelper.delete(musicianRepository, id);
    }

    @Override
    public Optional<Musician> findById(Long id) {
        return crudRepositoryHelper.findById(musicianRepository, id);
    }

    @Override
    public DataTableResponse<Musician> findAll(DataTableRequest request) {
        return crudRepositoryHelper.findAll(musicianRepository, request);
    }

    @Override
    public List<Musician> findAll() {
        return crudRepositoryHelper.findAll(musicianRepository);
    }
}
