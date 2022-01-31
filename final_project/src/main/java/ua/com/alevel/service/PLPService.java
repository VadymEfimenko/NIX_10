package ua.com.alevel.service;

import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.release.Release;

import java.util.Map;

public interface PLPService {
    DataTableResponse<Release> search(Map<String, Object> queryMap, DataTableRequest request);
}
