package com.cherryj.rentbill.domain;

import com.cherryj.base.domain.BaseDomain;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "building")
public class Building extends BaseDomain {

    @Column(name = "categoryName", length = 128)
    private @Getter
    @Setter
    String buildingNumber;
}