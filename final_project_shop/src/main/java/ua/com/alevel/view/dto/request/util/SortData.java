package ua.com.alevel.view.dto.request.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SortData {

    private String sort;
    private String order;

    public SortData() {
    }

    public SortData(String sort, String order) {
        this.sort = sort;
        this.order = order;
    }
}
