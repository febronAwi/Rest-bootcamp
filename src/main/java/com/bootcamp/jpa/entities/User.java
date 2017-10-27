
package com.bootcamp.jpa.entities;

import com.bootcamp.jpa.enums.RoleUser;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name ="tp_user")
public class User implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   
    @NotNull(message="l'attribut login doit etre renseigne")
    @Column(length = 30)
    private String login;
    
    @Enumerated(EnumType.STRING)
    private RoleUser roles;
    
    @Column(length = 15, nullable = true)
    private String pwd;

    public User() {
        this.roles = RoleUser.USER;
    }
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public RoleUser getRole() {
        return roles;
    }

    public void setRole(RoleUser role) {
        this.roles = role;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", login=" + login + ", pwd=" + pwd + '}';
    }
    
    
}
