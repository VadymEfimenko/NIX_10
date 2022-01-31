package ua.com.alevel.persistence.repository.shop;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.persistence.entity.release.Release;
import ua.com.alevel.persistence.repository.BaseRepository;

import java.util.List;

@Repository
public interface ReleaseRepository extends BaseRepository<Release> {

   Page<Release> findByMusicianNickNameIn(List<String> names, Pageable pageable);

   Page<Release> findByReleaseNameContaining(String releaseName, Pageable pageable);

   @Transactional
   @Modifying
   @Query(value = "delete from label_release where release_id = :id", nativeQuery = true)
   void detachReleaseFromLabel(@Param("id") Long id);
}
