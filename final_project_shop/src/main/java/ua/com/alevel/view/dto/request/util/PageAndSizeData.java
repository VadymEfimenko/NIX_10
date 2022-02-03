package ua.com.alevel.view.dto.request.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageAndSizeData {

    int page;
    int size;

    public PageAndSizeData() {
    }

    public PageAndSizeData(int page, int size) {
        this.page = page;
        this.size = size;
    }
}
