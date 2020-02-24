package com.cherryj.base.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
public @ToString
@EqualsAndHashCode
abstract class BaseDomain implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private @Getter
    @Setter
    Integer id;

    @Column(nullable = false)
    private @Getter
    @Setter
    Date createdDate;

    @Column(nullable = false)
    private @Getter
    @Setter
    Integer createdBy;

    @Column
    private @Getter
    @Setter
    Date modifiedDate;

    @Column
    private @Getter
    @Setter
    Integer modifiedBy;

}
