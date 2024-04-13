package com.spring_final.model;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Authority implements Serializable{


    public enum AuthorityEnum{
        ADMIN, USER
    }

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<User> users = new HashSet<>();

    @Transient
    private static final Authority USER = new Authority();
    @Transient
    private static final Authority ADMIN = new Authority();
    static {
        USER.setAuthority(AuthorityEnum.USER);
        USER.setId(1);
        ADMIN.setAuthority(AuthorityEnum.ADMIN);
        ADMIN.setId(2);
    }

    @Id
    private int id;

    private Authority(){

    }

    @Enumerated(EnumType.STRING)
    private AuthorityEnum authority;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AuthorityEnum getAuthority() {
        return authority;
    }

    public void setAuthority(AuthorityEnum authority) {
        this.authority = authority;
    }

    public static Authority getAdminAuthority(){
        return ADMIN;
    }

    public static Authority getUserAuthority(){
        return USER;
    }

    @Override
    public String toString(){
        return getAuthority().name();
    }
}
