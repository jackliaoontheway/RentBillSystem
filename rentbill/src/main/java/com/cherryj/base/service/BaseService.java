package com.cherryj.base.service;

import com.cherryj.base.domain.BaseDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BaseService<T extends BaseDomain> {

    T create(T domain, Integer operatorId);

    T update(T domain, Integer operatorId);

    boolean delete(Integer id);

    T getById(Integer id);

    List<T> listAll(Integer operatorId);

    Page<T> listByPage(Pageable pageable);

}
