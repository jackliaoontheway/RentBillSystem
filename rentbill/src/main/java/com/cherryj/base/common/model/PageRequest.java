package com.cherryj.base.common.model;

import lombok.Getter;
import lombok.Setter;

public class PageRequest<T> extends Request<T> {

    private @Getter
    @Setter
    Integer pageSize = 10;

    private @Getter
    @Setter
    Integer currentPage = 1;

    private @Getter
    @Setter
    String sortBy = "id";

    private @Getter
    @Setter
    String direction = "DESC"; // ASC // DESC

}
