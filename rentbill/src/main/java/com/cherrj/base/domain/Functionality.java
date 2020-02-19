package com.cherrj.base.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "functionality")
public class Functionality implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private @Getter @Setter int id;

    @Column(name = "name", length = 128)
    private @Getter @Setter String name;



}
