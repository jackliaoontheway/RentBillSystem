package com.cherryj.base.service;

import com.cherryj.base.domain.BaseDomain;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public abstract class BaseServiceImpl<T extends BaseDomain> implements BaseService<T> {

    @Autowired(required = false)
    private @Getter
    @Setter
    JpaRepository<T, Integer> repository;

    @Override
    public T create(T domain, Integer operatorId) {
        initialBeforeCreate(domain);
        domain.setCreatedDate(new Date());
        domain.setCreatedBy(operatorId);
        return repository.save(domain);
    }

    protected void initialBeforeCreate(T domain) {

    }

    @Override
    public T update(T domainFromView, Integer operatorId) {
        T existedDomain = this.getById(domainFromView.getId());

        initialBeforeUpdate(domainFromView, existedDomain);

        existedDomain.setModifiedBy(operatorId);
        existedDomain.setModifiedDate(new Date());
        return repository.save(existedDomain);
    }

    //TODO abstract
    protected void initialBeforeUpdate(T source, T target) {

    }

    @Override
    public boolean delete(Integer id) {
        repository.delete(this.getById(id));
        return true;
    }

    @Override
    public T getById(Integer id) {
        return repository.getOne(id);
    }

    @Override
    public List<T> listAll() {
        return repository.findAll();
    }

    @Override
    public Page<T> listByPage(Pageable pageable) {
        return repository.findAll(pageable);
    }

}
