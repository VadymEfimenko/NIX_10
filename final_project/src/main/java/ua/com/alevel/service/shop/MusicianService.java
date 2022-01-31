package ua.com.alevel.service.shop;

import ua.com.alevel.persistence.entity.musician.Musician;
import ua.com.alevel.service.BaseService;

import java.util.List;

public interface MusicianService extends BaseService<Musician> {
    List<Musician> findAll();
}
