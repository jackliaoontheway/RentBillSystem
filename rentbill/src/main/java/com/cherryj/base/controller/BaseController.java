package com.cherryj.base.controller;

import com.cherryj.base.common.model.Response;
import com.cherryj.base.common.model.ResponseStatus;
import com.cherryj.base.domain.BaseDomain;
import com.cherryj.base.service.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
@CrossOrigin(origins = "*",maxAge = 360000)
public abstract class BaseController<M extends BaseDomain, S extends BaseService<M>> {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private S service;

    @PostMapping("create")
    public Response<M> create(@RequestBody M domain) {
        Response<M> response = new Response<>();
        if (domain == null) {
            response.setStatus(ResponseStatus.RequestParameterError.name());
            response.setMsg("Request parameter is null");
            return response;
        }
        if (!validateBeforeCreate(domain, response)) {
            return response;
        }
        response.setData(service.create(domain, -1));
        return response;
    }

    protected boolean validateBeforeCreate(M domain, Response<M> response) {
        return true;
    }

    @PostMapping("update")
    public Response<M> update(@RequestBody M domain) {
        Response<M> response = new Response<>();
        if (domain == null) {
            response.setStatus(ResponseStatus.RequestParameterError.name());
            response.setMsg("Request parameter is null");
            return response;
        }
        if (!validateBeforeCreate(domain, response)) {
            return response;
        }
        response.setData(service.update(domain, -1));
        return response;
    }

    @PostMapping("delete")
    public Response<Boolean> delete(@RequestBody M domain) {
        Response<Boolean> response = new Response<>();
        if (domain == null) {
            response.setStatus(ResponseStatus.RequestParameterError.name());
            response.setMsg("Request parameter is null");
            return response;
        }
        if (StringUtils.isEmpty(domain.getId())) {
            response.setStatus(ResponseStatus.RequestParameterError.name());
            response.setMsg("Request parameter domain id is null");
            return response;
        }
        response.setData(service.delete(domain.getId()));
        return response;
    }

    @PostMapping("listAll")
    public Response<List<M>> listAll() {
        Response<List<M>> response = new Response<List<M>>();
        response.setData(service.listAll());
        return response;
    }

    //TODO pageable

}
