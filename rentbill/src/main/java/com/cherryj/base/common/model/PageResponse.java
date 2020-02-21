package com.cherryj.base.common.model;

import lombok.Getter;
import lombok.Setter;

public class PageResponse<T> extends Response<T> {

    private @Getter
    @Setter
    Integer total;

}
