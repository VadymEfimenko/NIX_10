package ua.com.alevel.persistence.crud.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.exception.EntityNotFoundException;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.BaseEntity;
import ua.com.alevel.persistence.repository.BaseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CrudRepositoryHelperImpl<
        E extends BaseEntity,
        R extends BaseRepository<E>>
        implements CrudRepositoryHelper<E, R> {

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void create(R repository, E entity) {
        repository.save(entity);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void update(R repository, E entity) {
        checkExist(repository, entity.getId());
        repository.save(entity);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void delete(R repository, Long id) {
        checkExist(repository, id);
        repository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<E> findById(R repository, Long id) {
        checkExist(repository, id);
        return repository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public DataTableResponse<E> findAll(R repository, DataTableRequest request) {

        int page = request.getPage() - 1;
        int size = request.getSize();
        String sortBy = request.getSort();
        String orderBy = request.getOrder();

        Sort sort = orderBy.equals("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();
        PageRequest pageRequest = PageRequest.of(page, size, sort);

        Page<E> pageEntity = repository.findAll(pageRequest);

        return generateDataTableResponse(pageEntity, sortBy, orderBy, request);
    }

    @Override
    @Transactional(readOnly = true)
    public List<E> findAll(R repository) {
        return repository.findAll();
    }

    private void checkExist(R repository, Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("this entity is not found");
        }
    }

    private DataTableResponse<E> generateDataTableResponse(
            Page<E> pageEntity,
            String sortBy,
            String orderBy,
            DataTableRequest dataTableRequest) {
        DataTableResponse<E> dataTableResponse = new DataTableResponse<>();
        dataTableResponse.setItemsSize(pageEntity.getTotalElements());
        dataTableResponse.setPageSize(pageEntity.getTotalPages());
        dataTableResponse.setItems(pageEntity.getContent());
        dataTableResponse.setOrder(orderBy);
        dataTableResponse.setSort(sortBy);
        dataTableResponse.setCurrentPage(dataTableRequest.getPage());
        dataTableResponse.setPageSize(dataTableRequest.getSize());
        return dataTableResponse;
    }
}
