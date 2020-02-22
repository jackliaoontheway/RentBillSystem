package com.cherryj.base.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "userrole")
public class UserRole extends BaseDomain {

    @Column(name = "code", length = 128)
    private @Getter
    @Setter
    String code;

    @Column(name = "name", length = 128)
    private @Getter
    @Setter
    String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "userrole_functionality_rel", joinColumns = {@JoinColumn(name = "userRoleId")}, inverseJoinColumns = {
            @JoinColumn(name = "functionalityId")})
    private @Getter
    @Setter
    List<Functionality> functionalities;

}
