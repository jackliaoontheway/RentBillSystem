package com.cherryj.rentbill.domain;

import com.cherrj.base.domain.BaseDomain;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "building")
public class Building extends BaseDomain {


    @Column(name = "name", length = 128)
    private @Getter
    @Setter
    String name;


}
