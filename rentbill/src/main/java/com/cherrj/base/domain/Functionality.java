package com.cherrj.base.domain;

import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "functionality")
public class Functionality extends BaseDomain {

    @Column(name = "name", length = 128)
    private @Getter
    @Setter
    String name;

}
