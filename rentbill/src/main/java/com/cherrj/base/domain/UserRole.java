package com.cherrj.base.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "userrole")
public class UserRole implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private @Getter @Setter int id;

    @Column(name = "userName", length = 128)
    private @Getter @Setter String roleName;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "userrole_functionality_rel", joinColumns = { @JoinColumn(name = "userRoleId") }, inverseJoinColumns = {
            @JoinColumn(name = "functionalityId") })
    private @Getter @Setter List<Functionality> functionalities;

}
