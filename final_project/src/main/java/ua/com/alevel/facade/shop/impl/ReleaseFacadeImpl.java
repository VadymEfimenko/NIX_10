package ua.com.alevel.facade.shop.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.shop.ReleaseFacade;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.label.Label;
import ua.com.alevel.persistence.entity.musician.Musician;
import ua.com.alevel.persistence.entity.release.Release;
import ua.com.alevel.persistence.listener.ReleaseVisibleGenerationListener;
import ua.com.alevel.service.shop.LabelService;
import ua.com.alevel.service.shop.MusicianService;
import ua.com.alevel.service.shop.ReleaseService;
import ua.com.alevel.util.WebRequestUtil;
import ua.com.alevel.util.WebResponseUtil;
import ua.com.alevel.view.dto.request.ReleaseRequestDto;
import ua.com.alevel.view.dto.response.PageData;
import ua.com.alevel.view.dto.response.ReleaseResponseDto;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ReleaseFacadeImpl implements ReleaseFacade {

    private final ReleaseService releaseService;
    private final MusicianService musicianService;
    private final LabelService labelService;

    public ReleaseFacadeImpl(ReleaseService releaseService, MusicianService musicianService, LabelService labelService) {
        this.releaseService = releaseService;
        this.musicianService = musicianService;
        this.labelService = labelService;
    }

    @Override
    public void create(ReleaseRequestDto releaseRequestDto) {
        Release release = new Release();
        release.setCreated(new Date(System.currentTimeMillis()));
        release.setUpdated(new Date(System.currentTimeMillis()));
        release.setReleaseName(releaseRequestDto.getReleaseName());
        release.setPrice(releaseRequestDto.getPrice());
        release.setDescription(releaseRequestDto.getDescription());
        release.setImageUrl(releaseRequestDto.getImageUrl());
        release.setYear(releaseRequestDto.getYear());
        release.setGenre(releaseRequestDto.getGenre());
        release.setQuantity(releaseRequestDto.getQuantity());
        release.setMusician(musicianService.findById(releaseRequestDto.getMusician()).get());
        ReleaseVisibleGenerationListener.generateReleaseVisible(release);
        try {
            Set<Long> label = releaseRequestDto.getLabelId();
            Musician musician = musicianService.findById(releaseRequestDto.getMusician()).get();
            release.setMusician(musician);
            releaseService.create(release);
            musician.addRelease(release);
            musicianService.update(musician);
            for (Long id : label) {
                Label labelFind = labelService.findById(id).get();
                labelFind.addRelease(release);
                labelService.update(labelFind);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(ReleaseRequestDto releaseRequestDto, Long id) {
        Release release = releaseService.findById(id).get();
        String releaseName = releaseRequestDto.getReleaseName();
        Integer price = releaseRequestDto.getPrice();
        String description = releaseRequestDto.getDescription();
        String imageUrl = releaseRequestDto.getImageUrl();
        Integer year = releaseRequestDto.getYear();
        String genre = releaseRequestDto.getGenre();
        Integer quantity = releaseRequestDto.getQuantity();
        release.setUpdated(new Date(System.currentTimeMillis()));
        release.setReleaseName(releaseName);
        release.setPrice(price);
        release.setDescription(description);
        release.setImageUrl(imageUrl);
        release.setYear(year);
        release.setGenre(genre);
        release.setQuantity(quantity);
        release.setMusician(musicianService.findById(releaseRequestDto.getMusician()).get());
        ReleaseVisibleGenerationListener.generateReleaseVisible(release);
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
        DataTableResponse<Release> response = releaseService.findAll(dataTableRequest);
        List<ReleaseResponseDto> releases = response.getItems().stream()
                .map(ReleaseResponseDto::new)
                .collect(Collectors.toList());
        PageData<ReleaseResponseDto> pageData;
        pageData = (PageData<ReleaseResponseDto>) WebResponseUtil.initPageData(response);
        pageData.setItems(releases);
        return pageData;
    }

    @Override
    public Map<Long, String> findAllLabelsByReleaseId(Long releaseId) {
        return releaseService.findAllLabelsByReleaseId(releaseId);
    }

    @Override
    public Release findReleaseById(Long id) {
        return releaseService.findById(id).get();
    }
}
