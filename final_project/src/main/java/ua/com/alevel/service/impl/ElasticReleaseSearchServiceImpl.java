package ua.com.alevel.service.impl;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;
import ua.com.alevel.elastic.document.ReleaseIndex;
import ua.com.alevel.service.ElasticReleaseSearchService;

import java.util.ArrayList;
import java.util.List;

@Service
public class ElasticReleaseSearchServiceImpl implements ElasticReleaseSearchService {


    private static final String RELEASE_INDEX = "releaseIndex";

    private final ElasticsearchOperations elasticsearchOperations;

    public ElasticReleaseSearchServiceImpl(ElasticsearchOperations elasticsearchOperations) {
        this.elasticsearchOperations = elasticsearchOperations;
    }

    @Override
    public List<String> searchReleaseName(String query) {
        QueryBuilder queryBuilder = QueryBuilders
                .wildcardQuery("releaseName", "*" + query.toLowerCase() + "*");
        Query searchQuery = new NativeSearchQueryBuilder()
                .withFilter(queryBuilder)
                .withPageable(PageRequest.of(0, 5))
                .build();
        SearchHits<ReleaseIndex> searchSuggestions =
                elasticsearchOperations.search(searchQuery,
                        ReleaseIndex.class,
                        IndexCoordinates.of(RELEASE_INDEX));
        final List<String> suggestions = new ArrayList<>();
        searchSuggestions.getSearchHits().forEach(searchHit-> suggestions.add(searchHit.getContent().getReleaseName()));
        return suggestions;
    }
}
