package ua.com.alevel.service.shop;

import ua.com.alevel.persistence.entity.label.Label;
import ua.com.alevel.service.BaseService;

import java.util.List;
import java.util.Map;

public interface LabelService extends BaseService<Label> {

    Map<Long, String> findAllReleasesByLabelId(Long labelId);

    List<Label> findAll();
}
