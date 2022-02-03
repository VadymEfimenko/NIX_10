package ua.com.alevel.facade.shop.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.shop.MusicianFacade;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.shop.Musician;
import ua.com.alevel.service.shop.MusicianService;
import ua.com.alevel.util.WebRequestUtil;
import ua.com.alevel.util.WebResponseUtil;
import ua.com.alevel.view.dto.request.shop.MusicianRequestDto;
import ua.com.alevel.view.dto.response.shop.MusicianResponseDto;
import ua.com.alevel.view.dto.response.util.PageData;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MusicianFacadeImpl implements MusicianFacade {

    private final MusicianService musicianService;

    public MusicianFacadeImpl(MusicianService musicianService) {
        this.musicianService = musicianService;
    }

    @Override
    public void create(MusicianRequestDto musicianRequestDto) {
        Musician musician = new Musician();
        musician.setCreated(new Date(System.currentTimeMillis()));
        musician.setUpdated(new Date(System.currentTimeMillis()));
        musician.setName(musicianRequestDto.getName());
        musician.setMusicianGroup(musicianRequestDto.getMusicianGroup());
        musicianService.create(musician);
    }

    @Override
    public void update(MusicianRequestDto musicianRequestDto, Long id) {
        Musician musician = musicianService.findById(id).get();
        musician.setUpdated(new Date(System.currentTimeMillis()));
        musician.setName(musicianRequestDto.getName());
        musician.setMusicianGroup(musicianRequestDto.getMusicianGroup());
        musicianService.update(musician);
    }

    @Override
    public void delete(Long id) {
        musicianService.delete(id);
    }

    @Override
    public MusicianResponseDto findById(Long id) {
        return new MusicianResponseDto(musicianService.findById(id).get());
    }

    @Override
    public PageData<MusicianResponseDto> findAll(WebRequest request) {
        DataTableRequest dataTableRequest = WebRequestUtil.initDataTableRequest(request);
        DataTableResponse<Musician> tableResponse = musicianService.findAll(dataTableRequest);
        List<MusicianResponseDto> musicians = tableResponse.getItems().stream()
                .map(MusicianResponseDto::new)
                .collect(Collectors.toList());
        PageData<MusicianResponseDto> pageData = (PageData<MusicianResponseDto>) WebResponseUtil.initPageData(tableResponse);
        pageData.setItems(musicians);
        return pageData;
    }

    @Override
    public List<Musician> findAll() {
        return musicianService.findAll();
    }
}
