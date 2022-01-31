package ua.com.alevel.service.shop.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.label.Label;
import ua.com.alevel.persistence.entity.release.Release;
import ua.com.alevel.persistence.listener.ReleaseVisibleGenerationListener;
import ua.com.alevel.persistence.repository.shop.ReleaseRepository;
import ua.com.alevel.service.shop.ReleaseService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Service
public class ReleaseServiceImpl implements ReleaseService {

    private final CrudRepositoryHelper<Release, ReleaseRepository> crudRepositoryHelper;
    private final ReleaseRepository releaseRepository;

    public ReleaseServiceImpl(CrudRepositoryHelper<Release, ReleaseRepository> crudRepositoryHelper, ReleaseRepository releaseRepository) {
        this.crudRepositoryHelper = crudRepositoryHelper;
        this.releaseRepository = releaseRepository;
    }

    @Override
    public void create(Release entity) {
        crudRepositoryHelper.create(releaseRepository, entity);
    }

    @Override
    public void update(Release entity) {
        ReleaseVisibleGenerationListener.generateReleaseVisible(entity);
        crudRepositoryHelper.update(releaseRepository, entity);
    }

    @Override
    public void delete(Long id) {
        releaseRepository.detachReleaseFromLabel(id);
        crudRepositoryHelper.delete(releaseRepository, id);
    }

    @Override
    public Optional<Release> findById(Long id) {
        return crudRepositoryHelper.findById(releaseRepository, id);
    }

    @Override
    public DataTableResponse<Release> findAll(DataTableRequest request) {
        return crudRepositoryHelper.findAll(releaseRepository, request);
    }

    @Override
    public Map<Long, String> findAllLabelsByReleaseId(Long releaseId) {
        Map<Long, String> map = new HashMap<>();
        Set<Label> labels = findById(releaseId).get().getLabels();
        for (Label label : labels){
            map.put(label.getId(), label.getLabelName());
        }
        return map;
    }
}
