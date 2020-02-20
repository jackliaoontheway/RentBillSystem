package com.cherryj.base.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "functionality")
public class Functionality extends BaseDomain {

    @Column(name = "name", length = 128)
    private @Getter
    @Setter
    String name;

}
