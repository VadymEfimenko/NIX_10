package ua.com.alevel.service;

import java.util.List;

public interface ElasticReleaseSearchService {

    List<String> searchReleaseName(String query);
}
