package ua.com.alevel.persistence.repository.shop;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.musician.Musician;
import ua.com.alevel.persistence.repository.BaseRepository;
import ua.com.alevel.persistence.tmp.MusicianTmp;

import java.util.List;

@Repository
public interface MusicianRepository extends BaseRepository<Musician> {

    @Query("select new ua.com.alevel.persistence.tmp.MusicianTmp(m.id, m.nickName) from Musician m")
    List<MusicianTmp> findAllMusicianTmp();
}
