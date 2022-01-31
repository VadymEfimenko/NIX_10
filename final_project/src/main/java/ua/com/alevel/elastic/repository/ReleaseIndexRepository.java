package ua.com.alevel.elastic.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;
import ua.com.alevel.elastic.document.ReleaseIndex;

@Repository
public interface ReleaseIndexRepository extends ElasticsearchRepository<ReleaseIndex, String> {
}
