package ua.com.alevel.persistence.entity.shop;


import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.persistence.entity.BaseEntity;
import ua.com.alevel.persistence.enums.MusicianGroup;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "musicians")
public class Musician extends BaseEntity {

    @AttributeOverride(name = "id", column = @Column(name = "musician_id"))

    private String name;

    @Enumerated(EnumType.STRING)
    private MusicianGroup musicianGroup;

    @OneToMany(mappedBy = "musician")
    private Set<Release> releases;

    public Musician(){
        super();
        this.releases = new HashSet<>();
    }

    public void addRelease(Release release) {
        releases.add(release);
        release.setMusician(this);
    }
}
