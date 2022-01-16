package ua.com.alevel.dao;

import ua.com.alevel.entity.BaseEntity;

import java.util.List;

public interface BaseDao<ENTITY extends BaseEntity> {

    void create(ENTITY entity);

    void delete(Long id);

    ENTITY findById(Long id);

    List<ENTITY> findAll();
}
