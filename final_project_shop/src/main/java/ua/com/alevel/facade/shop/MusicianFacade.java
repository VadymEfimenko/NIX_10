package ua.com.alevel.facade.shop;

import ua.com.alevel.facade.util.BaseFacade;
import ua.com.alevel.persistence.entity.shop.Musician;
import ua.com.alevel.view.dto.request.shop.MusicianRequestDto;
import ua.com.alevel.view.dto.response.shop.MusicianResponseDto;

import java.util.List;

public interface MusicianFacade extends BaseFacade<MusicianRequestDto, MusicianResponseDto> {

    List<Musician> findAll();
}
