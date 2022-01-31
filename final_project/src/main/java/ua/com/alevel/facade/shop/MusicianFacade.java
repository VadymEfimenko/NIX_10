package ua.com.alevel.facade.shop;

import ua.com.alevel.facade.BaseFacade;
import ua.com.alevel.persistence.entity.musician.Musician;
import ua.com.alevel.view.dto.request.MusicianRequestDto;
import ua.com.alevel.view.dto.response.MusicianResponseDto;

import java.util.List;

public interface MusicianFacade extends BaseFacade<MusicianRequestDto, MusicianResponseDto> {
    List<Musician> findAll();
}
