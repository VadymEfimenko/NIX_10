package ua.com.alevel.persistence.datatable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;


@Setter
@Getter
@ToString
public class DataTableRequest {

    private int page;
    private int size;
    private String order;
    private String sort;
    private Map<String, String[]> requestParamMap;

    public DataTableRequest() {
        this.requestParamMap = new HashMap<>();
    }
}
