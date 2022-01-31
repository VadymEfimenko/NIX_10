package ua.com.alevel.persistence.entity.musician;

import ua.com.alevel.persistence.entity.BaseEntity;
import ua.com.alevel.persistence.entity.release.Release;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "musicians")
public class Musician extends BaseEntity {

    @Column(name = "nick_name")
    private String nickName;

    @OneToMany(mappedBy = "musician")
    private Set<Release> releases;

    public Musician() {
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

    public void addRelease(Release release) {
        releases.add(release);
        release.setMusician(this);
    }

    @Override
    public String toString() {
        return "Musician{" +
                "nickName='" + nickName + '\'' +
                ", releases=" + releases +
                '}';
    }
}
