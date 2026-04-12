package com.example.repositories.interfaces;

import java.util.List;

// import org.springframework.data.repository.NoRepositoryBean;
// import org.springframework.stereotype.Repository;

// @NoRepositoryBean
public interface IRepository<T, ID> {
    List<T> findAll();
    T findById(ID id);
    void save(T entity);
    void delete(ID id);

}

// 
