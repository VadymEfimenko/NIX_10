package ua.com.alevel.persistence.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.persistence.entity.shop.Release;

import java.util.List;

@Repository
public interface ReleaseRepository extends BaseRepository<Release> {

    Page<Release> findByMusicianNameIn(List<String> names, Pageable pageable);

    Page<Release> findByNameContaining(String releaseName, Pageable pageable);

    @Transactional
    @Modifying
    @Query(value = "delete from distributor_release where release_id = :id", nativeQuery = true)
    void unlinkReleaseFromDistributor(@Param("id") Long id);
}
