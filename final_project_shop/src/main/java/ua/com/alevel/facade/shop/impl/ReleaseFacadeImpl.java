package ua.com.alevel.facade.shop.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.shop.ReleaseFacade;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.shop.Distributor;
import ua.com.alevel.persistence.entity.shop.Musician;
import ua.com.alevel.persistence.entity.shop.Release;
import ua.com.alevel.persistence.listener.ReleaseVisibleGenerationListener;
import ua.com.alevel.service.shop.DistributorService;
import ua.com.alevel.service.shop.MusicianService;
import ua.com.alevel.service.shop.ReleaseService;
import ua.com.alevel.util.WebRequestUtil;
import ua.com.alevel.util.WebResponseUtil;
import ua.com.alevel.view.dto.request.shop.ReleaseRequestDto;
import ua.com.alevel.view.dto.response.shop.ReleaseResponseDto;
import ua.com.alevel.view.dto.response.util.PageData;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ReleaseFacadeImpl implements ReleaseFacade {

    private final ReleaseService releaseService;
    private final DistributorService distributorService;
    private final MusicianService musicianService;

    public ReleaseFacadeImpl(ReleaseService releaseService,
                             DistributorService distributorService,
                             MusicianService musicianService) {
        this.releaseService = releaseService;
        this.distributorService = distributorService;
        this.musicianService = musicianService;
    }

    @Override
    public Map<Long, String> findAllDistributorsByReleaseId(Long releaseId) {
        return releaseService.findAllDistributorsByReleaseId(releaseId);
    }

    @Override
    public Release findReleaseById(Long id) {
        return releaseService.findById(id).get();
    }

    @Override
    public void create(ReleaseRequestDto releaseRequestDto) {
        Release release = new Release();
        release.setCreated(new Date(System.currentTimeMillis()));
        release.setUpdated(new Date(System.currentTimeMillis()));
        ReleaseVisibleGenerationListener.generateReleaseVisible(release);
        release.setName(releaseRequestDto.getReleaseName());
        release.setDescription(releaseRequestDto.getDescription());
        release.setPrice(releaseRequestDto.getPrice());
        release.setReleaseType(releaseRequestDto.getReleaseType());
        release.setImage(releaseRequestDto.getImageUrl());
        release.setQuantity(releaseRequestDto.getQuantity());
        try {
            Set<Long> distributorsId = releaseRequestDto.getDistributorsId();
            Musician musician = musicianService.findById(releaseRequestDto.getMusicianId()).get();
            release.setMusician(musician);
            releaseService.create(release);
            musician.addRelease(release);
            musicianService.update(musician);
            for (Long id : distributorsId) {
                Distributor distributor = distributorService.findById(id).get();
                distributor.addRelease(release);
                distributorService.update(distributor);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(ReleaseRequestDto releaseRequestDto, Long id) {
        Release release = releaseService.findById(id).get();
        release.setPrice(releaseRequestDto.getPrice());
        release.setName(releaseRequestDto.getReleaseName());
        release.setDescription(releaseRequestDto.getDescription());
        release.setImage(releaseRequestDto.getImageUrl());
        release.setQuantity(releaseRequestDto.getQuantity());
        release.setReleaseType(releaseRequestDto.getReleaseType());
        release.setMusician(musicianService.findById(id).get());
        ReleaseVisibleGenerationListener.generateReleaseVisible(release);
        release.setUpdated(new Date(System.currentTimeMillis()));
        releaseService.update(release);
    }

    @Override
    public void delete(Long id) {
        releaseService.delete(id);
    }

    @Override
    public ReleaseResponseDto findById(Long id) {
        return new ReleaseResponseDto(releaseService.findById(id).get());
    }

    @Override
    public PageData<ReleaseResponseDto> findAll(WebRequest request) {
        DataTableRequest dataTableRequest = WebRequestUtil.initDataTableRequest(request);
        DataTableResponse<Release> dataTableResponse = releaseService.findAll(dataTableRequest);
        List<ReleaseResponseDto> releases = dataTableResponse.getItems().stream().
                map(ReleaseResponseDto::new).
                collect(Collectors.toList());
        PageData<ReleaseResponseDto> pageData = (PageData<ReleaseResponseDto>) WebResponseUtil.initPageData(dataTableResponse);
        pageData.setItems(releases);
        return pageData;
    }
}
