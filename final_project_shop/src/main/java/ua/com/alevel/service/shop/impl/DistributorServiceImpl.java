package ua.com.alevel.service.shop.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.shop.Distributor;
import ua.com.alevel.persistence.entity.shop.Release;
import ua.com.alevel.persistence.repository.DistributorRepository;
import ua.com.alevel.service.shop.DistributorService;
import ua.com.alevel.service.shop.ReleaseService;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DistributorServiceImpl implements DistributorService {

    private final CrudRepositoryHelper<Distributor, DistributorRepository> distributorCrudRepositoryHelper;
    private final DistributorRepository distributorRepository;
    private final ReleaseService releaseService;

    public DistributorServiceImpl(CrudRepositoryHelper<Distributor, DistributorRepository> distributorCrudRepositoryHelper,
                                  DistributorRepository distributorRepository, ReleaseService releaseService) {
        this.distributorCrudRepositoryHelper = distributorCrudRepositoryHelper;
        this.distributorRepository = distributorRepository;
        this.releaseService = releaseService;
    }

    @Override
    public void create(Distributor entity) {
        distributorCrudRepositoryHelper.create(distributorRepository, entity);
    }

    @Override
    public void update(Distributor entity) {
        distributorCrudRepositoryHelper.update(distributorRepository, entity);
    }

    @Override
    public void delete(Long id) {
        Distributor distributor = distributorCrudRepositoryHelper.findById(distributorRepository, id).get();
        List<Release> releases = distributor.getReleases().stream().filter(release -> releaseService.findAllDistributorsByReleaseId(release.getId()).size() == 1)
                .collect(Collectors.toList());
        distributor.getReleases().retainAll(releases);
        distributorCrudRepositoryHelper.update(distributorRepository, distributor);
        distributorCrudRepositoryHelper.delete(distributorRepository, id);
    }

    @Override
    public Optional<Distributor> findById(Long id) {
        return distributorCrudRepositoryHelper.findById(distributorRepository, id);
    }

    @Override
    public DataTableResponse<Distributor> findAll(DataTableRequest request) {
        return distributorCrudRepositoryHelper.findAll(distributorRepository, request);
    }

    @Override
    public Map<Long, String> findAllReleasesByDistributorId(Long distributorId) {
        Map<Long, String> map = new HashMap<>();
        Set<Release> releases = findById(distributorId).get().getReleases();
        for (Release release : releases){
            map.put(release.getId(), release.getName());
        }
        return map;
    }

    @Override
    public List<Distributor> findAll() {
        return distributorCrudRepositoryHelper.findAll(distributorRepository);
    }
}
