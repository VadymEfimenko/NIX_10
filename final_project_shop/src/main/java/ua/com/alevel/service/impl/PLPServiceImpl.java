package ua.com.alevel.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.shop.Musician;
import ua.com.alevel.persistence.entity.shop.Release;
import ua.com.alevel.persistence.repository.MusicianRepository;
import ua.com.alevel.persistence.repository.ReleaseRepository;
import ua.com.alevel.service.PLPService;
import ua.com.alevel.util.WebRequestUtil;

import java.util.List;
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
        int size = dataTableRequest.getSize();
        int page = dataTableRequest.getPage() - 1;
        String sortBy = dataTableRequest.getSort();
        String orderBy = dataTableRequest.getOrder();
        Sort sort = orderBy.equals("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();
        PageRequest pageRequest = PageRequest.of(page, size, sort);
        if (queryMap.get(WebRequestUtil.MUSICIAN_PARAM) != null) {
            List<String> musicianNames = (List<String>) (queryMap.get(WebRequestUtil.MUSICIAN_PARAM));
            Page<Release> releases = releaseRepository.findByMusicianNameIn(musicianNames, pageRequest);
            return initDataTableResponseCube(releases, dataTableRequest, sortBy, orderBy);
        }
        if (queryMap.get(WebRequestUtil.SEARCH_RELEASE_PARAM) != null) {
            String searchRelease = (String) queryMap.get(WebRequestUtil.SEARCH_RELEASE_PARAM);
            Page<Release> releases = releaseRepository.findByNameContaining(searchRelease, pageRequest);
            return initDataTableResponseCube(releases, dataTableRequest, sortBy, orderBy);
        }
        return releaseCrudRepositoryHelper.findAll(releaseRepository, dataTableRequest);
    }

    private DataTableResponse<Release> initDataTableResponseCube(Page<Release> releases, DataTableRequest dataTableRequest, String sortBy, String orderBy) {

        DataTableResponse<Release> dataTableResponse = new DataTableResponse<>();
        dataTableResponse.setTotalPageSize(releases.getTotalPages());
        dataTableResponse.setItemsSize(releases.getTotalElements());
        dataTableResponse.setItems(releases.getContent());
        dataTableResponse.setOrder(orderBy);
        dataTableResponse.setSort(sortBy);
        dataTableResponse.setCurrentPage(dataTableRequest.getPage());
        dataTableResponse.setPageSize(dataTableRequest.getSize());
        return dataTableResponse;
    }
}
