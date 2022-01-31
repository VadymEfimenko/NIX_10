package ua.com.alevel.service.shop.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.label.Label;
import ua.com.alevel.persistence.entity.release.Release;
import ua.com.alevel.persistence.repository.shop.LabelRepository;
import ua.com.alevel.persistence.repository.shop.ReleaseRepository;
import ua.com.alevel.service.shop.LabelService;
import ua.com.alevel.service.shop.ReleaseService;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class LabelServiceImpl implements LabelService {

    private final CrudRepositoryHelper<Label, LabelRepository> crudRepositoryHelper;
    private final LabelRepository labelRepository;
    private final ReleaseService releaseService;

    public LabelServiceImpl(CrudRepositoryHelper<Label, LabelRepository> crudRepositoryHelper,
                            LabelRepository labelRepository,
                            ReleaseService releaseService) {
        this.crudRepositoryHelper = crudRepositoryHelper;
        this.labelRepository = labelRepository;
        this.releaseService = releaseService;
    }

    @Override
    public void create(Label entity) {
        crudRepositoryHelper.create(labelRepository, entity);
    }

    @Override
    public void update(Label entity) {
        crudRepositoryHelper.update(labelRepository, entity);
    }

    @Override
    public void delete(Long id) {
        Label label = crudRepositoryHelper.findById(labelRepository, id).get();
        List<Release> releases = label.getReleases().stream().filter(release -> releaseService.findAllLabelsByReleaseId(release.getId()).size() == 1)
                .collect(Collectors.toList());
        label.getReleases().retainAll(releases);
        crudRepositoryHelper.update(labelRepository, label);
        crudRepositoryHelper.delete(labelRepository, id);
    }

    @Override
    public Optional<Label> findById(Long id) {
        return crudRepositoryHelper.findById(labelRepository, id);
    }

    @Override
    public DataTableResponse<Label> findAll(DataTableRequest request) {
        return crudRepositoryHelper.findAll(labelRepository, request);
    }

    @Override
    public Map<Long, String> findAllReleasesByLabelId(Long labelId) {
        Map<Long, String> map = new HashMap<>();
        Set<Release> releases = findById(labelId).get().getReleases();
        for (Release release : releases){
            map.put(release.getId(), release.getReleaseName());
        }
        return map;
    }

    @Override
    public List<Label> findAll() {
        return crudRepositoryHelper.findAll(labelRepository);
    }
}
