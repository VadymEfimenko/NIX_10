package ua.com.alevel.elastic;

import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ua.com.alevel.elastic.document.ReleaseIndex;
import ua.com.alevel.elastic.repository.ReleaseIndexRepository;
import ua.com.alevel.persistence.entity.shop.Release;
import ua.com.alevel.persistence.repository.ReleaseRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class SyncElasticCronService {

    private final ElasticsearchOperations elasticsearchOperations;
    private final ReleaseIndexRepository releaseIndexRepository;
    private final ReleaseRepository releaseRepository;

    public SyncElasticCronService(ElasticsearchOperations elasticsearchOperations, ReleaseIndexRepository releaseIndexRepository, ReleaseRepository releaseRepository) {
        this.elasticsearchOperations = elasticsearchOperations;
        this.releaseIndexRepository = releaseIndexRepository;
        this.releaseRepository = releaseRepository;
    }


    @Scheduled(fixedDelay = 600000)
    public void syncToSupplier() {
        elasticsearchOperations.indexOps(ReleaseIndex.class).refresh();
        releaseIndexRepository.deleteAll();
        releaseIndexRepository.saveAll(prepareDataset());
    }

    private Collection<ReleaseIndex> prepareDataset() {
        List<Release> releases = releaseRepository.findAll();
        List<ReleaseIndex> releaseIndices = new ArrayList<>();
        releases.forEach(release -> {
            ReleaseIndex releaseIndex = new ReleaseIndex();
            releaseIndex.setName(release.getName());
            releaseIndices.add(releaseIndex);
        });
        return releaseIndices;
    }

}
