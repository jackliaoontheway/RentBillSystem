package com.cherryj.base.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "useraccount")
public class UserAccount extends BaseDomain {

    @Column(name = "userName", length = 128)
    private @Getter
    @Setter
    String userName;

    @Column(name = "status", length = 128)
    private @Getter
    @Setter
    String status;

    /**
     * 注册时从页面传过来的密码
     */
    @Transient
    private @Getter
    @Setter
    String password;

    @Column(name = "passwordSalt", length = 64, nullable = false)
    private @Getter
    @Setter
    String passwordSalt;

    @Column(name = "passwordHash", length = 128, nullable = false)
    private @Getter
    @Setter
    String passwordHash;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "useraccount_userrole_rel", joinColumns = {@JoinColumn(name = "userAccountId")}, inverseJoinColumns = {
            @JoinColumn(name = "userRoleId")})
    private @Getter
    @Setter
    List<UserRole> roles;
}
