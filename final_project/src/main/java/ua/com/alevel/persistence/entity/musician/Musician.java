package ua.com.alevel.persistence.entity.musician;

import ua.com.alevel.persistence.entity.BaseEntity;
import ua.com.alevel.persistence.entity.release.Release;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "musicians")
public class Musician extends BaseEntity {

    @Column(name = "first_name")
    private String nickName;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "musician_release",
            joinColumns = @JoinColumn(name = "musician_id"),
            inverseJoinColumns = @JoinColumn(name = "release_id"))
    private Set<Release> releases;

    public Musician(){
        super();
        releases = new HashSet<>();
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Set<Release> getReleases() {
        return releases;
    }

    public void setReleases(Set<Release> releases) {
        this.releases = releases;
    }
}
