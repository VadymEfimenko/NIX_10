package ua.com.alevel.persistence.listener;


import ua.com.alevel.persistence.entity.release.Release;

import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;

public class ReleaseVisibleGenerationListener {

    @PostLoad
    @PostPersist
    @PostUpdate
    public static void generateReleaseVisible(Release release){
        release.setVisible(release.getQuantity() != null &&
                release.getQuantity() > 0 &&
                release.getPrice() != null &&
                release.getPrice() > 0);
    }
}
