package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.shop.Musician;
import ua.com.alevel.persistence.entity.shop.Release;
import ua.com.alevel.persistence.repository.MusicianRepository;
import ua.com.alevel.persistence.repository.ReleaseRepository;
import ua.com.alevel.service.PLPService;

import java.util.Map;

@Service
public class PLPServiceImpl implements PLPService {

    private final ReleaseRepository releaseRepository;
    private final MusicianRepository musicianRepository;
    private final CrudRepositoryHelper<Musician, MusicianRepository> musicianCrudRepositoryHelper;
    private final CrudRepositoryHelper<Release, ReleaseRepository> releaseCrudRepositoryHelper;

    public PLPServiceImpl(ReleaseRepository releaseRepository, MusicianRepository musicianRepository,
                          CrudRepositoryHelper<Musician, MusicianRepository> musicianCrudRepositoryHelper,
                          CrudRepositoryHelper<Release, ReleaseRepository> releaseCrudRepositoryHelper) {
        this.releaseRepository = releaseRepository;
        this.musicianRepository = musicianRepository;
        this.musicianCrudRepositoryHelper = musicianCrudRepositoryHelper;
        this.releaseCrudRepositoryHelper = releaseCrudRepositoryHelper;
    }


    @Override
    public DataTableResponse<Release> search(Map<String, Object> queryMap, DataTableRequest dataTableRequest) {
        return null;
    }
}
