package ua.com.alevel.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.musician.Musician;
import ua.com.alevel.persistence.entity.release.Release;
import ua.com.alevel.persistence.repository.shop.MusicianRepository;
import ua.com.alevel.persistence.repository.shop.ReleaseRepository;
import ua.com.alevel.service.PLPService;
import ua.com.alevel.util.WebRequestUtil;

import java.util.List;
import java.util.Map;

@Service
public class PLPServiceImpl implements PLPService {

    private final ReleaseRepository releaseRepository;
    private final CrudRepositoryHelper<Release, ReleaseRepository> crudRepositoryHelper;
    private final MusicianRepository musicianRepository;
    private final CrudRepositoryHelper<Musician, MusicianRepository> musicianRepositoryHelper;

    public PLPServiceImpl(ReleaseRepository releaseRepository,
                          CrudRepositoryHelper<Release, ReleaseRepository> crudRepositoryHelper, MusicianRepository musicianRepository, CrudRepositoryHelper<Musician, MusicianRepository> musicianRepositoryHelper) {
        this.releaseRepository = releaseRepository;
        this.crudRepositoryHelper = crudRepositoryHelper;
        this.musicianRepository = musicianRepository;
        this.musicianRepositoryHelper = musicianRepositoryHelper;
    }

    @Override
    public DataTableResponse<Release> search(Map<String, Object> queryMap, DataTableRequest request) {
        int size = request.getSize();
        int page = request.getPage() - 1;
        String sortBy = request.getSort();
        String orderBy = request.getOrder();
        Sort sort = orderBy.equals("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();
        PageRequest pageRequest = PageRequest.of(page, size, sort);
        if (queryMap.get(WebRequestUtil.MUSICIAN_PARAM) != null) {
            List<String> musicians = (List<String>) (queryMap.get(WebRequestUtil.MUSICIAN_PARAM));
            Page<Release> releases = releaseRepository.findByMusicianNickNameIn(musicians, pageRequest);
            return initDataTableResponseRelease(releases, request, sortBy, orderBy);
        }
        if (queryMap.get(WebRequestUtil.SEARCH_RELEASE_PARAM) != null) {
            String searchRelease = (String) queryMap.get(WebRequestUtil.SEARCH_RELEASE_PARAM);
            Page<Release> releases = releaseRepository.findByReleaseNameContaining(searchRelease, pageRequest);
            return initDataTableResponseRelease(releases, request, sortBy, orderBy);
        }
        return crudRepositoryHelper.findAll(releaseRepository, request);
    }

    private DataTableResponse<Release> initDataTableResponseRelease(Page<Release> releases, DataTableRequest dataTableRequest, String sortBy, String orderBy) {

        DataTableResponse<Release> dataTableResponse = new DataTableResponse<>();
        dataTableResponse.setTotalPages(releases.getTotalPages());
        dataTableResponse.setItemsSize(releases.getTotalElements());
        dataTableResponse.setItems(releases.getContent());
        dataTableResponse.setOrder(orderBy);
        dataTableResponse.setSort(sortBy);
        dataTableResponse.setCurrentPage(dataTableRequest.getPage());
        dataTableResponse.setPageSize(dataTableRequest.getSize());
        return dataTableResponse;
    }
}
