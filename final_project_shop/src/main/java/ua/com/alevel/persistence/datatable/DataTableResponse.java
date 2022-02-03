package ua.com.alevel.persistence.datatable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ua.com.alevel.persistence.entity.BaseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Map;


@Getter
@Setter
@ToString
public class DataTableResponse <E extends BaseEntity> {

    private int currentPage;
    private int pageSize;
    private int totalPageSize;
    private long itemsSize;
    private String sort;
    private String order;
    private List<E> items;
    private Map<Object, Object> otherParamMap;

    public DataTableResponse() {
        this.currentPage = 0;
        this.pageSize = 10;
        this.itemsSize = 0;
        this.items = Collections.emptyList();
        this.otherParamMap = Collections.emptyMap();
    }
}
